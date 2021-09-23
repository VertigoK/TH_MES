package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.QualityStatusService;

public class QualityStatusAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		QualityStatusService qualityStatusService = new QualityStatusService();
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/status/qualityStatus.jsp");
		
		return forward;
		
	}

}
