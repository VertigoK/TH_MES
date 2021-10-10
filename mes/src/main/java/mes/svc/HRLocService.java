package mes.svc;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.WorkerBean;
import static db.JDBCUtility.*;

public class HRLocService {

	public ArrayList<WorkerBean> getHRLocList(String id, int no) {
		
		ArrayList<WorkerBean> hrLocList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		hrLocList = mesDAO.selectHRList(id, no);
		close(conn);
		return hrLocList;
	}

}
