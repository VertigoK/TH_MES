<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/css/lv1StyleSheet.css"/>
	<title>재고현황</title>
</head>
<body>
	<div id="header">
		<jsp:include page="/layout/header.jsp" />
	</div>
	<div id="navigation">
		<jsp:include page="/layout/navigation.jsp" />
	</div>
	<div class="content" align="center">
		<div class="dropdown">
  			<button>공장별 현황</button>
  			<div class="dropdown-content">
				<a href="/stock/plant?cd=1">공장 1</a>
				<a href="/stock/plant?cd=2">공장 2</a>
			</div>
		</div>
		<div class="dropdown">
  			<button>품목별 현황</button>
  			<div class="dropdown-content">
				<a href="/stock/item?type=제품">제품</a>
				<a href="/stock/item?type=자재">자재</a>
			</div>
		</div>
	</div>
</body>
</html>