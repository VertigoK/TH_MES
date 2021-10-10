package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import mes.dao.MESDAO;

public class ItemReservedService {

	public boolean registerReservedItem(int order_no, int plant_cd, int item_cd, int plan_qty) {
		
		boolean isRegisterSuccess = false;
		int insertReservedItemCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		int[] qtys = {0, 0, 0};
		switch(item_cd) {
			case 1: qtys = new int[] {plan_qty, 2 * plan_qty, 3 * plan_qty}; break;	// 제품1 생산에 필요한 자재1, 자재2, 자재3 소요량
			case 2: qtys = new int[] {2 * plan_qty, 3 * plan_qty, plan_qty}; break;	// 제품2 생산에 필요한 자재1, 자재2, 자재3 소요량
			case 3: qtys = new int[] {3 * plan_qty, plan_qty, 2 * plan_qty}; break;	// 제품3 생산에 필요한 자재1, 자재2, 자재3 소요량
		}		
		insertReservedItemCount = mesDAO.insertReservedItem(order_no, plant_cd, item_cd, plan_qty, qtys[0], qtys[1], qtys[2]);
		
		if(insertReservedItemCount > 0) {
			commit(conn);
			isRegisterSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isRegisterSuccess;
		
	}

	public boolean modifyReservedItem(int order_no) {
		
		boolean isUpdateSuccess = false;
		int updateReservedItemCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		updateReservedItemCount = mesDAO.updateReservedItem(order_no);
		
		if(updateReservedItemCount > 0) {
			commit(conn);
			isUpdateSuccess = true;
		} else {
			rollback(conn);
		}
		
		return isUpdateSuccess;
		
	}

}
