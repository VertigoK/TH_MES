package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.ItemStockBean;
import mes.svc.StockItemService;

public class StockItemAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		StockItemService stockItemService = new StockItemService();		
		
		ArrayList<ItemStockBean> itemTypeStockList = new ArrayList<ItemStockBean>();
		int no = Integer.parseInt(req.getParameter("no"));
		no += 2;
		itemTypeStockList = stockItemService.getItemTypeStockList(no);
		req.setAttribute("itemTypeStockList", itemTypeStockList);
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/stock_item.jsp");
		
		return forward;
		
	}

}
