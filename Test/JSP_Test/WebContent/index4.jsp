<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<script type="text/javascript">
	    function ChangeUrl(page, url) {
	        if (typeof (history.pushState) != "undefined") {
	            var obj = { Page: page, Url: url };
	            history.pushState(obj, obj.Page, obj.Url);
	        } else {
	            alert("Browser does not support HTML5.");
	        }
	    }
	    $(function () {
	        $("#button1").click(function () {
	            ChangeUrl('Page1', 'page1.jsp');
	        });
	        $("#button2").click(function () {
	            ChangeUrl('Page2', 'page2.jsp');
	        });
	        $("#button3").click(function () {
	            ChangeUrl('Page3', 'page3.jsp');
	        });
	    });
	</script>
	<input type="button" value="Page1" id="button1" />
	<input type="button" value="Page2" id="button2" />
	<input type="button" value="Page3" id="button3" />	
</body>
</html>
