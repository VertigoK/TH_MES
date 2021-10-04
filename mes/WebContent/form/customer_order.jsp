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
    <title>고객사 제품 주문</title>
</head>
<body>
	<div id="header">
		<jsp:include page="/layout/header.jsp" />
	</div>
	<div id="navigation">
		<jsp:include page="/layout/navigation.jsp" />
	</div>
	<div class="content" align="center">
		<form action="/order/custOrder" method="post">
			<table class="table1">
				<tr>
					<td>주문회사</td>
					<td>
						<select name="cust_cd">
							<option value="1">거래처1</option>					
							<option value="2">거래처2</option>
							<option value="3">거래처3</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>생산공장</td>
					<td>
						<select name="plant_cd">
							<option value="1">공장1</option>					
							<option value="2">공장2</option>
						</select>
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>생산제품</td> -->
<!-- 					<td> -->
<!-- 						<select name="item_cd"> -->
<!-- 							<option value="1">자동차부품1</option>					 -->
<!-- 							<option value="2">자동차부품2</option> -->
<!-- 							<option value="3">자동차부품3</option> -->
<!-- 						</select> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<td>주문수량</td>
					<td><input type="text" name="order_qty" value="100" required/></td>
				</tr>
				<tr>
					<td>납기일</td>
					<td><input type="date" name="delivery_date" required/></td>
				</tr>
			</table>
			<input type="reset" value="Reset" class="text-white btn btn-success"/>
			<input type="submit" value="Submit" class="text-white btn btn-warning"/>
		</form>
	</div>
</body>
</html>
