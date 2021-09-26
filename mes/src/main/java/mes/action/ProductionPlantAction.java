package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.ProductionPlantService;

public class ProductionPlantAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		ProductionPlantService productionPlantService = new ProductionPlantService();
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/productionplant.jsp");
		
		return forward;
		
	}

}
