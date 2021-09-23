package mes.dto;

import java.sql.Timestamp;

public class ItemStockBean {
	
	private String stock_no;
	private String item_cd;
	private String cust_cd;
	private String storage_cd;
	private String storage_nm;
	private int item_qty;
	private Timestamp update_dt;
	
	public String getStock_no() {
		return stock_no;
	}
	public void setStock_no(String stock_no) {
		this.stock_no = stock_no;
	}
	public String getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(String item_cd) {
		this.item_cd = item_cd;
	}
	public String getCust_cd() {
		return cust_cd;
	}
	public void setCust_cd(String cust_cd) {
		this.cust_cd = cust_cd;
	}
	public String getStorage_cd() {
		return storage_cd;
	}
	public void setStorage_cd(String storage_cd) {
		this.storage_cd = storage_cd;
	}
	public String getStorage_nm() {
		return storage_nm;
	}
	public void setStorage_nm(String storage_nm) {
		this.storage_nm = storage_nm;
	}
	public int getItem_qty() {
		return item_qty;
	}
	public void setItem_qty(int item_qty) {
		this.item_qty = item_qty;
	}
	public Timestamp getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(Timestamp update_dt) {
		this.update_dt = update_dt;
	}
	
}
