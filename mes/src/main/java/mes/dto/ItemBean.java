package mes.dto;

import java.sql.Timestamp;

public class ItemBean {
	
	private int item_cd;
	private int cust_cd;
	private String item_type;
	private String item_nm;
	private float item_spec1;
	private float item_spec2;
	private float item_spec3;
	private float item_spec4;
	private float item_spec5;
	private float item_spec6;
	private float item_spec7;
	private float item_spec8;
	private Timestamp update_dt;
	
	public int getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(int item_cd) {
		this.item_cd = item_cd;
	}
	public int getCust_cd() {
		return cust_cd;
	}
	public void setCust_cd(int cust_cd) {
		this.cust_cd = cust_cd;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getItem_nm() {
		return item_nm;
	}
	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}
	public float getItem_spec1() {
		return item_spec1;
	}
	public void setItem_spec1(float item_spec1) {
		this.item_spec1 = item_spec1;
	}
	public float getItem_spec2() {
		return item_spec2;
	}
	public void setItem_spec2(float item_spec2) {
		this.item_spec2 = item_spec2;
	}
	public float getItem_spec3() {
		return item_spec3;
	}
	public void setItem_spec3(float item_spec3) {
		this.item_spec3 = item_spec3;
	}
	public float getItem_spec4() {
		return item_spec4;
	}
	public void setItem_spec4(float item_spec4) {
		this.item_spec4 = item_spec4;
	}
	public float getItem_spec5() {
		return item_spec5;
	}
	public void setItem_spec5(float item_spec5) {
		this.item_spec5 = item_spec5;
	}
	public float getItem_spec6() {
		return item_spec6;
	}
	public void setItem_spec6(float item_spec6) {
		this.item_spec6 = item_spec6;
	}
	public float getItem_spec7() {
		return item_spec7;
	}
	public void setItem_spec7(float item_spec7) {
		this.item_spec7 = item_spec7;
	}
	public float getItem_spec8() {
		return item_spec8;
	}
	public void setItem_spec8(float item_spec8) {
		this.item_spec8 = item_spec8;
	}
	public Timestamp getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(Timestamp update_dt) {
		this.update_dt = update_dt;
	}

}
