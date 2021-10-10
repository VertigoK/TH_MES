package mes.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.CustomerOrderBean;
import mes.svc.ItemReservedService;

public class ItemReservedAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		CustomerOrderBean custOrder = (CustomerOrderBean) session.getAttribute("custOrderInfo");
		@SuppressWarnings("unchecked")
		ArrayList<Integer> requiredQtys = (ArrayList<Integer>) session.getAttribute("requiredQtysInfo");
		
		int order_no = custOrder.getOrder_no();
		int plant_cd = custOrder.getPlant_cd();
		int item_cd = custOrder.getItem_cd();
		int plan_qty = requiredQtys.get(0);		// 제품 생산 계획수량
		
		// 1. reserved_item 테이블에 발주(=입고)된 자재 등록
		boolean isRegisterSuccess = false;
		ItemReservedService itemReservedService = new ItemReservedService();
		isRegisterSuccess = itemReservedService.registerReservedItem(order_no, plant_cd, item_cd, plan_qty);
		
		if(!isRegisterSuccess) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('생산 예약된 자재의 등록에 실패했습니다!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			// 2. cust_order 테이블의 ourorder_status 업데이트 위해 이동
			forward = new ActionForward();
			forward.setRedirect(true);	// sendRedirect() 사용
			forward.setPath("/order/custOrder/update/ourOrder");
		}
		
		return forward;
		
	}

}
