package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.StockInOutService;

public class StockInOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		StockInOutService stockInOutService = new StockInOutService();
		
		
		
		ActionForward forward = new ActionForward();
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/stock_inout.jsp");
		return forward;
		
	}

}
