package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.ProductionService;

public class ProductionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		ProductionService productionService = new ProductionService();
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv1/production.jsp");
		
		return forward;
		
	}

}
