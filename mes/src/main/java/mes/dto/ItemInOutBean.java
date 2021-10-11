package mes.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItemInOutBean implements Serializable {
	
	private static final long serialVersionUID = -4407784402718573530L;
	
	private int inout_cd;
	private int plant_cd;
	private int item_cd;
	private String item_type;
	private Timestamp inout_dt;
	private String inout_type;
	private int storage_from;
	private String storage_from_nm;
	private int storage_to;
	private String storage_to_nm;
	private int cust_cd;
	private int item_cnt;
	
	public int getInout_cd() {
		return inout_cd;
	}
	public void setInout_cd(int inout_cd) {
		this.inout_cd = inout_cd;
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
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
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
	public int getStorage_from() {
		return storage_from;
	}
	public void setStorage_from(int storage_from) {
		this.storage_from = storage_from;
	}
	public String getStorage_from_nm() {
		return storage_from_nm;
	}
	public void setStorage_from_nm(String storage_from_nm) {
		this.storage_from_nm = storage_from_nm;
	}
	public int getStorage_to() {
		return storage_to;
	}
	public void setStorage_to(int storage_to) {
		this.storage_to = storage_to;
	}
	public String getStorage_to_nm() {
		return storage_to_nm;
	}
	public void setStorage_to_nm(String storage_to_nm) {
		this.storage_to_nm = storage_to_nm;
	}
	public int getCust_cd() {
		return cust_cd;
	}
	public void setCust_cd(int cust_cd) {
		this.cust_cd = cust_cd;
	}
	public int getItem_cnt() {
		return item_cnt;
	}
	public void setItem_cnt(int item_cnt) {
		this.item_cnt = item_cnt;
	}
	
}
