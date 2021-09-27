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
import mes.action.HRAction;
import mes.action.LogInAction;
import mes.action.LogOutAction;
import mes.action.ProductionLineAction;
import mes.action.ProductionPlantAction;
import mes.action.ProductionProcessAction;
import mes.action.ProductionAction;
import mes.action.QualityAction;
import mes.action.QualityLineAction;
import mes.action.QualityPlantAction;
import mes.action.QualityProcessAction;
import mes.action.SignUpAction;
import mes.action.StockAction;
import mes.action.StockItemAction;
import mes.action.StockPlantAction;
import mes.dto.ActionForward;

@WebServlet(urlPatterns = {"/logInForm", "/logIn", "/logOut", "/signUpForm", "/signUp", "/custOrderForm", "/custOrder",
						   "/production", "/production/*", "/quality", "/quality/*",
						   "/equipment", "/equipment/*", "/stock", "/stock/*", "/hr", "/hr/*"})
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
		} else if(command.equals("/custOrderForm")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/misc/custorder.jsp");
		} else if(command.equals("/custOrder")) {
			action = new CustOrderAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
