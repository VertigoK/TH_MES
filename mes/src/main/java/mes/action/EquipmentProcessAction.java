package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.svc.EquipmentProcessService;

public class EquipmentProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		EquipmentProcessService equipmentProcessService = new EquipmentProcessService();		
		
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/equipmentprocess.jsp");
		
		return forward;
		
	}

}
