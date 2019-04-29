/**
 * BP02 公司管理
 */
$(document).ready(function(){
	window.reBuildRight = function() {
		$.getJSON('/greedc/servlets/BPCompany-reBuildRight.json', {
			time: new Date().getTime()
		}, function(resp) {
			Dialog.alert(resp.message);
		});
	};
});