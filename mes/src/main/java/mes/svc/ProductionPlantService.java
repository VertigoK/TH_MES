package mes.svc;

import static db.JDBCUtility.*;

import java.sql.Connection;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.ProductionBean;

public class ProductionPlantService {

	public ArrayList<ProductionBean> getProductionList(String id, int no) {
		
		ArrayList<ProductionBean> productionList = null;
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		productionList = mesDAO.selectProductionList(id, no);
		close(conn);
		
		return productionList;
		
	}

}
