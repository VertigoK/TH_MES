package mes.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.ProductionBean;
import mes.dto.QualityBean;
import mes.dto.WorkOrderBean;
import mes.svc.ProductionGenerateService;
import mes.svc.ProductionStartService;

public class ProductionGenerateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		List<Object> inputToDataGeneration = (List<Object>) session.getAttribute("inputToDataGenerationInfo");
		
		int wo_no = (int) inputToDataGeneration.get(0);
		Timestamp start_dt = (Timestamp) inputToDataGeneration.get(1);
		int worker_no = (int) inputToDataGeneration.get(2);
		
		// work_order 테이블 조회 (w/ wo_no)
		ProductionStartService productionStartService = new ProductionStartService();
		WorkOrderBean workOrder = productionStartService.getWorkOrder(wo_no);
		
		// 1. 외부 Python 파일을 호출해 생산 및 품질검사 데이터 생성 / CSV 저장 / DB 등록
		ProductionGenerateService productionGenerateService = new ProductionGenerateService();
		productionGenerateService.registerProductionQualityData(workOrder, start_dt, worker_no);
		
		// 생산정보(production)와 품질검사정보(quality) 테이블 조회 (w/ wo_no)
		ArrayList<ProductionBean> productionDataList = null;
		ArrayList<QualityBean> qualityDataList = null;
		productionDataList = productionGenerateService.getProductionDataList(wo_no);
		qualityDataList = productionGenerateService.getQualityDataList(wo_no);
		
		// 외부 파일을 실행시켜 2개의 데이터를 만들고 그 결과를 각각 CSV에 저장한 후 DB에 등록하기 때문에
		// 실행 시간을 고려해 DB 등록 여부를 2초에 한 번씩 다시 확인하는 과정 필요함.
		// 20초까지 확인 후 그래도 DB 등록이 안 되면 에러 메시지 생성 후 프로그램 실행 종료
		int i = 0;
		while(productionDataList == null || qualityDataList == null) {
			TimeUnit.SECONDS.sleep(2); // 2초
			i++;
			
			if(i > 10) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('생산 및 품질검사 데이터 생성에 실패했습니다!! 지금까지 DB에 생성된 데이터를 모두 초기화하지 않으면 지금부터 재고를 비롯한 모든 수치에 에러가 발생하게 됩니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.flush();
				out.close();
				System.exit(-1);	// Shut down JVM
			}
			
			// 생산정보(production)와 품질검사정보(quality) 테이블 다시 조회 (w/ wo_no)
			productionDataList = productionGenerateService.getProductionDataList(wo_no);
			qualityDataList = productionGenerateService.getQualityDataList(wo_no);
		}
		
		// 신규 생성된 production과 quality 데이터를 session 객체에 저장
		session.setAttribute("productionDataListInfo", productionDataList);
		session.setAttribute("qualityDataListInfo", qualityDataList);
		
		// 2. 생산이력(production_hist) 테이블에 데이터 등록 위해 이동
		forward = new ActionForward();
		forward.setRedirect(true);	// sendRedirect() 사용
		forward.setPath("/production/history");
		
		return forward;
		
	}

}
