package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.ItemStockBean;
import mes.svc.StockPlantService;

public class StockPlantAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		StockPlantService stockPlantService = new StockPlantService();		
		
		ArrayList<ItemStockBean> plantStockList = new ArrayList<ItemStockBean>();
		int no = Integer.parseInt(req.getParameter("no"));
		
		plantStockList = stockPlantService.getPlantStockList(no);
		req.setAttribute("plantStockList", plantStockList);
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/stock_plant.jsp");
		
		return forward;
		
	}

}
