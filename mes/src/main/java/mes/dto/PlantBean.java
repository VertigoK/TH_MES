package mes.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class PlantBean implements Serializable {
	
	private static final long serialVersionUID = -4258648102365842181L;
	
	private int plant_cd;
	private String plant_nm;
	private Timestamp update_dt;
	
	public int getPlant_cd() {
		return plant_cd;
	}
	public void setPlant_cd(int plant_cd) {
		this.plant_cd = plant_cd;
	}
	public String getPlant_nm() {
		return plant_nm;
	}
	public void setPlant_nm(String plant_nm) {
		this.plant_nm = plant_nm;
	}
	public Timestamp getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(Timestamp update_dt) {
		this.update_dt = update_dt;
	}
	
}
