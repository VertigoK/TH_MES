package mes.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mes.action.Action;
import mes.action.EquipmentStatusAction;
import mes.action.HRStatusAction;
import mes.action.ProductionStatusAction;
import mes.action.QualityStatusAction;
import mes.action.StockStatusAction;
import mes.dto.ActionForward;

@WebServlet(urlPatterns = {"/productionStatus", "/qualityStatus", "/equipmentStatus", "/stockStatus", "/hrStatus"})
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
		String command = req.getServletPath();
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/productionStatus")) {
			action = new ProductionStatusAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/qualityStatus")) {
			action = new QualityStatusAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/equipmentStatus")) {
			action = new EquipmentStatusAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/stockStatus")) {
			action = new StockStatusAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/hrStatus")) {
			action = new HRStatusAction();
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
