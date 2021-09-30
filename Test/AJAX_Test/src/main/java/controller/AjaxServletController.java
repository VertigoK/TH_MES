package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/generate", "/generate/*"})
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
		String command = req.getRequestURI();

		if(command.equals("/generate")) {
			
			Process pr = Runtime.getRuntime().exec("python C:\\projects\\TH_MES\\Test\\AJAX_Test\\WebContent\\py\\make_plot.py");
			
//			// Python 파일 실행 결과 출력하기
//		    BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//		    String line = null;
//		    while ((line = br.readLine()) != null) {  
//		        System.out.println(line);  
//		    }
//		    br.close();
		    
		    pr.getErrorStream().close();
		    pr.getInputStream().close();
		    pr.getOutputStream().close();
		    try {
				pr.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    
		    System.out.println("Execution ends!");
		}
		
	}

}