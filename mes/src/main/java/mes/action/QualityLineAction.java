package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.QualityBean;
import mes.svc.QualityLineService;

public class QualityLineAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		
		QualityLineService qualityLineService = new QualityLineService();		
		ArrayList<QualityBean> qualityList = null;
		qualityList = qualityLineService.getQualityList(id, no);
		req.setAttribute("qualityList", qualityList);
		
		ActionForward forward = new ActionForward();
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/quality_line.jsp");
		return forward;
	}

}
