package mes.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.WorkOrderBean;
import mes.svc.ItemReservedService;

public class ItemReservedUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		WorkOrderBean workOrderNew = (WorkOrderBean) session.getAttribute("workOrderNewInfo");
		
		int order_no = workOrderNew.getOrder_no();
		
		// 1. 생산 예약자재(reserved_item) 테이블 업데이트: used_yn
		boolean isUpdateSuccess = false;
		ItemReservedService itemReservedService = new ItemReservedService();
		isUpdateSuccess = itemReservedService.modifyReservedItem(order_no);
		
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
			// 2. 설비(equipment) 테이블의 가동시간(run_time) 업데이트 위해 이동
			forward = new ActionForward();
			forward.setRedirect(true);	// sendRedirect() 사용
			forward.setPath("/equipment/updateRunTime");
		}
		
		return forward;
		
	}

}
