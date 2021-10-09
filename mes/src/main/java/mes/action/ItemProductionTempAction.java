package mes.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.ProductionHistoryBean;
import mes.svc.ItemStockService;

public class ItemProductionTempAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		ProductionHistoryBean productionHistoryNew = (ProductionHistoryBean) session.getAttribute("productionHistoryNewInfo");
		ItemStockService itemStockService = new ItemStockService();
		
		// 생산 후 자재 임시창고는 소모된 자재의 수량만큼 감소
		// 제품 임시창고는 생산된 양품 수량만큼 증가

		int production_item_cd = productionHistoryNew.getItem_cd();
		int line_cd = productionHistoryNew.getLine_cd();
		int[] material_storage_cd_for_line = {3, 5, 7, 11, 13, 15};
		int storage_cd = material_storage_cd_for_line[line_cd - 1];
		int in_qty = productionHistoryNew.getIn_qty();
		Timestamp end_dt = productionHistoryNew.getEnd_dt();
		
		// 제품 종류에 따른 자재별 소모량 배수(multiplier) 설정
		int[] k = {0, 0, 0};
		switch(production_item_cd) {
			case 1: k = new int[] {1, 2, 3}; break;	// multipliers for 자재1, 자재2, 자재3
			case 2: k = new int[] {2, 3, 1}; break;	// multipliers for 자재1, 자재2, 자재3
			case 3: k = new int[] {3, 1, 2}; break;	// multipliers for 자재1, 자재2, 자재3
		}
		
		// 1. 자재가 3 종류이므로 자재별로 하나씩 업데이트
		boolean[] isMaterialModifySuccess = new boolean[3];
		for(int i = 0; i < 3; i++) {
			// 자재 종류: 자재1, 자재2, 자재3 (item_cd = 4, 5, 6)
			int item_cd = i + 4;
			// 자재 소모량
			int item_cnt = k[i] * in_qty;
			isMaterialModifySuccess[i] = itemStockService.modifyItemStockTempInOut(item_cd, storage_cd, -item_cnt, end_dt);
		}
		
		// 2. 제품 업데이트
		int item_cd = production_item_cd;
		int[] production_storage_cd_for_line = {4, 6, 8, 12, 14, 16};
		storage_cd = production_storage_cd_for_line[line_cd - 1];
		int out_qty = productionHistoryNew.getOut_qty();
		
		boolean isProductionModifySuccess = false;
		isProductionModifySuccess = itemStockService.modifyItemStockTempInOut(item_cd, storage_cd, out_qty, end_dt);
		
		if(!(isMaterialModifySuccess[0] && isMaterialModifySuccess[1] && isMaterialModifySuccess[2] && isProductionModifySuccess)) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('자재 및 제품의 품목 재고현황 업데이트에 실패했습니다!!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			// 3. 품목입출고(item_io) 테이블에 제품 입고(in) 등록을 위해 이동
			forward = new ActionForward();
			forward.setRedirect(true);	// sendRedirect() 사용
			forward.setPath("/item/production/in");
		}
		
		return forward;
		
	}

}
