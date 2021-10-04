package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.OurOrderBean;

public class OrderOutListService {

	public ArrayList<OurOrderBean> getOrderOutList() {
		
		ArrayList<OurOrderBean> orderOutList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		orderOutList = mesDAO.selectOrderOutList();
		close(conn);
		
		return orderOutList;
		
	}

}
