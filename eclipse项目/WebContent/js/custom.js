$(window).resize(function() {
	// chosen resize bug
	"use strict";
	$('.chzn-container').each(function() {
		$(this).css('width', $('.chzn-container').parent().width()+'px');
		$(".chzn-drop").css('width', ($('.chzn-container').parent().width()-2)+'px');
		$(".chzn-search input").css('width', ($('.chzn-container').parent().width()-37)+'px');
	});
});