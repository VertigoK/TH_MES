package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.EquipmentBean;

public class EquipmentPlantService {

	public ArrayList<EquipmentBean> getEquipmentList(String id, int no) {
		
		ArrayList<EquipmentBean> equipmentList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		equipmentList = mesDAO.selectEquipmentList(id, no);
		close(conn);
		
		return equipmentList;
		
	}

}
