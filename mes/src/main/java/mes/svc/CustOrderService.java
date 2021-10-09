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
	
	// cust_order 테이블에 새 레코드 생성
	public List<Object> custOrderProduct(int cust_cd, int plant_cd, int item_cd, int order_qty, Date delivery_date) {
		
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
	
	// cust_order 조회 (w/ order_no)s
	public CustomerOrderBean getCustOrder(int order_no) {
		
		CustomerOrderBean custOrder = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		custOrder = mesDAO.selectCustOrder(order_no);
		close(conn);
		
		return custOrder;
	}

	// cust_order 업데이트: finished_date, order_status, delayed_days
	public boolean modifyCustOrder(int order_no, Date finished_date, int delayed_days) {
		
		boolean isUpdateSuccess = false;
		int updateCustOrderCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		updateCustOrderCount = mesDAO.updateCustOrder(order_no, finished_date, delayed_days);
		
		if(updateCustOrderCount > 0) {
			commit(conn);
			isUpdateSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
				
		return isUpdateSuccess;
		
	}

	

}
