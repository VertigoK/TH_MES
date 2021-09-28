$(document).ready(function() {
	var loc = $(location).attr("href");
	if (loc == "http://localhost:8088/") {
		$("#home").addClass("active");
	};
	if (loc == "http://localhost:8088/page1.jsp") {
		$("#product").addClass("active");
	};
	if (loc == "http://localhost:8088/page2.jsp") {
		$("#quality").addClass("active");
	};
	if (loc == "http://localhost:8088/page3.jsp") {
		$("#equipment").addClass("active");
	};
	if (loc == "http://localhost:8088/page4.jsp") {
		$("#stock").addClass("active");
	};
	if (loc == "http://localhost:8088/page5.jsp") {
		$("#hr").addClass("active");
	};
});