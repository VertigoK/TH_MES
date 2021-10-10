package mes.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.MemberBean;
import mes.dto.NoticeBean;
import mes.svc.LogInService;
import mes.svc.NoticeService;

public class LogInAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ActionForward forward = null;
		
		if(req.getParameter("id") == null || req.getParameter("pw") == null) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('정상적인 접근이 아닙니다!');");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			
			LogInService logInService = new LogInService();
			boolean isIdAlready = logInService.isIdAlready(id);
			
			if(!isIdAlready) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('입력하신 ID는 존재하지 않습니다!');");
				out.println("history.back()");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				boolean isPasswordCorrect = logInService.isPasswordCorrect(id, pw);
				if(!isPasswordCorrect) {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("alert('비밀번호가 틀렸습니다. 다시 입력하세요!');");
					out.println("history.back()");
					out.println("</script>");
					out.flush();
					out.close();
				} else {
					HttpSession session = req.getSession();
					MemberBean member = logInService.getMember(id);
					session.setAttribute("logInInfo", member);
					
					NoticeService noticeService = new NoticeService();
					ArrayList<NoticeBean> recentNotice = noticeService.recentNotice();
					session.setAttribute("recentNotice", recentNotice);
					
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath("/");
				}
			}
		}

		return forward;

	}

}



