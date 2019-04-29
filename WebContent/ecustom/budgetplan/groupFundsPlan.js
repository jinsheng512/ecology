/**
 * 格力地产资金计划明细表
 */
//
$(document).ready(function(){
	
	var config = {};	// 配置信息
	var accountList = [];	// 科目信息
	
	var getYearMonthFilter = function() {
		var reportType = $('#reportType').val();	// 1-月度，2-季度，3-年度
		var yearMonth = $('#yearMonth').val();
		// 月度条件
		if (reportType == 1) {
			return 'LASTYEARMONTH=\'' + yearMonth + '\'';
		} 
		// 季度条件
		else if (reportType == 2) {
			var year = yearMonth.substring(0, 4);
			var month = yearMonth.substring(4, 6) * 1 - 1;
			if (parseInt(month / 3) == 0) {
				return 'LASTYEARMONTH IN (\'' + year + '01' + '\', \'' + year + '02' + '\', \'' + year + '03' + '\')';
			} else if (parseInt(month / 3) == 1) {
				return 'LASTYEARMONTH IN (\'' + year + '04' + '\', \'' + year + '05' + '\', \'' + year + '06' + '\')';
			} else if (parseInt(month / 3) == 2) {
				return 'LASTYEARMONTH IN (\'' + year + '07' + '\', \'' + year + '08' + '\', \'' + year + '09' + '\')';
			} else if (parseInt(month / 3) == 3) {
				return 'LASTYEARMONTH IN (\'' + year + '10' + '\', \'' + year + '11' + '\', \'' + year + '12' + '\')';
			}
		}
		// 季度条件
		else if (reportType == 3) {
			var year = yearMonth.substring(0, 4);
			return 'LASTYEARMONTH {LIKE} \'' + year + '__' + '\'';
		}
	};
	
	/**
	 * 加载配置信息
	 */
	var loadConfig = function() {
		$.getJSON('/greedc/servlets/BPConfig-getConfig.json', {
			time: new Date().getTime()
		}, function(_config) {
			if (_config.status == false || !_config.id) {
				alert('加载配置失败！');
				return ;
			}
			config = _config;
			loadAccountL2();
		});
	};
	
	/**
	 * 加载科目信息
	 */
	var loadAccountL2 = function() {
		$.getJSON('/greedc/servlets/Record-listAll.json', {
			name: 'CV_GROUPFUNDS_ACCOUNT',
			field: 'id,idL2,accountNameL2',
			sort: 'dataOrder',
			time: new Date().getTime()
		}, function(_accountList) {
			if (_accountList.status == false || _accountList.length == 0) {
				alert('加载科目信息失败！');
				return ;
			}
			accountList = _accountList;
			showAccount();
		});
	};
	
	/**
	 * 显示科目信息到页面中
	 */
	var showAccount = function() {
		var accountMap = splitAccount();
		// 外部经营性收入、外部经营性支出
		showAccountI_O('trBuss', accountMap[config.bussIncAccount + 'l1'], accountMap[config.bussExpAccount + 'l1']);

		// 筹资收入、项目投资支出
		showAccountI_O('trFnaIn', accountMap[config.financeIncAccount + 'l1'], accountMap[config.projectExpAccount + 'l1']);

		// 空、筹资支出
		showAccountI_O('trFnaOut', [], accountMap[config.financeExpAccount + 'l1']);
		
		// 显示现金月初余额
		showCashMonthHap();
	};
	
	/**
	 * 将科目进行分类。
	 */
	var splitAccount = function() {
		var accountMap = {};
		for (var i = 0; i < accountList.length; i++) {
			var account = accountList[i];
			if (!accountMap[account.id + 'l1']) {
				accountMap[account.id + 'l1'] = [];
			}
			accountMap[account.id + 'l1'].push(account);
		}
		return accountMap;
	};
	
	/**
	 * 显示科目信息到页面中
	 */
	var showAccountI_O = function(trId, inList, exList) {
		var inLen = inList.length;
		var exLen = exList.length;
		var rowLen = inLen > exLen ? inLen : exLen, trHtml = '';
		for (var i = 0; i < rowLen; i++) {
			var inId = inList[i] ? inList[i].idL2 : '0';
			var exId = exList[i] ? exList[i].idL2 : '0';
			var inPId = inList[i] ? inList[i].id : '0';
			var exPId = exList[i] ? exList[i].id : '0';
			trHtml += '<tr>'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td>&nbsp;' + (inList[i] ? ((i + 1) + '.' + inList[i].accountNameL2) : '') + '</td>'
				+ 	'<td class="right l1_' + inPId + '" id="hapAmount' +  inId + '">&nbsp;</td>'
//				+ 	'<td class="right l1_' + inPId + '" id="dfaAmount' +  inId + '">&nbsp;</td>'
//				+ 	'<td class="right l1_' + inPId + '" id="dfrAmount' +  inId + '">&nbsp;</td>'
				+ 	'<td class="right l1_' + inPId + '" id="nplAmount' +  inId + '">&nbsp;</td>'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td>&nbsp;' + (exList[i] ? ((i + 1) + '.' + exList[i].accountNameL2) : '') + '</td>'
				+ 	'<td class="right l1_' + exPId + '" id="hapAmount' +  exId + '">&nbsp;</td>'
//				+ 	'<td class="right l1_' + exPId + '" id="dfaAmount' +  exId + '">&nbsp;</td>'
//				+ 	'<td class="right l1_' + exPId + '" id="dfrAmount' +  exId + '">&nbsp;</td>'
				+ 	'<td class="right l1_' + exPId + '" id="nplAmount' +  exId + '">&nbsp;</td>'
				+ '</tr>';
		}
		$('#' + trId).after(trHtml);
	};
	
	/**
	 * 显示现金月初余额“实际发生”。
	 */
	var showCashMonthHap = function() {
		$.getJSON('/greedc/servlets/Record-listAll.json', {
			name: 'CV_GROUPFUNDS_CASH',
			field: 'lastMonthBegin',
			defFilter: getYearMonthFilter(),
			time: new Date().getTime()
		}, function(resp) {
			if (resp.status == false || resp.length == 0) {
				alert('加载现金月初余额信息失败！');
				return ;
			}
			$('#hapAmountCashBegin').text((resp[0].lastMonthBegin * 1).toFixed(2));
			showCashMonthNpl();
		});
	};

	
	/**
	 * 显示现金月初余额“计划”。
	 */
	var showCashMonthNpl = function() {
		$.getJSON('/greedc/servlets/Record-listAll.json', {
			name: 'CV_GROUPFUNDS_CASH',
			field: 'thisMonthPlan',
			defFilter: getYearMonthFilter(),
			time: new Date().getTime()
		}, function(resp) {
			if (resp.status == false || resp.length == 0) {
				alert('加载现金月初余额信息失败！');
				return ;
			}
			$('#nplAmountCashBegin').text((resp[0].thisMonthPlan * 1).toFixed(2));
			showAccountHappened();
		});
	};
	
	/**
	 * 显示科目发生额。
	 */
	var showAccountHappened = function() {
		$.getJSON('/greedc/servlets/Record-listAll.json', {
			name: 'CV_GROUPFUNDS_AMOUNT',
			field: 'accountId,lastHappend,lastPlan',
			defFilter: getYearMonthFilter(),
			time: new Date().getTime()
		}, function(resp) {
			if (resp.status == false || resp.length == 0) {
				alert('加载科目发生额信息失败！');
				return ;
			}
			for (var i = 0; i < resp.length; i++) {
				var pageAmount = ($('#hapAmount' + resp[i].accountId).text() || 0) * 1;
				var hapAmount = resp[i].lastHappend * 1 + pageAmount;
				$('#hapAmount' + resp[i].accountId).text(hapAmount.toFixed(2));
				
//				var lplAmount = resp[i].lastPlan * 1;
//				var dfaAmount = hapAmount - lplAmount;
//				$('#dfaAmount' + resp[i].accountId).text(dfaAmount.toFixed(2));
//				$('#dfrAmount' + resp[i].accountId).text((dfaAmount / hapAmount * 100).toFixed(2));
			}
			showAccountPlan();
		});
	};
	
	/**
	 * 显示科目计划额。
	 */
	var showAccountPlan = function() {
		$.getJSON('/greedc/servlets/Record-listAll.json', {
			name: 'CV_GROUPFUNDS_AMOUNT',
			field: 'accountId,thisPlan',
			defFilter: getYearMonthFilter(),
			time: new Date().getTime()
		}, function(resp) {
			if (resp.status == false || resp.length == 0) {
				alert('加载显示科目计划额信息失败！');
				return ;
			}
			for (var i = 0; i < resp.length; i++) {
				var pageAmount = ($('#nplAmount' + resp[i].accountId).text() || 0) * 1;
				var hapAmount = resp[i].thisPlan * 1 + pageAmount;
				$('#nplAmount' + resp[i].accountId).text(hapAmount.toFixed(2));
			}
			showSum();	
		});	
	};
	
	/**
	 * 显示合计。
	 */
	var showSum = function() {
		showSumAccount(config.bussIncAccount, 'hapAmount', 'BussIn');
		showSumAccount(config.bussIncAccount, 'nplAmount', 'BussIn');
		
		showSumAccount(config.bussExpAccount, 'hapAmount', 'BussEx');
		showSumAccount(config.bussExpAccount, 'nplAmount', 'BussEx');
		
		showSumAccount(config.financeIncAccount, 'hapAmount', 'FinaIn');
		showSumAccount(config.financeIncAccount, 'nplAmount', 'FinaIn');
		
		showSumAccount(config.projectExpAccount, 'hapAmount', 'ProjEx');
		showSumAccount(config.projectExpAccount, 'nplAmount', 'ProjEx');
		
		showSumAccount(config.financeExpAccount, 'hapAmount', 'FinaEx');
		showSumAccount(config.financeExpAccount, 'nplAmount', 'FinaEx');
		
		showCashIn();
		showCashEx();
		showCashMonthEnd();
	};
	
	/**
	 * 显示现金收入。
	 */
	var showCashIn = function() {
		var cashHap = $('#hapAmountBussIn').text() * 1 + $('#hapAmountFinaIn').text() * 1;
		$('#hapAmountCashIn').text(cashHap.toFixed(2));

		var cashNpl = $('#nplAmountBussIn').text() * 1 + $('#nplAmountFinaIn').text() * 1;
		$('#nplAmountCashIn').text(cashNpl.toFixed(2));
	};
	
	/**
	 * 显示现金支出。
	 */
	var showCashEx = function() {
		
		var cashHap = $('#hapAmountBussEx').text() * 1;
		
			cashHap += $('#hapAmountProjEx').text() * 1;
			cashHap += $('#hapAmountFinaEx').text() * 1;
			$('#hapAmountCashEx').text(cashHap.toFixed(2));
		
		

		var cashNpl = $('#nplAmountBussEx').text() * 1;
		cashNpl += $('#nplAmountProjEx').text() * 1;
		cashNpl += $('#nplAmountFinaEx').text() * 1;
		$('#nplAmountCashEx').text(cashNpl.toFixed(2));
	};
	
	/**
	 * 显示现金月末余额
	 */
	var showCashMonthEnd = function() {
			var cashHap = $('#hapAmountCashBegin').text() * 1;
			cashHap += $('#hapAmountCashIn').text() * 1;
			cashHap -= $('#hapAmountCashEx').text() * 1;
			$('#hapAmountCashEnd').text(cashHap.toFixed(2));
		
			var cashNpl = $('#nplAmountCashBegin').text() * 1;
			cashNpl += $('#nplAmountCashIn').text() * 1;
			cashNpl -= $('#nplAmountCashEx').text() * 1;
			$('#nplAmountCashEnd').text(cashNpl.toFixed(2));
		
	};
	
	/**
	 * 显示外部经营性收入合计
	 */
	var showSumAccount = function(accountId, column, lable) {
		var eList = $('td.l1_' + accountId + '[id^=' + column + ']');
		var sum = 0;
		for(var i = 0; i < eList.length; i++) {
			sum += $(eList[i]).text() * 1;
		}
		$('#' + column + lable).text(sum.toFixed(2));
	};
	
	var colorRgbToHex = function(rgb){
		if (rgb == 'rgba(0, 0, 0, 0)' || rgb == 'transparent') {
			return '';
		}
		var aColor = rgb.replace(/(?:\(|\)|rgb|RGB)*/g, "").split(",");
		var strHex = "#";
		for(var i=0; i < aColor.length; i++){
			var hex = Number(aColor[i]).toString(16);
			if(hex === "0"){
				hex += hex;		
			}
			strHex += hex;
		}
		if(strHex.length !== 7){
			strHex = rgb;		
		}
		return strHex;
	};

	$('#btnExportExcel').bind('click', function(){
		var exportRows = [];
		$('#tbFunds>tbody>tr').each(function(){
			var cells = [];
			$(this).children().each(function(){
				var cell = $(this);
				cells.push({
					value: cell.text(),
					rowspan: cell.attr('rowspan'),
					colspan: cell.attr('colspan'),
					backgroundColor: colorRgbToHex(cell.css('background-color')),
					fontWeight: cell.css('font-weight'),
					fontColor: colorRgbToHex(cell.css('color')),
					textAlign: cell.css('text-align')
				});
			});
			exportRows.push(cells);
		});
		var exportRowsStr = JSON.stringify(exportRows);
		$('#exportData').val(exportRowsStr);
		$('#fileName').val('格力地产资金计划明细表');
		$('#formExport').submit();
	});

	loadConfig();	// 加载配置信息，加载后继续加载科目信息
});