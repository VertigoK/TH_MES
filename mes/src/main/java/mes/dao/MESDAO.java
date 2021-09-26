package mes.dao;

import java.sql.Connection;

public class MESDAO {
	
	// Singleton Pattern
	private MESDAO() {}
	private static MESDAO mesDAO;
	public static MESDAO getInstance() {
		if(mesDAO == null) mesDAO = new MESDAO();
		return mesDAO;
	}
	
	// DB Connection
	Connection conn = null;
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	// Production Status 보기
	
	// Quality Status 보기
	
	// Equipment Status 보기
	
	// Inventory Status 보기
	
	// HR Status 보기
	

}
