/**
 * BP09-数据填报-显示布局
 */
$(document).ready(function(){
	var FM = {};
	
	var doEdit = function() {
		var status = $('#dis' + FM.status).val() * 1;
		if (status != 0 && status != 4) {
			window.parent.toEdit = function(p1, p2){ 
				Dialog.alert('当前状态不可编辑！');
			};
			window.toEdit = parent.toEdit;
		}
	};
	
	
	window.doCreateRequest = function() {
		Dialog.confirm('提交流程后不可编辑，确认提交？', function() {
			var url = '/greedc/servlets/BPCompanyPlan-createRequest.json';
			$.getJSON(url, {
				billid: $('#billid').val(),
				time: new Date().getTime()
			}, function(resp) {
				Dialog.alert(resp.message);
				if (resp.status) {
					window.parent.location.reload();
				}
			});
		});
	};
	
	/**
	 * 反审核
	 */
	window.doCancelAudit = function() {
		Dialog.confirm('确认将此单据重置为“未提交”状态？', function() {
			var url = '/greedc/servlets/BPCompanyPlan-cancelAudit.json';
			$.getJSON(url, {
				billid: $('#billid').val(),
				time: new Date().getTime()
			}, function(resp) {
				Dialog.alert(resp.message);
				if (resp.status) {
					window.parent.location.reload();
				}
			});
		});
	
		
	};
	
	jQuery.getJSON('/greedc/servlets/WFField-getFieldMap.json', {
		formId: $('#formid').val(),
		time: new Date().getTime()
	}, function(resp) {
		if (resp.status == false) {
			Dialog.alert(resp.message);
			return ;
		}
		FM = resp;
		doEdit();
	});
	
});