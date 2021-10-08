package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mes.dao.MESDAO;
import mes.dto.CustomerOrderBean;

public class CustOrderService {

	public List<Object> custOrderProduct(int cust_cd, int plant_cd, int item_cd, int order_qty, Date delivery_date) {
		
		// cust_order 테이블에 새 레코드 생성
		boolean isCustOrderSuccess = false;
		int order_no = 0;
		int insertCustOrderCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		ArrayList<Integer> insertCustOrderResult = mesDAO.insertCustOrder(cust_cd, plant_cd, item_cd, order_qty, delivery_date);
		order_no = insertCustOrderResult.get(0);
		insertCustOrderCount = insertCustOrderResult.get(1);
		
		if(insertCustOrderCount > 0) {
			commit(conn);
			isCustOrderSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return Arrays.asList(isCustOrderSuccess, order_no);
		
	}

	public CustomerOrderBean getCustOrder(int order_no) {
		
		CustomerOrderBean custOrder = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		custOrder = mesDAO.selectCustOrder(order_no);
		close(conn);
		
		return custOrder;
	}

	

}
