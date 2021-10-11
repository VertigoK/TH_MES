package mes.dto;

import java.io.Serializable;

public class ActionForward implements Serializable {
	
	private static final long serialVersionUID = -1034156968477997501L;
	
	private boolean isRedirect = false;
	private String path = null;
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
