package mes.dto;

import java.sql.Date;

public class WorkOrderBean {
	
	private String wo_no;
	private String plant_cd;
	private String line_cd;
	private String order_no;
	private Date start_date;
	private String start_shift;
	private Date end_date;
	private String end_shift;
	private boolean flag_end;
	private int plan_qty;
	
	public String getWo_no() {
		return wo_no;
	}
	public void setWo_no(String wo_no) {
		this.wo_no = wo_no;
	}
	public String getPlant_cd() {
		return plant_cd;
	}
	public void setPlant_cd(String plant_cd) {
		this.plant_cd = plant_cd;
	}
	public String getLine_cd() {
		return line_cd;
	}
	public void setLine_cd(String line_cd) {
		this.line_cd = line_cd;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public String getStart_shift() {
		return start_shift;
	}
	public void setStart_shift(String start_shift) {
		this.start_shift = start_shift;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getEnd_shift() {
		return end_shift;
	}
	public void setEnd_shift(String end_shift) {
		this.end_shift = end_shift;
	}
	public boolean isFlag_end() {
		return flag_end;
	}
	public void setFlag_end(boolean flag_end) {
		this.flag_end = flag_end;
	}
	public int getPlan_qty() {
		return plan_qty;
	}
	public void setPlan_qty(int plan_qty) {
		this.plan_qty = plan_qty;
	}

}
