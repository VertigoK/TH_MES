package mes.svc;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.ItemStockBean;
import static db.JDBCUtility.*;

public class StockPlantService {

	public ArrayList<ItemStockBean> getPlantStockList(int no) {
		
		ArrayList<ItemStockBean> plantStockList = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		plantStockList = mesDAO.selectStockList(no);
		close(conn);
		
		return plantStockList;
		
	}
}
