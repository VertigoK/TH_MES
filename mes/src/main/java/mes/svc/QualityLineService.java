package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.QualityBean;

public class QualityLineService {

	public ArrayList<QualityBean> getQualityList(String id, int no) {
		
		ArrayList<QualityBean> qualityList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		qualityList = mesDAO.selectQualityList(id, no);
		close(conn);
		
		return qualityList;
		
	}

}
