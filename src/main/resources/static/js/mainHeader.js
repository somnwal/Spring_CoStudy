(function ($) {
	$(document).ready(function() {
		$("#btnSidebarToggle").click(function() {
			$('#mainMenuSidebar').sidebar('toggle');
		});
	});
}(jQuery));