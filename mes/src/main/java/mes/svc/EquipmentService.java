package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.EquipmentBean;

public class EquipmentService {

	public boolean modifyEquipmentRunTime(int line_cd, int run_time) {
		
		boolean isUpdateEquipmentSuccess = false;
		int updateEquipmentRunTimeCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		updateEquipmentRunTimeCount = mesDAO.updateEquipmentRunTime(line_cd, run_time);
		
		if(updateEquipmentRunTimeCount > 0) {
			commit(conn);
			isUpdateEquipmentSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isUpdateEquipmentSuccess;
		
	}

	public ArrayList<EquipmentBean> getEquipmentList() {
		
		ArrayList<EquipmentBean> equipmentList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		equipmentList = mesDAO.selectEquipmentList();
		close(conn);
		
		return equipmentList;
	}

}
