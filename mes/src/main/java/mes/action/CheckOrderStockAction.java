package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.CustomerOrderBean;
import mes.svc.CheckOrderStockService;
import mes.svc.CustOrderService;

public class CheckOrderStockAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ActionForward forward = null;
		HttpSession session = req.getSession();
				
		// 1. order_no에 해당하는 제품 주문 정보 조회
		int order_no = Integer.parseInt(req.getParameter("order_no"));
		CustOrderService custOrderService = new CustOrderService();
		CustomerOrderBean custOrder = custOrderService.getCustOrder(order_no);
		session.setAttribute("custOrderInfo", custOrder);			// session attribute 생성
		
		// 2. plant_cd와 item_cd에 해당하는 제품과 자재의 재고수량 조회
		CheckOrderStockService checkOrderStockService = new CheckOrderStockService();
		int plant_cd = custOrder.getPlant_cd();
		int item_cd = custOrder.getItem_cd();
		ArrayList<Integer> stockQtys = checkOrderStockService.getStockQuantity(plant_cd, item_cd);
		session.setAttribute("stockQtysInfo", stockQtys);			// session attribute 생성
		
		// 3. item_cd와 order_qty, stockQtys를 바탕으로 제품 생산수량 및 자재별(item_cd=4,5,6) 소요수량, 발주수량 계산
		int order_qty = custOrder.getOrder_qty();
		ArrayList<Integer> requiredQtys = checkOrderStockService.computeRequiredQuantity(item_cd, order_qty, stockQtys);
		session.setAttribute("requiredQtysInfo", requiredQtys);		// session attribute 생성
		
		forward = new ActionForward();
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv3/check_order_stock.jsp");
		return forward;
		
	}

}
