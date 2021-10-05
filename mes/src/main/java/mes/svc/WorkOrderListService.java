package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.WorkOrderBean;

public class WorkOrderListService {

	public ArrayList<WorkOrderBean> getWorkOrderList() {
		
		ArrayList<WorkOrderBean> workOrderList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		workOrderList = mesDAO.selectWorkOrderList();
		close(conn);
		
		return workOrderList;
		
	}

}
