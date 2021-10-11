package mes.dto;

import java.io.Serializable;
import java.sql.Date;

public class WorkOrderBean implements Serializable {
	
	private static final long serialVersionUID = -8053360587903897867L;
	
	private int wo_no;
	private int plant_cd;
	private int line_cd;
	private int order_no;
	private int item_cd;
	private Date start_date;
	private String start_shift;
	private Date end_date;
	private String end_shift;
	private int plan_qty;
	private boolean flag_end;
	
	public int getWo_no() {
		return wo_no;
	}
	public void setWo_no(int wo_no) {
		this.wo_no = wo_no;
	}
	public int getPlant_cd() {
		return plant_cd;
	}
	public void setPlant_cd(int plant_cd) {
		this.plant_cd = plant_cd;
	}
	public int getLine_cd() {
		return line_cd;
	}
	public void setLine_cd(int line_cd) {
		this.line_cd = line_cd;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(int item_cd) {
		this.item_cd = item_cd;
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
	public int getPlan_qty() {
		return plan_qty;
	}
	public void setPlan_qty(int plan_qty) {
		this.plan_qty = plan_qty;
	}
	public boolean isFlag_end() {
		return flag_end;
	}
	public void setFlag_end(boolean flag_end) {
		this.flag_end = flag_end;
	}
	
}
