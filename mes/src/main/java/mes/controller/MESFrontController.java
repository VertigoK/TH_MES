package mes.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.action.Action;
import mes.action.CustOrderAction;
import mes.action.EquipmentAction;
import mes.action.EquipmentLineAction;
import mes.action.EquipmentPlantAction;
import mes.action.EquipmentProcessAction;
import mes.action.EquipmentUpdateRunTimeAction;
import mes.action.HRAction;
import mes.action.ItemProductionInAction;
import mes.action.ItemProductionOutAction;
import mes.action.LogInAction;
import mes.action.LogOutAction;
import mes.action.OrderAction;
import mes.action.OrderInListAction;
import mes.action.OrderOutListAction;
import mes.action.OurOrderAction;
import mes.action.OurOrderAutoAction;
import mes.action.ProductionLineAction;
import mes.action.ProductionPlantAction;
import mes.action.ProductionProcessAction;
import mes.action.ProductionStartAction;
import mes.action.WorkOrderUpdateAction;
import mes.action.ProductionAction;
import mes.action.ProductionHistoryAction;
import mes.action.QualityAction;
import mes.action.QualityLineAction;
import mes.action.QualityPlantAction;
import mes.action.QualityProcessAction;
import mes.action.SignUpAction;
import mes.action.StockAction;
import mes.action.StockItemAction;
import mes.action.StockPlantAction;
import mes.action.WorkOrderAction;
import mes.action.WorkOrderFormAction;
import mes.action.WorkOrderListAction;
import mes.action.CheckOrderStockAction;
import mes.dto.ActionForward;

@WebServlet(urlPatterns = {"/logInForm", "/logIn", "/logOut", "/signUpForm", "/signUp",
						   "/production", "/production/*", "/quality", "/quality/*",
						   "/equipment", "/equipment/*", "/stock", "/stock/*", "/hr", "/hr/*", "/order", "/order/*",
						   "/generate", "/generate/*", "/notice", "/notice/*", "/item", "/item/*"})
public class MESFrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doProcess(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doProcess(req, res);
	}
	
	private void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
//		String command = req.getServletPath();
		String command = req.getRequestURI();
			
		// /////////////////////////
		// 주소 확인용 (나중에 삭제 예정)
		System.out.println(command);
		// /////////////////////////
		
		ActionForward forward = null;
		Action action = null;
			
		if(command.equals("/logInForm")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/misc/login.jsp");
		} else if(command.equals("/logIn")) {
			action = new LogInAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/logOut")) {
			action = new LogOutAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/signUpForm")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/misc/signup.jsp");
		} else if(command.equals("/signUp")) {
			action = new SignUpAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/notice")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/misc/notice.jsp");
		} else if(command.equals("/production")) {
			action = new ProductionAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/quality")) {
			action = new QualityAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/equipment")) {
			action = new EquipmentAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/stock")) {
			action = new StockAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/order")) {
			action = new OrderAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/hr")) {
			action = new HRAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/production/plant")) {
			action = new ProductionPlantAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/production/line")) {
			action = new ProductionLineAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/production/process")) {
			action = new ProductionProcessAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/quality/plant")) {
			action = new QualityPlantAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/quality/line")) {
			action = new QualityLineAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/quality/process")) {
			action = new QualityProcessAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/equipment/plant")) {
			action = new EquipmentPlantAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/equipment/line")) {
			action = new EquipmentLineAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/equipment/process")) {
			action = new EquipmentProcessAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/stock/plant")) {
			action = new StockPlantAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/stock/item")) {
			action = new StockItemAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/order/inList")) {
			action = new OrderInListAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/order/outList")) {
			action = new OrderOutListAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/order/custOrderForm")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/form/customer_order.jsp");
		} else if(command.equals("/order/custOrder")) {
			action = new CustOrderAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/order/ourOrderForm")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/form/our_order.jsp");
		} else if(command.equals("/order/ourOrder")) {
			action = new OurOrderAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/order/inList/checkOrderStock")) {
			action = new CheckOrderStockAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/production/workOrderForm")) {
			action = new WorkOrderFormAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/production/workOrder")) {
			action = new WorkOrderAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/production/workOrderList")) {
			action = new WorkOrderListAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/order/ourOrderAuto")) {
			action = new OurOrderAutoAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/production/start")) {
			action = new ProductionStartAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/production/history")) {
			action = new ProductionHistoryAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/production/workOrder/update")) {
			action = new WorkOrderUpdateAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/equipment/updateRunTime")) {
			action = new EquipmentUpdateRunTimeAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/item/production/in")) {
			action = new ItemProductionInAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/item/production/out")) {
			action = new ItemProductionOutAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// sendRedirect() or forward() 선택
		if(forward != null) {
			String path = forward.getPath();
			if(forward.isRedirect()) {
				res.sendRedirect(path);
			} else {
				RequestDispatcher rd = req.getRequestDispatcher(path);
				rd.forward(req, res);
			}
		}
		
	}

}
