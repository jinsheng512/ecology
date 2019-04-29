/**
 * BP08-筹资数据-显示布局
 */
$(document).ready(function(){
	
	var status = $('#disfield11189').val() * 1;
	if (status == 1) {
		window.parent.toEdit = function(){ 
			Dialog.alert('当前状态不可编辑！');
		};
		window.toEdit = parent.toEdit;
	}
	
	
	/**
	 * 执行生效动作。
	 */
	window.doActive = function() {
		Dialog.confirm('生效后不可编辑，确认生效？', function() {
			var url = '/greedc/servlets/BPFinancing-effectiveFinancing.json';
			$.getJSON(url, {
				id: $('#billid').val(),
				time: new Date().getTime()
			}, function(resp) {
				if (resp.status == true) {
					window.location.reload();
				} else {
					Dialog.alert(resp.message);
				}
			});
		});
	};
	
	
});