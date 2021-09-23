package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.HRStatusService;

public class HRStatusAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		HRStatusService hrStatusService = new HRStatusService();
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/status/hrStatus.jsp");
		
		return forward;
		
	}

}
