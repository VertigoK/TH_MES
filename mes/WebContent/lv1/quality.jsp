<%@page import="java.text.DecimalFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	float[] yield100 = (float[]) session.getAttribute("yield100");
	float[] yield1000 = (float[]) session.getAttribute("yield1000");
	DecimalFormat df = new DecimalFormat("#.##");
	System.out.println(yield1000[0]);
	System.out.println(yield1000[1]);
	System.out.println(yield1000[2]);
	System.out.println(yield1000[3]);
	System.out.println(yield1000[4]);
	System.out.println(yield1000[5]);
%>
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
	<style>
/*  		.line { */
/*  			width: 140px; */
/*  			height: 49px; */
/*  			border-bottom: 1px solid #383636; */
/*  			transform: translateY(-23px) translateX(5px) rotate(20deg); */
/*  			position: absolute; */
/*  			/* z-index: -1; */ */
/*  		} */
		th>div {
			position: relative;
			height: 100%;
			width: 100%;
			top: 0;
			left: 0;
		}
		.bottom {
			position: absolute;
			bottom: 1px;
			left: 5px;
		}
		
		.top {
			position: absolute;
			top: 1px;
			right: 1px;
		}
		.title {
			display: flex;
			width: 100%;
			height: 33.5px;
			align-items: stretch;
			margin: 0 0 10px 0;
		 }
		.title-left { width: calc(100% - 60px); }
		.title-right {
			width: 60px;
			padding: 5px;
			text-align: right;
			font-wight: blod;
			background-color: #3F5060;
		}
		.title-right a { color: white; }
		.dropdown { margin-bottom: 10px; }
		.dropdown-content a { background-color: white; }
		.line {
			display: flex;
			justify-content: space-around;
			height: 30px;
			margin: 10px;
		}
		.line .percent, .line .title { pink; display: flex; align-items: center; }
		.line .percent { width: 60px; }
		.myProgress { height: 100%; width: 90%; background-color: #ddd; }
		.myBar { width: 1%; height: 100%; background-color: #DF3C2F;}
	</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/layout/header.jsp" />
	</div>
	<div id="navigation">
		<jsp:include page="/layout/navigation.jsp" />
	</div>
	<div class="content" align="center">
		<div class="item">
			<div class="dropdown">
	  			<button class="btn btn-outline-info"><strong>공장별 품질 데이터</strong></button>
	  			<div class="dropdown-content">
					<a href="/quality/plant?id=plant_cd&no=1">공장 1</a>
					<a href="/quality/plant?id=plant_cd&no=2">공장 2</a>
				</div>
			</div>
			<div class="dropdown">
	  			<button class="btn btn-outline-info"><strong>라인별 품질 데이터</strong></button>
	  			<div class="dropdown-content">
					<a href="/quality/line?id=line_cd&no=1">라인 1</a>
					<a href="/quality/line?id=line_cd&no=2">라인 2</a>
					<a href="/quality/line?id=line_cd&no=3">라인 3</a>
					<a href="/quality/line?id=line_cd&no=4">라인 4</a>
					<a href="/quality/line?id=line_cd&no=5">라인 5</a>
					<a href="/quality/line?id=line_cd&no=6">라인 6</a>
				</div>
			</div>
			<h5>라인별 양품률(%) (최근 100개 생산 기준: 목표 97%)</h5>
		</div>
		<div class="line">
			<div class="title">라인 1</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield100[0])%>%" />
		</div>
		
		<div class="line">
			<div class="title">라인 2</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield100[1])%>%" />
		</div>
		
		<div class="line">
			<div class="title">라인 3</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield100[2])%>%" />
		</div>
		
		<div class="line">
			<div class="title">라인 4</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield100[3])%>%" />
		</div>
		
		<div class="line">
			<div class="title">라인 5</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield100[4])%>%" />
		</div>
		
		<div class="line">
			<div class="title">라인 6</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield100[5])%>%" />
		</div>
		
	<h5>라인별 양품률(%) (최근 1000개 생산 기준: 목표 97%)</h5>
		<div class="line">
			<div class="title">라인 1</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield1000[0])%>%" />
		</div>
		
		<div class="line">
			<div class="title">라인 2</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield1000[1])%>%" />
		</div>
		
		<div class="line">
			<div class="title">라인 3</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield1000[2])%>%" />
		</div>
		
		<div class="line">
			<div class="title">라인 4</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield1000[3])%>%" />
		</div>
		
		<div class="line">
			<div class="title">라인 5</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield1000[4])%>%" />
		</div>
		
		<div class="line">
			<div class="title">라인 6</div>
			<div class="myProgress">
				<div class="myBar"></div>
			</div>
			<div class="percent">0%</div>
			<input type="hidden" class="value" value="<%=df.format(yield1000[5])%>%" />
		</div>
		<script>
			// 양품률 데이터
			var vals = document.querySelectorAll(".value");
			// 양품률 퍼센트
			var pers = document.querySelectorAll(".percent");
			// 양품률 막대 넓이
			var myBars = document.querySelectorAll(".myBar");
			
			// 양품률 데이터별 양품률 퍼센트, 막대 넓이 표현
			for (var i=0; i<vals.length; i++) {
				// 막대 넓이
				myBars[i].style.width = vals[i].value;
				// 양품률 퍼센트
				pers[i].innerHTML=vals[i].value;
				// 95% 기준 막대 색상 변화
				if(vals[i].value>="97"){
					myBars[i].style.backgroundColor="#7CCE76";
				} else {
					myBars[i].style.backgroundColor="#DF3C2F";				
				}
			}
		</script>	
	</div>
</body>
</html>