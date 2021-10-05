$(document).ready(function() {
	$("#runPython").click(function() {
		var woNo = $(this).attr('value');
		$.ajax({
			type: "post",
			url: "/generate",
			data: jQuery.param({ field1: woNo, field2 : "hello2"}),
			success: function() {
				window.location.href = "/misc/data_generated.jsp";
			}
		});
	});
});