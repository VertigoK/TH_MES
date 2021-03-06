package mes.action;

import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.CustomerOrderBean;
import mes.dto.WorkOrderBean;
import mes.svc.CustOrderService;
import mes.svc.WorkOrderService;
import mes.svc.WorkOrderTodayService;

public class WorkOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		CustomerOrderBean custOrder = (CustomerOrderBean) session.getAttribute("custOrderInfo");
		int plant_cd = custOrder.getPlant_cd();
		int order_no = custOrder.getOrder_no();
		int item_cd = custOrder.getItem_cd();
		
		int line_cd = Integer.parseInt(req.getParameter("line_cd"));
		Date start_date = Date.valueOf(req.getParameter("start_date"));
		String start_shift = req.getParameter("start_shift");
		int plan_qty = Integer.parseInt(req.getParameter("plan_qty"));
		
		boolean isWorkOrderSuccess = false;
		int wo_no = 0;
		
		// end_date과 end_shift 계산
		WorkOrderService workOrderService = new WorkOrderService();
		List<Object> endDateShift = workOrderService.computeEndDateShift(start_date, start_shift, plan_qty);
		Date end_date = (Date) endDateShift.get(0);
		String end_shift = (String) endDateShift.get(1);
		
		// 1. 생산지시 정보 DB에 등록
		List<Object> workOrderResult = workOrderService.registerWorkOrder(plant_cd, line_cd, order_no, item_cd,
				start_date, start_shift, end_date, end_shift, plan_qty);
		isWorkOrderSuccess = (boolean) workOrderResult.get(0);
		wo_no = (int) workOrderResult.get(1);
		
		if(!isWorkOrderSuccess) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('생산지시 생성에 실패했습니다!!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			// DB에 등록된 생산지시 정보를 session 객체에 저장
			WorkOrderBean workOrder = workOrderService.getWorkOrder(wo_no);
			session.setAttribute("workOrderInfo", workOrder);
			
			// 'workOrderTodayListInfo' session 객체에 등록
			Date todayDate = Date.valueOf(LocalDate.now());
			WorkOrderTodayService workOrderTodayService = new WorkOrderTodayService();
			ArrayList<WorkOrderBean> workOrderTodayList = workOrderTodayService.getWorkOrderTodayList(todayDate);
			session.setAttribute("workOrderTodayListInfo", workOrderTodayList);
			
			// 3. cust_order 테이블의 생산지시 여부 업데이트
			boolean isModifySuccess = false;
			CustOrderService custOrderService = new CustOrderService();
			isModifySuccess = custOrderService.modifyCustOrder(order_no, "wo_status");
			
			if(!isModifySuccess) {
				res.setContentType("text/html; charset=utf-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('cust_order 테이블의 생산지시여부 업데이트에 실패했습니다!');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.flush();
				out.close();
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);	// sendRedirect() 사용
				forward.setPath("/misc/work_order_success.jsp");
			}
		}
		
		return forward;
		
	}

}
