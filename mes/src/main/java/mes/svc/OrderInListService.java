package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.CustomerOrderBean;

public class OrderInListService {

	public ArrayList<CustomerOrderBean> getOrderInList() {

		ArrayList<CustomerOrderBean> orderInList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		orderInList = mesDAO.selectOrderInList();
		close(conn);
		
		return orderInList;
		
	}

}
