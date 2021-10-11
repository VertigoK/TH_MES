package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.ProcessBean;

public class ProcessService {

	public ArrayList<ProcessBean> getProcessList() {
		
		ArrayList<ProcessBean> processList = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		processList = mesDAO.selectProcessList();
		close(conn);
		
		return processList;
		
	}

}
