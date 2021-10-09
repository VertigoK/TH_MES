package mes.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.ProductionBean;
import mes.dto.ProductionHistoryBean;
import mes.svc.ProductionHistoryService;

public class ProductionHistoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<ProductionBean> productionDataList = (ArrayList<ProductionBean>) session.getAttribute("productionDataListInfo");
		
		ProductionHistoryService productionHistoryService = new ProductionHistoryService();
		
		// 처음 생산된 제품의 데이터
		ProductionBean productionDataFirst = productionDataList.get(0);
		int wo_no = productionDataFirst.getWo_no();
		int plant_cd = productionDataFirst.getPlant_cd();
		int line_cd = productionDataFirst.getLine_cd();
		int item_cd = productionDataFirst.getItem_cd();
		Timestamp start_dt = productionDataFirst.getPrd_dt();
				
		// 마지막 생산된 제품의 데이터
		ProductionBean productionDataLast = productionDataList.get(productionDataList.size()-1);
		Timestamp end_dt = productionDataLast.getPrd_dt();

		// 생산지시SEQ (wo_seq) 생성 -> 일단 보류 (Not not -> null로 변경) !!!
		
		// 품질검사 테이블로부터 투입수량(in_qty), 배출수량(out_qty), NG수량(ng_qty) 구하기 (w/ wo_no)
		int[] qtys = new int[3];
		qtys = productionHistoryService.getGoodBadQuantity(wo_no);
		int in_qty = qtys[0];
		int out_qty = qtys[1];
		int ng_qty = qtys[2];
		 
		// 1. 생산이력(production_hist) 테이블에 데이터 등록
		boolean isRegisterSuccess = false;
		isRegisterSuccess = productionHistoryService.registerProductionHistory(wo_no, plant_cd,
				line_cd, item_cd, start_dt, end_dt, in_qty, out_qty, ng_qty);
		
		if(!isRegisterSuccess) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('생산이력 등록에 실패했습니다!!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			// 신규 등록된 생산이력 데이터를 session 객체에 저장
			ProductionHistoryBean productionHistoryNew = productionHistoryService.getProductionHistory(wo_no);
			session.setAttribute("productionHistoryNewInfo", productionHistoryNew);
			
			// 2. 생산지시(work_order) 테이블의 작업상태(flag_end) 업데이트 위해 이동
			forward = new ActionForward();
			forward.setRedirect(true);	// sendRedirect() 사용
			forward.setPath("/production/workOrder/update");
		}
		
		return forward;
		
	}

}
