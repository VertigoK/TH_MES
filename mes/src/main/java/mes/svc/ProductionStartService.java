package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;

import mes.dao.MESDAO;
import mes.dto.WorkOrderBean;

public class ProductionStartService {
	
	public WorkOrderBean getWorkOrder(int wo_no) {
		
		WorkOrderBean workOrder = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		workOrder = mesDAO.selectWorkOrder(wo_no);
		close(conn);
		
		return workOrder;
		
	}
	
	public Timestamp computeStartDT(Date start_date, String start_shift) {
		
		Timestamp start_dt = null;
		
		String start_time = null;
		switch(start_shift) {
			case "주간": start_time = "07:00:01"; break;
			case "주야간": start_time = "15:00:01"; break;
			case "야간": start_time = "23:00:01"; break;
		}
		start_dt = Timestamp.valueOf(start_date.toString() + " " + start_time);
		
		return start_dt;
		
	}
	
	public int getWorkerNo(int line_cd, String start_shift) {
				
		int worker_no = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		worker_no = mesDAO.selectWorkerNo(line_cd, start_shift);
		close(conn);
		
		return worker_no;
		
	}
	
}
