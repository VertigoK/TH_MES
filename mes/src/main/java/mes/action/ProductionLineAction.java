package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.ProductionBean;
import mes.svc.ProductionLineService;

public class ProductionLineAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		
		ProductionLineService productionLineService = new ProductionLineService();
		ArrayList<ProductionBean> productionList = productionLineService.getProductionList(id, no);
		req.setAttribute("productionList", productionList);
		
		ActionForward forward = new ActionForward();
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/production_line.jsp");
		return forward;
		
	}

}
