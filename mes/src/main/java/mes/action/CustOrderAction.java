package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.CustOrderService;

public class CustOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		CustOrderService custOrderService = new CustOrderService();
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/");
		
		return forward;
		
	}

}
