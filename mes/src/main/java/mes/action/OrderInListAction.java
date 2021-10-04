package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.CustomerOrderBean;
import mes.svc.OrderInListService;

public class OrderInListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		OrderInListService orderInListService = new OrderInListService();
		ArrayList<CustomerOrderBean> orderInList = null;
		orderInList = orderInListService.getOrderInList();
		req.setAttribute("orderInList", orderInList);
		
		ActionForward forward = new ActionForward();
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/order_in.jsp");
				
		return forward;
		
	}

}
