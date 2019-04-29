/**
 * BP08-筹资数据
 */
$(document).ready(function(){
	
	var FM = {};

	$('#' + FM.year).attr('readonly', 'readonly');
	$('#' + FM.month).attr('readonly', 'readonly');
	$('#' + FM.yearmonth).attr('readonly', 'readonly');
	
	var getLastYearMonth = function() {
		var yearMonth = $('#' + FM.yearmonth).val();
		var yearStr = yearMonth.substring(0, 4);
		var monthStr = yearMonth.substring(4, 6);
		if (monthStr == '01') {
			yearStr = yearStr - 1;
			monthStr = '12';
		} else {
			monthStr = monthStr - 1;
			if (monthStr < 10) {
				monthStr = '0' + monthStr;
			}
		}
		return yearStr + monthStr;
	};

	var getCompanyAccount = function() {
		$.getJSON('/greedc/servlets/BPFinancing-getCompanyAccount.json', {
			yearMonth: getLastYearMonth(),
			time: new Date().getTime()
		}, function(resp) {
			for(var i=0 ;i<resp.dt1_account.length;i++){
				if ($('input[id^=' + FM.dt1accountid + '][value=' + resp.dt1_account[i].id + ']').length > 0) {
					// 编辑模版中如果明细表中存在则跳过
					continue ;
				}
				addRow0(0);
				var indexNum = $('#indexnum0').val() - 1;

				$('#' + FM.dt1accountid + '_' + indexNum).val(resp.dt1_account[i]['id']);	// 筹资收入值
				$('#' + FM.dt1accountid + '_' + indexNum+ 'span').text(resp.dt1_account[i]['accountName']);	// 筹资收入科目


				if(resp.dt1!=null){
					for(var j=0 ;j<resp.dt1.length;j++){
						if(resp.dt1[j]['accountId']==resp.dt1_account[i]['id']){
							$('#' + FM.dt1lastplan + '_' + indexNum).val(resp.dt1[j]['thisPlan']);
							$('#' + FM.dt1lastplan + '_'+ indexNum).attr('readonly', 'readonly');
						}
					}

				}
			}

			if(resp.dt2_account.length!=0){

				for(var k=0 ;k<resp.dt2_account.length;k++){

					if ($('input[id^=' + FM.dt2accountid + '][value=' + resp.dt2_account[k].id + ']').length > 0) {
						// 编辑模版中如果明细表中存在则跳过
						continue ;
					}
					addRow1(1);
					indexNum = $('#indexnum1').val() - 1;

					$('#' + FM.dt2accountid + '_' + indexNum).val(resp.dt2_account[k]['id']);	// 筹资收入值
					$('#' + FM.dt2accountid + '_' + indexNum+ 'span').text(resp.dt2_account[k]['accountName']);	// 筹资支出科目

					if(resp.dt1!=null){
						for(var f=0 ;f<resp.dt2.length;f++){


							if(resp.dt2[f]['accountId']==resp.dt2_account[k]['id']){

								$('#' + FM.dt2lastplan + '_' + indexNum).val(resp.dt2[f]['thisPlan']);
								$('#' + FM.dt2lastplan + '_'+ indexNum).attr('readonly', 'readonly');

							}
						}
					}
				}
			}

		});
	};

	// 明細表1
	addFormValueChangeEvent(function(_fieldId, _rowIndex, _value){
		if ([FM.dt1lastplan, FM.dt1lasthappend].indexOf(_fieldId) >= 0) {
			var plan = $('#' + FM.dt1lastplan + '_' + _rowIndex).val();
			var happen = $('#' + FM.dt1lasthappend + '_' + _rowIndex).val();
			var diffRate = calDiffRate(happen, plan);
			setFieldValue(FM.dt1difference + '_' + _rowIndex, diffRate, diffRate);
		}
	});



	// 明細表2
	addFormValueChangeEvent(function(_fieldId, _rowIndex, _value){
		if ([FM.dt2lastplan, FM.dt2lasthappend].indexOf(_fieldId) >= 0) {
			var plan = $('#' + FM.dt2lastplan + '_' + _rowIndex).val();
			var happen = $('#' + FM.dt2lasthappend + '_' + _rowIndex).val();
			var diffRate = calDiffRate(happen, plan);
			setFieldValue(FM.dt2difference + '_' + _rowIndex, diffRate, diffRate);
		}
	});


	/**
	 * 计算差额率
	 */
	var calDiffRate = function(happen, plan) {
		return ((happen - plan) / happen * 100).toFixed(2);
	};


	var setYearMonth = function() {

		var date = new Date();  
		var year = date.getFullYear(); 
		var month = date.getMonth()+1;
		var yearMonth = $('#' + FM.yearmonth).val();
		if (!yearMonth) {
			setFieldValue(FM.year, year, year);
			setFieldValue(FM.month, month, month);
			var year = new Date().getFullYear();
			var month = new Date().getMonth() + 1;
			month = (month < 10 ? '0' : '') + month;
			yearMonth = year + month;
			setFieldValue(FM.yearmonth, yearMonth, yearMonth);
			
			var lastYearMonth = getLastYearMonth();
			setFieldValue(FM.lastyearmonth, lastYearMonth, lastYearMonth);
		}
		
		getCompanyAccount();
		
		// 如果是编辑模板，则不需要跳转至指定期间，新建模板才需要跳转
		if ($('#billid').val() && $('#billid').val() > 0) {
			return ;
		}

		$.getJSON('/greedc/servlets/BPFinancing-getYearMonth.json', {
			yearMonth: yearMonth,
			time: new Date().getTime()
		}, function(resp) {
			resp.main = resp.main || {};
			if(resp.main.id){
				window.parent.location.href = '/formmode/view/AddFormMode.jsp?modeId=344&formId=-109&billid=' + resp.main.id;
				return ;
			}
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
		setYearMonth();
	});
});