<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>jQuery/Ajax/Java/Python integration example</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="js/interval_call.js" type="text/javascript"></script>
</head>
<body>
	
	<button id="runPython">Run a python file.</button>
	<br />
	<h4>Hit the button to run a python file.</h4>
	<br />
	<button id="periodicCall">Call run_python.jsp periodically</button>
	<br />
	<div id="div1"><h4></h4></div>
</body>
</html>