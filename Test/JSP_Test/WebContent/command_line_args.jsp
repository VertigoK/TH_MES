<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.io.BufferedReader" %>
<%@ page import = "java.io.InputStreamReader" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Run py.file from JSP</title>
</head>
<body>
<%
	String myString = "python C:\\projects\\TH_MES\\Test\\JSP_Test\\WebContent\\command_line_args.py".concat(" --prdInfo 1 2 1 7")
						.concat(" --prdTime 2021-09-17 13:51:57").concat(" --plan_qty 1000").concat(" --specs 40 30 0 0 5 5 0 0")
						.concat(" --tols 0.1 0.1 0.001 0.001 0.1 0.1 0.05 0.05");
    Process pr = Runtime.getRuntime().exec(myString);
    BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));  
    String line = null;
    
    while ((line = br.readLine()) != null) {  
        out.println(line);  
    }  
    br.close();
    
    pr.getErrorStream().close();
    pr.getInputStream().close();
    pr.getOutputStream().close();
    pr.waitFor();
    
    out.println("end");
%>
</body>
</html>