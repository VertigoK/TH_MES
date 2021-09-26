package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.svc.LogOutService;

public class LogOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		HttpSession session = req.getSession();
//		MemberBean member = (MemberBean) session.getAttribute("loginInfo");
		
		
		LogOutService logOutService = new LogOutService();
		
		
		session.invalidate();
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/");
		
		return forward;
		
	}

}
