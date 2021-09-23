package mes.dto;

public class EquipmentBean {
	
	private String equip_id;
	private String plant_cd;
	private String line_cd;
	private String process_cd;
	private int error_cd;
	private String equip_nm;
	private String equip_model;
	private int check_term;
	private String use_type;
	private boolean use_yn;
	private float run_time;
	
	public String getEquip_id() {
		return equip_id;
	}
	public void setEquip_id(String equip_id) {
		this.equip_id = equip_id;
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
	public int getError_cd() {
		return error_cd;
	}
	public void setError_cd(int error_cd) {
		this.error_cd = error_cd;
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
	public float getRun_time() {
		return run_time;
	}
	public void setRun_time(float run_time) {
		this.run_time = run_time;
	}

}
