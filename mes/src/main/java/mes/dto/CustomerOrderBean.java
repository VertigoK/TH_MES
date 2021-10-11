package mes.dto;

import java.io.Serializable;
import java.sql.Date;

public class CustomerOrderBean implements Serializable {
	
	private static final long serialVersionUID = 4969920134567226578L;
	
	private int order_no;
	private int cust_cd;
	private int plant_cd;
	private int item_cd;
	private int order_qty;
	private Date order_date;
	private Date delivery_date;
	private Date finished_date;
	private boolean order_status;
	private int delayed_days;
	private boolean wo_status;
	private boolean ourorder_status;
	
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
	public int getDelayed_days() {
		return delayed_days;
	}
	public void setDelayed_days(int delayed_days) {
		this.delayed_days = delayed_days;
	}
	public boolean isWo_status() {
		return wo_status;
	}
	public void setWo_status(boolean wo_status) {
		this.wo_status = wo_status;
	}
	public boolean isOurorder_status() {
		return ourorder_status;
	}
	public void setOurorder_status(boolean ourorder_status) {
		this.ourorder_status = ourorder_status;
	}
	
}
