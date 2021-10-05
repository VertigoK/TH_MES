package mes.dao;

import static db.JDBCUtility.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mes.dto.CustomerOrderBean;
import mes.dto.EquipmentBean;
import mes.dto.ItemStockBean;
import mes.dto.LineBean;
import mes.dto.MemberBean;
import mes.dto.OurOrderBean;
import mes.dto.ProductionBean;
import mes.dto.QualityBean;
import mes.dto.WorkOrderBean;

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
	
	// 로그인 ID 존재 여부 확인
	public boolean isIdAlready(String id) {

		boolean isIdAlready = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where mem_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) isIdAlready = true;
		} catch (SQLException e) {
			System.out.println("ID 존재 여부 확인 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return isIdAlready;
		
	}
	
	// 로그인 Password 매칭 확인
	public boolean isPasswordCorrect(String id, String pw) {
		
		boolean isPasswordCorrect = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where mem_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {		// ID가 존재하는 경우
				if(pw.equals(rs.getString("mem_pw"))) isPasswordCorrect = true;
			}
		} catch (SQLException e) {
			System.out.println("Password 매칭 확인 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		
		}
		return isPasswordCorrect;
		
	}
	
	// 사용자 정보 조회
	public MemberBean selectMember(String id) {
		
		MemberBean member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where mem_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member = new MemberBean();
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_pw(rs.getString("mem_pw"));
				member.setMem_nm(rs.getString("mem_nm"));
				member.setMem_dt(rs.getTimestamp("mem_dt"));
			}
		} catch (SQLException e) {
			System.out.println("사용자 정보 조회 실패! " + e.getMessage());
		}
		
		return member;
		
	}
	
	// 사용자 등록
	public int registerMember(MemberBean member) {
		
		int registerCount = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into member values(?,?,?,now())";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pw());
			pstmt.setString(3, member.getMem_nm());
			registerCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("사용자 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt);
		}			

		return registerCount;
		
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
				production.setSerial_no(rs.getString("serial_no"));
				production.setWo_no(rs.getInt("wo_no"));
				production.setPlant_cd(rs.getInt("plant_cd"));
				production.setLine_cd(rs.getInt("line_cd"));
				production.setItem_cd(rs.getInt("item_cd"));
				production.setWorker_no(rs.getInt("worker_no"));
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
				quality.setSerial_no(rs.getString("serial_no"));
				quality.setWo_no(rs.getInt("wo_no"));
				quality.setPlant_cd(rs.getInt("plant_cd"));
				quality.setLine_cd(rs.getInt("line_cd"));
				quality.setItem_cd(rs.getInt("item_cd"));
				quality.setWorker_no(rs.getInt("worker_no"));
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
	
	// 고객사 제품 주문 정보 등록 (cust_order 테이블)
	public ArrayList<Integer> insertCustOrder(int cust_cd, int plant_cd, int item_cd,
											  int order_qty, Date delivery_date) {
		
		ArrayList<Integer> insertCustOrderResult = new ArrayList<Integer>();
		int order_no = 0;
		int insertCustOrderCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "select max(order_no) from cust_order";
		String sql2 = "insert into cust_order(order_no, cust_cd, plant_cd, item_cd, order_qty, delivery_date) " +
					  "values(?,?,?,?,?,?)";
		
		try {
			// 1. 자동 생성된 주문번호 가져오기 (order_no 리턴)
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) order_no = rs.getInt(1) + 1;
			else order_no = 1;
			close(pstmt, rs);
			
			// 2. cust_order 테이블에 등록하기 (insertCustOrderCount 리턴)
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, order_no);
			pstmt.setInt(2, cust_cd);
			pstmt.setInt(3, plant_cd);
			pstmt.setInt(4, item_cd);
			pstmt.setInt(5, order_qty);
			pstmt.setDate(6, delivery_date);
			insertCustOrderCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("고객사 제품 주문 정보 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		insertCustOrderResult.add(order_no);
		insertCustOrderResult.add(insertCustOrderCount);
		
		return insertCustOrderResult;
		
	}
	
	// 자사 자재 발주 정보 등록 (our_order 테이블)
	public ArrayList<Integer> insertOurOrder(int cust_cd, int plant_cd, int item_cd, int order_qty) {
		
		ArrayList<Integer> insertOurOrderResult = new ArrayList<Integer>();
		int insertOurOrderCount = 0;
		int order_no = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "select max(order_no) from our_order";
		String sql2 = "insert into our_order(order_no, cust_cd, plant_cd, item_cd, order_qty) " +
					  "values(?,?,?,?,?)";
		
		try {
			// 1. 자동 생성된 주문번호 가져오기 (order_no 리턴)
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) order_no = rs.getInt(1) + 1;
			else order_no = 1;
			close(pstmt, rs);
			
			// 2. our_order 테이블에 등록하기 (insertOurOrderCount 리턴)
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, order_no);
			pstmt.setInt(2, cust_cd);
			pstmt.setInt(3, plant_cd);
			pstmt.setInt(4, item_cd);
			pstmt.setInt(5, order_qty);
			insertOurOrderCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("자사 자재 발주 정보 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		insertOurOrderResult.add(insertOurOrderCount);
		insertOurOrderResult.add(order_no);
		
		return insertOurOrderResult;
		
	}

	// '고객 제품 주문' 정보 조회 (w/ order_no)
	public CustomerOrderBean selectCustOrder(int order_no) {
		
		CustomerOrderBean custOrder = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from cust_order where order_no = " + order_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				custOrder = new CustomerOrderBean();
				custOrder.setOrder_no(rs.getInt("order_no"));
				custOrder.setCust_cd(rs.getInt("cust_cd"));
				custOrder.setPlant_cd(rs.getInt("plant_cd"));
				custOrder.setItem_cd(rs.getInt("item_cd"));
				custOrder.setOrder_qty(rs.getInt("order_qty"));
				custOrder.setOrder_date(rs.getDate("order_date"));
				custOrder.setDelivery_date(rs.getDate("delivery_date"));
				custOrder.setFinished_date(rs.getDate("finished_date"));
				custOrder.setOrder_status(rs.getBoolean("order_status"));
				custOrder.setDelayed_date(rs.getInt("delayed_date"));
			}
		} catch (SQLException e) {
			System.out.println("고객사 제품 주문 정보 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return custOrder;
		
	}
	
	// '자사 자재 발주' 정보 조회 (w/ order_no)
	public OurOrderBean selectOurOrder(int order_no) {
		
		OurOrderBean ourOrder = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from our_order where order_no = " + order_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ourOrder = new OurOrderBean();
				ourOrder.setOrder_no(rs.getInt("order_no"));
				ourOrder.setCust_cd(rs.getInt("cust_cd"));
				ourOrder.setPlant_cd(rs.getInt("plant_cd"));
				ourOrder.setItem_cd(rs.getInt("item_cd"));
				ourOrder.setOrder_qty(rs.getInt("order_qty"));
				ourOrder.setOrder_dt(rs.getTimestamp("order_dt"));
				ourOrder.setOrder_status(rs.getBoolean("order_status"));
			}
		} catch (SQLException e) {
			System.out.println("자사 자재 발주 정보 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return ourOrder;
		
	}

	// '고객 제품 주문' 정보 조회 (전체)
	public ArrayList<CustomerOrderBean> selectOrderInList() {
		
		ArrayList<CustomerOrderBean> orderInList = null;
		CustomerOrderBean orderIn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from cust_order";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				if(i == 0) {
					orderInList = new ArrayList<CustomerOrderBean>();	// 주문 내역이 없으면 orderInList = null 리턴
					i++;
				}
				orderIn = new CustomerOrderBean();
				orderIn.setOrder_no(rs.getInt("order_no"));
				orderIn.setCust_cd(rs.getInt("cust_cd"));
				orderIn.setPlant_cd(rs.getInt("plant_cd"));
				orderIn.setItem_cd(rs.getInt("item_cd"));
				orderIn.setOrder_qty(rs.getInt("order_qty"));
				orderIn.setOrder_date(rs.getDate("order_date"));
				orderIn.setDelivery_date(rs.getDate("delivery_date"));
				orderIn.setFinished_date(rs.getDate("finished_date"));
				orderIn.setOrder_status(rs.getBoolean("order_status"));
				orderIn.setDelayed_date(rs.getInt("delayed_date"));
				orderIn.setWo_status(rs.getBoolean("wo_status"));
				orderInList.add(orderIn);
			}
		} catch (SQLException e) {
			System.out.println("고객 제품 주문 정보 전체 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return orderInList;
		
	}

	// '자사 자재 발주' 정보 조회 (전체)
	public ArrayList<OurOrderBean> selectOrderOutList() {
		
		ArrayList<OurOrderBean> orderOutList = null;
		OurOrderBean orderOut = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from our_order";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next() ) {
				if(i == 0) {
					orderOutList = new ArrayList<OurOrderBean>();	// 주문 내역이 없으면 orderOutList = null 리턴
					i++;
				}
				orderOut = new OurOrderBean();
				orderOut.setOrder_no(rs.getInt("order_no"));
				orderOut.setCust_cd(rs.getInt("cust_cd"));
				orderOut.setPlant_cd(rs.getInt("plant_cd"));
				orderOut.setItem_cd(rs.getInt("item_cd"));
				orderOut.setOrder_qty(rs.getInt("order_qty"));
				orderOut.setOrder_dt(rs.getTimestamp("order_dt"));
				orderOut.setOrder_status(rs.getBoolean("order_status"));
				orderOutList.add(orderOut);
			}
		} catch (SQLException e) {
			System.out.println("자사 자재 발주 정보 전체 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return orderOutList;
		
	}

	// '제품과 자재 재고량' 조회 (w/ plant_cd, item_cd) 
	public ArrayList<Integer> selectStockQuantity(int plant_cd, int item_cd) {
		
		ArrayList<Integer> stockQtys = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "select item_qty from item_stock where item_cd = ? and storage_nm = ?";
		String sql2 = "select item_qty from item_stock where item_cd in (4,5,6) and storage_nm = ?";
		
		try {
			// 1. 제품 재고량
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, item_cd);
			pstmt.setString(2, "제품창고" + plant_cd);
			rs = pstmt.executeQuery();
			if(rs.next()) stockQtys.add(rs.getInt(1));
			close(pstmt, rs);
			
			// 2. 자재 재고량
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "자재창고" + plant_cd);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				stockQtys.add(rs.getInt(1));
			}
			close(pstmt, rs);
		} catch (SQLException e) {
			System.out.println("제품과 자재 재고량 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return stockQtys;
		
	}
	
	// '라인 현황' 조회 (w/ plant_cd)
	public ArrayList<LineBean> selectLineList(int plant_cd) {
		
		ArrayList<LineBean> lineList = new ArrayList<LineBean>();
		LineBean line = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from line where plant_cd = " + plant_cd;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				line = new LineBean();
				line.setLine_cd(rs.getInt("line_cd"));
				line.setPlant_cd(rs.getInt("plant_cd"));
				line.setLine_nm(rs.getString("line_nm"));
				line.setUse_yn(rs.getBoolean("use_yn"));
				line.setRemark(rs.getString("remark"));
				line.setUpdate_dt(rs.getTimestamp("update_dt"));
				lineList.add(line);
			}
		} catch (SQLException e) {
			System.out.println("라인 현황 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return lineList;
		
	}

	// '생산지시 현황' 조회 (w/ plant_cd)
	public ArrayList<WorkOrderBean> selectWorkOrderList(int plant_cd) {
		
		ArrayList<WorkOrderBean> workOrderList = null;
		WorkOrderBean workOrder = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from work_order where plant_cd = " + plant_cd;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				if(i == 0) {
					workOrderList = new ArrayList<WorkOrderBean>();	// 주문 내역이 없으면 workOrderList = null 리턴
					i++;
				}
				workOrder = new WorkOrderBean();
				workOrder.setWo_no(rs.getInt("wo_no"));
				workOrder.setPlant_cd(rs.getInt("plant_cd"));
				workOrder.setLine_cd(rs.getInt("line_cd"));
				workOrder.setOrder_no(rs.getInt("order_no"));
				workOrder.setItem_cd(rs.getInt("item_cd"));
				workOrder.setStart_date(rs.getDate("start_date"));
				workOrder.setStart_shift(rs.getString("start_shift"));
				workOrder.setEnd_date(rs.getDate("end_date"));
				workOrder.setEnd_shift(rs.getString("end_shift"));
				workOrder.setPlan_qty(rs.getInt("plan_qty"));
				workOrder.setFlag_end(rs.getBoolean("flag_end"));
				workOrderList.add(workOrder);
			}
		} catch (SQLException e) {
			System.out.println("생산지시 현황 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return workOrderList;
		
	}
	
	// '생산지시' 정보 등록 (work_order 테이블)
	public ArrayList<Integer> insertWorkOrder(int plant_cd, int line_cd, int order_no, int item_cd, 
			Date start_date, String start_shift, Date end_date, String end_shift, int plan_qty) {
		
		ArrayList<Integer> insertWorkOrderResult = new ArrayList<Integer>();
		int insertWorkOrderCount = 0;
		int wo_no = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "select max(wo_no) from work_order";
		String sql2 = "insert into work_order(wo_no, plant_cd, line_cd, order_no, item_cd, start_date, start_shift, end_date, end_shift, plan_qty) " +
					  "values(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			// 1. 자동 생성된 생산지시번호 가져오기 (wo_no 리턴)
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) wo_no = rs.getInt(1) + 1;
			else wo_no = 1;
			close(pstmt, rs);
			
			// 2. work_order 테이블에 등록하기 (insertWorkOrderCount 리턴)
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, wo_no);
			pstmt.setInt(2, plant_cd);
			pstmt.setInt(3, line_cd);
			pstmt.setInt(4, order_no);
			pstmt.setInt(5, item_cd);
			pstmt.setDate(6, start_date);
			pstmt.setString(7, start_shift);
			pstmt.setDate(8, end_date);
			pstmt.setString(9, end_shift);
			pstmt.setInt(10, plan_qty);
			insertWorkOrderCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("생산지시 정보 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		insertWorkOrderResult.add(insertWorkOrderCount);
		insertWorkOrderResult.add(wo_no);
		
		return insertWorkOrderResult;
		
	}

	// '생산지시' 정보 조회 (w/ wo_no)
	public WorkOrderBean selectWorkOrder(int wo_no) {
		
		WorkOrderBean workOrder = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from work_order where wo_no = " + wo_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				workOrder = new WorkOrderBean();
				workOrder.setWo_no(rs.getInt("wo_no"));
				workOrder.setPlant_cd(rs.getInt("plant_cd"));
				workOrder.setLine_cd(rs.getInt("line_cd"));
				workOrder.setOrder_no(rs.getInt("order_no"));
				workOrder.setItem_cd(rs.getInt("item_cd"));
				workOrder.setStart_date(rs.getDate("start_date"));
				workOrder.setStart_shift(rs.getString("start_shift"));
				workOrder.setEnd_date(rs.getDate("end_date"));
				workOrder.setEnd_shift(rs.getString("end_shift"));
				workOrder.setPlan_qty(rs.getInt("plan_qty"));
				workOrder.setFlag_end(rs.getBoolean("flag_end"));
			}
		} catch (SQLException e) {
			System.out.println("생산지시 정보 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return workOrder;
		
	}

	// 생산지시 생성시 cust_order 테이블 생산지시여부 업데이트
	public int updateCustOrder(int order_no) {
		
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "update cust_order set wo_status = 1 where order_no = " + order_no; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("생산지시 정보 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return updateCount;
		
	}

	// 자동 자재 발주 성공시 item_stock 테이블 업데이트 (자재 발주 = 자재 입고)
	public int updateItemStock(int plant_cd, int item_cd, int order_qty) {
		
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "update item_stock set item_qty = item_qty + ?, update_dt = now() " +
					 " where item_cd = ? and storage_nm = ?"; 
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_qty);
			pstmt.setInt(2, item_cd);
			pstmt.setString(3, "자재창고" + plant_cd);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("item_stock 테이블 업데이트 실패! " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return updateCount;
		
	}

	// '생산지시 현황' 전체 조회
	public ArrayList<WorkOrderBean> selectWorkOrderList() {
		
		ArrayList<WorkOrderBean> workOrderList = null;
		WorkOrderBean workOrder = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from work_order";;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				if(i == 0) {
					workOrderList = new ArrayList<WorkOrderBean>();	// 주문 내역이 없으면 workOrderList = null 리턴
					i++;
				}
				workOrder = new WorkOrderBean();
				workOrder.setWo_no(rs.getInt("wo_no"));
				workOrder.setPlant_cd(rs.getInt("plant_cd"));
				workOrder.setLine_cd(rs.getInt("line_cd"));
				workOrder.setOrder_no(rs.getInt("order_no"));
				workOrder.setItem_cd(rs.getInt("item_cd"));
				workOrder.setStart_date(rs.getDate("start_date"));
				workOrder.setStart_shift(rs.getString("start_shift"));
				workOrder.setEnd_date(rs.getDate("end_date"));
				workOrder.setEnd_shift(rs.getString("end_shift"));
				workOrder.setPlan_qty(rs.getInt("plan_qty"));
				workOrder.setFlag_end(rs.getBoolean("flag_end"));
				workOrderList.add(workOrder);
			}
		} catch (SQLException e) {
			System.out.println("생산지시 현황 전체 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return workOrderList;
		
	}


	// 재고 현황 전체 조회
	public ArrayList<ItemStockBean> totalStockList() {
		ArrayList<ItemStockBean> stockList = new ArrayList<ItemStockBean>();
		ItemStockBean itemStock = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from item_stock";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				itemStock = new ItemStockBean();
				itemStock.setStock_no(rs.getInt("stock_no"));
				itemStock.setItem_cd(rs.getInt("item_cd"));
				itemStock.setItem_type(rs.getString("item_type"));
				itemStock.setStorage_cd(rs.getInt("storage_cd"));
				itemStock.setStorage_nm(rs.getString("storage_nm"));
				itemStock.setItem_qty(rs.getInt("item_qty"));
				itemStock.setUpdate_dt(rs.getTimestamp("update_dt"));
				stockList.add(itemStock);
			}
		} catch (Exception e) {
			System.out.println("품목재고현황 조회 실패: "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		return stockList;
	}
	
	// 재고 현황 조회 (w/ no)
	public ArrayList<ItemStockBean> selectStockList(int no) {
		ArrayList<ItemStockBean> plantStockList = new ArrayList<ItemStockBean>();
		ItemStockBean itemStock = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from item_stock where ";
		switch (no) {
			case 1: sql += "storage_cd in (select storage_cd from storage where plant_cd = 1) order by storage_cd"; break;
			case 2: sql += "storage_cd in (select storage_cd from storage where plant_cd = 2) order by storage_cd"; break;
			case 3: sql += "item_type = '제품' order by storage_cd, item_cd"; break;
			case 4: sql += "item_type = '자재' order by storage_cd, item_cd"; break;
		}
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				itemStock = new ItemStockBean();
				itemStock.setStock_no(rs.getInt("stock_no"));
				itemStock.setItem_cd(rs.getInt("item_cd"));
				itemStock.setItem_type(rs.getString("item_type"));
				itemStock.setStorage_cd(rs.getInt("storage_cd"));
				itemStock.setStorage_nm(rs.getString("storage_nm"));
				itemStock.setItem_qty(rs.getInt("item_qty"));
				itemStock.setUpdate_dt(rs.getTimestamp("update_dt"));
				plantStockList.add(itemStock);
			}
		} catch (Exception e) {
			System.out.println("공장별 품목재고현황 조회 실패: "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		return plantStockList;
	}
	
	
	
	// Production Status 보기
	
	// Quality Status 보기
	
	// Equipment Status 보기
	
	// Inventory Status 보기
	
	// HR Status 보기
	

}
