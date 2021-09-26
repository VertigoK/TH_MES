$(document).ready(function() {
	$("#someName").click(function() {
		$.ajax({
			type : "post",
			url : "/production",
			data : "",
//			success : function() {
//				window.location.href = "/lv1/production.jsp";
//			}
		});
	});
});