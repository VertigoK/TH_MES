package mes.dao;

import static db.JDBCUtility.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mes.dto.EquipmentBean;
import mes.dto.ProductionBean;
import mes.dto.QualityBean;

public class MESDAO {
	
	// Singleton Pattern
	private MESDAO() {}
	private static MESDAO mesDAO;
	public static MESDAO getInstance() {
		if(mesDAO == null) mesDAO = new MESDAO();
		return mesDAO;
	}
	
	// DB Connection
	Connection conn = null;
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	// Equipment List 조회 (공장별, 라인별, 공정별)
	public ArrayList<EquipmentBean> selectEquipmentList(String id, int no) {
		
		ArrayList<EquipmentBean> equipmentList = new ArrayList<EquipmentBean>();
		EquipmentBean equipment = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		if(!id.equals("process_cd")) {
			sql = "select * from equipment where " + id +  " = " + no;
		} else {
			String name = null;
			switch(no) {
				case 1: name = "'cut%'"; break;
				case 2: name = "'dril%'"; break;
				case 3: name = "'assem%'"; break;
				case 4: name = "'qc_cut%'"; break;
				case 5: name = "'qc_drill%'"; break;
			}
			sql = "select * from equipment where " + id + " like " + name;
		}
				
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				equipment = new EquipmentBean();
				equipment.setPlant_cd(rs.getInt("plant_cd"));
				equipment.setLine_cd(rs.getInt("line_cd"));
				equipment.setProcess_cd(rs.getString("process_cd"));
				equipment.setEquip_id(rs.getInt("equip_id"));
				equipment.setEquip_cd(rs.getString("equip_cd"));
				equipment.setEquip_nm(rs.getString("equip_nm"));
				equipment.setEquip_model(rs.getString("equip_model"));
				equipment.setCheck_term(rs.getInt("check_term"));
				equipment.setUse_type(rs.getString("use_type"));
				equipment.setUse_yn(rs.getBoolean("use_yn"));
				equipment.setError_cd(rs.getInt("error_cd"));
				equipment.setRun_time(rs.getFloat("run_time"));
				equipmentList.add(equipment);
			}
		} catch (SQLException e) {
			System.out.println("설비 현황 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return equipmentList;
		
	}
	
	// Production List 조회 (공장별, 라인별)
	public ArrayList<ProductionBean> selectProductionList(String id, int no) {
		
		ArrayList<ProductionBean> productionList = new ArrayList<ProductionBean>();
		ProductionBean production = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 테스트 용도로 20개만 조회하기 
		String sql = "select * from production where " + id +  " = " + no + " limit 20";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				production = new ProductionBean();
				production.setPlant_cd(rs.getInt("plant_cd"));
				production.setLine_cd(rs.getInt("line_cd"));
				production.setItem_cd(rs.getInt("item_cd"));
				production.setWorker_no(rs.getInt("worker_no"));
				production.setSerial_no(rs.getString("serial_no"));
				production.setDim_x(rs.getFloat("dim_x"));
				production.setDim_y(rs.getFloat("dim_y"));
				production.setDim_h(rs.getFloat("dim_h"));
				production.setDim_w(rs.getFloat("dim_w"));
				production.setHole_x(rs.getFloat("hole_x"));
				production.setHole_y(rs.getFloat("hole_y"));
				production.setHole_xc(rs.getFloat("hole_xc"));
				production.setHole_yc(rs.getFloat("hole_yc"));
				production.setStr_x(rs.getFloat("str_x"));
				production.setStr_y(rs.getFloat("str_y"));
				production.setHole_d(rs.getFloat("hole_d"));
				production.setHole_ratio(rs.getFloat("hole_ratio"));
				production.setPrd_dt(rs.getTimestamp("prd_dt"));
				productionList.add(production);
			}
			
		} catch (SQLException e) {
			System.out.println("생산 현황 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return productionList;
		
	}

	// Quality List 조회 (공장별, 라인별)
	public ArrayList<QualityBean> selectQualityList(String id, int no) {
		
		ArrayList<QualityBean> qualityList = new ArrayList<QualityBean>();
		QualityBean quality = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 테스트 용도로 20개만 조회하기 
		String sql = "select * from quality where " + id +  " = " + no + " limit 20";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				quality = new QualityBean();
				quality.setPlant_cd(rs.getInt("plant_cd"));
				quality.setLine_cd(rs.getInt("line_cd"));
				quality.setItem_cd(rs.getInt("item_cd"));
				quality.setWorker_no(rs.getInt("worker_no"));
				quality.setSerial_no(rs.getString("serial_no"));
				quality.setDimcheck_x(rs.getBoolean("dimcheck_x"));
				quality.setDimcheck_y(rs.getBoolean("dimcheck_y"));
				quality.setHolecheck_xc(rs.getBoolean("holecheck_xc"));
				quality.setHolecheck_yc(rs.getBoolean("holecheck_yc"));
				quality.setDimcheck_hx(rs.getBoolean("dimcheck_hx"));
				quality.setDimcheck_wy(rs.getBoolean("dimcheck_wy"));
				quality.setHolecheck_d(rs.getBoolean("holecheck_d"));
				quality.setHolecheck_ratio(rs.getBoolean("holecheck_ratio"));
				quality.setCheck_result(rs.getBoolean("check_result"));
				qualityList.add(quality);
			}
			
		} catch (SQLException e) {
			System.out.println("품질 현황 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return qualityList;
	}
	
	// Production Status 보기
	
	// Quality Status 보기
	
	// Equipment Status 보기
	
	// Inventory Status 보기
	
	// HR Status 보기
	

}
