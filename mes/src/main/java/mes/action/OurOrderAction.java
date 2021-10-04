package mes.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.OurOrderBean;
import mes.svc.OurOrderService;

public class OurOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int plant_cd = Integer.parseInt(req.getParameter("plant_cd"));
		int item_cd = Integer.parseInt(req.getParameter("item_cd"));
		int order_qty = Integer.parseInt(req.getParameter("order_qty"));
		int cust_cd = item_cd;
		
		ActionForward forward = null;
		
		boolean isOurOrderSuccess = false;
		int order_no = 0;
		
		OurOrderService ourOrderService = new OurOrderService();
		List<Object> ourOrderResult = ourOrderService.registerOurOrder(cust_cd, plant_cd, item_cd, order_qty);
		isOurOrderSuccess = (boolean) ourOrderResult.get(0);
		order_no = (int) ourOrderResult.get(1);
		
		if(!isOurOrderSuccess) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('자재 발주에 실패했습니다!!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			// DB에 등록된 발주 정보를 session 객체에 저장
			OurOrderBean ourOrder = ourOrderService.getOurOrder(order_no);
			HttpSession session = req.getSession();
			session.setAttribute("ourOrderInfo", ourOrder);
			
			forward = new ActionForward();
			forward.setRedirect(true);	// sendRedirect() 사용
			forward.setPath("/misc/our_order_success.jsp");
		}
		
		return forward;
		
	}

}
