package mes.dto;

import java.io.Serializable;

public class ReservedItemBean implements Serializable {

	private static final long serialVersionUID = 6217868792093069674L;
	
	private int order_no;
	private int plant_cd;
	private int item_cd;
	private int plan_qty;
	private int item4_qty;
	private int item5_qty;
	private int item6_qty;
	private boolean used_yn;
	
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
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
	public int getPlan_qty() {
		return plan_qty;
	}
	public void setPlan_qty(int plan_qty) {
		this.plan_qty = plan_qty;
	}
	public int getItem4_qty() {
		return item4_qty;
	}
	public void setItem4_qty(int item4_qty) {
		this.item4_qty = item4_qty;
	}
	public int getItem5_qty() {
		return item5_qty;
	}
	public void setItem5_qty(int item5_qty) {
		this.item5_qty = item5_qty;
	}
	public int getItem6_qty() {
		return item6_qty;
	}
	public void setItem6_qty(int item6_qty) {
		this.item6_qty = item6_qty;
	}
	public boolean isUsed_yn() {
		return used_yn;
	}
	public void setUsed_yn(boolean used_yn) {
		this.used_yn = used_yn;
	}
	
}
