package mes.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.ProductionBean;
import mes.dto.QualityBean;
import mes.dto.WorkOrderBean;
import mes.svc.ItemInOutService;
import mes.svc.ItemStockService;
import mes.svc.ProductionStartService;

public class ProductionStartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		ActionForward forward = null;
		
		int wo_no = Integer.parseInt(req.getParameter("wo_no"));
		ProductionStartService productionStartService = new ProductionStartService();
		
		// work_order 테이블 조회 (w/ wo_no)
		WorkOrderBean workOrder = productionStartService.getWorkOrder(wo_no);
		
		// 생산시작시간(start_dt) 계산 (w/ start_date, start_shift)
		Date start_date = workOrder.getStart_date();
		String start_shift = workOrder.getStart_shift();
		Timestamp start_dt = productionStartService.computeStartDT(start_date, start_shift);
				
		// 근무자번호(worker_no) 조회 (w/ line_cd, start_shift)
		int line_cd = workOrder.getLine_cd();
		int worker_no = productionStartService.getWorkerNo(line_cd, start_shift);
			
		// 제품 종류: 제품1, 제품2, 제품3
		int production_item_cd = workOrder.getItem_cd();
		
		// 제품 생산 계획수량
		int plan_qty = workOrder.getPlan_qty();
		
		// 제품 종류에 따른 자재별 소요량 배수(multiplier) 설정
		int[] k = {0, 0, 0};
		switch(production_item_cd) {
			case 1: k = new int[] {1, 2, 3}; break;	// multipliers for 자재1, 자재2, 자재3
			case 2: k = new int[] {2, 3, 1}; break;	// multipliers for 자재1, 자재2, 자재3
			case 3: k = new int[] {3, 1, 2}; break;	// multipliers for 자재1, 자재2, 자재3
		}
		
		// 자재가 3 종류이므로 자재별로 하나씩 등록 및 업데이트
		for(int i = 0; i < 3; i++) {
			// 자재 종류: 자재1, 자재2, 자재3 (item_cd = 4, 5, 6) 
			int item_cd = i + 4;
			// 자재 반출량
			int item_cnt = k[i] * plan_qty;
			
			// 1. item_io 테이블에 생산을 위해 반출된 자재 등록 (외부 자재 창고에서 라인의 자재 임시창고로 이동)
			boolean isRegisterSuccess = false;
			ItemInOutService itemInOutService = new ItemInOutService();
			isRegisterSuccess = itemInOutService.registerMaterialOut(workOrder, item_cd, item_cnt, start_dt);
			
			if(!isRegisterSuccess) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('생산을 위한 자재 반출시 품목 입출고 등록에 실패했습니다!!');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				// 2. item_stock 테이블에 반출된 자재 업데이트
				// 외부 자재 창고는 감소하고 라인의 자재 임시창고는 같은 수량만큼 증가
				boolean isModifySuccess = false;
				ItemStockService itemStockService = new ItemStockService();
				isModifySuccess = itemStockService.modifyItemStockMaterialInOut(workOrder, item_cd, item_cnt, start_dt);
				
				if(!isModifySuccess) {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("alert('생산을 위한 자재 반출시 품목 재고현황 업데이트에 실패했습니다!!');");
					out.println("history.go(-1);");
					out.println("</script>");
					out.flush();
					out.close();
				}
			}
		}
		
		// 3. 외부 Python 파일을 호출해 생산 및 품질검사 데이터 생성 / CSV 저장 / DB 등록
		productionStartService.registerProductionQualityData(workOrder, start_dt, worker_no);
		
		// time delay: 외부 파일을 실행시켜 CSV 저장과 DB 등록까지 해야 하므로 실행 종료까지 약간의 시간이 필요함
		TimeUnit.SECONDS.sleep(5); // 5초
		
		// 생산정보(production)와 품질검사정보(quality) 테이블 조회 (w/ wo_no)
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
			// 신규 생성된 production과 quality 데이터를 session 객체에 저장
			HttpSession session = req.getSession();
			session.setAttribute("productionDataListInfo", productionDataList);
			session.setAttribute("qualityDataListInfo", qualityDataList);
			
			// 4. 생산이력(production_hist) 테이블에 데이터 등록 위해 이동
			forward = new ActionForward();
			forward.setRedirect(true);	// sendRedirect() 사용
			forward.setPath("/production/history");	
		}
	
		return forward;
		
	}

}
