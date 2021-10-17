package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.ErrorBean;
import mes.dto.ErrorLogBean;

public class EquipmentErrorService {
	
	public ErrorBean getEquipmentErrorDetail(int error_cd) {
		
		ErrorBean equipmentErrorDetail = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		equipmentErrorDetail = mesDAO.selectEquipmentErrorDetail(error_cd);
		close(conn);
		
		return equipmentErrorDetail;
		
	}
	
	public ArrayList<ErrorLogBean> getEquipmentErrorLog(int equip_id) {
		
		ArrayList<ErrorLogBean> equipmentErrorLog = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		equipmentErrorLog = mesDAO.selectEquipmentErrorLog(equip_id);
		close(conn);
		
		return equipmentErrorLog;
		
	}	

}
