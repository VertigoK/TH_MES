<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<script>
         function goPython() {
             $.ajax({
            	 url: "kke_script.py",
            	 context: document.body
           	 }).done(function() {
           		 alert('finished python script');
             });
         }
    </script>
	<input type="button" id='script' name="scriptbutton" value="Run Script" onclick="goPython()">
</body>
</html>