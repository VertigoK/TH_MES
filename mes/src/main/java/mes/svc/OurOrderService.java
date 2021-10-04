package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mes.dao.MESDAO;
import mes.dto.OurOrderBean;

public class OurOrderService {

	public List<Object> registerOurOrder(int cust_cd, int plant_cd, int item_cd, int order_qty) {
		
		// our_order 테이블에 새 레코드 작성
		boolean isOurOrderSuccess = false;
		int order_no = 0;
		int insertOurOrderCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		ArrayList<Integer> insertOurOrderResult = mesDAO.insertOurOrder(cust_cd, plant_cd, item_cd, order_qty);
		insertOurOrderCount = insertOurOrderResult.get(0);
		order_no = insertOurOrderResult.get(1);
		
		if(insertOurOrderCount > 0) {
			commit(conn);
			isOurOrderSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return Arrays.asList(isOurOrderSuccess, order_no);
		
	}

	public OurOrderBean getOurOrder(int order_no) {
		
		OurOrderBean ourOrder = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		ourOrder = mesDAO.selectOurOrder(order_no);
		close(conn);
		
		return ourOrder;
		
	}

}
