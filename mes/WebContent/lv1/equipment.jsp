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
	<title>설비 현황</title>
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
				<a href="/equipment/plant?id=plant_cd&no=1">공장 1</a>
				<a href="/equipment/plant?id=plant_cd&no=2">공장 2</a>
			</div>
		</div>
		<div class="dropdown">
  			<button>라인별 현황</button>
  			<div class="dropdown-content">
				<a href="/equipment/line?id=line_cd&no=1">라인 1</a>
				<a href="/equipment/line?id=line_cd&no=2">라인 2</a>
				<a href="/equipment/line?id=line_cd&no=3">라인 3</a>
				<a href="/equipment/line?id=line_cd&no=4">라인 4</a>
				<a href="/equipment/line?id=line_cd&no=5">라인 5</a>
				<a href="/equipment/line?id=line_cd&no=6">라인 6</a>
			</div>
		</div>
		<div class="dropdown">
  			<button>공정별 현황</button>
  			<div class="dropdown-content">
				<a href="/equipment/process?id=process_cd&no=1">커팅</a>
				<a href="/equipment/process?id=process_cd&no=2">드릴링</a>
				<a href="/equipment/process?id=process_cd&no=3">조립</a>
				<a href="/equipment/process?id=process_cd&no=4">치수검사</a>
				<a href="/equipment/process?id=process_cd&no=5">홀검사</a>
			</div>
		</div>
	</div>
</body>
</html>