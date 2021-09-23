package mes.dto;

public class QualityBean {
	
	private String serial_no;
	private String plant_cd;
	private String line_cd;
	private int worker_no;
	private boolean dimcheck_x;
	private boolean dimcheck_y;
	private boolean dimcheck_hx;
	private boolean dimcheck_wy;
	private boolean holecheck_d;
	private boolean holecheck_ratio;
	private boolean holecheck_xc;
	private boolean holecheck_yc;
	private boolean check_result;
	
	public String getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
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
	public int getWorker_no() {
		return worker_no;
	}
	public void setWorker_no(int worker_no) {
		this.worker_no = worker_no;
	}
	public boolean isDimcheck_x() {
		return dimcheck_x;
	}
	public void setDimcheck_x(boolean dimcheck_x) {
		this.dimcheck_x = dimcheck_x;
	}
	public boolean isDimcheck_y() {
		return dimcheck_y;
	}
	public void setDimcheck_y(boolean dimcheck_y) {
		this.dimcheck_y = dimcheck_y;
	}
	public boolean isDimcheck_hx() {
		return dimcheck_hx;
	}
	public void setDimcheck_hx(boolean dimcheck_hx) {
		this.dimcheck_hx = dimcheck_hx;
	}
	public boolean isDimcheck_wy() {
		return dimcheck_wy;
	}
	public void setDimcheck_wy(boolean dimcheck_wy) {
		this.dimcheck_wy = dimcheck_wy;
	}
	public boolean isHolecheck_d() {
		return holecheck_d;
	}
	public void setHolecheck_d(boolean holecheck_d) {
		this.holecheck_d = holecheck_d;
	}
	public boolean isHolecheck_ratio() {
		return holecheck_ratio;
	}
	public void setHolecheck_ratio(boolean holecheck_ratio) {
		this.holecheck_ratio = holecheck_ratio;
	}
	public boolean isHolecheck_xc() {
		return holecheck_xc;
	}
	public void setHolecheck_xc(boolean holecheck_xc) {
		this.holecheck_xc = holecheck_xc;
	}
	public boolean isHolecheck_yc() {
		return holecheck_yc;
	}
	public void setHolecheck_yc(boolean holecheck_yc) {
		this.holecheck_yc = holecheck_yc;
	}
	public boolean isCheck_result() {
		return check_result;
	}
	public void setCheck_result(boolean check_result) {
		this.check_result = check_result;
	}

}
