package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.NoticeBean;
import mes.svc.NoticeDetailService;

public class NoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		int notice_no = Integer.parseInt(req.getParameter("notice_no"));
		NoticeDetailService noticeDetailService = new NoticeDetailService();
		NoticeBean notice = noticeDetailService.getNotice(notice_no);
		req.setAttribute("notice", notice);
		
		forward.setPath("/lv2/noticeDetail.jsp");
		
		return forward;
	}

}
