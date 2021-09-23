package mes.dto;

import java.sql.Timestamp;

public class LineBean {
	
	private String plant_cd;
	private String line_cd;
	private String line_nm;
	private boolean use_yn;
	private String remark;
	private Timestamp update_dt;
	
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
	public String getLine_nm() {
		return line_nm;
	}
	public void setLine_nm(String line_nm) {
		this.line_nm = line_nm;
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
