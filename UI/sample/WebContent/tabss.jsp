<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- <script type="text/javascript" src="/../js/jquery-3.5.1.min.js"></script> -->
<title>Test tab</title>
</head>
<body>
	<button id="clickMe1" type="button">One</button>
	<button id="clickMe2" type="button">Two</button>
	<button id="clickMe3" type="button">Three</button>
	<div id="content"></div>
	<script>
		$('#clickMe1').on('click', function() {
			$('#content').load('/tabs/One.jsp')
		});
		$('#clickMe2').on('click', function() {
			$('#content').load('/tabs/Two.jsp')
		});
		$('#clickMe3').on('click', function() {
			$('#content').load('/tabs/Three.jsp')
		});
	</script>	
</body>
</html>