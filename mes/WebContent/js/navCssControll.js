$(document).ready(function() {
	var loc = $(location).attr("href");
	if (loc == "http://localhost:8088/") {
		$("#home").addClass("active");
	};
	if (loc == "http://localhost:8088/production") {
		$("#product").addClass("active");
	};
	if (loc == "http://localhost:8088/quality") {
		$("#quality").addClass("active");
	};
	if (loc == "http://localhost:8088/equipment") {
		$("#equipment").addClass("active");
	};
	if (loc == "http://localhost:8088/stock") {
		$("#stock").addClass("active");
	};
	if (loc == "http://localhost:8088/hr") {
		$("#hr").addClass("active");
	};
});