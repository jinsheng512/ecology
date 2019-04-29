/**
 * 
 */
$(document).ready(function(){
	var blocks = {};
	var tdRowspan = {};
	var jkdwmcid;
	var daiklxid;
	var kuaijkmid;
	var xiangmmcid;
	var danbhtid;
	var shouxhthid;
	var jinrjgid;
	var fuxfsid;
	var diyhtbhid;
	
	$('#title').text( '借款明细表');
	
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	$.getJSON('/greedc/servlets/Loan-dataGrid.json?name=CV_LOADINFO', {
		time: new Date().getTime(),
		q: encodeURIComponent($('#keyString').val()),
		startDate: startDate,
		filter:'companyName',
		endDate: endDate
	}, function(projects) {

		for (var i = 0; i < projects.length; i++) {
			var companyName = projects[i].COMPANYNAME;
			var bankName = projects[i].BANKNAME;
			var shouxed = projects[i].SHOUXED;
			blocks[companyName] = blocks[companyName] || {};
			blocks[companyName][bankName] = blocks[companyName][bankName] || [];
			blocks[companyName][bankName].push(projects[i]);
		}
		viewProjects();
//		megreBlock();
		
	});
	
	
	$.getJSON('/greedc/servlets/Loan-getSearchData.json?', {
		time: new Date().getTime(),
		
	}, function(projects) {
		
		for (var i = 0; i < projects.jkcompanyList.length; i++) {
			
			$("#company").append('<option value='+projects.jkcompanyList[i].id + '>'+projects.jkcompanyList[i].jkdwmc +'</option>');
		}
		
		for (var i = 0; i < projects.daiktypeList.length; i++) {
			
			$("#daiklx").append('<option value='+projects.daiktypeList[i].id + '>'+projects.daiktypeList[i].daiklx +'</option>');
		}
		
		for (var i = 0; i < projects.kjsubjectList.length; i++) {
			
			$("#kuaijkm").append('<option value='+projects.kjsubjectList[i].id + '>'+projects.kjsubjectList[i].kuaijkm +'</option>');
		}
		
		for (var i = 0; i < projects.projectList.length; i++) {
			
			$("#project").append('<option value='+projects.projectList[i].id + '>'+projects.projectList[i].xiangmmc +'</option>');
		}
		
		for (var i = 0; i < projects.guaranteeList.length; i++) {
			
			$("#danbht").append('<option value='+projects.guaranteeList[i].id + '>'+projects.guaranteeList[i].danbht +'</option>');
		}
		
		for (var i = 0; i < projects.lettersList.length; i++) {
			
			$("#shouxhth").append('<option value='+projects.lettersList[i].id + '>'+projects.lettersList[i].shouxhth +'</option>');
		}
		
		for (var i = 0; i < projects.bankList.length; i++) {
			
			$("#jinrjg").append('<option value='+projects.bankList[i].id + '>'+projects.bankList[i].jinrjg +'</option>');
		}
		
		for (var i = 0; i < projects.interesttypeList.length; i++) {
			
			$("#fuxfs").append('<option value='+projects.interesttypeList[i].id + '>'+projects.interesttypeList[i].fuxfs +'</option>');
		}
		
		for (var i = 0; i < projects.mortgageList.length; i++) {
		
			$("#diyht").append('<option value='+projects.mortgageList[i].id + '>'+projects.mortgageList[i].diyhtbh +'</option>');
		}
	
		
	});
	
	
	$("#company").on("change",function(){
		
		jkdwmcid = $("#company").val();
		
	});
	
	$("#daiklx").on("change",function(){
		
		daiklxid = $("#daiklx").val();
		
	});
	
	$("#kuaijkm").on("change",function(){
		
		kuaijkmid = $("#kuaijkm").val();
		
	});
	
	$("#project").on("change",function(){
		
		xiangmmcid = $("#project").val();
		
	});
	
	$("#danbht").on("change",function(){
		
		danbhtid = $("#danbht").val();
		
	});
	
	$("#shouxhth").on("change",function(){
		
		shouxhthid = $("#shouxhth").val();
		
	});
	
	$("#jinrjg").on("change",function(){
		
		jinrjgid = $("#jinrjg").val();
		
	});
	
	$("#fuxfs").on("change",function(){
		
		fuxfsid = $("#fuxfs").val();
		
	});
	
	$("#diyht").on("change",function(){
		
		diyhtbhid = $("#diyht").val();
		
	});
	
	
	
	var viewProjectAmount = function(projectAmounts) {
		for (var i = 0; i < projectAmounts.length; i++) {
			var projectId = projectAmounts[i].ACCOUNTID;
			var month = projectAmounts[i].LASTYEARMONTH.substring(4, 6);
			var plan = (projectAmounts[i].LASTPLAN * 1).toFixed(2);
			var hppn = (projectAmounts[i].LASTHAPPEND * 1).toFixed(2);
			$('.summary_' + projectId + '_plan_' + month).text(plan);
			$('.summary_' + projectId + '_hppn_' + month).text(hppn);
			
			$('.summary_' + projectId + '_plan_' + month).attr('title', parseInt(month) + '月计划');
			$('.summary_' + projectId + '_hppn_' + month).attr('title', parseInt(month) + '月实际');
		}
	};
	
	var viewProjects = function() {
		var index = 1;
		var trHtmls = '';
		for (var companyName in blocks) {
			var companys = blocks[companyName];
			for (company in companys) {
				var projects = companys[company];
				for (i in projects) {
					var tdPrefix = '<td>' + index++ + '</td>';
					tdPrefix += '<td companyName=' +  projects[i].COMPANYNAME  + '>' + projects[i].COMPANYNAME + '</td>';
					tdPrefix += '<td bankName=' + projects[i].BANKNAME + '>' + projects[i].BANKNAME + '</td>';
					
					if(projects[i].SHOUXED == undefined){ // 授信额度
						
						tdPrefix += '<td shouxed=' + projects[i].SHOUXED + '>' + 0 + '</td>';

						
					}else{
						
						
						tdPrefix += '<td shouxed=' + projects[i].SHOUXED + '>' + toMoney(projects[i].SHOUXED) + '</td>';
					}
					
					
					if(projects[i].YUE == undefined){ // 余额
						
						tdPrefix += '<td yue=' + projects[i].YUE + '>' + 0 + '</td>';

					}else{
						
						tdPrefix += '<td yue=' + projects[i].YUE + '>' + projects[i].YUE + '</td>';
					}
					
					if(projects[i].SHOUXHTH == undefined){ // 授信合同
						
						tdPrefix += '<td shouxhth=' + projects[i].SHOUXHTH + '>' + 0 + '</td>';

					}else{
						
						tdPrefix += '<td shouxhth=' + projects[i].SHOUXHTH + '>' + projects[i].SHOUXHTH + '</td>';
					}
					
					if(projects[i].DAIKLX == undefined){ // 贷款类型
						
						tdPrefix += '<td daiklx=' + projects[i].DAIKLX + '>' + 0 + '</td>';

					}else{
						
						tdPrefix += '<td daiklx=' + projects[i].DAIKLX + '>' + projects[i].DAIKLX + '</td>';
					}
					
					if(projects[i].QISRQ == undefined){ //起始日期
						
						tdPrefix += '<td qisrq=' + projects[i].QISRQ + '>' + "" + '</td>';

						
					}else{
						
						tdPrefix += '<td qisrq=' + projects[i].QISRQ + '>' + projects[i].QISRQ + '</td>';
					}
					
					if(projects[i].ZHONGZRQ == undefined){ //终止日期
						
						tdPrefix += '<td zhongzrq=' + projects[i].ZHONGZRQ + '>' + ""+ '</td>';
					}else{
						
						tdPrefix += '<td zhongzrq=' + projects[i].ZHONGZRQ + '>' + projects[i].ZHONGZRQ + '</td>';
					}
					
					if(projects[i].JIEKLL == undefined){ ///借款利率
						
						tdPrefix += '<td jiekll=' + projects[i].JIEKLL + '>' + 0 + '</td>';

						
					}else{
						
						tdPrefix += '<td jiekll=' + projects[i].JIEKLL + '>' + projects[i].JIEKLL + '%' + '</td>';
						
					}
					
					if(projects[i].GUWF == undefined){ ///顾问费
						
						tdPrefix += '<td guwf=' + projects[i].GUWF + '>' + 0 + '</td>';

						
					}else{
						
						tdPrefix += '<td guwf=' + projects[i].GUWF + '>' + toMoney(projects[i].GUWF ) + '</td>';
						
					}
					
					if(projects[i].JIEKZCB == undefined){ //总成本
						
						tdPrefix += '<td jiekzcb=' + projects[i].JIEKZCB + '>' + 0+ '</td>';

					}else{
						
						tdPrefix += '<td jiekzcb=' + projects[i].JIEKZCB + '>' + projects[i].JIEKZCB + '</td>';
					}
					
					if(projects[i].DANBHT == undefined){ //担保合同
						
						tdPrefix += '<td danbht=' + projects[i].DANBHT + '>' + "无" + '</td>';

					}else{
						
						tdPrefix += '<td danbht=' + projects[i].DANBHT + '>' + projects[i].DANBHT + '</td>';
						
					}
					
					if(projects[i].DIYWMC == undefined){ //抵押
						
						tdPrefix += '<td diywmc=' + projects[i].DIYWMC + '>' + "无" + '</td>';

					}else{
						
						
						tdPrefix += '<td diywmc=' + projects[i].DIYWMC + '>' + projects[i].DIYWMC + '</td>';
					}
					
					if(projects[i].FUXFS == undefined){ //付息方式
						
						tdPrefix += '<td fuxfs=' + projects[i].FUXFS + '>' + "无"+ '</td>';

					}else{
						
						
						tdPrefix += '<td fuxfs=' + projects[i].FUXFS + '>' + projects[i].FUXFS + '</td>';
					}
					
					
					if(projects[i].XIANGMMC == undefined){ //项目
						
						tdPrefix += '<td xiangmmc=' + projects[i].XIANGMMC + '>' + "无"+ '</td>';

					}else{
						
						
						tdPrefix += '<td xiangmmc=' + projects[i].XIANGMMC + '>' + projects[i].XIANGMMC + '</td>';
					}
					
					if(projects[i].KUAIJKM == undefined){ //会计科目
						
						tdPrefix += '<td kuaijkm=' + projects[i].KUAIJKM + '>' + "无"+ '</td>';

					}else{
						
						
						tdPrefix += '<td kuaijkm=' + projects[i].KUAIJKM + '>' + projects[i].KUAIJKM + '</td>';
					}
					
					if(projects[i].JIEKDH == undefined){ //借款单号
						
						tdPrefix += '<td jiekdh=' + projects[i].JIEKDH + '>' + "无" + '</td>';

						
					}else{
						
						tdPrefix += '<td jiekdh=' + projects[i].JIEKDH + '>' + projects[i].JIEKDH + '</td>';
					}
					
					
					trHtmls += '<tr class="tr_data">' + tdPrefix  + '</tr>';
				}
			}
			
		}
		$('.summary_head').after(trHtmls);
	}
	
	var summaryBlock = function() {
		for (var i = 1; i <= 12; i++) {
			var month = (i < 10 ? '0' : '') + i;
			for (var block in blocks) {
				var plans = $('td[summaryblockname=' + block + '_plan_' + month + ']');
				var hppns = $('td[summaryblockname=' + block + '_hppn_' + month + ']');
				
				var planAmount = 0;
				for (var m = 0; m < plans.length; m++) {
					planAmount += ($(plans[m]).text() || 0) * 1;
				}
				$('td[summaryblockname=summary' + block + '_plan_' + month + ']').text(planAmount.toFixed(2));

				var hppnAmount = 0;
				for (var m = 0; m < hppns.length; m++) {
					hppnAmount += ($(hppns[m]).text() || 0) * 1;
				}
				$('td[summaryblockname=summary' + block + '_hppn_' + month + ']').text(hppnAmount.toFixed(2));
				
				$('td[summaryblockname=summary' + block + '_plan_' + month + ']').attr('title', parseInt(month) + '月计划');
				$('td[summaryblockname=summary' + block + '_hppn_' + month + ']').attr('title', parseInt(month) + '月实际');
			}
		}
	};
	
	var summaryGroup = function() {
		for (var i = 1; i <= 12; i++) {
			var month = (i < 10 ? '0' : '') + i;
			var plans = $('td.summary_block_plan_' + month);
			var hppns = $('td.summary_block_hppn_' + month);
			
			var planAmount = 0;
			for (var m = 0; m < plans.length; m++) {
				planAmount += ($(plans[m]).text() || 0) * 1;
			}
			$('td.summary_total_plan_' + month).text(planAmount.toFixed(2));
			
			var hppnAmount = 0;
			for (var m = 0; m < hppns.length; m++) {
				hppnAmount += ($(hppns[m]).text() || 0) * 1;
			}
			$('td.summary_total_hppn_' + month).text(hppnAmount.toFixed(2));
			
			$('td.summary_total_plan_' + month).attr('title', parseInt(month) + '月计划');
			$('td.summary_total_hppn_' + month).attr('title', parseInt(month) + '月实际');
		}
	};
	
	var summaryGroupPlanHppn = function() {
		for (var i = 1; i <= 12; i++) {
			var month = (i < 10 ? '0' : '') + i;
			var plans = $('td[class^=summary_total_plan_]');
			var hppns = $('td[class^=summary_total_hppn_]');
			
			var planAmount = 0;
			for (var m = 0; m < plans.length; m++) {
				planAmount += ($(plans[m]).text() || 0) * 1;
			}
			$('td.summary_year_plan').text(planAmount.toFixed(2));
			
			var hppnAmount = 0;
			for (var m = 0; m < hppns.length; m++) {
				hppnAmount += ($(hppns[m]).text() || 0) * 1;
			}
			$('td.summary_year_hppn').text(hppnAmount.toFixed(2));
		}
	};
	
	
	var megreBlock = function() {
		for (var COMPANYNAME in blocks) {
			var len = $('td[companyName=' + COMPANYNAME + ']').length;
			$('td[companyName=' + COMPANYNAME + ']').eq(0).attr('rowspan', len);
			for (var i = 1; i < len; i++) {
				$('td[companyName=' + COMPANYNAME + ']').eq(1).remove();
			}
			
			var companys = blocks[COMPANYNAME];
			megreCompany(companys);
		}
	};
	
	var megreCompany = function(companys) {
		for (var company in companys) {
			var len = $('td[bankName=' + company + ']').length;
			$('td[bankName=' + company + ']').eq(0).attr('rowspan', len);
			for (var i = 1; i < len; i++) {
				$('td[bankName=' + company + ']').eq(1).remove();
			}
		}
	};
	
	
	
	$('#btnExport').bind('click', function(){
		var exportRows = [];
		$('#dg>tbody>tr').each(function(){
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
		
		var $form = $("<form></form>");
		$form.append(generateHiddenInput('exportData', exportRowsStr));
		$form.append(generateHiddenInput('fileName', $('#title').text()));
		$form.attr('action', '/greedc/servlets/InventoryReport-download.xls');
		$form.attr('method', 'post');
		$form.css('display', 'none');
		$form.appendTo("body");
		$form.submit();
		$form.remove();
	});
	
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
	
	var generateHiddenInput = function(name, value) {
		return '<input type="hidden" name="' + name + '" value=\'' + value + '\' />';
	};
	
	$('#startDate').bind('click', function(){
		WdatePicker({
			el: 'startDate',
			readOnly: true,
//			onpicked: function(){alert(456);},
		});
	});

	$('#endDate').bind('click', function(){
		WdatePicker({
			el: 'endDate',
			readOnly: true,
//			onpicked: function(){alert(123);},
		});
	});
	
	$('#btnSearch').bind('click', function(){
		
		$(".tr_data").html("");
		$(".tr_data").remove();
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		
		 blocks = {};
		 tdRowspan = {};
		 projects ={};
		
		$.getJSON('/greedc/servlets/Loan-dataGrid.json?name=CV_LOADINFO', {
			time: new Date().getTime(),
			q: encodeURIComponent($('#keyString').val()),
			startDate: startDate,
			filter:'companyName',
			jkdwmcid: jkdwmcid,
			daiklxid: daiklxid,
			kuaijkmid: kuaijkmid,
			xiangmmcid: xiangmmcid,
			danbhtid: danbhtid,
			shouxhthid: shouxhthid,
			jinrjgid: jinrjgid,
			fuxfsid: fuxfsid,
			diyhtbhid: diyhtbhid,
			endDate: endDate
		}, function(projects) {

			for (var i = 0; i < projects.length; i++) {
				var companyName = projects[i].COMPANYNAME;
				var bankName = projects[i].BANKNAME;
				var shouxed = projects[i].SHOUXED;
				blocks[companyName] = blocks[companyName] || {};
				blocks[companyName][bankName] = blocks[companyName][bankName] || [];
				blocks[companyName][bankName].push(projects[i]);
			}
			viewProjects();
//			megreBlock();
			
		});
//		
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
	
	var init = function() {
		
	};

});