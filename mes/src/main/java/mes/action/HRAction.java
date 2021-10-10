package mes.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;
import mes.dto.WorkerBean;
import mes.svc.HRService;

public class HRAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		HRService hrService = new HRService();
		
		ArrayList<WorkerBean> workerList = new ArrayList<WorkerBean>();
		workerList = hrService.getWorkerList();
		
		req.setAttribute("workerList", workerList);
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/lv1/hr.jsp");
		
		return forward;
		
	}

}
