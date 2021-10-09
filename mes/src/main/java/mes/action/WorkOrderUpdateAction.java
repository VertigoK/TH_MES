package mes.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.ProductionHistoryBean;
import mes.dto.WorkOrderBean;
import mes.svc.WorkOrderService;
import mes.svc.WorkOrderUpdateService;

public class WorkOrderUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		ProductionHistoryBean productionHistoryNew = (ProductionHistoryBean) session.getAttribute("productionHistoryNewInfo");
		int wo_no = productionHistoryNew.getWo_no();
		
		// 1. 생산지시(work_order) 테이블의 작업상태(flag_end) 업데이트
		boolean isUpdateSuccess = false;
		WorkOrderUpdateService workOrderUpdateService = new WorkOrderUpdateService();
		isUpdateSuccess = workOrderUpdateService.modifyWorkOrder(wo_no);
		
		if(!isUpdateSuccess) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('생산지시 테이블의 작업상태 업데이트에 실패했습니다!!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			// 업데이트된 생산지시 정보를 session 객체에 저장
			WorkOrderService workOrderService = new WorkOrderService();
			WorkOrderBean workOrderNew = workOrderService.getWorkOrder(wo_no);
			session.setAttribute("workOrderNewInfo", workOrderNew);
			
			// 2. 설비(equipment) 테이블의 가동시간(run_time) 업데이트 위해 이동
			forward = new ActionForward();
			forward.setRedirect(true);	// sendRedirect() 사용
			forward.setPath("/equipment/updateRunTime");
		}
		
		return forward;
		
	}

}
