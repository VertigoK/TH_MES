package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.ItemInOutBean;
import mes.svc.StockService;

public class StockAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		StockService stockService = new StockService();
		
		// 1. 재고 현황 전체 조회
		int[] itemStockQtys = new int[12];
		itemStockQtys = stockService.getItemStockList();
		req.setAttribute("itemStockQtys", itemStockQtys);
		
		// 2. 품목별 입출고 내역 전체 조회 
		ArrayList<ItemInOutBean> itemInOutList = new ArrayList<ItemInOutBean>();
		itemInOutList = stockService.getItemInOutList();
		req.setAttribute("itemInOutList", itemInOutList);
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv1/stock.jsp");
		
		return forward;
		
	}

}
