package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.CustomerOrderBean;
import mes.dto.OurOrderBean;
import mes.svc.OrderInListService;
import mes.svc.OrderOutListService;

public class OrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		OrderInListService orderInListService = new OrderInListService();
		ArrayList<CustomerOrderBean> orderInList = null;
		orderInList = orderInListService.getOrderInList();
		req.setAttribute("orderInList", orderInList);
		
		OrderOutListService orderOutListService = new OrderOutListService();
		ArrayList<OurOrderBean> orderOutList = null;
		orderOutList = orderOutListService.getOrderOutList();
		req.setAttribute("orderOutList", orderOutList);
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv1/order.jsp");
				
		return forward;
		
	}

}
