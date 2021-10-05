package mes.svc;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.ItemStockBean;
import static db.JDBCUtility.*;

public class StockItemService {

	public ArrayList<ItemStockBean> getItemTypeStockList(int no) {
		
		ArrayList<ItemStockBean> itemTypeStockList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		itemTypeStockList = mesDAO.selectStockList(no);
		close(conn);
		return itemTypeStockList;
	}

}
