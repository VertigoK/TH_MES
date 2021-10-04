package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.LineBean;
import mes.dto.WorkOrderBean;

public class WorkOrderFormService {

	public ArrayList<LineBean> getLineList(int plant_cd) {
		
		ArrayList<LineBean> lineList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		lineList = mesDAO.selectLineList(plant_cd);
		close(conn);
		
		return lineList;
		
	}

	public ArrayList<WorkOrderBean> getWorkOrderList(int plant_cd) {
		
		ArrayList<WorkOrderBean> workOrderList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		workOrderList = mesDAO.selectWorkOrderList(plant_cd);
		close(conn);
		
		return workOrderList;
		
	}

}
