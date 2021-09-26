package mes.dto;

public class WorkerBean {
	
	private int worker_no;
	private int plant_cd;
	private int line_cd;
	private String worker_loc;
	private String worker_time;
	private String worker_nm;
	
	public int getWorker_no() {
		return worker_no;
	}
	public void setWorker_no(int worker_no) {
		this.worker_no = worker_no;
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
	public String getWorker_loc() {
		return worker_loc;
	}
	public void setWorker_loc(String worker_loc) {
		this.worker_loc = worker_loc;
	}
	public String getWorker_time() {
		return worker_time;
	}
	public void setWorker_time(String worker_time) {
		this.worker_time = worker_time;
	}
	public String getWorker_nm() {
		return worker_nm;
	}
	public void setWorker_nm(String worker_nm) {
		this.worker_nm = worker_nm;
	}
	
}
