package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.WorkerBean;
import mes.svc.HRLocService;

public class HRLocAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		
		HRLocService hrLocService = new HRLocService();
		
		ArrayList<WorkerBean> hrLocList = new ArrayList<WorkerBean>();
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		
		hrLocList = hrLocService.getHRLocList(id, no);
		req.setAttribute("hrLocList", hrLocList);
		
		forward.setPath("/lv2/hr_loc.jsp");
		return forward;
	}

}
