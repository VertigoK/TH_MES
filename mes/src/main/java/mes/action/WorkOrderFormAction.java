package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.CustomerOrderBean;
import mes.dto.LineBean;
import mes.dto.WorkOrderBean;
import mes.svc.WorkOrderFormService;

public class WorkOrderFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession();
		CustomerOrderBean custOrder = (CustomerOrderBean) session.getAttribute("custOrderInfo");
		int plant_cd = custOrder.getPlant_cd();
		
		// 1. 해당 공장 '라인 현황' 조회
		WorkOrderFormService workOrderFormService = new WorkOrderFormService();
		ArrayList<LineBean> lineList = workOrderFormService.getLineList(plant_cd);
		req.setAttribute("lineList", lineList);
		
		// 2. 해당 공장 '생산지시 현황' 조회
		ArrayList<WorkOrderBean> workOrderList = workOrderFormService.getWorkOrderList(plant_cd);
		req.setAttribute("workOrderList", workOrderList);
		
		ActionForward forward = null;
		forward = new ActionForward();
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/form/work_order.jsp");
		
		return forward;
		
	}

}
