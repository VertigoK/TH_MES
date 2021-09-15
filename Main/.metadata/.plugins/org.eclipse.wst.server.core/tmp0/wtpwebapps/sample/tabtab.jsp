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
   
<title>Insert title here</title>
</head>
<body>
	<div class="tab_content">
		<input type="radio" name="tabmenu" id='label1' checked />
		<label for="label1">one</label>
		<input type="radio" name="tabmenu" id='label2' />
		<label for="label2">two</label>
		<input type="radio" name="tabmenu" id='label3' />
		<label for="label3">three</label>
	</div>
	
	<div id="courseList"></div>
	<script>
		$("#courseList").jsp(msg);
		
		$.ajax({
			dataType:"text",
			url:basePath+"/courseList",
			data: {
				courseBox : types
			}, success: function(msg) {
				$('#courseList').jsp(msg);
			}
		})
		
	</script>
</body>
</html>