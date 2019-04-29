/**
 * 创建节点禁止第二次提交。
 */
$(document).ready(function(){
	ECSTM.beforeSubmit(function(submintFn, obj){
		var _reqId = $('input[name=requestid]').val();
		if (_reqId > 0) {
			$.getJSON('/greedc/servlets/CurrentOperator-getFlowedNode.json', {
				requestId: _reqId
			}, function(_resp){
				if (_resp.status && _resp.message*1 > 1) {
					alert('不能提交，该流程只能由EAS发起。');
				} else {
					submintFn(obj);
				}
			});
		} else {
			submintFn(obj);
		}
	});
});