package mes.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductionBean implements Serializable {
	
	private static final long serialVersionUID = 1115846784267122924L;
	
	private String serial_no;
	private int wo_no;
	private int plant_cd;
	private int line_cd;
	private int item_cd;
	private int worker_no;
	private float dim_x;
	private float dim_y;
	private float dim_h;
	private float dim_w;
	private float hole_x;
	private float hole_y;
	private float hole_xc;
	private float hole_yc;
	private float str_x;
	private float str_y;
	private float hole_d;
	private float hole_ratio;
	private Timestamp prd_dt;
	
	public String getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}
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
	public int getWorker_no() {
		return worker_no;
	}
	public void setWorker_no(int worker_no) {
		this.worker_no = worker_no;
	}
	public float getDim_x() {
		return dim_x;
	}
	public void setDim_x(float dim_x) {
		this.dim_x = dim_x;
	}
	public float getDim_y() {
		return dim_y;
	}
	public void setDim_y(float dim_y) {
		this.dim_y = dim_y;
	}
	public float getDim_h() {
		return dim_h;
	}
	public void setDim_h(float dim_h) {
		this.dim_h = dim_h;
	}
	public float getDim_w() {
		return dim_w;
	}
	public void setDim_w(float dim_w) {
		this.dim_w = dim_w;
	}
	public float getHole_x() {
		return hole_x;
	}
	public void setHole_x(float hole_x) {
		this.hole_x = hole_x;
	}
	public float getHole_y() {
		return hole_y;
	}
	public void setHole_y(float hole_y) {
		this.hole_y = hole_y;
	}
	public float getHole_xc() {
		return hole_xc;
	}
	public void setHole_xc(float hole_xc) {
		this.hole_xc = hole_xc;
	}
	public float getHole_yc() {
		return hole_yc;
	}
	public void setHole_yc(float hole_yc) {
		this.hole_yc = hole_yc;
	}
	public float getStr_x() {
		return str_x;
	}
	public void setStr_x(float str_x) {
		this.str_x = str_x;
	}
	public float getStr_y() {
		return str_y;
	}
	public void setStr_y(float str_y) {
		this.str_y = str_y;
	}
	public float getHole_d() {
		return hole_d;
	}
	public void setHole_d(float hole_d) {
		this.hole_d = hole_d;
	}
	public float getHole_ratio() {
		return hole_ratio;
	}
	public void setHole_ratio(float hole_ratio) {
		this.hole_ratio = hole_ratio;
	}
	public Timestamp getPrd_dt() {
		return prd_dt;
	}
	public void setPrd_dt(Timestamp prd_dt) {
		this.prd_dt = prd_dt;
	}
		
}
