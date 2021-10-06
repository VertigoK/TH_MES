package mes.dto;

import java.sql.Timestamp;

public class ProductionHistoryBean {
	
	private int wo_no;
	private int plant_cd;
	private int line_cd;
	private int item_cd;
	private int wo_seq;
	private Timestamp start_dt;
	private Timestamp end_dt;
	private int in_qty;
	private int out_qty;
	private int ng_qty;
	
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
	public int getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(int item_cd) {
		this.item_cd = item_cd;
	}
	public int getWo_seq() {
		return wo_seq;
	}
	public void setWo_seq(int wo_seq) {
		this.wo_seq = wo_seq;
	}
	public Timestamp getStart_dt() {
		return start_dt;
	}
	public void setStart_dt(Timestamp start_dt) {
		this.start_dt = start_dt;
	}
	public Timestamp getEnd_dt() {
		return end_dt;
	}
	public void setEnd_dt(Timestamp end_dt) {
		this.end_dt = end_dt;
	}
	public int getIn_qty() {
		return in_qty;
	}
	public void setIn_qty(int in_qty) {
		this.in_qty = in_qty;
	}
	public int getOut_qty() {
		return out_qty;
	}
	public void setOut_qty(int out_qty) {
		this.out_qty = out_qty;
	}
	public int getNg_qty() {
		return ng_qty;
	}
	public void setNg_qty(int ng_qty) {
		this.ng_qty = ng_qty;
	}
	
}
