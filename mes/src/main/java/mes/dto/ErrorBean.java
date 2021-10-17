package mes.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ErrorBean implements Serializable {
	
	private static final long serialVersionUID = -3296601443916209788L;
	
	private int error_cd;
	private String error_gd;
	private String error_msg;
	private int down_dr;
	private Timestamp update_dt;
	
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
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public int getDown_dr() {
		return down_dr;
	}
	public void setDown_dr(int down_dr) {
		this.down_dr = down_dr;
	}
	public Timestamp getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(Timestamp update_dt) {
		this.update_dt = update_dt;
	}
	
}
