package mes.dto;

import java.sql.Timestamp;

public class ErrorLogBean {
	
	private int log_no;
	private String plant_cd;
	private String line_cd;
	private String process_cd;
	private String equip_id;
	private int err_cd;
	private String err_gd;
	private Timestamp start_dt;
	private Timestamp end_dt;
	
	public int getLog_no() {
		return log_no;
	}
	public void setLog_no(int log_no) {
		this.log_no = log_no;
	}
	public String getPlant_cd() {
		return plant_cd;
	}
	public void setPlant_cd(String plant_cd) {
		this.plant_cd = plant_cd;
	}
	public String getLine_cd() {
		return line_cd;
	}
	public void setLine_cd(String line_cd) {
		this.line_cd = line_cd;
	}
	public String getProcess_cd() {
		return process_cd;
	}
	public void setProcess_cd(String process_cd) {
		this.process_cd = process_cd;
	}
	public String getEquip_id() {
		return equip_id;
	}
	public void setEquip_id(String equip_id) {
		this.equip_id = equip_id;
	}
	public int getErr_cd() {
		return err_cd;
	}
	public void setErr_cd(int err_cd) {
		this.err_cd = err_cd;
	}
	public String getErr_gd() {
		return err_gd;
	}
	public void setErr_gd(String err_gd) {
		this.err_gd = err_gd;
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
