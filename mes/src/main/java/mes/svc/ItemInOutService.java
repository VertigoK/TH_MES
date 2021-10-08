package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.sql.Timestamp;

import mes.dao.MESDAO;
import mes.dto.OurOrderBean;
import mes.dto.WorkOrderBean;

public class ItemInOutService {

	public boolean registerMaterialIn(OurOrderBean ourOrder) {
		
		boolean isRegisterSuccess = false;
		int insertItemInOutCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		int plant_cd = ourOrder.getPlant_cd();
		int item_cd = ourOrder.getItem_cd();
		String item_type = "자재";
		Timestamp inout_dt = ourOrder.getOrder_dt();
		String inout_type = "in";
		int storage_from = 1;
		String storage_from_nm = "자재창고1";
		int storage_to = 1;
		String storage_to_nm = "자재창고1";
		
		if(plant_cd == 2) {
			storage_from = 9;
			storage_from_nm = "자재창고2";
			storage_to = 9;
			storage_to_nm = "자재창고2";
		}
		
		int cust_cd = ourOrder.getCust_cd();
		int item_cnt = ourOrder.getOrder_qty();
		
		insertItemInOutCount = mesDAO.insertItemInOut(plant_cd, item_cd, item_type, inout_dt, inout_type,
				storage_from, storage_from_nm, storage_to, storage_to_nm, cust_cd, item_cnt);
		
		if(insertItemInOutCount > 0) {
			commit(conn);
			isRegisterSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isRegisterSuccess;
		
	}

	public boolean registerMaterialOut(WorkOrderBean workOrder, int item_cd, int item_cnt, Timestamp inout_dt) {
		
		boolean isRegisterSuccess = false;
		int insertItemInOutCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		int plant_cd = workOrder.getPlant_cd();
		String item_type = "자재";
		String inout_type = "out";
		
		int storage_from = 1;
		String storage_from_nm = "자재창고1";
		if(plant_cd == 2) {
			storage_from = 9;
			storage_from_nm = "자재창고2";
		}
		
		int line_cd = workOrder.getLine_cd();
		int[] storage_cd_for_line = {3, 5, 7, 11, 13, 15};
		int storage_to = storage_cd_for_line[line_cd - 1];
		String storage_to_nm = "자재임시창고" + line_cd;
		
		int cust_cd = 0;	// cust_cd = 0 -> 회사 내부 이동을 의미
		
		insertItemInOutCount = mesDAO.insertItemInOut(plant_cd, item_cd, item_type, inout_dt, inout_type,
				storage_from, storage_from_nm, storage_to, storage_to_nm, cust_cd, item_cnt);
		
		if(insertItemInOutCount > 0) {
			commit(conn);
			isRegisterSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isRegisterSuccess;
		
	}

}
