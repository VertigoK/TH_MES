package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;

import mes.dao.MESDAO;
import mes.dto.MemberBean;

public class SignUpService {
	
	public boolean isIdAlready(String id) {
			
		boolean isIdAlready = false;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		isIdAlready = mesDAO.isIdAlready(id);
		close(conn);
		
		return isIdAlready;
		
	}

	public boolean signUpMember(MemberBean member) {
	
		boolean isSignUpSuccess = false;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		int registerCount = mesDAO.registerMember(member);

		if(registerCount > 0) {
			commit(conn);
			isSignUpSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		return isSignUpSuccess;
		
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
