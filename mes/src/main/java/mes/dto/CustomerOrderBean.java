package mes.dto;

import java.sql.Date;

public class CustomerOrderBean {
	
	private int order_no;
	private int cust_cd;
	private int plant_cd;
	private int item_cd;
	private int order_qty;
	private Date order_date;
	private Date delivery_date;
	private Date finished_date;
	private boolean order_status;
	private int delayed_date;
	
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
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public Date getFinished_date() {
		return finished_date;
	}
	public void setFinished_date(Date finished_date) {
		this.finished_date = finished_date;
	}
	public boolean isOrder_status() {
		return order_status;
	}
	public void setOrder_status(boolean order_status) {
		this.order_status = order_status;
	}
	public int getDelayed_date() {
		return delayed_date;
	}
	public void setDelayed_date(int delayed_date) {
		this.delayed_date = delayed_date;
	}
		
}
