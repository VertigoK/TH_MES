package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.ProductionProcessService;

public class ProductionProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		ProductionProcessService productionProcessService = new ProductionProcessService();
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/productionprocess.jsp");
		
		return forward;
		
	}

}
