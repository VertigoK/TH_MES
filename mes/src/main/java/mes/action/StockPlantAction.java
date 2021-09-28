package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.StockPlantService;

public class StockPlantAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		StockPlantService stockPlantService = new StockPlantService();		
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/stock_plant.jsp");
		
		return forward;
		
	}

}
