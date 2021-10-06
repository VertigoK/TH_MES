package mes.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.ProductionBean;
import mes.dto.QualityBean;
import mes.dto.WorkOrderBean;
import mes.svc.ProductionStartService;

public class ProductionStartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		ActionForward forward = null;
		
		int wo_no = Integer.parseInt(req.getParameter("wo_no"));
		ProductionStartService productionStartService = new ProductionStartService();
		
		// 1. work_order 테이블 조회 (w/ wo_no)
		WorkOrderBean workOrder = productionStartService.getWorkOrder(wo_no);
		
		// 2. 생산시작시간(start_dt) 계산 (w/ start_date, start_shift)
		Date start_date = workOrder.getStart_date();
		String start_shift = workOrder.getStart_shift();
		Timestamp start_dt = productionStartService.computeStartDT(start_date, start_shift);
				
		// 3. 근무자번호(worker_no) 조회 (w/ line_cd, start_shift)
		int line_cd = workOrder.getLine_cd();
		int worker_no = productionStartService.getWorkerNo(line_cd, start_shift);
		
		// 4. 외부 Python 함수를 호출해 생산 및 품질검사 데이터 생성 / CSV 저장 / DB 등록
		productionStartService.registerProductionQualityData(workOrder, start_dt, worker_no);
				
		// 5. 생산정보(production)와 품질검사정보(quality) 테이블 조회 (w/ wo_no)
		ArrayList<ProductionBean> productionDataList = null;
		ArrayList<QualityBean> qualityDataList = null;
		productionDataList = ProductionStartService.getProductionDataList(wo_no);
		qualityDataList = ProductionStartService.getQualityDataList(wo_no);
		
		if(productionDataList == null || qualityDataList == null) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('생산 및 품질검사 데이터 생성에 실패했습니다!!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			// 6. production과 quality 테이블 조회 결과(w/ wo_no)를 session 객체에 저장
			HttpSession session = req.getSession();
			session.setAttribute("productionDataListInfo", productionDataList);
			session.setAttribute("qualityDataListInfo", qualityDataList);
			
			forward = new ActionForward();
			forward.setRedirect(true);	// sendRedirect() 사용
			forward.setPath("/misc/production_start_success.jsp");
		}
		
		return forward;
		
	}

}
