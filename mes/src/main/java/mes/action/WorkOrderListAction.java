package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.WorkOrderBean;
import mes.svc.WorkOrderListService;

public class WorkOrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		WorkOrderListService workOrderListService = new WorkOrderListService();
		ArrayList<WorkOrderBean> workOrderList = workOrderListService.getWorkOrderList();
		req.setAttribute("workOrderList", workOrderList);		
		
		ActionForward forward = new ActionForward();
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/production_workorder.jsp");
		return forward;
	}

}
