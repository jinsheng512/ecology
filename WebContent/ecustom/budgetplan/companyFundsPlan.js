/**
 * 格力地产资金计划明细表
 */
//
$(document).ready(function(){
	
	var accountInfo = {};
	
	var getNextYearMonth = function() {
		var yearMonth = $('#yearMonth').val();
		var yearStr = yearMonth.substring(0, 4);
		var monthStr = yearMonth.substring(4, 6);
		if (monthStr == '12') {
			yearStr = yearStr*1 + 1;
			monthStr = '01';
		} else {
			monthStr = monthStr*1 + 1;
			if (monthStr < 10) {
				monthStr = '0' + monthStr;
			}
		}
		var ys = yearStr + monthStr;
		return ys;
	};
	
	var getLastYearMonth = function() {
		var yearMonth = $('#yearMonth').val();
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
	
	var getCompanyInfo = function(){
		$.getJSON('/greedc/servlets/BPCompany-getCompanyInfo.json', {
			companyId: $('#companyId').val(),
			time: new Date().getTime()
		}, function(resp) {
			$("#companyName").text(resp.subCompanyName);
		});
	};
	
	/**
	 * 从数据中获取科目信息
	 */
	var loadAccountInfo = function() {
		var companyId = $("#companyId").val();
		$.getJSON('/greedc/servlets/BPCompanyAccount-getCompanyAccount.json', {
			companyId: companyId,
			time: new Date().getTime(),
		}, function(resp) {
			for (var i = 0; i < resp.length; i++) {
				accountInfo['' + resp[i].accountL2] = resp[i].accountName;
			}
			
			loadPlanInfo();
		});
		
		//BP03-公司项目
		//通过 HTTP GET 请求载入 JSON 数据。
		
		$.getJSON('/greedc/servlets/BPCompanyProject-getCompanyProject.json', {
			companyId: companyId,
			time: new Date().getTime()
		}, function(resp) {
			for (var i = 0; i < resp.length; i++) {
				accountInfo['' + resp[i].id] = resp[i].projectName;
			}
			
		});
	};
	
	var sumAmount = function(dtList) {
		// 汇总经营性收入数据
		var amount = {
				// 发生额、差额、差额率、下月计划额
				happened: 0, diffAmount: 0, diffRate: 0, nextPlan: 0, lastPlan: 0
		};
		for (var i = 0; i < dtList.length; i++) {
			amount.happened += dtList[i].lastHappend * 1;
			amount.diffAmount += dtList[i].lastHappend - dtList[i].lastPlan;
			amount.nextPlan += dtList[i].thisPlan * 1;
		}
		if(amount.happened == 0){
			amount.diffRate = 0;
		}else{
			amount.diffRate = ((amount.diffAmount / amount.happened) * 100).toFixed(2);
		}
		
		return amount;
	};
	
	var showAmount = function(label, amount) {
		$('#' + label + 'Hap').text(amount.happened.toFixed(2));
		$('#' + label + 'Dam').text(amount.diffAmount.toFixed(2));
		$('#' + label + 'Dra').text(amount.diffRate + '%');
		$('#' + label + 'Npl').text(amount.nextPlan.toFixed(2));
	};
	
	/**
	 * 显示：外部经营性收入、外部经营性支出
	 * @param dtBussIn	外部经营性收入明细
	 * @param dtBussOut	外部经营性支出明细
	 */
	var showPlan2_5 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += '<tr onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td>&nbsp;' + (dtBussIn[i] ? ((i + 1) + '.' + accountInfo['' + dtBussIn[i].accountId]) : '') + '</td>'
				+ 	'<td id="a" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].lastHappend.toFixed(2)) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtBussIn[i] ? ((dtBussIn[i].lastHappend - dtBussIn[i].lastPlan).toFixed(2)) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtBussIn[i] ? (dtBussIn[i].difference + '%') : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtBussIn[i] ? (dtBussIn[i].thisPlan.toFixed(2)) : '') + '</td>'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td>&nbsp;' + (dtBussOut[i] ? ((i + 1) + '.' + accountInfo['' + dtBussOut[i].accountId]) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].lastHappend.toFixed(2)) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].lastHappend - dtBussOut[i].lastPlan).toFixed(2)) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].difference + '%') : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].thisPlan.toFixed(2)) : '') + '</td>'
				+ '</tr>';
		}
		$('#trBuss').after(trHtml);
		
		showAmount('bussIn', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut', sumAmount(dtBussOut));	// 汇总经营性支出数据
	};
	
	

	
	/**
	 * 显示：筹资收入、项目投资支出
	 * @param dtFnaIn	筹资收入明细
	 * @param dtProjOut	项目投资支出明细
	 */
	var showPlan3_6 = function(dtFnaIn, dtProjOut,id) {
		dtFnaIn = dtFnaIn || [];
		dtProjOut = dtProjOut || [];
		var trHtml = '', len = dtFnaIn.length > dtProjOut.length ? dtFnaIn.length : dtProjOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += '<tr onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td>&nbsp;' + (dtFnaIn[i] ? ((i + 1) + '.' + accountInfo['' + dtFnaIn[i].accountId]) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtFnaIn[i] ? (dtFnaIn[i].lastHappend.toFixed(2)) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtFnaIn[i] ? ((dtFnaIn[i].lastHappend - dtFnaIn[i].lastPlan).toFixed(2)) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtFnaIn[i] ? (dtFnaIn[i].difference + '%') : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtFnaIn[i] ? (dtFnaIn[i].thisPlan.toFixed(2)) : '') + '</td>'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td>&nbsp;' + (dtProjOut[i] ? ((i + 1) + '.' + accountInfo['' + dtProjOut[i].accountId]) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtProjOut[i] ? (dtProjOut[i].lastHappend.toFixed(2)) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtProjOut[i] ? ((dtProjOut[i].lastHappend - dtProjOut[i].lastPlan).toFixed(2)) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtProjOut[i] ? (dtProjOut[i].difference + '%') : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtProjOut[i] ? (dtProjOut[i].thisPlan.toFixed(2)) : '') + '</td>'
				+ '</tr>';
		}
		$('#trFnaIn').after(trHtml);
		
		showAmount('FnaIn', sumAmount(dtFnaIn));	// 筹资收入数据
		showAmount('ProjIn', sumAmount(dtProjOut));	// 项目投资支出数据
	};
	
	/**
	 * 显示：筹资支出
	 * @param dtFnaOut	筹资支出明细
	 */
	var showPlan7 = function(dtFnaOut,id) {
		dtFnaOut = dtFnaOut || [];
		var trHtml = '';
		for (var i = 0; i < dtFnaOut.length; i++) {
			trHtml += '<tr onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td class="right">&nbsp;</td>'
				+ 	'<td class="right">&nbsp;</td>'
				+ 	'<td class="right">&nbsp;</td>'
				+ 	'<td class="right">&nbsp;</td>'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td>&nbsp;' + (dtFnaOut[i] ? ((i + 1) + '.' + accountInfo['' + dtFnaOut[i].accountId]) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtFnaOut[i] ? (dtFnaOut[i].lastHappend.toFixed(2)) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtFnaOut[i] ? ((dtFnaOut[i].lastHappend - dtFnaOut[i].lastPlan).toFixed(2)) : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtFnaOut[i] ? (dtFnaOut[i].difference + '%') : '') + '</td>'
				+ 	'<td class="right">&nbsp;' + (dtFnaOut[i] ? (dtFnaOut[i].thisPlan.toFixed(2)) : '') + '</td>'
				+ '</tr>';
		}
		
		$('#trFnaOut').after(trHtml);
		
		showAmount('FnaOut', sumAmount(dtFnaOut));	// 筹资收入数据
		
	};
	
	var sumByClass = function(className) {
		var amount = 0;
		$('.' + className).each(function(i){
			amount += $(this).text() * 1;
		});
		return amount;
	};
	
	/**
	 * 	现金收入(2+3)、	现金支出(5+6+7)
	 */
	var calCash = function() {
		
		var incomeAmount = {};  //4
		incomeAmount.happened = sumByClass('incomeHap');
		incomeAmount.diffAmount = sumByClass('incomeDam');
		incomeAmount.nextPlan = sumByClass('incomeNpl');
		if(incomeAmount.happened==0){
			incomeAmount.diffRate = 0;
		}else{
			incomeAmount.diffRate = (incomeAmount.diffAmount / incomeAmount.happened * 100).toFixed(2);
		}
	
		var happenedAmount = {};  //8
		happenedAmount.happened = sumByClass('spendHap');
		happenedAmount.diffAmount = sumByClass('spendDam');
		happenedAmount.nextPlan = sumByClass('spendNpl');
		if(happenedAmount.happened==0){
			happenedAmount.diffRate = 0;
		}else{
			happenedAmount.diffRate = (happenedAmount.diffAmount / happenedAmount.happened * 100).toFixed(2);
		}
		
		var cashBeginAmount = {};  //1
		cashBeginAmount.happened = sumByClass('cashBeginHap');
		cashBeginAmount.diffAmount = sumByClass('cashBeginDam');
		cashBeginAmount.nextPlan = sumByClass('cashBeginNpl');
		if(cashBeginAmount.happened==0){
			cashBeginAmount.diffRate = 0;
		}else{
			cashBeginAmount.diffRate = (cashBeginAmount.diffAmount / cashBeginAmount.happened * 100).toFixed(2);
		}
		
		var monthEndAmount = {};
		monthEndAmount.happened = cashBeginAmount.happened +incomeAmount.happened - happenedAmount.happened;
		monthEndAmount.diffAmount = cashBeginAmount.diffAmount +incomeAmount.diffAmount - happenedAmount.diffAmount;
		monthEndAmount.nextPlan = cashBeginAmount.nextPlan +incomeAmount.nextPlan - happenedAmount.nextPlan;
		if(monthEndAmount.happened==0){
			monthEndAmount.diffRate = 0;
		}else{
			monthEndAmount.diffRate = (monthEndAmount.diffAmount / monthEndAmount.happened * 100).toFixed(2);
		}
		

		showAmount('cashIn', incomeAmount);	// 现金收入
		showAmount('cashOut', happenedAmount);	// 现金支出
		showAmount('monthEnd', monthEndAmount);	// 现金月末余额
	
	};

	/**
	 * 加载资金计划额。
	 */
	var loadPlanInfo = function() {
		var companyId = $("#companyId").val();
		//通过 HTTP GET 请求载入 JSON 数据。
		$.getJSON('/greedc/servlets/BPCompanyPlan-getBPCompanyPlan.json', {
			companyId: companyId,
			yearMonth: getNextYearMonth(),
			time: new Date().getTime(),
		}, function(resp) {
			var yearMonth = $('#yearMonth').val();
			var value = yearMonth.substring(0, 4) + '年' + yearMonth.substring(4, 6) + '月';
			$('#yearAndMonth').text(value);
			getCompanyInfo();
			showPlan2_5(resp.dt1, resp.dt3,resp.main.id);	// 外部经营性收入，外部经营性支出
			showPlan3_6(resp.dt2, resp.dt4,resp.main.id);	// 筹资收入，项目投资支出
			showPlan7(resp.dt5,resp.main.id);	// 筹资支出
			
			showBeginCash(resp.main.lastMonthBegin,resp.main.thisMonthPlan); // 现金月初余额
			calCash();
		});
	};
	
	
	var showBeginCash = function(lastMonthBegin,thisMonthPlan) {
		var happenedAmount = {};
		
		happenedAmount.happened = lastMonthBegin;
		happenedAmount.nextPlan = thisMonthPlan;
		happenedAmount.diffAmount = thisMonthPlan-lastMonthBegin;
		
		if(lastMonthBegin==0){
			happenedAmount.diffRate = 0;
		}else{
			happenedAmount.diffRate = (happenedAmount.diffAmount / happenedAmount.happened * 100).toFixed(2);
		}
		
		showAmount('cashBegin', happenedAmount);	// 现金月初余额
	}
	
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
	
	loadAccountInfo();
});

var doCLick = function (billId){
   
    window.parent.location.href = '/formmode/view/AddFormMode.jsp?modeId=401&formId=-129&billid='+ billId;
};