$(document).ready(function(){
	var requestId = $('input[name=requestid]').val();
	ECSTM.beforeSubmit(function(){
		var _url = '/greedc/servlets/EBProjectEntry-getRejectCountsByRequestId.json?requestId=' + requestId;
		var _params = {};
		_params.requestId = $('#requestid').val();
		$.getJSON(_url, _params, function(resp) {
			if (resp.status) {
				if (resp.message*1 > 0) {
					alert('费用计划清单中，勾选状态有“修改”或“会计驳回”的记录，因此不能提交流程。如果需要申请人修改单据信息，请退回到申请人。');
				} else {
					ECSTM.beforeSubmitAction(ECSTM.beforeSubmitObject);
				}
			} 
		});
		return false;
	});
});