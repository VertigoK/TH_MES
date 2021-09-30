package mes.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.CustomerOrderBean;
import mes.svc.CustOrderService;

public class CustOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int plant_cd = Integer.parseInt(req.getParameter("plant_cd"));
		int item_cd = Integer.parseInt(req.getParameter("item_cd"));
		int cust_cd = Integer.parseInt(req.getParameter("cust_cd"));
		int order_qty = Integer.parseInt(req.getParameter("order_qty"));
		Date delivery_date = Date.valueOf(req.getParameter("delivery_date"));
		
		ActionForward forward = null;
		
		boolean isCustOrderSuccess = false;
		int order_no = 0;

		CustOrderService custOrderService = new CustOrderService();
		List<Object> custOrderResult = custOrderService.custOrderProduct(plant_cd, item_cd, cust_cd, order_qty, delivery_date);
		isCustOrderSuccess = (boolean) custOrderResult.get(0);
		order_no = (int) custOrderResult.get(1);
		
		if(!isCustOrderSuccess) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('제품 주문에 실패했습니다!!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			// 등록된 주문 정보를 session 객체에 저장
			CustomerOrderBean custOrder = custOrderService.getCustOrder(order_no);
			HttpSession session = req.getSession();
			session.setAttribute("custOrderInfo", custOrder);
		}
		
		forward = new ActionForward();
		forward.setRedirect(true);	// sendRedirect() 사용
		forward.setPath("/misc/customer_order_success.jsp");
		
		return forward;
		
	}

}
