package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.WorkOrderBean;

public class WorkOrderTodayService {

	public ArrayList<WorkOrderBean> getWorkOrderTodayList(Date todayDate) {
		
		ArrayList<WorkOrderBean> workOrderTodayList = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		workOrderTodayList = mesDAO.selectWorkOrder(todayDate);
		close(conn);
		
		return workOrderTodayList;
		
	}

}
