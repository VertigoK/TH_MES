package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.ProductionLineService;

public class ProductionLineAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		ProductionLineService productionLineService = new ProductionLineService();
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/productionline.jsp");
				
		return forward;
		
	}

}
