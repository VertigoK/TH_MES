package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.svc.QualityService;

public class QualityAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		QualityService qualityService = new QualityService();
		float[] yield100 = qualityService.getYield(100);		// 100개 생산 기준
		float[] yield1000 = qualityService.getYield(1000);	// 1000개 생산 기준
		
		HttpSession session = req.getSession();
		session.setAttribute("yield100", yield100);
		session.setAttribute("yield1000", yield1000);
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv1/quality.jsp");
		
		return forward;
		
	}

}
