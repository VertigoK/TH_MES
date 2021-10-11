package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import mes.dao.MESDAO;

public class QualityService {

	public float[] getYield(int per) {
		
		float[] yield = new float[6]; 
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		yield = mesDAO.selectYield(per);
		close(conn);
		
		return yield;
		
	}

}
