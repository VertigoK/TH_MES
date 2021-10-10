package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.sql.Timestamp;

import mes.dao.MESDAO;
import mes.dto.ProductionHistoryBean;
import mes.dto.WorkOrderBean;

public class ItemStockService {
	
	public boolean modifyItemStockMaterialIn(int plant_cd, int item_cd, int order_qty, Timestamp update_dt) {
		
		boolean isModifySuccess = false;
		int updateCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		int storage_cd = 1;
		if(plant_cd == 2) storage_cd = 9;

		updateCount = mesDAO.updateItemStock(item_cd, storage_cd, order_qty, update_dt);
		
		if(updateCount > 0) {
			commit(conn);
			isModifySuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isModifySuccess;
		
	}

	public boolean modifyItemStockMaterialInOut(WorkOrderBean workOrder, int item_cd, int item_cnt, Timestamp start_dt) {
	
		boolean isModifySuccess = false;
		int updateCount1 = 0;
		int updateCount2 = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		// 1. 외부 자재창고는 감소
		int plant_cd = workOrder.getPlant_cd();
		int storage_cd = 1;
		if(plant_cd == 2) storage_cd = 9;
		
		updateCount1 = mesDAO.updateItemStock(item_cd, storage_cd, -item_cnt, start_dt);
		
		// 2. 라인의 자재임시창고는 같은 수량만큼 증가
		int line_cd = workOrder.getLine_cd();
		int[] material_storage_cd_for_line = {3, 5, 7, 11, 13, 15};
		storage_cd = material_storage_cd_for_line[line_cd - 1];
		
		updateCount2 = mesDAO.updateItemStock(item_cd, storage_cd, item_cnt, start_dt);
			
		if(updateCount1 > 0 && updateCount2 > 0) {
			commit(conn);
			isModifySuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
	
		return isModifySuccess;
	
	}

	public boolean modifyItemStockProductionInOut(ProductionHistoryBean productionHistoryNew) {

		boolean isModifySuccess = false;
		int updateCount1 = 0;
		int updateCount2 = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		// 1. 라인의 제품임시창고는 감소
		int item_cd = productionHistoryNew.getItem_cd();
		int line_cd = productionHistoryNew.getLine_cd();
		int[] production_storage_cd_for_line = {4, 6, 8, 12, 14, 16};
		int storage_cd = production_storage_cd_for_line[line_cd - 1];
		int item_cnt = productionHistoryNew.getOut_qty();
		Timestamp end_dt = productionHistoryNew.getEnd_dt();
		
		updateCount1 = mesDAO.updateItemStock(item_cd, storage_cd, -item_cnt, end_dt);
		
		// 2. 외부 제품창고는 같은 수량만큼 증가
		int plant_cd = productionHistoryNew.getPlant_cd();
		storage_cd = 2;
		if(plant_cd == 2) storage_cd = 10;
		
		updateCount2 = mesDAO.updateItemStock(item_cd, storage_cd, item_cnt, end_dt);
		
		if(updateCount1 > 0 && updateCount2 > 0) {
			commit(conn);
			isModifySuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isModifySuccess;
		
	}

	public boolean modifyItemStockProductionOut(ProductionHistoryBean productionHistoryNew, int order_qty) {
		
		boolean isModifySuccess = false;
		int updateCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
				
		int item_cd = productionHistoryNew.getItem_cd();
		int plant_cd = productionHistoryNew.getPlant_cd();
		int storage_cd = 2;
		if(plant_cd == 2) storage_cd = 10;
		Timestamp end_dt = productionHistoryNew.getEnd_dt();
		
		updateCount = mesDAO.updateItemStock(item_cd, storage_cd, -order_qty, end_dt);
		
		if(updateCount > 0) {
			commit(conn);
			isModifySuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isModifySuccess;
		
	}

	public boolean modifyItemStockTempInOut(int item_cd, int storage_cd, int item_qty, Timestamp end_dt) {
		
		boolean isModifySuccess = false;
		int updateCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		updateCount = mesDAO.updateItemStock(item_cd, storage_cd, item_qty, end_dt);
		
		if(updateCount > 0) {
			commit(conn);
			isModifySuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isModifySuccess;
		
	}

}
