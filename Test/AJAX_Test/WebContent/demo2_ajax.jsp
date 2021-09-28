<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	<style>
	table, th, td {
	  border: 1px solid black;
	}
	</style>

	<script>
	$(document).ready(function(){
	  $("button").click(function(){
		$("#table1").load("demo2_ajax_target.jsp #table2");
	  });
	});
	</script>

<title>Partial refresh without reloading via jQuery and AJAX</title>
</head>
<body>
	<table id="table1">
		<tr>
			<th>Year</th>
			<th>Month</th>
		</tr>
		<tr>
			<td>2018</td>
			<td>1</td>
		</tr>
		<tr>
			<td>2019</td>
			<td>2</td>
		</tr>
	</table>
	<button>Hit this button!</button>
</body>
</html>