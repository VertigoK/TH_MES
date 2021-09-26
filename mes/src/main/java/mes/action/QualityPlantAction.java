package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.QualityPlantService;

public class QualityPlantAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		QualityPlantService qualityPlantService = new QualityPlantService();		
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/qualityplant.jsp");
		
		return forward;
		
	}

}
