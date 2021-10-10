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
				
		// order_no에 해당하는 제품 주문 정보 조회
		int order_no = Integer.parseInt(req.getParameter("order_no"));
		CustOrderService custOrderService = new CustOrderService();
		CustomerOrderBean custOrder = custOrderService.getCustOrder(order_no);
		session.setAttribute("custOrderInfo", custOrder);			// session attribute 생성!
		
		// plant_cd와 item_cd에 해당하는 제품과 자재의 재고수량 조회 (4개의 값 리턴)
		int plant_cd = custOrder.getPlant_cd();
		int item_cd = custOrder.getItem_cd();
		CheckOrderStockService checkOrderStockService = new CheckOrderStockService();
		ArrayList<Integer> stockQtys = checkOrderStockService.getStockQuantity(plant_cd, item_cd);
		
		// plant_cd에 따라 자재별 생산 예약된 수량 조회 (3개의 값 리턴)
		int[] reservedQtys = checkOrderStockService.getReservedQuantity(plant_cd);
		
		// plant_cd에 따라 자재별 실제 가용 재고수량 계산 (전체 재고수량 - 생산 예약된 자재수량)
		stockQtys.set(1, stockQtys.get(1) - reservedQtys[0]);		// 자재1 가용 재고수량
		stockQtys.set(2, stockQtys.get(2) - reservedQtys[1]);		// 자재2 가용 재고수량
		stockQtys.set(3, stockQtys.get(3) - reservedQtys[2]);		// 자재3 가용 재고수량
		session.setAttribute("stockQtysInfo", stockQtys);			// session attribute 생성!
		
		// item_cd와 plan_qty, stockQtys를 바탕으로 자재별(item_cd=4,5,6) 발주수량 계산 후
		// {제품 생산 계획수량, 자재1 발주수량, 자재2 발주수량, 자재3 발주수량} 리턴
		int order_qty = custOrder.getOrder_qty();
		int plan_qty = (int) Math.ceil((order_qty - stockQtys.get(0)) * 1.1);	// 제품 생산 계획수량
		if(plan_qty < 0) plan_qty = 0;
		ArrayList<Integer> requiredQtys = checkOrderStockService.computeRequiredQuantity(item_cd, plan_qty, stockQtys);
		session.setAttribute("requiredQtysInfo", requiredQtys);		// session attribute 생성!
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/lv3/check_order_stock.jsp");
		return forward;
		
	}

}
