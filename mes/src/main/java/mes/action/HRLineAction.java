package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.WorkerBean;
import mes.svc.HRLineService;

public class HRLineAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		HRLineService hrLineService = new HRLineService();
		
		ArrayList<WorkerBean> hrLineList = new ArrayList<WorkerBean>();
		String id = req.getParameter("id");
		int no = Integer.parseInt(req.getParameter("no"));
		
		hrLineList = hrLineService.getHRLineList(id, no);
		req.setAttribute("hrLineList", hrLineList);
		
		forward.setPath("/lv2/hr_line.jsp");
		return forward;
	}

}
