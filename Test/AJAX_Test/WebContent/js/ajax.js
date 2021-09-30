$(document).ready(function() {
	$("#runPython").click(function() {
		$.ajax({
			type : "post",
			url : "/generate",
			data : "",
			success : function() {
				window.location.href = "/misc/data_generated.jsp";
			}
		});
	});
//	$("#periodicCall").click(function() {
//		$("#div1").load("run_python.jsp");
//	});
});