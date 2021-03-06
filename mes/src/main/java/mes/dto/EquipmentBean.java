package mes.dto;

import java.io.Serializable;

public class EquipmentBean implements Serializable {
	
	private static final long serialVersionUID = 5644907475213859079L;
	
	private int equip_id;
	private int plant_cd;
	private int line_cd;
	private String process_cd;
	private String equip_cd;
	private String equip_nm;
	private String equip_model;
	private int check_term;
	private String use_type;
	private boolean use_yn;
	private int error_cd;
	private int run_time;
	
	public int getEquip_id() {
		return equip_id;
	}
	public void setEquip_id(int equip_id) {
		this.equip_id = equip_id;
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
	public String getEquip_cd() {
		return equip_cd;
	}
	public void setEquip_cd(String equip_cd) {
		this.equip_cd = equip_cd;
	}
	public String getEquip_nm() {
		return equip_nm;
	}
	public void setEquip_nm(String equip_nm) {
		this.equip_nm = equip_nm;
	}
	public String getEquip_model() {
		return equip_model;
	}
	public void setEquip_model(String equip_model) {
		this.equip_model = equip_model;
	}
	public int getCheck_term() {
		return check_term;
	}
	public void setCheck_term(int check_term) {
		this.check_term = check_term;
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
	public int getError_cd() {
		return error_cd;
	}
	public void setError_cd(int error_cd) {
		this.error_cd = error_cd;
	}
	public int getRun_time() {
		return run_time;
	}
	public void setRun_time(int run_time) {
		this.run_time = run_time;
	}
	
}
