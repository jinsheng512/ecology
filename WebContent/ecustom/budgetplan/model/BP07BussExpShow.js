/**
 * BP07-外部经营性支出显示模板JS
 * <script type="text/javascript" src="/ecustom/budgetplan/common.js"></script>
 * <script type="text/javascript" src="/ecustom/budgetplan/model/BP07BussExpShow.js"></script>
 */
 $(document).ready(function(){

// 	var FM = {};	// 字段映射

 	
	
	

	/**
	 * 执行生效动作。
	 */
	window.doActiveBussExp = function() {
		Dialog.confirm('生效后不可编辑，确认生效？', function() {
			var url = '/greedc/servlets/BPBussExp-effectiveBussExpServlet.json';
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
	
	/**
	 * 取消生效动作。
	 */
	window.unDoActiveBussExp = function() {
		Dialog.confirm('确认取消生效状态？', function() {
			var url = '/greedc/servlets/BPBussExp-unEffectiveBussExpServlet.json';
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

	
	
//	/**
//	 * 执行生效动作。
//	 */
//	window.doActive = function() {
//		var url = '/greedc/servlets/BPCompanyPlan-createRequest.json';
//		$.getJSON(url, {
//			billid: $('#billid').val(),
//			time: new Date().getTime()
//		}, function(resp) {
//			Dialog.alert(resp.message);
//			if (resp.status) {
//				window.location.reload();
//			}
//		});
//	};	

	jQuery.getJSON('/greedc/servlets/WFField-getFieldMap.json', {
		formId: $('#formid').val(),
		time: new Date().getTime()
	}, function(resp) {
		if (resp.status == false) {
			Dialog.alert(resp.message);
			return ;
		}
		FM = resp;
		var status = $('#dis' + FM.status).val() * 1;
		if (!isNaN(status) && status != 0) {
			window.parent.toEdit = function(p1, p2){ 
				Dialog.alert('当前状态不可编辑！');
			};
			window.toEdit = parent.toEdit;
		}
	});

 });