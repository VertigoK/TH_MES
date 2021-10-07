package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import mes.dao.MESDAO;

public class ProductionHistoryService {

	public int[] getGoodBadQuantity(int wo_no) {
			
		int[] qtys = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		qtys = mesDAO.selectGoodBadQuantity(wo_no);
		close(conn);
		
		return qtys;
		
	}

}
