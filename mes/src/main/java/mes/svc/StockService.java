package mes.svc;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.ItemInOutBean;
import mes.dto.ItemStockBean;
import static db.JDBCUtility.*;

public class StockService {

	public ArrayList<Integer> getStockTotalList() {
		
		ArrayList<ItemStockBean> stockList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		stockList = mesDAO.totalStockList();
		
		ArrayList<Integer> stockTotalList = new ArrayList<Integer>();
		int total1 = 0;
		int total2 = 0;
		int total3 = 0;
		int total4 = 0;
		int total5 = 0;
		int total6 = 0;
		int total7 = 0;
		int total8 = 0;
		int total9 = 0;
		int total10 = 0;
		int total11 = 0;
		int total12 = 0;
		for(ItemStockBean stock: stockList) {
			if(stock.getStorage_cd() <= 8) {
				switch(stock.getItem_cd()) {
					case 1:total1 += stock.getItem_qty(); break;
					case 2:total2 += stock.getItem_qty(); break;
					case 3:total3 += stock.getItem_qty(); break;
					case 4:total4 += stock.getItem_qty(); break;
					case 5:total5 += stock.getItem_qty(); break;
					case 6:total6 += stock.getItem_qty(); break;
				}
			}
			else {
				switch(stock.getItem_cd()) {
				case 1:total7 += stock.getItem_qty(); break;
				case 2:total8 += stock.getItem_qty(); break;
				case 3:total9 += stock.getItem_qty(); break;
				case 4:total10 += stock.getItem_qty(); break;
				case 5:total11 += stock.getItem_qty(); break;
				case 6:total12 += stock.getItem_qty(); break;
				}
			}
		}
		stockTotalList.add(total1);
		stockTotalList.add(total2);
		stockTotalList.add(total3);
		stockTotalList.add(total4);
		stockTotalList.add(total5);
		stockTotalList.add(total6);
		stockTotalList.add(total7);
		stockTotalList.add(total8);
		stockTotalList.add(total9);
		stockTotalList.add(total10);
		stockTotalList.add(total11);
		stockTotalList.add(total12);
		
//		stockTotalList.add(Arrays.asList(total1, total2, total3, total4, total5, total6, total7, total8, total9, total10, total11, total12)); // 타입이.. 안 맞는다고?
		
		close(conn);
		return stockTotalList;
		
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
