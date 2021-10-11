package mes.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class OurOrderBean implements Serializable {
	
	private static final long serialVersionUID = 4405603906802917535L;
	
	private int order_no;
	private int cust_cd;
	private int plant_cd;
	private int item_cd;
	private int order_qty;
	private Timestamp order_dt;
	private boolean order_status;
	
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getCust_cd() {
		return cust_cd;
	}
	public void setCust_cd(int cust_cd) {
		this.cust_cd = cust_cd;
	}
	public int getPlant_cd() {
		return plant_cd;
	}
	public void setPlant_cd(int plant_cd) {
		this.plant_cd = plant_cd;
	}
	public int getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(int item_cd) {
		this.item_cd = item_cd;
	}
	public int getOrder_qty() {
		return order_qty;
	}
	public void setOrder_qty(int order_qty) {
		this.order_qty = order_qty;
	}
	public Timestamp getOrder_dt() {
		return order_dt;
	}
	public void setOrder_dt(Timestamp order_dt) {
		this.order_dt = order_dt;
	}
	public boolean isOrder_status() {
		return order_status;
	}
	public void setOrder_status(boolean order_status) {
		this.order_status = order_status;
	}
	
}
