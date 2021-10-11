package mes.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.CustomerOrderBean;
import mes.svc.CustOrderService;

public class CustOrderUpdateOurOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		CustomerOrderBean custOrder = (CustomerOrderBean) session.getAttribute("custOrderInfo");
		int order_no = custOrder.getOrder_no();
		
		// 1. cust_order 테이블의 자재발주상태(ourorder_status)를 true로 업데이트
		boolean isUpdateSuccess = false;
		CustOrderService custOrderService = new CustOrderService();
		isUpdateSuccess = custOrderService.modifyCustOrder(order_no, "ourorder_status");
		
		if(!isUpdateSuccess) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('고객 제품주문 테이블의 자재 발주 여부 업데이트에 실패했습니다!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);	// sendRedirect() 사용
			forward.setPath("/production/workOrderForm?order_no=" + order_no);
		}

		return forward;
		
	}

}
