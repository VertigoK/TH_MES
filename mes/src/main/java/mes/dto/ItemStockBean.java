package mes.dto;

import java.sql.Timestamp;

public class ItemStockBean {
	
	private int stock_no;
	private int item_cd;
	private String item_type;
	private int storage_cd;
	private String storage_nm;
	private int item_qty;
	private Timestamp update_dt;
	
	public int getStock_no() {
		return stock_no;
	}
	public void setStock_no(int stock_no) {
		this.stock_no = stock_no;
	}
	public int getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(int item_cd) {
		this.item_cd = item_cd;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public int getStorage_cd() {
		return storage_cd;
	}
	public void setStorage_cd(int storage_cd) {
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
