package mes.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.MemberBean;
import mes.svc.SignUpService;
public class SignUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ActionForward forward = null;
		
		if(req.getParameter("id") == null) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('정상적인 접근이 아닙니다!');");
			out.println("history.go(-1);");
			out.println("</script>");
		} else {
			String id = req.getParameter("id");
			SignUpService signUpService = new SignUpService();
			boolean isIdAlready = signUpService.isIdAlready(id);
			
			if(isIdAlready) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('입력하신 ID는 이미 사용 중입니다. 다른 ID를 선택하세요!');");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				MemberBean member = new MemberBean();
				boolean isSignUpSuccess = false;
				
				member.setMem_id(id);
		        member.setMem_pw(req.getParameter("pw"));
		        member.setMem_nm(req.getParameter("nm"));
				isSignUpSuccess = signUpService.signUpMember(member);
				
				if(!isSignUpSuccess) {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("alert('회원 가입에 실패했습니다!');");
					out.println("history.go(-1);");
					out.println("</script>");
					out.flush();
					out.close();
				} else {
					HttpSession session = req.getSession();
					member = signUpService.getMember(id);
					session.setAttribute("logInInfo", member);
					
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath("/misc/signup_success.jsp");
				}	
			}
		}
		
		return forward;
		
	}

}
