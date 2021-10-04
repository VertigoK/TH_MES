package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.OurOrderBean;
import mes.svc.OrderOutListService;

public class OrderOutListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		OrderOutListService orderOutListService = new OrderOutListService();
		ArrayList<OurOrderBean> orderOutList = null;
		orderOutList = orderOutListService.getOrderOutList();
		req.setAttribute("orderOutList", orderOutList);
		
		ActionForward forward = new ActionForward();
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/order_out.jsp");
				
		return forward;
		
	}

}
