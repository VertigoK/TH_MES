package mes.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.dto.ActionForward;

public class InventoryStatusAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// isRedirect = false (기본값) -> forward() 사용
		forward.setPath("/status/inventoryStatus.jsp");
		
		return forward;
		
	}

}
