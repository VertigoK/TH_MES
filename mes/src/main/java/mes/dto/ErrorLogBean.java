package mes.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ErrorLogBean implements Serializable {
	
	private static final long serialVersionUID = -7150645102052965394L;
	
	private int log_no;
	private int plant_cd;
	private int line_cd;
	private String process_cd;
	private int equip_id;
	private int error_cd;
	private String error_gd;
	private Timestamp start_dt;
	private Timestamp end_dt;
	
	public int getLog_no() {
		return log_no;
	}
	public void setLog_no(int log_no) {
		this.log_no = log_no;
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
	public String getProcess_cd() {
		return process_cd;
	}
	public void setProcess_cd(String process_cd) {
		this.process_cd = process_cd;
	}
	public int getEquip_id() {
		return equip_id;
	}
	public void setEquip_id(int equip_id) {
		this.equip_id = equip_id;
	}
	public int getError_cd() {
		return error_cd;
	}
	public void setError_cd(int error_cd) {
		this.error_cd = error_cd;
	}
	public String getError_gd() {
		return error_gd;
	}
	public void setError_gd(String error_gd) {
		this.error_gd = error_gd;
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
	
}
