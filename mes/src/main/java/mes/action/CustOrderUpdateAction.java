package mes.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.CustomerOrderBean;
import mes.dto.ProductionHistoryBean;
import mes.dto.WorkOrderBean;
import mes.svc.CustOrderService;

public class CustOrderUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		ProductionHistoryBean productionHistoryNew = (ProductionHistoryBean) session.getAttribute("productionHistoryNewInfo");
		WorkOrderBean workOrderNew = (WorkOrderBean) session.getAttribute("workOrderNewInfo");
		
		// 납기 지연일(delayed_days) 구하기 (단위: 일)
		int order_no = workOrderNew.getOrder_no();
		CustOrderService custOrderService = new CustOrderService();
		CustomerOrderBean custOrder = custOrderService.getCustOrder(order_no);
		Date delivery_date = custOrder.getDelivery_date();
		
		Timestamp end_dt = productionHistoryNew.getEnd_dt();
		Date finished_date = new Date(end_dt.getTime());
		
		long differenceInTime = end_dt.getTime() - delivery_date.getTime();
		int delayed_days = (int) Math.floor(differenceInTime / 1000 / 60 / 60 / 24);
		
		// 1. 고객 제품주문(cust_order) 테이블 업데이트: finished_date, order_status, delayed_days
		boolean isUpdateSuccess = false;
		isUpdateSuccess = custOrderService.modifyCustOrder(order_no, finished_date, delayed_days);
		
		if(!isUpdateSuccess) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('고객 제품 주문 정보 업데이트에 실패했습니다!!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);	// sendRedirect() 사용
			forward.setPath("/production");
		}
		
		return forward;
		
	}

}
