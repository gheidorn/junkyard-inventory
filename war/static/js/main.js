$(document).ready(function() {
	$("span.help-inline").each(function() {
		if(!$(this).is(':empty')) {
			$(this).parent().parent().addClass("error");
		}
	});
	
	
	
	$("#inventoryDateHeader").popover({offset:10});
	$("#inventoryDateHeader").hover(
		function() {
			$(this).popover('show');
		},
		function() {
			$(this).popover('hide');
		}
	);
	$("#inventoryDateHeader").mouseover(function() {
	    console.info($(".popover"));
	});

});