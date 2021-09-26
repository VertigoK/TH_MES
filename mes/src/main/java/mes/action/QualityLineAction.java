package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.QualityLineService;

public class QualityLineAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		ActionForward forward = new ActionForward();
		
		QualityLineService qualityLineService = new QualityLineService();		
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/qualityline.jsp");
		
		return forward;
	}

}
