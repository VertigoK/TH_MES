package mes.svc;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.ItemInOutBean;
import mes.dto.ItemStockBean;
import static db.JDBCUtility.*;

public class StockService {

	public int[] getItemStockList() {
		
		int[] itemStockQtys = new int[12];
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		ArrayList<ItemStockBean> itemStockList = mesDAO.selectItemStockList();

		for(ItemStockBean itemStock: itemStockList) {
			if(itemStock.getStorage_cd() <= 8) {
				itemStockQtys[itemStock.getItem_cd() - 1] += itemStock.getItem_qty();
			} else {
				itemStockQtys[itemStock.getItem_cd() + 5] += itemStock.getItem_qty();
			}
		}
		
		close(conn);
		
		return itemStockQtys;

	}

	public ArrayList<ItemInOutBean> getItemInOutList() {
		
		ArrayList<ItemInOutBean> itemInOutList = new ArrayList<ItemInOutBean>();
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		itemInOutList = mesDAO.selectItemInOutList();
		close(conn);
		
		return itemInOutList;
		
	}
}
