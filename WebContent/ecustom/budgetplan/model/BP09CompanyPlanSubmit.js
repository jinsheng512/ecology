$(document).ready(function(){

	var FM = {};
	var companyId = 0;
	
	var initForm = function() {
		var indexNum0 = $('#indexnum0').val();
		for (var i = 0; i < indexNum0; i++) {
			calDetailDiffRate(i, FM.dt1lastplan, FM.dt1lasthappend, FM.dt1difference, FM.dt1remarks, FM.bussinoverdiff, FM.bussinoverreason);
		}
		
		var indexNum1 = $('#indexnum1').val();
		for (var i = 0; i < indexNum1; i++) {
			calDetailDiffRate(i, FM.dt2lastplan, FM.dt2lasthappend, FM.dt2difference, FM.dt2remarks, FM.fnainoverdiff, FM.fnainoverreason);
		}
		
		var indexNum2 = $('#indexnum2').val();
		for (var i = 0; i < indexNum2; i++) {
			calDetailDiffRate(i, FM.dt3lastplan, FM.dt3lasthappend, FM.dt3difference, FM.dt3remarks, FM.bussoutoverdiff, FM.bussoutoverreason);
		}
		
		var indexNum3 = $('#indexnum3').val();
		for (var i = 0; i < indexNum3; i++) {
			calDetailDiffRate(i, FM.dt4lastplan, FM.dt4lasthappend, FM.dt4difference, FM.dt4remarks, FM.projoutoverdiff, FM.projoutoverreason);
		}
		
		var indexNum4 = $('#indexnum4').val();
		for (var i = 0; i < indexNum4; i++) {
			calDetailDiffRate(i, FM.dt5lastplan, FM.dt5lasthappend, FM.dt5difference, FM.dt5remarks, FM.fnaoutoverdiff, FM.fnaoutoverreason);
		}
		

		companyId = $('#' + FM.company).val();
		if (!companyId) {
			Dialog.alert('没有指定公司名称！');
			return ;
		}
		
		setYearMonth();	// 初始化期间
		
		loadConfig();	// 加载配置并加载科目信息
		
		loadProjectAccount();	// 加载项目
	};

	/**
	 * 计算明细表差额，如果差额超出指定数值，备注必填。
	 */
	var calDetailDiffRate = function(_rowIndex, planId, happenId, diffRateId, remarksId, sumDiffId, mainRemarksId) {
		var plan = $('#' + planId + '_' + _rowIndex).val();
		var happen = $('#' + happenId + '_' + _rowIndex).val();
		var diffRate = calDiffRate(happen, plan);
		if (happen == "" || plan == ""){
			diffRate = 0;
			setFieldValue(diffRateId + '_' + _rowIndex, diffRate, diffRate);
		} else {
			setFieldValue(diffRateId + '_' + _rowIndex, diffRate, diffRate);
		}
		
		if (diffRate * 1 > 20 && !$('#' + remarksId + '_' + _rowIndex).val()) {
			addInputCheckField(remarksId + '_' + _rowIndex, remarksId + '_' + _rowIndex + 'span');	// 添加必填验证，明细表文本框、选择框
		} else {
			removeInputCheckField(remarksId + '_' + _rowIndex, remarksId + '_' + _rowIndex + 'span');	// 移除必填验证，明细表文本框、选择框
		}

		var plans = $('input[id^=' + planId + '_]');
		var sumPlan = 0;
		for (var i = 0; i < plans.length; i++) {
			sumPlan += plans[i].value * 1;
		}

		var happens = $('input[id^=' + happenId + '_]');
		var sumHappen = 0;
		for (var i = 0; i < happens.length; i++) {
			sumHappen += happens[i].value * 1;
		}
		var sumDiff = calDiffRate(sumHappen, sumPlan);
		if(sumHappen == "" ||sumPlan == ""){
			sumDiff = 0;
			setFieldValue(sumDiffId, sumDiff, sumDiff);
		}else{
			setFieldValue(sumDiffId, sumDiff, sumDiff);
		}
		
		if (sumDiff * 1 > 20 && !$('#' + mainRemarksId).val()) {
			addInputCheckField(mainRemarksId, mainRemarksId + 'span');	// 添加必填验证，明细表文本框、选择框
		} else {
				removeInputCheckField(mainRemarksId, mainRemarksId + 'span');	// 移除必填验证，明细表文本框、选择框
		}
	};
	
	//计算差额并判断备注是否必填
	addFormValueChangeEvent(function(_fieldId, _rowIndex, _value){
		//dt1
		if ([FM.dt1lasthappend, FM.dt1remarks].indexOf(_fieldId) >= 0) {
			calDetailDiffRate(_rowIndex, FM.dt1lastplan, FM.dt1lasthappend, FM.dt1difference, FM.dt1remarks, FM.bussinoverdiff, FM.bussinoverreason);
		}
		//dt2
		if ([FM.dt2lasthappend, FM.dt2remarks].indexOf(_fieldId) >= 0) {
			calDetailDiffRate(_rowIndex, FM.dt2lastplan, FM.dt2lasthappend, FM.dt2difference, FM.dt2remarks, FM.fnainoverdiff, FM.fnainoverreason);
		}
		//dt3
		if ([FM.dt3lasthappend, FM.dt3remarks].indexOf(_fieldId) >= 0) {
			calDetailDiffRate(_rowIndex, FM.dt3lastplan, FM.dt3lasthappend, FM.dt3difference, FM.dt3remarks, FM.bussoutoverdiff, FM.bussoutoverreason);
		}
		//dt4
		if ([FM.dt4lasthappend, FM.dt4remarks].indexOf(_fieldId) >= 0) {
			calDetailDiffRate(_rowIndex, FM.dt4lastplan, FM.dt4lasthappend, FM.dt4difference, FM.dt4remarks, FM.projoutoverdiff, FM.projoutoverreason);
		}
		//dt5
		if ([FM.dt5lasthappend, FM.dt5remarks].indexOf(_fieldId) >= 0) {
			calDetailDiffRate(_rowIndex, FM.dt5lastplan, FM.dt5lasthappend, FM.dt5difference, FM.dt5remarks, FM.fnaoutoverdiff, FM.fnaoutoverreason);
		}
	});
	
	/**
	 * 加载项目
	 */
	var loadProjectAccount = function() {
		$.getJSON('/greedc/servlets/BPCompanyProject-getCompanyProject.json', {
			companyId: companyId,
			time: new Date().getTime()
		}, function(resp) {

			for(var i = 0; i < resp.length; i++){
				if ($('input[id^=' + FM.dt4accountid + '][value=' + resp[i].id + ']').length > 0) {
					// 如果明细表中存在则跳过
					continue ;
				}
				if (resp[i].company == companyId) {
					//添加行数
					addRow3(3);
					//获取新行号
					var indexNum = $('#indexnum3').val() - 1;
					setFieldValue(FM.dt4accountid+'_' + indexNum, resp[i].id, resp[i].projectName);
				} 
			}

		});
	};
	
	/**
	 * 加载二级科目。
	 */
	var loadAccountL2 = function(config) {
		//通过 HTTP GET 请求载入 JSON 数据。
		$.getJSON('/greedc/servlets/BPCompanyAccount-getCompanyAccount.json', {
			companyId: companyId,
			time: new Date().getTime()
		}, function(resp) {
			for(var i = 0; i < resp.length; i++) {
				// 外部经营性收入
				if (resp[i].accountL1 == config.bussIncAccount) {
					if ($('input[id^=' + FM.dt1accountid + '][value=' + resp[i].accountL2 + ']').length > 0) {
						// 如果明细表中存在则跳过
						continue ;
					}
					addRow0(0);
					//获取新行号
					var indexNum = $('#indexnum0').val() - 1;
					setFieldValue(FM.dt1accountid + '_' + indexNum, resp[i].accountL2, resp[i].accountName);
				} 
				// 内部经营性收入
				else if (resp[i].accountL1 == config.inBussIncAccount) {
					if ($('input[id^=' + FM.dt2accountid + '][value=' + resp[i].accountL2 + ']').length > 0) {
						// 如果明细表中存在则跳过
						continue ;
					}
					addRow1(1);
					//获取新行号
					var indexNum = $('#indexnum1').val() - 1;
					setFieldValue(FM.dt2accountid+'_' + indexNum, resp[i].accountL2, resp[i].accountName);
				}
				// 外部经营性支出
				else if (resp[i].accountL1 == config.bussExpAccount) {
					if ($('input[id^=' + FM.dt3accountid + '][value=' + resp[i].accountL2 + ']').length > 0) {
						// 如果明细表中存在则跳过
						continue ;
					}
					addRow2(2);
					//获取新行号
					var indexNum = $('#indexnum2').val() - 1;
					setFieldValue(FM.dt3accountid+'_' + indexNum, resp[i].accountL2, resp[i].accountName);
				}
				//  内部经营性支出
				else if (resp[i].accountL1 == config.inBussExpAccount) {
					if ($('input[id^=' + FM.dt5accountid + '][value=' + resp[i].accountL2 + ']').length > 0) {
						// 如果明细表中存在则跳过
						continue ;
					}
					addRow4(4);
					//获取新行号
					var indexNum = $('#indexnum4').val() - 1;
					setFieldValue(FM.dt5accountid+'_' + indexNum, resp[i].accountL2, resp[i].accountName);
				}
			}
		});
	};
	
	/**
	 * 1、加载配置；
	 * 2、加载科目信息；
	 * 3、加载上月计划
	 */
	var loadConfig = function() {
		$.getJSON('/greedc/servlets/BPConfig-getConfig.json', {
			time: new Date().getTime()
		}, function(config) {
			if (config.status == false || !config.id) {
				Dialog.alert('加载配置失败！');
				return ;
			}

			var companyId = $('#' + FM.company).val();
			if (!companyId || companyId * 1 <= 0) {
				Dialog.alert('没有传入公司');
				return ;
			}
			
			loadAccountL2(config);
			loadLastMonthPlan();
		});
	};
	
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
	
	var loadLostMonthPlanDt1 = function(dtList) {
		dtList = dtList || [];
		//获取新行号
		var indexNum = $('#indexnum0').val() - 1;
		for(var i = 0; i < dtList.length; i++) {
			var dt1AccountId  = dtList[i].accountId * 1;
			for (var rIndex = 0; rIndex <= indexNum; rIndex++) {
				var rowAccountId = $('#' + FM.dt1accountid + '_' + rIndex).val() * 1;
				if (rowAccountId == dt1AccountId) {
					var value = (dtList[i].thisPlan * 1 || 0).toFixed(2);
					setFieldValue(FM.dt1lastplan + '_' + rIndex, value, value);
				}
			}
		}
	};
	
	var loadLostMonthPlanDt2 = function(dtList) {
		dtList = dtList || [];
		var indexNum = $('#indexnum1').val() - 1;
		for(var i = 0; i < dtList.length; i++){
			var dt1AccountId  = dtList[i].accountId * 1;
			for (var rIndex = 0; rIndex <= indexNum; rIndex++) {
				var rowAccountId = $('#' + FM.dt2accountid+'_' + rIndex).val() * 1;
				if (rowAccountId == dt1AccountId) {
					var value = (dtList[i].thisPlan * 1 || 0).toFixed(2);
					setFieldValue(FM.dt2lastplan + '_' + rIndex, value, value);
				}
			}
		}
	};
	
	var getExternalThisPlan = function(extRow) {
		var yearMonth = $('#' + FM.yearmonth).val();
		var month = yearMonth.substring(4, 6) * 1 - 1;
		var season = parseInt(month / 3) + 1;
		return extRow['season' + season] / 3;
	};
	
	var loadLostMonthPlanDt3 = function(dtList) {
		//通过 HTTP GET 请求载入 JSON 数据。
		var yearMonth = $('#' + FM.yearmonth).val();
		var year = yearMonth.substring(0, 4);
		$.getJSON('/greedc/servlets/BPBussExp-listExternalPay.json', {
			company: companyId,
			year: year,
			time: new Date().getTime()
		}, function(resp) {
			dtList = dtList || [];
			var indexNum = $('#indexnum2').val() - 1;
			for(var i = 0; i < dtList.length; i++){
				var dt1AccountId  = dtList[i].accountId * 1;
				for (var rIndex = 0; rIndex <= indexNum; rIndex++) {
					var rowAccountId = $('#' + FM.dt3accountid+'_' + rIndex).val() * 1;
					if (rowAccountId == dt1AccountId) {
						var value = (dtList[i].thisPlan * 1 || 0).toFixed(2);
						setFieldValue(FM.dt3lastplan+'_' + rIndex, value, value);
					}
				}
			}
			var indexNum = $('#indexnum2').val() - 1;
			for (var rIndex = 0; rIndex <= indexNum; rIndex++) {
				for(var i = 0; i < resp.length; i++){
					var rowAccountId = $('#' + FM.dt3accountid+'_' + rIndex).val() * 1;
					if (rowAccountId == resp[i].accountId) {
						var thisPlan = getExternalThisPlan(resp[i]);
						var value = (thisPlan * 1 || 0).toFixed(2);
						setFieldValue(FM.dt3thisplan+'_' + rIndex, value, value);
					}
				}
			}
		});
	};
	
	var loadLostMonthPlanDt4 = function(dtList) {
		dtList = dtList || [];
		var indexNum = $('#indexnum3').val() - 1;
		for(var i = 0; i < dtList.length; i++){
			var dt1AccountId  = dtList[i].accountId * 1;
			for (var rIndex = 0; rIndex <= indexNum; rIndex++) {
				var rowAccountId = $('#' + FM.dt4accountid+'_' + rIndex).val() * 1;
				if (rowAccountId == dt1AccountId) {
					var value = (dtList[i].thisPlan * 1 || 0).toFixed(2);
					setFieldValue(FM.dt4lastplan+'_' + rIndex, value, value);
				}
			}
		}
	};
	
	var loadLostMonthPlanDt5 = function(dtList) {
		dtList = dtList || [];
		var indexNum = $('#indexnum4').val() - 1;
		for(var i = 0; i < dtList.length; i++){
			var dt1AccountId  = dtList[i].accountId * 1;
			for (var rIndex = 0; rIndex <= indexNum; rIndex++) {
				var rowAccountId = $('#' + FM.dt5accountid+'_' + rIndex).val() * 1;
				if (rowAccountId == dt1AccountId) {
					var value = (dtList[i].thisPlan * 1 || 0).toFixed(2);
					setFieldValue(FM.dt5lastplan+'_' + rIndex, value, value);
				}
			}
		}
	};
	
	/**
	 * 加载上月资金计划
	 */
	var loadLastMonthPlan = function() {
		var companyId = $('#' + FM.company).val();
		var lastYearMonth = getLastYearMonth();
		$('#' + FM.lastyearmonth).val(lastYearMonth);	// 给上月期间字段赋值
		
		//通过 HTTP GET 请求载入 JSON 数据。
		$.getJSON('/greedc/servlets/BPCompanyPlan-getBPCompanyPlan.json', {
			companyId: companyId,
			yearMonth: lastYearMonth,
			time: new Date().getTime()
		}, function(resp) {
			var main = resp.main || 0;
			setFieldValue(FM.lastmonthbegin, (main.thisMonthPlan || 0).toFixed(2));
			loadLostMonthPlanDt1(resp.dt1);	// 外部经营性收入
			calSum(0);	//  触发列字段合计功能
			
			loadLostMonthPlanDt2(resp.dt2);
			calSum(1);
			
			loadLostMonthPlanDt3(resp.dt3);
			calSum(2);
			
			loadLostMonthPlanDt4(resp.dt4);
			calSum(3);
			
			loadLostMonthPlanDt5(resp.dt5);
			calSum(4);
		});
	
	};
	
	/**
	 * 加载填报截止日期
	 */
	var loadSubmitEndDate = function() {
		//通过 HTTP GET 请求载入 JSON 数据。
		$.getJSON('/greedc/servlets/BPCompany-getCompanyInfo.json', {
			companyId: companyId,
			time: new Date().getTime()
		}, function(resp) {
			var yearMonth = $('#' + FM.yearmonth).val();
			var value = yearMonth.substring(0, 4) + '-' + yearMonth.substring(4, 6) + '-' + resp.submitEndDate;
			setFieldValue(FM.submitenddate, value, value);
		});
	};
	
	/**
	 * 计算差额率
	 */
	var calDiffRate = function(happen, plan) {
		return happen * 1 ? ((happen - plan) / happen * 100).toFixed(2) : '0.00';
	};
	
	/**
	 * 初始化期间。
	 */
	var setYearMonth = function() {
		var yearMonth = $('#' + FM.yearmonth).val();
		if (!yearMonth) {
			var year = new Date().getFullYear();
			var month = new Date().getMonth() + 1;
			month = (month < 10 ? '0' : '') + month;
			yearMonth = year + month;
			setFieldValue(FM.yearmonth, yearMonth, yearMonth);
		}
		
		loadSubmitEndDate();	// 加载填报截止日期
		
		// 如果是编辑模板，则不需要跳转至指定期间，新建模板才需要跳转
		if ($('#billid').val() && $('#billid').val() > 0) {
			return ;
		}
		
		$.getJSON('/greedc/servlets/BPCompanyPlan-getCompanyPlanMain.json', {
			companyId: companyId,
			yearMonth: yearMonth,
			time: new Date().getTime()
		}, function(resp) {
			if (resp.id) {
				window.parent.location.href = '/formmode/view/AddFormMode.jsp?modeId=361&formId=-110&billid=' + resp.id;
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
		initForm();
	});
	
});