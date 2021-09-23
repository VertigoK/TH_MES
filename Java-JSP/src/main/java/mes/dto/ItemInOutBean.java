package mes.dto;

import java.sql.Timestamp;

public class ItemInOutBean {
	
	private String inout_cd;
	private String plant_cd;
	private String item_cd;
	private String cust_cd;
	private String item_type;
	private String storage_from;
	private String storage_from_nm;
	private String storage_to;
	private String storage_to_nm;
	private Timestamp inout_dt;
	private String inout_type;
	private int inout_cnt;
	
	public String getInout_cd() {
		return inout_cd;
	}
	public void setInout_cd(String inout_cd) {
		this.inout_cd = inout_cd;
	}
	public String getPlant_cd() {
		return plant_cd;
	}
	public void setPlant_cd(String plant_cd) {
		this.plant_cd = plant_cd;
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
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getStorage_from() {
		return storage_from;
	}
	public void setStorage_from(String storage_from) {
		this.storage_from = storage_from;
	}
	public String getStorage_from_nm() {
		return storage_from_nm;
	}
	public void setStorage_from_nm(String storage_from_nm) {
		this.storage_from_nm = storage_from_nm;
	}
	public String getStorage_to() {
		return storage_to;
	}
	public void setStorage_to(String storage_to) {
		this.storage_to = storage_to;
	}
	public String getStorage_to_nm() {
		return storage_to_nm;
	}
	public void setStorage_to_nm(String storage_to_nm) {
		this.storage_to_nm = storage_to_nm;
	}
	public Timestamp getInout_dt() {
		return inout_dt;
	}
	public void setInout_dt(Timestamp inout_dt) {
		this.inout_dt = inout_dt;
	}
	public String getInout_type() {
		return inout_type;
	}
	public void setInout_type(String inout_type) {
		this.inout_type = inout_type;
	}
	public int getInout_cnt() {
		return inout_cnt;
	}
	public void setInout_cnt(int inout_cnt) {
		this.inout_cnt = inout_cnt;
	}

}
