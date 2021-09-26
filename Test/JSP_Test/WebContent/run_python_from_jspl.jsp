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
    Process pr = Runtime.getRuntime().exec("python C:\\projects\\TH_MES\\Test\\JSP_Test\\WebContent\\run_python_from_jsp.py");
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
    
    out.println("Execution ends");
%>
</body>
</html>