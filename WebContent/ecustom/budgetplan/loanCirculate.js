/**
 * 格力地产资金借还明细表
 */
//
$(document).ready(function(){
	
	var bankInfo = {};
	var projectInfo = {};
	var loanInfo = {};
	var curYear;
	var trHtml1;
	
	
	
	var myDate = new Date();
	curYear = myDate.getFullYear();
	
	$('#curYear').text(curYear+'年');
	
	
	$("#choose").on("change",function(){
		
		if ($("#choose").val() == '1') {
			
			curYear=2019;
			loadDataInfo(curYear);
		}
		
		if ($("#choose").val() == '2') {
			
			curYear=2020;
			loadDataInfo(curYear);
			
		}
		
		if ($("#choose").val() == '3') {
			
			curYear=2021;
			loadDataInfo(curYear);
			
		}
		
		if ($("#choose").val() == '4') {
			
			curYear=2022;
			loadDataInfo(curYear);
		}
		
		if ($("#choose").val() == '5') {
			
			curYear=2023;
			loadDataInfo(curYear);
		}
	  		
		});
	
	/**
	 * 从数据中获取金融机构
	 */
	var loadAccountInfo = function() {
	
		$.getJSON('/greedc/servlets/Loan-getLoanBank.json', {
			time: new Date().getTime(),
		}, function(resp) {
			for (var i = 0; i < resp.length; i++) {
				bankInfo['' + resp[i].id] = resp[i].jinrjg;
			}
			
			loadDataInfo(curYear);
		});
		
		//从数据中获取项目
		//通过 HTTP GET 请求载入 JSON 数据。
		
		$.getJSON('/greedc/servlets/Loan-getLoanProject.json', {
			time: new Date().getTime()
		}, function(resp) {
			for (var i = 0; i < resp.length; i++) {
				projectInfo['' + resp[i].id] = resp[i].xiangmmc;
			}
			
		});
	};
	
	$.getJSON('/greedc/servlets/Loan-getLoanInfo.json', {
		time: new Date().getTime()
	}, function(resp) {
		for (var i = 0; i < resp.length; i++) {
			loanInfo['' + resp[i].id] = resp[i].jinrjg;
		}
		
	});

	
	var sumAmount = function(dtList) {
		// 借款小计
		var amount = {
				
				 jiekje: 0
		};
		for (var i = 0; i < dtList.length; i++) {
			amount.jiekje += dtList[i].jiekje * 1;
		}
		
		return amount;
	};
	
	var sumAmount2 = function(dtList) {
		// 还款小计
		var amount = {
				// 
				 jiekje: 0
		};
		for (var i = 0; i < dtList.length; i++) {
			amount.jiekje += dtList[i].huankje * 1;
		}
		
		return amount;
	};
	

    
	
	var sumByClass = function(className) {
		var amount = 0;
		$('.' + className).each(function(i){
			amount += $(this).text() * 1;
		});
		return amount;
	};
	

	
	/**
	 * 加载资金计划额。
	 */
	var loadDataInfo = function(curYear) {
	
		//通过 HTTP GET 请求载入 JSON 数据。
		$.getJSON('/greedc/servlets/Loan-getBPCompanyPlan.json', {
			year:curYear,
			time: new Date().getTime(),
		}, function(resp) {
		
			$('#curYear').text(curYear+'年');
			
			$('.right').remove();
		
			showPlan1(resp.dt1, resp.dt_back1,resp.dt1.id);	//1月，借，还
			showPlan2(resp.dt2, resp.dt_back2,resp.dt1.id);	// 2月
			showPlan3(resp.dt3, resp.dt_back3,resp.dt1.id);	// 3
			showPlan4(resp.dt4, resp.dt_back4,resp.dt1.id);	// 4
			showPlan5(resp.dt5, resp.dt_back5,resp.dt1.id);	// 5
			
			showPlan6(resp.dt6, resp.dt_back6,resp.dt1.id);	// 6
			showPlan7(resp.dt7, resp.dt_back7,resp.dt1.id);	// 7
			showPlan8(resp.dt8, resp.dt_back8,resp.dt1.id);	// 8
			showPlan9(resp.dt9, resp.dt_back9,resp.dt1.id);	// 9
			showPlan10(resp.dt10, resp.dt_back10,resp.dt1.id);	// 10
			
			showPlan11(resp.dt11, resp.dt_back11,resp.dt1.id);	// 11
			showPlan12(resp.dt12, resp.dt_back12,resp.dt1.id);	// 12
			
			
			calMoney();
		});
	};
	
	/**
	 * 	12个月总计
	 */
	var calMoney = function() {
		
		var totalAmount = {};  //4
		totalAmount.jiekje = sumByClass('incomeDra'); //借款
		totalAmount.huankje = sumByClass('spendDra'); //还款

	
		$('#monthEndInDra').text(toMoney(totalAmount.jiekje.toFixed(2)));
		$('#monthEndOutDra').text(toMoney(totalAmount.huankje.toFixed(2)));
	};

	
	var showPlan1 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml  = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
	
		for (var i = 0; i < len; i++) {
			trHtml += 
				  '<tr id="showPlan1"  class="right" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ? toMoney((dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
			  
		}
		
		
		$('#trBuss1').before(trHtml);
		
		
	
		showAmount('bussIn1',sumAmount(dtBussIn));	// 借款小计
		showAmount('bussOut1', sumAmount2(dtBussOut));	//还款小计
	};





	var showPlan2 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
		
			trHtml += '<tr  id="showPlan2"  class="right" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ? toMoney((dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
		}
		$('#trBuss2').before(trHtml);
		
		showAmount('bussIn2', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut2', sumAmount2(dtBussOut));	// 汇总经营性支出数据
	};

	var showPlan3 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += '<tr  id="showPlan3" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ? toMoney((dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
		}
		$('#trBuss3').before(trHtml);
		
		showAmount('bussIn3', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut3', sumAmount2(dtBussOut));	// 汇总经营性支出数据
	};



	var showPlan4 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += '<tr  id="showPlan4" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ? toMoney((dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
		}
		$('#trBuss4').before(trHtml);
		
		showAmount('bussIn4', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut4', sumAmount2(dtBussOut));	// 汇总经营性支出数据
	};


	var showPlan5 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += '<tr  id="showPlan5" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ? toMoney((dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
		}
		$('#trBuss5').before(trHtml);
		
		showAmount('bussIn5', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut5', sumAmount2(dtBussOut));	// 汇总经营性支出数据
	};

	var showPlan6 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += '<tr  id="showPlan6" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ? toMoney((dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
		}
		$('#trBuss6').before(trHtml);
		
		showAmount('bussIn6', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut6', sumAmount2(dtBussOut));	// 汇总经营性支出数据
	};

	var showPlan7 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += '<tr  id="showPlan7" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ?toMoney( (dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
		}
		$('#trBuss7').before(trHtml);
		
		showAmount('bussIn7', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut7', sumAmount2(dtBussOut));	// 汇总经营性支出数据
	};

	var showPlan8 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += '<tr  id="showPlan8" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ? toMoney((dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
		}
		$('#trBuss8').before(trHtml);
		
		showAmount('bussIn8', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut8', sumAmount2(dtBussOut));	// 汇总经营性支出数据
	};

	var showPlan9 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += 
				    '<tr  id="showPlan9" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ?toMoney((dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
		}
		$('#trBuss9').before(trHtml);
		
		showAmount('bussIn9', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut9', sumAmount2(dtBussOut));	// 汇总经营性支出数据
	};


	var showPlan10 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += '<tr  id="showPlan10" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ? toMoney((dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
		}
		$('#trBuss10').before(trHtml);
		
		showAmount('bussIn10', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut10', sumAmount2(dtBussOut));	// 汇总经营性支出数据
	};

	var showPlan11 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += '<tr  id="showPlan11" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ? toMoney((dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
		}
		$('#trBuss11').before(trHtml);
		
		showAmount('bussIn11', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut11', sumAmount2(dtBussOut));	// 汇总经营性支出数据
	};

	var showPlan12 = function(dtBussIn, dtBussOut,id) {
		dtBussIn = dtBussIn || [];
		dtBussOut = dtBussOut || [];
		var trHtml = '', len = dtBussIn.length > dtBussOut.length ? dtBussIn.length : dtBussOut.length;
		for (var i = 0; i < len; i++) {
			trHtml += '<tr  id="showPlan12" onclick="doCLick('+id+')">'
				+ 	'<td>&nbsp;</td>'
				+ 	'<td id="a" style="width:8%" class="right">&nbsp;'+ (dtBussIn[i] ? (dtBussIn[i].qisrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussIn[i] ? (bankInfo['' + (dtBussIn[i].jinrjg)]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussIn[i] ? toMoney((dtBussIn[i].jiekje.toFixed(2))) : '') + '</td>'
				+ 	'<td class="right" style="width:15%">&nbsp;' + (dtBussIn[i] ? (projectInfo['' + (dtBussIn[i].xiangm)]) : '') + '</td>'
				
				+ 	'<td class="right" style="width:8%">&nbsp;' + (dtBussOut[i] ? (dtBussOut[i].shijhkrq) : '') + '</td>'
				+ 	'<td class="right" style="width:12%">&nbsp;' + (dtBussOut[i] ? (bankInfo['' +loanInfo['' + (dtBussOut[i].mainid)]]) : '') + '</td>'
				+ 	'<td class="right" style="width:10%">&nbsp;' + (dtBussOut[i] ? toMoney((dtBussOut[i].huankje.toFixed(2))) : '0.00') + '</td>'
				+ 	'<td class="right"style="width:15%">&nbsp;' + (dtBussOut[i] ? ((dtBussOut[i].beiz)) : '无') + '</td>'
				+ '</tr>';
		}
		$('#trBuss12').before(trHtml);
		
		showAmount('bussIn12', sumAmount(dtBussIn));	// 汇总经营性收入数据
		showAmount('bussOut12', sumAmount2(dtBussOut));	// 汇总经营性支出数据
	};

	
	
	var showAmount = function(label, amount) {
		$('#' + label + 'Dra').text((amount.jiekje.toFixed(2)));
		
	};
	
	
	var doCLick = function (billId){
		   
	    window.parent.location.href = '/formmode/view/AddFormMode.jsp?modeId=401&formId=-129&billid='+ billId;
	};



	$('#btnExport').bind('click', function(){
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
	
	
	 var toMoney = function(num) {
			num = isNaN(num) ? 0 : num * 1;
			var lessZero = num < 0;
			var float = (num - parseInt(num)).toFixed(2).substring(1, 4);	// 取小数位
			var integer = Math.abs(parseInt(num)) + '';	// 取整数位
			var money = '', split = '';
			while (integer.length > 3) {
				split = money.length > 0 ? ',' : '';
				money = (integer.substring(integer.length - 3, integer.length)) + split + money;
				integer = integer.substring(0, integer.length - 3);
			}
			split = money.length > 0 ? ',' : '';
			money = integer + split + money + float;
			return (lessZero ? '-' : '') + money;
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

	loadAccountInfo();
});




  	

