package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.ProductionHistoryBean;
import mes.dto.WorkOrderBean;
import mes.svc.ProductionHistoryService;
import mes.svc.WorkOrderListService;

public class ProductionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
//		ProductionService productionService = new ProductionService();
		
		WorkOrderListService workOrderListService = new WorkOrderListService();
		ArrayList<WorkOrderBean> workOrderList = workOrderListService.getWorkOrderList();
		req.setAttribute("workOrderList", workOrderList);
		
		ProductionHistoryService productionHistoryService = new ProductionHistoryService();
		ArrayList<ProductionHistoryBean> productionHistoryList = productionHistoryService.getProductionHistoryList();
		req.setAttribute("productionHistoryList", productionHistoryList);
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv1/production.jsp");
		
		return forward;
		
	}

}
