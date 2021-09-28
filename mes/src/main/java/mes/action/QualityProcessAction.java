package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.QualityProcessService;

public class QualityProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		QualityProcessService qualityProcessService = new QualityProcessService();		
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/quality_process.jsp");
		
		return forward;
		
	}

}
