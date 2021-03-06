package mes.dao;

import static db.JDBCUtility.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import mes.dto.CustomerOrderBean;
import mes.dto.EquipmentBean;
import mes.dto.ErrorBean;
import mes.dto.ErrorLogBean;
import mes.dto.ItemInOutBean;
import mes.dto.ItemStockBean;
import mes.dto.LineBean;
import mes.dto.MemberBean;
import mes.dto.NoticeBean;
import mes.dto.OurOrderBean;
import mes.dto.ProcessBean;
import mes.dto.ProductionBean;
import mes.dto.ProductionHistoryBean;
import mes.dto.QualityBean;
import mes.dto.WorkOrderBean;
import mes.dto.WorkerBean;

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
	
	// '설비(equipment)' 목록 조회 (공장별, 라인별, 공정별)
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
				equipment.setRun_time(rs.getInt("run_time"));
				equipmentList.add(equipment);
			}
		} catch (SQLException e) {
			System.out.println("설비 목록 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return equipmentList;
		
	}
	
	// '생산정보(production)' 목록 조회 (공장별, 라인별)
	public ArrayList<ProductionBean> selectProductionList(String id, int no) {
		
		ArrayList<ProductionBean> productionList = new ArrayList<ProductionBean>();
		ProductionBean production = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 100개 조회하기 
		String sql = "select * from production where " + id +  " = " + no + " limit 100";
		
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
			System.out.println("생산정보 목록 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return productionList;
		
	}

	// 품질검사정보(quality) 목록 조회 (공장별, 라인별)
	public ArrayList<QualityBean> selectQualityList(String id, int no) {
		
		ArrayList<QualityBean> qualityList = new ArrayList<QualityBean>();
		QualityBean quality = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 100개 조회하기 
		String sql = "select * from quality where " + id +  " = " + no + " limit 100";
		
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
			System.out.println("품질검사정보 목록 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return qualityList;
	}
	
	// 고객사 '제품 주문(cust_order)' 데이터 등록
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
			System.out.println("고객사 제품 주문 데이터 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		insertCustOrderResult.add(order_no);
		insertCustOrderResult.add(insertCustOrderCount);
		
		return insertCustOrderResult;
		
	}
	
	// 자사 '자재 발주(our_order)' 데이터 등록
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
			System.out.println("자사 자재 발주 데이터 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		insertOurOrderResult.add(insertOurOrderCount);
		insertOurOrderResult.add(order_no);
		
		return insertOurOrderResult;
		
	}

	// 고객 '제품 주문' 목록 조회 (w/ order_no)
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
				custOrder.setDelayed_days(rs.getInt("delayed_days"));
				custOrder.setWo_status(rs.getBoolean("wo_status"));
				custOrder.setOurorder_status(rs.getBoolean("ourorder_status"));
			}
		} catch (SQLException e) {
			System.out.println("고객사 제품 주문 목록 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return custOrder;
		
	}
	
	// 자사 '자재 발주' 목록 조회 (w/ order_no)
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
			System.out.println("자사 자재 발주 목록 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return ourOrder;
		
	}

	// 고객 '제품 주문' 목록 전체 조회
	public ArrayList<CustomerOrderBean> selectOrderInList() {
		
		ArrayList<CustomerOrderBean> orderInList = null;
		CustomerOrderBean orderIn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from cust_order order by order_no desc";
		
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
				orderIn.setDelayed_days(rs.getInt("delayed_days"));
				orderIn.setWo_status(rs.getBoolean("wo_status"));
				orderIn.setOurorder_status(rs.getBoolean("ourorder_status"));
				orderInList.add(orderIn);
			}
		} catch (SQLException e) {
			System.out.println("고객 제품 주문 목록 전체 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return orderInList;
		
	}

	// 자사 '자재 발주' 목록 전체 조회
	public ArrayList<OurOrderBean> selectOrderOutList() {
		
		ArrayList<OurOrderBean> orderOutList = null;
		OurOrderBean orderOut = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from our_order order by order_no desc";
		
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
			System.out.println("자사 자재 발주 목록 전체 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return orderOutList;
		
	}

	// '제품 재고량' 및 '자재 재고량' 조회 (w/ plant_cd, item_cd) 
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
			System.out.println("제품 재고량 및 자재 재고량 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return stockQtys;
		
	}
	
	// '라인(line)' 목록 조회 (w/ plant_cd)
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
			System.out.println("라인 목록 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return lineList;
		
	}

	// '생산지시(work_order)' 목록 조회 (w/ plant_cd)
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
			System.out.println("생산지시 목록 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return workOrderList;
		
	}
	
	// '생산지시(work_order)' 데이터 등록
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
			System.out.println("생산지시 데이터 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		insertWorkOrderResult.add(insertWorkOrderCount);
		insertWorkOrderResult.add(wo_no);
		
		return insertWorkOrderResult;
		
	}

	// '생산지시' 목록 조회 (w/ wo_no)
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
			System.out.println("생산지시 목록 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return workOrder;
		
	}

	// cust_order 테이블 생산지시여부 or 자재발주여부 업데이트
	public int updateCustOrder(int order_no, String status) {
		
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "update cust_order set " + status +  " = 1 where order_no = " + order_no; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("제품 주문 테이블 " + status + " 업데이트 실패! " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return updateCount;
		
	}

	// '생산지시(work_order)' 목록 전체 조회
	public ArrayList<WorkOrderBean> selectWorkOrderList() {
		
		ArrayList<WorkOrderBean> workOrderList = null;
		WorkOrderBean workOrder = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from work_order order by wo_no desc";;
		
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


	// '품목 재고 현황' 목록 전체 조회
	public ArrayList<ItemStockBean> selectItemStockList() {
		
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
			System.out.println("품목 재고 현황 전체 조회 실패! "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return stockList;
		
	}
	
	// 공장별 '품목 재고 현황' 목록 조회 (w/ no)
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
			System.out.println("공장별 품목 재고 현황 조회 실패! "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return plantStockList;
		
	}

	// '근무자번호' 조회 (w/ line_cd, start_shift)
	public int selectWorkerNo(int line_cd, String start_shift) {
		
		int worker_no = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select worker_no from worker where line_cd = ? " +
					 " and worker_loc = '생산' and worker_time = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, line_cd);
			pstmt.setString(2, start_shift);
			rs = pstmt.executeQuery();
			if(rs.next()) worker_no = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("근무자 번호 조회 실패! "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return worker_no;
		
	}

	// '생산정보(production)' 목록 조회 (w/ wo_no)
	public ArrayList<ProductionBean> selectProductionDataList(int wo_no) {
		
		ArrayList<ProductionBean> productionDataList = null; 
		ProductionBean productionData = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from production where wo_no = " + wo_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
					
			int i = 0;
			while(rs.next()) {
				if(i == 0) {
					productionDataList = new ArrayList<ProductionBean>();	// 조회 결과가 없으면 productionDataList = null 리턴
					i++;
				}
				productionData = new ProductionBean();
				productionData.setSerial_no(rs.getString("serial_no"));
				productionData.setWo_no(rs.getInt("wo_no"));
				productionData.setPlant_cd(rs.getInt("plant_cd"));
				productionData.setLine_cd(rs.getInt("line_cd"));
				productionData.setItem_cd(rs.getInt("item_cd"));
				productionData.setWorker_no(rs.getInt("worker_no"));
				productionData.setDim_x(rs.getFloat("dim_x"));
				productionData.setDim_y(rs.getFloat("dim_y"));
				productionData.setDim_h(rs.getFloat("dim_h"));
				productionData.setDim_w(rs.getFloat("dim_w"));
				productionData.setHole_x(rs.getFloat("hole_x"));
				productionData.setHole_y(rs.getFloat("hole_y"));
				productionData.setHole_xc(rs.getFloat("hole_xc"));
				productionData.setHole_yc(rs.getFloat("hole_yc"));
				productionData.setStr_x(rs.getFloat("str_x"));
				productionData.setStr_y(rs.getFloat("str_y"));
				productionData.setHole_d(rs.getFloat("hole_d"));
				productionData.setHole_ratio(rs.getFloat("hole_ratio"));
				productionData.setPrd_dt(rs.getTimestamp("prd_dt"));
				productionDataList.add(productionData);
			}
		} catch (Exception e) {
			System.out.println("생산정보 목록 조회 실패! "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return productionDataList;
		
	}
		
	// '품질검사정보(quality)' 목록 조회 (w/ wo_no)
	public ArrayList<QualityBean> selectQualityDataList(int wo_no) {
		
		ArrayList<QualityBean> qualityDataList = null;
		QualityBean qualityData = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from quality where wo_no = " + wo_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				if(i == 0) {
					qualityDataList = new ArrayList<QualityBean>();	// 조회 결과가 없으면 qualityDataList = null 리턴
					i++;
				}
				qualityData = new QualityBean();
				qualityData.setSerial_no(rs.getString("serial_no"));
				qualityData.setWo_no(rs.getInt("wo_no"));
				qualityData.setPlant_cd(rs.getInt("plant_cd"));
				qualityData.setLine_cd(rs.getInt("line_cd"));
				qualityData.setItem_cd(rs.getInt("item_cd"));
				qualityData.setWorker_no(rs.getInt("worker_no"));
				qualityData.setDimcheck_x(rs.getBoolean("dimcheck_x"));
				qualityData.setDimcheck_y(rs.getBoolean("dimcheck_y"));
				qualityData.setHolecheck_xc(rs.getBoolean("holecheck_xc"));
				qualityData.setHolecheck_yc(rs.getBoolean("holecheck_yc"));
				qualityData.setDimcheck_hx(rs.getBoolean("dimcheck_hx"));
				qualityData.setDimcheck_wy(rs.getBoolean("dimcheck_wy"));
				qualityData.setHolecheck_d(rs.getBoolean("holecheck_d"));
				qualityData.setHolecheck_ratio(rs.getBoolean("holecheck_ratio"));
				qualityData.setCheck_result(rs.getBoolean("check_result"));
				qualityDataList.add(qualityData);
			}
		} catch (Exception e) {
			System.out.println("품질검사정보 목록 조회 실패! "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return qualityDataList;
		
	}
	
	// 품질검사정보(quality) 투입수량(in_qty), 배출수량(out_qty), NG수량(ng_qty) 조회 (w/ wo_no)
	public int[] selectGoodBadQuantity(int wo_no) {
		
		int[] qtys = new int[3];
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "select count(*) from quality where wo_no = " + wo_no;
		String sql2 = "select count(*) from quality where check_result = 0 and wo_no = " + wo_no;
		
		try {
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) qtys[0] = rs.getInt(1);
			close(pstmt, rs);
			
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if(rs.next()) qtys[2] = rs.getInt(1);
			close(pstmt, rs);
			
			qtys[1] = qtys[0] - qtys[2];
		} catch (Exception e) {
			System.out.println("품질검사 투입수량/배출수량/NG수량 조회 실패! "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return qtys;
		
	}
	
	// '생산이력(production_hist)' 데이터 등록
	public int insertProductionHistory(int wo_no, int plant_cd, int line_cd, int item_cd,
			Timestamp start_dt, Timestamp end_dt, int in_qty, int out_qty, int ng_qty) {
		
		int insertProductionHistoryCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into production_hist(wo_no, plant_cd, line_cd, item_cd, start_dt, end_dt, in_qty, out_qty, ng_qty) " +
					 "values(?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, wo_no);
			pstmt.setInt(2, plant_cd);
			pstmt.setInt(3, line_cd);
			pstmt.setInt(4, item_cd);
			pstmt.setTimestamp(5, start_dt);
			pstmt.setTimestamp(6, end_dt);
			pstmt.setInt(7, in_qty);
			pstmt.setInt(8, out_qty);
			pstmt.setInt(9, ng_qty);
			insertProductionHistoryCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("생산이력 데이터 등록 실패!"+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return insertProductionHistoryCount;
		
	}

	// '설비(equipment)' 테이블 '가동시간(run_time)' 업데이트
	public int updateEquipmentRunTime(int line_cd, int run_time) {
		
		int updateEquipmentRunTimeCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "update equipment set run_time = run_time + ? where line_cd = " + line_cd;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, run_time);
			updateEquipmentRunTimeCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("설비 가동시간 업데이트 실패!"+ e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return updateEquipmentRunTimeCount;
		
	}

	// '설비(equipment)' 목록 전체 조회
	public ArrayList<EquipmentBean> selectEquipmentList() {
		
		ArrayList<EquipmentBean> equipmentList = null;
		EquipmentBean equipment = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from equipment";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				if(i == 0) {
					equipmentList = new ArrayList<EquipmentBean>();	// 조회 결과가 없으면 equipmentList = null 리턴
					i++;
				}
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
				equipment.setRun_time(rs.getInt("run_time"));
				equipmentList.add(equipment);
			}
		} catch (SQLException e) {
			System.out.println("설비 목록 전체 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return equipmentList;
		
	}

	// '생산이력(production_hist)' 목록 조회 (w/ wo_no)
	public ProductionHistoryBean selectProductionHistory(int wo_no) {
		
		ProductionHistoryBean productionHistory = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from production_hist where wo_no = " + wo_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				productionHistory = new ProductionHistoryBean();
				productionHistory.setWo_no(rs.getInt("wo_no"));
				productionHistory.setPlant_cd(rs.getInt("plant_cd"));
				productionHistory.setLine_cd(rs.getInt("line_cd"));
				productionHistory.setItem_cd(rs.getInt("item_cd"));
				productionHistory.setWo_seq(rs.getInt("wo_seq"));
				productionHistory.setStart_dt(rs.getTimestamp("start_dt"));
				productionHistory.setEnd_dt(rs.getTimestamp("end_dt"));
				productionHistory.setIn_qty(rs.getInt("in_qty"));
				productionHistory.setOut_qty(rs.getInt("out_qty"));
				productionHistory.setNg_qty(rs.getInt("ng_qty"));
			}
		} catch (SQLException e) {
			System.out.println("생산 이력 목록 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return productionHistory;
		
	}

	// '품목 입출고(item_io)' 테이블에 제품/자재 입출고 등록
	public int insertItemInOut(int plant_cd, int item_cd, String item_type, Timestamp inout_dt, String inout_type,
			int storage_from, String storage_from_nm, int storage_to, String storage_to_nm, int cust_cd, int item_cnt) {
		
		int insertItemInOutCount = 0;
		int inout_cd = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "select max(inout_cd) from item_io";
		String sql2 = "insert into item_io values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			// 1. 자동 생성된 입출고코드 가져오기
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) inout_cd = rs.getInt(1) + 1;
			else inout_cd = 1;
			close(pstmt, rs);
			
			// 2. item_io 테이블에 등록하기
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, inout_cd);
			pstmt.setInt(2, plant_cd);
			pstmt.setInt(3, item_cd);
			pstmt.setString(4, item_type);
			pstmt.setTimestamp(5, inout_dt);
			pstmt.setString(6, inout_type);
			pstmt.setInt(7, storage_from);
			pstmt.setString(8, storage_from_nm);
			pstmt.setInt(9, storage_to);
			pstmt.setString(10, storage_to_nm);
			pstmt.setInt(11, cust_cd);
			pstmt.setInt(12, item_cnt);
			insertItemInOutCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("품목 입출고에 제품/자재 입출고 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return insertItemInOutCount;
		
	}

	// 품목 재고 현황(item_stock) 테이블 업데이트
	public int updateItemStock(int item_cd, int storage_cd, int item_cnt, Timestamp update_dt) {
		
		int updateCount = 0;
		
		PreparedStatement pstmt = null;		
		String sql = "update item_stock set item_qty = item_qty + ?, update_dt = ? " +
				 " where item_cd = ? and storage_cd = ?"; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item_cnt);
			pstmt.setTimestamp(2, update_dt);
			pstmt.setInt(3, item_cd);
			pstmt.setInt(4, storage_cd);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("품목 재고 현황 업데이트 실패! " + e.getMessage());
		} finally {
			close(pstmt);
		}
	
		return updateCount;
		
	}

	// 생산지시(work_order) 테이블 업데이트
	public int updateWorkOrder(int wo_no) {
		
		int updateWorkOrderCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "update work_order set flag_end = ? where wo_no = " + wo_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			updateWorkOrderCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("생산 지시 업데이트 실패! " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return updateWorkOrderCount;
		
	}

	// 제품이 고객사로 출고시 cust_order 업데이트 
	public int updateCustOrder(int order_no, Date finished_date, int delayed_days) {
		
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "update cust_order set finished_date = ?, order_status = ?, delayed_days = ? " +
				" where order_no = " + order_no; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, finished_date);
			pstmt.setBoolean(2, true);
			pstmt.setInt(3, delayed_days);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("제품 주문 테이블 생산지시여부 업데이트 실패! " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return updateCount;
		
	}
	
	// HR Status 보기
	public ArrayList<WorkerBean> selectWorkerList() {
		ArrayList<WorkerBean> workerList = new ArrayList<WorkerBean>();
		WorkerBean worker = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from worker";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				worker = new WorkerBean();
				worker.setWorker_no(rs.getInt("worker_no"));
				worker.setPlant_cd(rs.getInt("plant_cd"));
				worker.setLine_cd(rs.getInt("line_cd"));
				worker.setWorker_loc(rs.getString("worker_loc"));
				worker.setWorker_time(rs.getString("worker_time"));
				worker.setWorker_nm(rs.getString("worker_nm"));
				workerList.add(worker);
			}
		} catch (Exception e) {
			System.out.println("근무자 조회 실패: "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return workerList;
		
	}

	public ArrayList<WorkerBean> selectHRList(String id, int no) {
		
		ArrayList<WorkerBean> hrPlantList = new ArrayList<WorkerBean>();
		WorkerBean workerBean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		if (!id.equals("worker_loc")) {
			sql = "select * from worker where " + id + " = " + no;
		} else {
			switch (no) {
				case 1: sql = "select * from worker where worker_loc = '생산'"; break;
				case 2: sql = "select * from worker where worker_loc = '품질검사'"; break;
			}
		}
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				workerBean = new WorkerBean();
				workerBean.setWorker_no(rs.getInt("worker_no"));
				workerBean.setPlant_cd(rs.getInt("plant_cd"));
				workerBean.setLine_cd(rs.getInt("line_cd"));
				workerBean.setWorker_loc(rs.getString("worker_loc"));
				workerBean.setWorker_time(rs.getString("worker_time"));
				workerBean.setWorker_nm(rs.getString("worker_nm"));
				hrPlantList.add(workerBean);
			}
		} catch (Exception e) {
			System.out.println("근무자 조회 실패: "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return hrPlantList;
		
	}

	// 공지사항 보기
	public int selectListCount() {
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select COUNT(*) from notice";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("공자사항 개수 가져오기 실패: "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return listCount;
		
	}

	public ArrayList<NoticeBean> selectNoticeList(int page, int limit) {
		
		ArrayList<NoticeBean> noticeList = new ArrayList<NoticeBean>();
		NoticeBean notice = null;
		SimpleDateFormat df = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from notice order by notice_no desc limit ?," + limit;
		
		int startRow = (page - 1) * limit;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				notice = new NoticeBean();
				notice.setNotice_no(rs.getInt("notice_no"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setDate(df.format(rs.getTimestamp("date")));
				noticeList.add(notice);
			}
		} catch (Exception e) {
			System.out.println("공자사항 조회 실패: "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return noticeList;
		
	}

	public NoticeBean detailNotice(int notice_no) {
		
		NoticeBean notice = null;
		SimpleDateFormat df = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from notice where notice_no = " + notice_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				notice = new NoticeBean();
				notice.setNotice_no(rs.getInt("notice_no"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setDate(df.format(rs.getTimestamp("date")));
			}
		} catch (Exception e) {
			System.out.println("공자사항 내용조회 실패: "+ e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return notice;
		
	}

	public int writeNotice(NoticeBean notice) {
		
		int writeCount = 0;
		int notice_no = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql1 = "select max(notice_no) from notice";
		String sql2 = "insert into notice(notice_no, title, content) values(?,?,?)";
		
		try {
			// 자동 생성된 공지작성번호 가져오기 (1번부터 생성)
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) notice_no = rs.getInt(1) + 1;
			else notice_no = 1;
			close(pstmt, rs);
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, notice_no);
			pstmt.setString(2, notice.getTitle());
			pstmt.setString(3, notice.getContent());
			writeCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("공지사항 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return writeCount;
		
	}

	// 생산 예약자재(reserved_item) 테이블에서 생산 예약된 자재별 수량 조회 (w/ plant_cd)
	public int[] selectReservedQuantity(int plant_cd) {
		
		int[] reservedQtys = {0, 0, 0};
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sum(item4_qty), sum(item5_qty), sum(item6_qty) from reserved_item where used_yn = 0 and plant_cd = " + plant_cd; 
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reservedQtys[0] = rs.getInt(1);
				reservedQtys[1] = rs.getInt(2);
				reservedQtys[2] = rs.getInt(3);
			}
			close(pstmt, rs);
		} catch (SQLException e) {
			System.out.println("생산 예약된 자재별 수량 조회 실패!" + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return reservedQtys;

	}

	// 자재 발주 후 입고시 생산 예약 자재(reserved_item) 테이블에 등록
	public int insertReservedItem(int order_no, int plant_cd, int item_cd, int plan_qty, int qty1, int qty2, int qty3) {
		
		int insertReservedItemCount = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into reserved_item values(?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_no);
			pstmt.setInt(2, plant_cd);
			pstmt.setInt(3, item_cd);
			pstmt.setInt(4, plan_qty);
			pstmt.setInt(5, qty1);
			pstmt.setInt(6, qty2);
			pstmt.setInt(7, qty3);
			pstmt.setBoolean(8, false);
			insertReservedItemCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("생산 예약된 자재의 등록 실패!" + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return insertReservedItemCount;
		
	}

	// 예약 자재가 생산에 사용시 생산 예약 자재(reserved_item) 테이블의 사용여부 업데이트
	public int updateReservedItem(int order_no) {
		
		int updateReservedItemCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update reserved_item set used_yn = ? where order_no = " + order_no;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			updateReservedItemCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("생산 예약 자재 업데이트 실패! " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return updateReservedItemCount;

	}

	// 품목 입출고(item_io) 전체 조회
	public ArrayList<ItemInOutBean> selectItemInOutList() {
		
		ArrayList<ItemInOutBean> itemInOutList = null;
		ItemInOutBean itemInOut = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from item_io order by inout_cd desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				if(i == 0) {
					itemInOutList = new ArrayList<ItemInOutBean>();	// 품목 입출고 내역이 없으면 null 리턴
					i++;
				}
				itemInOut = new ItemInOutBean();
				itemInOut.setInout_cd(rs.getInt("inout_cd"));
				itemInOut.setPlant_cd(rs.getInt("plant_cd"));
				itemInOut.setItem_cd(rs.getInt("item_cd"));
				itemInOut.setItem_type(rs.getString("item_type"));
				itemInOut.setInout_dt(rs.getTimestamp("inout_dt"));
				itemInOut.setInout_type(rs.getString("inout_type"));
				itemInOut.setStorage_from(rs.getInt("storage_from"));
				itemInOut.setStorage_from_nm(rs.getString("storage_from_nm"));
				itemInOut.setStorage_to(rs.getInt("storage_to"));
				itemInOut.setStorage_to_nm(rs.getString("storage_to_nm"));
				itemInOut.setCust_cd(rs.getInt("cust_cd"));
				itemInOut.setItem_cnt(rs.getInt("item_cnt"));;
				itemInOutList.add(itemInOut);
			}
		} catch (SQLException e) {
			System.out.println("품목 입출고 목록 전체 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return itemInOutList;
	}

	// 현재일이 작업 시작일과 종료일 사이에 있는 생산 지시 목록 조회
	public ArrayList<WorkOrderBean> selectWorkOrder(Date todayDate) {
		
		ArrayList<WorkOrderBean> workOrderTodayList = new ArrayList<WorkOrderBean>();
		WorkOrderBean workOrderToday = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from work_order where start_date <= ? and end_date >= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, todayDate);
			pstmt.setDate(2, todayDate);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				workOrderToday = new WorkOrderBean();
				workOrderToday.setWo_no(rs.getInt("wo_no"));
				workOrderToday.setPlant_cd(rs.getInt("plant_cd"));
				workOrderToday.setLine_cd(rs.getInt("line_cd"));
				workOrderToday.setOrder_no(rs.getInt("order_no"));
				workOrderToday.setItem_cd(rs.getInt("item_cd"));
				workOrderToday.setStart_date(rs.getDate("start_date"));
				workOrderToday.setStart_shift(rs.getString("start_shift"));
				workOrderToday.setEnd_date(rs.getDate("end_date"));
				workOrderToday.setEnd_shift(rs.getString("end_shift"));
				workOrderToday.setPlan_qty(rs.getInt("plan_qty"));
				workOrderToday.setFlag_end(rs.getBoolean("flag_end"));
				workOrderTodayList.add(workOrderToday);
			}
		} catch (SQLException e) {
			System.out.println("금일 생산 지시 목록 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return workOrderTodayList;
		
	}

	// 공정(process) 테이블에서 사용 불가 목록 조회
	public ArrayList<ProcessBean> selectProcessList() {
		
		ArrayList<ProcessBean> processList = new ArrayList<ProcessBean>();
		ProcessBean process = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from process where use_yn = 0";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				process = new ProcessBean();
				process.setPlant_cd(rs.getInt("plant_cd"));
				process.setLine_cd(rs.getInt("line_cd"));
				process.setProcess_cd(rs.getString("process_cd"));
				process.setProcess_nm(rs.getString("process_nm"));
				process.setUse_type(rs.getString("use_type"));
				process.setUse_yn(rs.getBoolean("use_yn"));
				process.setRemark(rs.getString("remark"));
				process.setUpdate_dt(rs.getTimestamp("update_dt"));
				processList.add(process);
			}
		} catch (SQLException e) {
			System.out.println("공정 테이블에서 사용 불가 목록 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return processList;
		
	}

	// 생산 이력(production_hist) 목록 전체 조회
	public ArrayList<ProductionHistoryBean> selectProductionHistoryList() {
		
		ArrayList<ProductionHistoryBean> productionHistoryList = new ArrayList<ProductionHistoryBean>();
		ProductionHistoryBean productionHistory = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from production_hist order by wo_no desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next() ) {
				productionHistory = new ProductionHistoryBean();
				productionHistory.setWo_no(rs.getInt("wo_no"));
				productionHistory.setPlant_cd(rs.getInt("plant_cd"));
				productionHistory.setLine_cd(rs.getInt("line_cd"));
				productionHistory.setItem_cd(rs.getInt("item_cd"));
				productionHistory.setWo_seq(rs.getInt("wo_seq"));
				productionHistory.setStart_dt(rs.getTimestamp("start_dt"));
				productionHistory.setEnd_dt(rs.getTimestamp("end_dt"));
				productionHistory.setIn_qty(rs.getInt("in_qty"));
				productionHistory.setOut_qty(rs.getInt("out_qty"));
				productionHistory.setNg_qty(rs.getInt("ng_qty"));
				productionHistoryList.add(productionHistory);
			}
		} catch (SQLException e) {
			System.out.println("생산 이력 목록 전체 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return productionHistoryList;
		
	}

	// quality 테이블에서 라인별, 생산제품수별 최근 양품률 구하기
	public float[] selectYield(int per) {
		
		float[] yield = new float[6];
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select sum(t.check_result)/count(t.check_result)*100 from (select * from quality where line_cd = ? order by serial_no desc limit ?) t";
		
		for(int i = 1; i < 7; i++) {
			try {	
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				pstmt.setInt(2, per);				
				rs = pstmt.executeQuery();
				if(rs.next()) yield[i-1] = rs.getFloat(1);
			} catch (SQLException e) {
				System.out.println("품질검사정보 테이블에서 양품률 구하기 실패! " + e.getMessage());
			} finally {
				close(pstmt, rs);
			}
		}
		
		return yield;
		
	}

	// 설비 에러 내용 조회 (w/ error_cd)
	public ErrorBean selectEquipmentErrorDetail(int error_cd) {
		
		ErrorBean equipmentErrorDetail = new ErrorBean();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from error where error_cd = " + error_cd;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				equipmentErrorDetail.setError_cd(rs.getInt("error_cd"));
				equipmentErrorDetail.setError_gd(rs.getString("error_gd"));
				equipmentErrorDetail.setError_msg(rs.getString("error_msg"));
				equipmentErrorDetail.setDown_dr(rs.getInt("down_dr"));
				equipmentErrorDetail.setUpdate_dt(rs.getTimestamp("update_dt"));
			}
		} catch (SQLException e) {
			System.out.println("에러 내용 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return equipmentErrorDetail;
		
	}

	// 설비 에러 로그 조회 (w/ equip_id)
	public ArrayList<ErrorLogBean> selectEquipmentErrorLog(int equip_id) {
		
		ArrayList<ErrorLogBean> equipmentErrorLog = new ArrayList<ErrorLogBean>();
		ErrorLogBean equipmentError = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from error_log where equip_id = " + equip_id + " order by log_no desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				equipmentError = new ErrorLogBean();
				equipmentError.setLog_no(rs.getInt("log_no"));
				equipmentError.setPlant_cd(rs.getInt("plant_cd"));
				equipmentError.setLine_cd(rs.getInt("line_cd"));
				equipmentError.setProcess_cd(rs.getString("process_cd"));
				equipmentError.setEquip_id(rs.getInt("equip_id"));
				equipmentError.setError_cd(rs.getInt("error_cd"));
				equipmentError.setError_gd(rs.getString("error_gd"));
				equipmentError.setStart_dt(rs.getTimestamp("start_dt"));
				equipmentError.setEnd_dt(rs.getTimestamp("end_dt"));
				equipmentErrorLog.add(equipmentError);
			}
		} catch (SQLException e) {
			System.out.println("설비 에러 로그 조회 실패! " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		
		return equipmentErrorLog;
		
	}
	
}

























