package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.EquipmentBean;
import mes.svc.EquipmentLineService;

public class EquipmentLineAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		
		EquipmentLineService equipmentLineService = new EquipmentLineService();
		ArrayList<EquipmentBean> equipmentList = null;
		equipmentList = equipmentLineService.getEquipmentList(id, no);
		req.setAttribute("equipmentList", equipmentList);
		
		ActionForward forward = new ActionForward();
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv2/equipment_line.jsp");
		return forward;
		
	}

}
