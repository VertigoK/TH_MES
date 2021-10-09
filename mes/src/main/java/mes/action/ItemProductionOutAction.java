package mes.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.CustomerOrderBean;
import mes.dto.ProductionHistoryBean;
import mes.dto.WorkOrderBean;
import mes.svc.CustOrderService;
import mes.svc.ItemInOutService;
import mes.svc.ItemStockService;

public class ItemProductionOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		ProductionHistoryBean productionHistoryNew = (ProductionHistoryBean) session.getAttribute("productionHistoryNewInfo");
		WorkOrderBean workOrderNew = (WorkOrderBean) session.getAttribute("workOrderNewInfo");
		
		// 거래처(cust_cd) 구하기
		int order_no = workOrderNew.getOrder_no();
		CustOrderService custOrderService = new CustOrderService();
		CustomerOrderBean custOrder = custOrderService.getCustOrder(order_no);
		int cust_cd = custOrder.getCust_cd();
		
		// 1. 품목 입출고(item_io) 테이블에 출고된 제품 등록 (외부 제품 창고에서 고객사로 이동)
		boolean isRegisterSuccess = false;
		ItemInOutService itemInOutService = new ItemInOutService();
		isRegisterSuccess = itemInOutService.registerProductionOut(productionHistoryNew, cust_cd);
		
		if(!isRegisterSuccess) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('출고된 제품의 품목 입출고 등록에 실패했습니다!!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			// 2. 품목 재고현황(item_stock) 테이블에 출고된 제품 업데이트
			boolean isModifySuccess = false;
			ItemStockService itemStockService = new ItemStockService();
			isModifySuccess = itemStockService.modifyItemStockProductionOut(productionHistoryNew);
			
			if(!isModifySuccess) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('출고된 제품의 품목 재고현황 업데이트에 실패했습니다!!');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				// 3. 고객제품주문(cust_order) 테이블의 finished_date, order_status, delayed_days 업데이트 위해 이동
				forward = new ActionForward();
				forward.setRedirect(true);	// sendRedirect() 사용
				forward.setPath("/order/custOrder/update");
			}
		}
		
		return forward;
		
	}

}
