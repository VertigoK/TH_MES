package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.LogInService;

public class LogInAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		LogInService logInService = new LogInService();
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/misc/login_success.jsp");
		
		return forward;
		
	}

}
