package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.StockStatusService;

public class StockStatusAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		StockStatusService stockStatusService = new StockStatusService();
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/status/stockStatus.jsp");
		
		return forward;
		
	}

}
