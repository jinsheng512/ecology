;(function($) {
	$('#showHistoryLog').html('<a href="javascript:$.openLogWindow()">[查看历史签字意见]</a>');
	
	$.extend({
		openFullWindow: function(_url){
			var width = screen.availWidth-10 ;
			var height = screen.availHeight-50 ;
			var szFeatures = "top=0," ;
			szFeatures +="left=0," ;
			szFeatures +="width="+width+"," ;
			szFeatures +="height="+height+"," ;
			szFeatures +="directories=no," ;
			szFeatures +="status=yes,toolbar=no,location=no," ;
			szFeatures +="menubar=no," ;
			szFeatures +="scrollbars=yes," ;
			szFeatures +="resizable=yes";
			window.open(_url, "", szFeatures);
			
		},
		openLogWindow: function() {
			var _url = '/ecustom/workflow/WorkflowRequestLog.jsp?requestId=' + $('input[name=requestid]').val();
			$.openFullWindow(_url);
		}
	});
	
})(jQuery);