package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.StockService;

public class StockAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		StockService stockService = new StockService();
		
		ArrayList<Integer> totalStockList = new ArrayList<Integer>();
		totalStockList = stockService.getStockTotalList();
		
		req.setAttribute("totalStockList", totalStockList);
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv1/stock.jsp");
		
		return forward;
		
	}

}
