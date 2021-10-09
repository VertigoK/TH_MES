package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import mes.dao.MESDAO;

public class WorkOrderUpdateService {

	public boolean modifyWorkOrder(int wo_no) {
		
		boolean isUpdateSuccess = false;
		int updateWorkOrderCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		updateWorkOrderCount = mesDAO.updateWorkOrder(wo_no);
		
		if(updateWorkOrderCount > 0) {
			commit(conn);
			isUpdateSuccess = true;
		} else {
			rollback(conn);
		}
		
		return isUpdateSuccess;
		
	}

}
