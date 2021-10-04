package mes.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.CustomerOrderBean;
import mes.svc.OurOrderAutoService;

public class OurOrderAutoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		CustomerOrderBean custOrder = (CustomerOrderBean) session.getAttribute("custOrderInfo");
		@SuppressWarnings("unchecked")
		ArrayList<Integer> requiredQtys = (ArrayList<Integer>) session.getAttribute("requiredQtysInfo");
		
		int plant_cd = custOrder.getPlant_cd();
		
		for(int i = 1; i < 4; i++) {
			int item_cd = i + 3;	// item_cd = 4, 5, 6 (자재)
			int cust_cd = item_cd;
			int order_qty = requiredQtys.get(i);
			if(order_qty != 0) {
				
				// 1. our_order 테이블에 등록
				boolean isOurOrderSuccess = false;
				int order_no = 0;
				
				OurOrderAutoService ourOrderAutoService = new OurOrderAutoService();
				List<Object> ourOrderResult = ourOrderAutoService.registerOurOrder(cust_cd, plant_cd, item_cd, order_qty);
				isOurOrderSuccess = (boolean) ourOrderResult.get(0);
				order_no = (int) ourOrderResult.get(1);
				
				if(!isOurOrderSuccess) {
					res.setContentType("text/html; charset=utf-8");
					PrintWriter out = res.getWriter();
					out.println("<script>");
					out.println("alert('자동 자재 발주에 실패했습니다!!');");
					out.println("history.go(-1);");
					out.println("</script>");
					out.flush();
					out.close();
				} else {
					// 2. item_stock 테이블에 발주(=입고)된 자재 업데이트
					boolean isModifySuccess = false;
					isModifySuccess = ourOrderAutoService.modifyItemStock(plant_cd, item_cd, order_qty);
					
					if(!isModifySuccess) {
						res.setContentType("text/html; charset=utf-8");
						PrintWriter out = res.getWriter();
						out.println("<script>");
						out.println("alert('item_stock 테이블의 업데이트에 실패했습니다!');");
						out.println("history.go(-1);");
						out.println("</script>");
						out.flush();
						out.close();
					} else {
						forward = new ActionForward();
						forward.setRedirect(true);	// sendRedirect() 사용
						forward.setPath("/order/outList");
					}	
				}
			}		
		}
		
		return forward;
		
	}

}
