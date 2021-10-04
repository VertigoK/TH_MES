package mes.svc;

import static db.JDBCUtility.*;
import java.sql.Connection;

import mes.dao.MESDAO;
import mes.dto.MemberBean;

public class LogInService {
public boolean isIdAlready(String id) {
		
		boolean isIdAlready = false;
		
		Connection conn = getConnection();
		MESDAO 	mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		isIdAlready = mesDAO.isIdAlready(id);
		close(conn);
		
		return isIdAlready;
		
	}
	
	public boolean isPasswordCorrect(String id, String pw ) {
		
		boolean isPasswordCorrect = false;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		isPasswordCorrect = mesDAO.isPasswordCorrect(id, pw);
		close(conn);
		
		return isPasswordCorrect;
		
	}
	
	public MemberBean getMember(String id) {
		
		MemberBean member = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		member = mesDAO.selectMember(id);
		close(conn);
		
		return member;
		
	}

}
