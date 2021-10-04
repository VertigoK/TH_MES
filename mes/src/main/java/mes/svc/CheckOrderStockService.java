package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;

public class CheckOrderStockService {
	
	public ArrayList<Integer> getStockQuantity(int plant_cd, int item_cd) {
		
		ArrayList<Integer> stockQtys = new ArrayList<Integer>();
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		stockQtys = mesDAO.selectStockQuantity(plant_cd, item_cd);
		close(conn);
		
		return stockQtys;
		
	}

	public ArrayList<Integer> computeRequiredQuantity(int item_cd, int order_qty, ArrayList<Integer> stockQtys) {
		
		int[] k = {0, 0, 0, 0};
		switch(item_cd) {
			case 1: k = new int[] {1, 1, 2, 3}; break;	// multipliers for 제품, 자재1, 자재2, 자재3
			case 2: k = new int[] {1, 2, 3, 1}; break;	// multipliers for 제품, 자재1, 자재2, 자재3
			case 3: k = new int[] {1, 3, 1, 2}; break;	// multipliers for 제품, 자재1, 자재2, 자재3
		}
		
		ArrayList<Integer> requiredQtys = new ArrayList<Integer>();
		int temp = (int) Math.ceil((order_qty - stockQtys.get(0)) * 1.05);
		if(temp < 0) requiredQtys.add(0);
		else requiredQtys.add(temp);	// 제품 계획수량
		
		for(int i = 1; i < 4; i++) {
			int temp1 = (int) Math.ceil(k[i] * requiredQtys.get(0) * 1.05);
			int temp2 = temp1 - stockQtys.get(i);
			if(temp2 < 0) requiredQtys.add(0); 
			else requiredQtys.add(temp2);	// 자재1, 자재2, 자재3 발주수량
		}

		return requiredQtys;
		
	}

}
