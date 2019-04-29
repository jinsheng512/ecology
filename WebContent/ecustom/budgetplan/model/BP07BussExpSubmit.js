/**
 * BP07-外部经营性支出新建/编辑模板JS
 * <script type="text/javascript" src="/ecustom/budgetplan/common.js"></script>
 * <script type="text/javascript" src="/ecustom/budgetplan/model/BP07BussExpSubmit.js"></script>
 */
 $(document).ready(function(){

 	var FM = {};	// 字段映射
 	var init_dt1Account = function(accountList) {
 		for (var i = 0; i < accountList.length; i++) {
 			var account = accountList[i];
 			var len = $('input[id^=' + FM.dt1accountid + '][value=' + account.id + ']').length;
 			if (len <= 0) {
 				addRow0(0);
 				var indexNum = $('#indexnum0').val() - 1;
 				setFieldValue(FM.dt1accountid + '_' + i, account.id, account.accountName);
 			}
 		}
 	};

 	var formInit = function() {
 		var url = '/greedc/servlets/BPBussExp-listAccount.json';
 		$.getJSON(url, {
 			time: new Date().getTime()
 		}, function(resp) {
 			if (resp.status == false) {
 				Dialog.alert(resp.message);
 				return ;
 			}
 			init_dt1Account(resp);
 		});
 	};

 	beforeSubmit(function(fnSubmit, obj) {
 		var companyId = $('#' + FM.company).val();

		if (!companyId || companyId <= 0) {
			Dialog.alert('公司不能为空！');
			return ;
		}
		fnSubmit(obj);
	});

	jQuery.getJSON('/greedc/servlets/WFField-getFieldMap.json', {
		formId: $('#formid').val(),
		time: new Date().getTime()
	}, function(resp) {
		if (resp.status == false) {
			Dialog.alert(resp.message);
			return ;
		}
		FM = resp;
		formInit();
	});

 });