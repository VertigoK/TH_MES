package mes.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class MemberBean implements Serializable {
	
	private static final long serialVersionUID = -7915818711358881959L;
	
	private String mem_id;		// 사용자 ID
	private String mem_pw;		// 시용자 Password
	private String mem_nm;		// 사용자 이름
	private Timestamp mem_dt;	// 사용자 등록일
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_nm() {
		return mem_nm;
	}
	public void setMem_nm(String mem_nm) {
		this.mem_nm = mem_nm;
	}
	public Timestamp getMem_dt() {
		return mem_dt;
	}
	public void setMem_dt(Timestamp mem_dt) {
		this.mem_dt = mem_dt;
	}

}
