package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/production", "/production/*", "/quality", "/quality/*",
		   				   "/equipment", "/equipment/*", "/stock", "/stock/*", "/hr", "/hr/*"})
public class AjaxServletController extends HttpServlet {
	
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
		
		System.out.println(command);
		
		if(command.equals("/production")) {
			System.out.println("Good");
			String path = "/lv1/production.jsp";
			
			// Dispatcher does not work.
			RequestDispatcher rd = req.getRequestDispatcher(path);
			rd.forward(req, res);
		}
		
	}

}