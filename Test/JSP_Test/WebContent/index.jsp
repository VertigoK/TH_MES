<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Sample UI</title>
    <style>
		html, body {
			margin: 0;
			height: 100%;
			width: 100%;
			/* overflow: hidden; */
		}
		#tab {
			float: left;
			width: 5%;
			background-color: #0D0D0D;
		}
		#tab button {
			display: block;
			background: transparent;
			color: white;
			width: 100%;
			padding: 10px 16px;
			border: none;
			outline: none;
			text-align: left;
			cursor: pointer;
			transition: 0.3s;
			font-size: 17px;
		}
		#tab button:hover, #tab button.active  {
			background-color: #BF4B6A;
			color: black;
			overflow: hidden;
		}
		#tab button:focus { outline: 0; }
		#tab-content {
			float: right;
			padding: 2%;
			background-color: green;
			width: 95%;
		}
		header {
			height: 30px;
			padding-left: 5px;
		}
		header p {
			letter-spacing: 5px;
		}

    </style>
    
</head>
<body>
	<header><p>Company Name</p></header>
	<div id="tab">
		<button class="tablinks active" id="home">home</button>
		<button class="tablinks" id="product">product</button>
		<button class="tablinks" id="quality">quality</button>
		<button class="tablinks" id="facility">facility</button>
		<button class="tablinks" id="inventory">inventory</button>
		<button class="tablinks" id="kke">kke</button>
	</div>
	
	<!-- 탭 콘텐트가 표시되는 div -->
	<div id="tab-content"></div>	

	<script>
		// div height = window height - header height
		$('div').css('height', $(window).height() - $('header').height());
	
		//btn 클릭 시 active class 생성
		var btns = $('.tablinks');
		btns.click(function() {
			btns.removeClass("active");
			$(this).addClass("active");
		})
		
		// tab-content links
		$('#home').on('click', function() {
			history.pushtate(null, null, '/');
			$('#tab-content').load('page1.jsp');
		});
		$('#product').on('click', function() {
			history.pushState(null, null, '/production');
			$('#tab-content').load('page2.jsp');
		});
		$('#quality').on('click', function() {
			history.pushState(null, null, '/quality');
			$('#tab-content').load('page3.jsp');
		});
		$('#facility').on('click', function() {
			history.pushState(null, null, '/facility');
			$('#tab-content').load('page4.jsp');
		});
		$('#inventory').on('click', function() {
			history.pushState(null, null, '/inventory');
			$('#tab-content').load('page5.jsp');
		});
		$('#kke').click(function() {
			$.ajax({
				type : 'post',
				data : '',
				success : function() {
					window.location.href = 'kke.jsp';
				}
				
			});
		});
		
		$(window).on("popstate", function() {
			history.back();
		})
		
		// index.jsp open 시 home 버튼 클릭
		document.getElementById("home").click();	
	</script>
</body>
</html>