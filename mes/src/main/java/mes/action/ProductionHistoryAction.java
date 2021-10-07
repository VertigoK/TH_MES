package mes.action;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.ProductionBean;
import mes.svc.ProductionHistoryService;

public class ProductionHistoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<ProductionBean> productionDataList = (ArrayList<ProductionBean>) session.getAttribute("productionDataListInfo"); 
		
		// 1. 처음 생산된 제품의 데이터
		ProductionBean productionDataFirst = productionDataList.get(0);
		int wo_no = productionDataFirst.getWo_no();
		int plant_cd = productionDataFirst.getPlant_cd();
		int line_cd = productionDataFirst.getLine_cd();
		int item_cd = productionDataFirst.getItem_cd();
		Timestamp start_dt = productionDataFirst.getPrd_dt();
				
		// 2. 마지막 생산된 제품의 데이터
		ProductionBean productionDataLast = productionDataList.get(productionDataList.size()-1);
		Timestamp end_dt = productionDataLast.getPrd_dt();

		// 3. 생산지시SEQ(wo_seq) 생성 -> 일단 보류 !!!
		// Not not -> null로 변경함
		
		// 4. 품질검사 테이블로부터 투입수량(in_qty), 배출수량(out_qty), NG수량(ng_qty) 구하기 (w/ wo_no)
		ProductionHistoryService productionHistoryService = new ProductionHistoryService();
		int[] qtys = null;
		qtys = productionHistoryService.getGoodBadQuantity(wo_no);
		int in_qty = qtys[0];
		int out_qty = qtys[1];
		int ng_qty = qtys[2];
		 
		// 5. 생산이력 테이블에 등록하기
		
		
		
		
		forward = new ActionForward();
		forward.setRedirect(true);	// sendRedirect() 사용
		forward.setPath("/misc/production_history_success.jsp");
		
		return forward;		
		
	}

}
