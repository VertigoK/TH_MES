<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/css/lv1StyleSheet.css"/>
	<title>Telstar-Hommel</title>
</head>
<body>
	<div id="header">
		<jsp:include page="/layout/header.jsp" />
	</div>
	<div id="navigation">
		<jsp:include page="/layout/navigation.jsp" />
	</div>
	<div class="content" align="center">
		<form action="/order/ourOrder" method="post">
			<table class="table1">
				<tr>
					<td>납품공장</td>
					<td>
						<select name="plant_cd">
							<option value="1">공장1</option>					
							<option value="2">공장2</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>납품자재</td>
					<td>
						<select name="item_cd">
							<option value="4">자재1</option>					
							<option value="5">자재2</option>
							<option value="6">자재3</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>주문수량</td>
					<td><input type="text" name="order_qty" value="100" required/></td>
				</tr>
			</table>
			<input type="reset" value="Reset" class="text-white btn btn-success"/>
			<input type="submit" value="Submit" class="text-white btn btn-warning"/>
		</form>
	</div>	
</body>
</html>