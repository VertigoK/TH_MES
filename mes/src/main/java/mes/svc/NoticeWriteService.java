package mes.svc;

import java.sql.Connection;

import mes.dao.MESDAO;
import mes.dto.NoticeBean;

import static db.JDBCUtility.*;

public class NoticeWriteService {

	public boolean insertNotice(NoticeBean notice) {
		
		boolean isWriteSuccess = false;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		int writeCount = mesDAO.writeNotice(notice);
		
		if(writeCount > 0) {
			commit(conn);
			isWriteSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		return isWriteSuccess;
	}
}
