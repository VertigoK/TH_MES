package mes.svc;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.WorkerBean;
import static db.JDBCUtility.*;

public class HRPlantService {

	public ArrayList<WorkerBean> getHRPlantList(String id, int no) {
		
		ArrayList<WorkerBean> hrPlantList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		hrPlantList = mesDAO.selectHRList(id, no);
		close(conn);
		return hrPlantList;
	}

}
