package mes.svc;

import static db.JDBCUtility.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import mes.dao.MESDAO;
import mes.dto.ProductionBean;
import mes.dto.QualityBean;
import mes.dto.WorkOrderBean;

public class ProductionGenerateService {

	public void registerProductionQualityData(WorkOrderBean workOrder, Timestamp start_dt, int worker_no) {
		
		// 1. generateData.py에 넘겨줄 매개변수 정의
		int wo_no = workOrder.getWo_no(); 
		int plant_cd = workOrder.getPlant_cd();
		int line_cd = workOrder.getLine_cd();
		int item_cd = workOrder.getItem_cd();
		int plan_qty = workOrder.getPlan_qty();
		
		String path = "python C:\\\\projects\\\\TH_MES\\\\DataGeneration\\\\generateData.py";
		String arg1 = " --prdInfo " + wo_no + " " + plant_cd + " " + line_cd + " " + item_cd + " " + worker_no;
		String arg2 = " --prdTime " + start_dt;
		String arg3 = " --plan_qty " + plan_qty;
		String pathArgs = path.concat(arg1).concat(arg2).concat(arg3);
		
		// 2. generateData.py 실행
		Process pr = null;
		try {
			pr = Runtime.getRuntime().exec(pathArgs);
			pr.getErrorStream().close();
		    pr.getInputStream().close();
		    pr.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<ProductionBean> getProductionDataList(int wo_no) {
		
		ArrayList<ProductionBean> productionDataList = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		productionDataList = mesDAO.selectProductionDataList(wo_no);
		close(conn);
		
		return productionDataList;
		
	}

	public ArrayList<QualityBean> getQualityDataList(int wo_no) {
		
		ArrayList<QualityBean> qualityDataList = null;
		
		Connection conn = getConnection();
		MESDAO mesDAO = MESDAO.getInstance();
		mesDAO.setConnection(conn);
		
		qualityDataList = mesDAO.selectQualityDataList(wo_no);
		close(conn);
		
		return qualityDataList;
		
	}
	
}
