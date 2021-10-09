package mes.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.ProductionHistoryBean;
import mes.svc.ItemInOutService;
import mes.svc.ItemStockService;

public class ItemProductionInAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		ProductionHistoryBean productionHistoryNew = (ProductionHistoryBean) session.getAttribute("productionHistoryNewInfo");
		
		// 1. 품목입출고(item_io) 테이블에 생산 종료 후 입고된 제품(양품) 등록 (라인의 제품 임시창고에서 외부 제품 창고로 이동)
		boolean isRegisterSuccess = false;
		ItemInOutService itemInOutService = new ItemInOutService();
		isRegisterSuccess = itemInOutService.registerProductionIn(productionHistoryNew);
		
		if(!isRegisterSuccess) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('입고된 제품의 품목 입출고 등록에 실패했습니다!!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			// 2. 품목재고현황(item_stock) 테이블에 생산 종료 후 입고된 제품(양품) 업데이트
			// 라인의 제품임시창고는 감소하고 외부 제품창고는 같은 수량만큼 증가
			boolean isModifySuccess = false;
			ItemStockService itemStockService = new ItemStockService();
			isModifySuccess = itemStockService.modifyItemStockProductionInOut(productionHistoryNew);
			
			if(!isModifySuccess) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('생산 종료 후 제품 반입시 품목 재고현황 업데이트에 실패했습니다!!');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				// 3. 품목입출고(item_io) 테이블에 제품 출고(out) 등록을 위해 이동
				forward = new ActionForward();
				forward.setRedirect(true);	// sendRedirect() 사용
				forward.setPath("/item/production/out");
			}
		}
		
		return forward;
		
	}

}
