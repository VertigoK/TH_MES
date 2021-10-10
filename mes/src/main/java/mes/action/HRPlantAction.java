package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.WorkerBean;
import mes.svc.HRPlantService;

public class HRPlantAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		HRPlantService hrPlantService = new HRPlantService();
		
		ArrayList<WorkerBean> hrPlantList = new ArrayList<WorkerBean>();
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		
		hrPlantList = hrPlantService.getHRPlantList(id, no);
		req.setAttribute("hrPlantList", hrPlantList);
		
		forward.setPath("/lv2/hr_plant.jsp");
		return forward;
	}

}
