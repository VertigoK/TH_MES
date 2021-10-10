package mes.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.NoticeBean;
import mes.svc.NoticeWriteService;

public class NoticeWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		NoticeWriteService noticeWriteService = new NoticeWriteService();
		
		boolean isWriteSuccess = false;
		
		NoticeBean notice = new NoticeBean();
		notice.setTitle(title);
		notice.setContent(content);
		isWriteSuccess = noticeWriteService.insertNotice(notice);
		if(!isWriteSuccess) {
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('공지사항 등록에 실패했습니다!')");
			out.println("history.go(-1)");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			forward.setRedirect(true);
			forward.setPath("/misc/write_success.jsp");
		}
		
		return forward;
	}

}
