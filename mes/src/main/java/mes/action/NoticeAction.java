package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.NoticeBean;
import mes.dto.PageInfo;
import mes.svc.NoticeService;

public class NoticeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		NoticeService noticeService = new NoticeService();
		
		ArrayList<NoticeBean> noticeList = new ArrayList<NoticeBean>();
		
		int page = 1;
		int numOfPages = 10;
		int limit = 10;
		
		if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));
		if(req.getParameter("limit") != null) limit = Integer.parseInt(req.getParameter("limit"));
		
		int listCount = noticeService.getListCount();
		noticeList = noticeService.getNoticeList(page, limit);
		
		int totalPage = (int) Math.ceil((double) listCount / limit);
		int startPage = ((int) Math.floor((double) (page - 1) / numOfPages)) * numOfPages + 1;
		int endPage = startPage + numOfPages - 1;
		if(endPage > totalPage) endPage = totalPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setNumOfPages(numOfPages);
		pageInfo.setLimit(limit);
		
		req.setAttribute("pageInfo", pageInfo);
		req.setAttribute("noticeList", noticeList);
		
		forward.setPath("/lv1/notice.jsp");
		
		return forward;
	}

}
