<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<style>        
		.dropdown {
		  display: inline-block;
		  position: relative;
		}
		.dropdown-content {
		  display: none;
		  position: absolute;
		  width: 100%;
		  overflow: auto;
		  box-shadow: 0px 10px 10px 0px rgba(0,0,0,0.4);
		}
		.dropdown:hover .dropdown-content {
		  display: block;
		}
		.dropdown-content a {
		  display: block;
		  color: #000000;
		  padding: 5px;
		  text-decoration: none;
		}
		.dropdown-content a:hover {
		  color: #FFFFFF;
		  background-color: #00A4BD;
		}
    </style>
	<title>품질현황</title>
</head>
<body>
	<div class="container" align="center">
		<div class="dropdown">
  			<button>공장별 현황</button>
  			<div class="dropdown-content">
				<a href="/quality/plant?cd=1">공장 1</a>
				<a href="/quality/plant?cd=2">공장 2</a>
			</div>
		</div>
		<div class="dropdown">
  			<button>라인별 현황</button>
  			<div class="dropdown-content">
				<a href="/quality/line?cd=1">라인 1</a>
				<a href="/quality/line?cd=2">라인 2</a>
				<a href="/quality/line?cd=3">라인 3</a>
				<a href="/quality/line?cd=4">라인 4</a>
				<a href="/quality/line?cd=5">라인 5</a>
				<a href="/quality/line?cd=6">라인 6</a>
			</div>
		</div>
		<div class="dropdown">
  			<button>공정별 현황</button>
  			<div class="dropdown-content">
				<a href="/quality/process?cd=qc_cut">Cut</a>
				<a href="/quality/process?cd=qc_drill">Drill</a>
			</div>
		</div>
	</div>
</body>
</html>