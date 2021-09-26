package mes.dto;

public class StorageBean {
	
	private int storage_cd;
	private String storage_nm;
	private int plant_cd;
	private String storage_type;
	private String storage_loc;
	
	public int getStorage_cd() {
		return storage_cd;
	}
	public void setStorage_cd(int storage_cd) {
		this.storage_cd = storage_cd;
	}
	public String getStorage_nm() {
		return storage_nm;
	}
	public void setStorage_nm(String storage_nm) {
		this.storage_nm = storage_nm;
	}
	public int getPlant_cd() {
		return plant_cd;
	}
	public void setPlant_cd(int plant_cd) {
		this.plant_cd = plant_cd;
	}
	public String getStorage_type() {
		return storage_type;
	}
	public void setStorage_type(String storage_type) {
		this.storage_type = storage_type;
	}
	public String getStorage_loc() {
		return storage_loc;
	}
	public void setStorage_loc(String storage_loc) {
		this.storage_loc = storage_loc;
	}
	
}
