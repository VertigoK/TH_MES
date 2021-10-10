package mes.svc;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.NoticeBean;
import static db.JDBCUtility.*;

public class NoticeService {

	public int getListCount() {
		
		int listCount = 0;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		listCount = mesDAO.selectListCount();
		close(conn);
		return listCount;
	}

	public ArrayList<NoticeBean> getNoticeList(int page, int limit) {
		
		ArrayList<NoticeBean> noticeList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		noticeList = mesDAO.selectNoticeList(page, limit);
		close(conn);
		return noticeList;
	}
	
	public ArrayList<NoticeBean> recentNotice() {
		
		ArrayList<NoticeBean> recentNotice = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		recentNotice = mesDAO.selectNoticeList(1, 8);
		close(conn);
		
		return recentNotice;
	}

}
