package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;

public class SignUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		
		HttpSession session = req.getSession();
//		MemberBean member = new MemberBean();
//		member = signUpService.getMember(id);
//		session.setAttribute("loginInfo", member);
		
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/misc/signup_success.jsp");
		
		return forward;
		
	}

}
