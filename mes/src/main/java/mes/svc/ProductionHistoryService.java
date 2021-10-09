package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.sql.Timestamp;

import mes.dao.MESDAO;
import mes.dto.ProductionHistoryBean;

public class ProductionHistoryService {

	public int[] getGoodBadQuantity(int wo_no) {
			
		int[] qtys = new int[3];
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		qtys = mesDAO.selectGoodBadQuantity(wo_no);
		close(conn);
		
		return qtys;
		
	}

	public boolean registerProductionHistory(int wo_no, int plant_cd, int line_cd, int item_cd,
			Timestamp start_dt, Timestamp end_dt, int in_qty, int out_qty, int ng_qty) {
		
		boolean isRegisterSuccess = false;
		int insertProductionHistoryCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		insertProductionHistoryCount = mesDAO.insertProductionHistory(wo_no, plant_cd, line_cd,
				item_cd, start_dt, end_dt, in_qty, out_qty, ng_qty);
		
		if(insertProductionHistoryCount > 0) {
			commit(conn);
			isRegisterSuccess = true;
		} else {
			rollback(conn);
		}
		
		return isRegisterSuccess;
		
	}

	public ProductionHistoryBean getProductionHistory(int wo_no) {
		
		ProductionHistoryBean productionHistory = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		productionHistory = mesDAO.selectProductionHistory(wo_no);
		close(conn);
		
		return productionHistory;
		
	}

}
