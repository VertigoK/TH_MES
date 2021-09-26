package mes.dto;

import java.sql.Timestamp;

public class ProcessBean {
	
	private int plant_cd;
	private int line_cd;
	private String process_cd;
	private String process_nm;
	private String use_type;
	private boolean use_yn;
	private String remark;
	private Timestamp update_dt;
	
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
	public String getProcess_nm() {
		return process_nm;
	}
	public void setProcess_nm(String process_nm) {
		this.process_nm = process_nm;
	}
	public String getUse_type() {
		return use_type;
	}
	public void setUse_type(String use_type) {
		this.use_type = use_type;
	}
	public boolean isUse_yn() {
		return use_yn;
	}
	public void setUse_yn(boolean use_yn) {
		this.use_yn = use_yn;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(Timestamp update_dt) {
		this.update_dt = update_dt;
	}
	
}
