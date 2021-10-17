package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.ErrorBean;
import mes.dto.ErrorLogBean;
import mes.svc.EquipmentErrorService;

public class EquipmentErrorAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		int error_cd = Integer.parseInt(req.getParameter("error_cd"));
		int equip_id = Integer.parseInt(req.getParameter("equip_id"));
		
		EquipmentErrorService equipmentErrorService = new EquipmentErrorService();
		ErrorBean equipmentErrorDetail = equipmentErrorService.getEquipmentErrorDetail(error_cd);
		ArrayList<ErrorLogBean> equipmentErrorLog = equipmentErrorService.getEquipmentErrorLog(equip_id);
		
		req.setAttribute("equipmentErrorDetail", equipmentErrorDetail);
		req.setAttribute("equipmentErrorLog", equipmentErrorLog);
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/equipment_error.jsp");
		
		return forward;
		
	}

}
