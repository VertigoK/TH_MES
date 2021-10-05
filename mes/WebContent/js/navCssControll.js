$(document).ready(function() {
	var loc = $(location).attr("href");
	var prd = "http://localhost:8088/production";
	var qt = "http://localhost:8088/quality";
	var eq = "http://localhost:8088/equipment";
	var st = "http://localhost:8088/stock";
	var hr = "http://localhost:8088/hr";
	
	if (loc == "http://localhost:8088/") {
		$("#home").addClass("active");
	};
	if (loc.indexOf(prd) != -1) {
		$("#product").addClass("active");
	};
	if (loc.indexOf(qt) != -1) {
		$("#quality").addClass("active");
	};
	if (loc.indexOf(eq) != -1) {
		$("#equipment").addClass("active");
	};
	if (loc.indexOf(st) != -1) {
		$("#stock").addClass("active");
	};
	if (loc.indexOf(hr) != -1) {
		$("#hr").addClass("active");
	};
	
	/*if(loc.match(/http:\/\/localhost:8088\/[\w]+\/[\w\=\&\?]+/g)) {
		$("#hidden").show();
	} else{
		$("#hidden").hide();
	};*/
});