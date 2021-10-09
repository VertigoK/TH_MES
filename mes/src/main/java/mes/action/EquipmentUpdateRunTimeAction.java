package mes.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mes.dto.ActionForward;
import mes.dto.ProductionBean;
import mes.svc.EquipmentUpdateService;

public class EquipmentUpdateRunTimeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ActionForward forward = null;
		
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<ProductionBean> productionDataList = (ArrayList<ProductionBean>) session.getAttribute("productionDataListInfo");
		
		// 동일 wo_no를 가지는 생산 데이터 중에서 처음 생산된 제품의 데이터
		ProductionBean productionDataFirst = productionDataList.get(0);
		int line_cd = productionDataFirst.getLine_cd();
		Timestamp start_dt = productionDataFirst.getPrd_dt();
		
		// 동일 wo_no를 가지는 생산 데이터 중에서 마지막 생산된 제품의 데이터
		ProductionBean productionDataLast = productionDataList.get(productionDataList.size()-1);
		Timestamp end_dt = productionDataLast.getPrd_dt();
		
		// 가동시간(run_time) 구하기 (단위: 분)
		long differenceInTime = end_dt.getTime() - start_dt.getTime();
		int run_time = (int) (differenceInTime / 1000 / 60);
		
		// 1. 설비(equipment) 테이블에서 해당 라인(line_cd) 모든 설비의 가동시간(run_time)을 생산에 소요된 시간만큼 업데이트
		boolean isUpdateSuccess = false;
		EquipmentUpdateService equipmentUpdateService = new EquipmentUpdateService();
		isUpdateSuccess = equipmentUpdateService.modifyEquipmentRunTime(line_cd, run_time);
		
		if(!isUpdateSuccess) {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('설비 가동시간 업데이트에 실패했습니다!!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			out.close();
		} else {
			// 2. 품목입출고(item_io) 테이블에 제품 입고(in) 등록을 위해 이동
			forward = new ActionForward();
			forward.setRedirect(true);	// sendRedirect() 사용
			forward.setPath("/item/production/temp");
		}
		
		return forward;
		
	}

}
