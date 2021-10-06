package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mes.dao.MESDAO;
import mes.dto.WorkOrderBean;

public class WorkOrderService {

	public List<Object> computeEndDateShift(Date start_date, String start_shift, int plan_qty) {
			
		String start_time = null;
		switch(start_shift) {
			case "주간": start_time = "07:00:01"; break;
			case "주야간": start_time = "15:00:01"; break;
			case "야간": start_time = "23:00:01"; break;
		}
		Timestamp startDateTime = Timestamp.valueOf(start_date.toString() + " " + start_time);
		
		long now = System.currentTimeMillis();
		Timestamp endDateTime = new Timestamp(now);
		endDateTime.setTime(startDateTime.getTime() + (plan_qty - 1) * 60 * 1000);
		Date end_date = new Date(endDateTime.getTime());
		Time end_time = new Time(endDateTime.getTime());
		
		Time shift2 = new Time(Timestamp.valueOf(end_date.toString() + " " + "15:00:00").getTime());
		Time shift3 = new Time(Timestamp.valueOf(end_date.toString() + " " + "23:00:00").getTime());
		
		String end_shift = null;
		if(end_time.getTime() >= shift3.getTime()) {
			end_shift = "야간";
		} else if(end_time.getTime() >= shift2.getTime()) {
			end_shift = "주야간";
		} else {
			end_shift = "주간";
		}
		
		return Arrays.asList(end_date, end_shift);
		
	}

	public List<Object> registerWorkOrder(int plant_cd, int line_cd, int order_no, int item_cd,
			Date start_date, String start_shift, Date end_date, String end_shift, int plan_qty) {
		
		boolean isWorkOrderSuccess = false;
		int wo_no = 0;
		int insertWorkOrderCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		ArrayList<Integer> insertWorkOrderResult = mesDAO.insertWorkOrder(plant_cd, line_cd, order_no, item_cd,
				start_date, start_shift, end_date, end_shift, plan_qty);
		insertWorkOrderCount = insertWorkOrderResult.get(0);
		wo_no = insertWorkOrderResult.get(1);
		
		if(insertWorkOrderCount > 0) {
			commit(conn);
			isWorkOrderSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return Arrays.asList(isWorkOrderSuccess, wo_no);
		
	}
	
	public WorkOrderBean getWorkOrder(int wo_no) {

		WorkOrderBean workOrder = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		workOrder = mesDAO.selectWorkOrder(wo_no);
		close(conn);
		
		return workOrder;
		
	}

	public boolean modifyCustOrder(int order_no) {
		
		boolean isModifySuccess = false;
		int updateCount = 0;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		updateCount = mesDAO.updateCustOrder(order_no);
		
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
