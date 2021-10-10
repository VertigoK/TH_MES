package mes.svc;

import java.sql.Connection;

import mes.dao.MESDAO;
import mes.dto.NoticeBean;
import static db.JDBCUtility.*;

public class NoticeDetailService {

	public NoticeBean getNotice(int notice_no) {
		
		NoticeBean notice = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		notice = mesDAO.detailNotice(notice_no);
		close(conn);
		
		return notice;
	}

	
}
