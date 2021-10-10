package mes.svc;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.WorkerBean;
import static db.JDBCUtility.*;

public class HRLineService {

	public ArrayList<WorkerBean> getHRLineList(String id, int no) {
		
		ArrayList<WorkerBean> hrLineList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		hrLineList = mesDAO.selectHRList(id, no);
		close(conn);
		return hrLineList;
	}

}
