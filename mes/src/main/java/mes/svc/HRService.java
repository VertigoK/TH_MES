package mes.svc;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.WorkerBean;
import static db.JDBCUtility.*;

public class HRService {

	public ArrayList<WorkerBean> getWorkerList() {
		
		ArrayList<WorkerBean> workerList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		workerList = mesDAO.selectWorkerList();
		close(conn);
		return workerList;
	}

}
