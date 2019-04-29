/**
 * 
 */
$(document).ready(function(){
	var blocks = {};
	var tdRowspan = {};
	var projects ={};
	
	$('#title').text( '还款明细表');
	
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	$.getJSON('/greedc/servlets/Loan-dataGridRepay.json?name=CV_LOADREPAYINFO', {
		time: new Date().getTime(),
		startDate: startDate,
		endDate: endDate
	}, function(projects) {
		console.log(blocks);
		for (var i = 0; i < projects.length; i++) {
			var companyName = projects[i].COMPANYNAME;
			var bankName = projects[i].BANKNAME;
			var shouxed = projects[i].SHOUXED;
			blocks[companyName] = blocks[companyName] || {};
			blocks[companyName][bankName] = blocks[companyName][bankName] || [];
			blocks[companyName][bankName].push(projects[i]);
			
		}
		viewProjects();
		megreBlock();
		
	});
	
	var viewProjects = function() {
		var index = 1;
		var trHtmls = '';
		for (var companyName in blocks) {
			var companys = blocks[companyName];
			for (company in companys) {
				var projects = companys[company];
				for (i in projects) {
					var tdPrefix = '<td>' + index++ + '</td>';
					
					if(projects[i].COMPANYNAME == undefined){ // 1公司
						
						tdPrefix += '<td companyName=' +  projects[i].COMPANYNAME  + '>' + 无 + '</td>';

					}else{
						
						tdPrefix += '<td companyName=' +  projects[i].COMPANYNAME  + '>' + projects[i].COMPANYNAME + '</td>';
						
					}
					
					
					if(projects[i].BANKNAME == undefined){ // 2金融机构
						
						tdPrefix += '<td bankName=' + projects[i].BANKNAME + '>' + 无 + '</td>';
						
					}else{
						
						tdPrefix += '<td bankName=' + projects[i].BANKNAME + '>' + projects[i].BANKNAME + '</td>';
					}
						
					
					
					if(projects[i].SHOUXED == undefined){ // 3授信额度
						
						tdPrefix += '<td shouxed=' + projects[i].SHOUXED + '>' + 0 + '</td>';

						
					}else{
						
						
						tdPrefix += '<td shouxed=' + projects[i].SHOUXED + '>' + projects[i].SHOUXED + '</td>';
					}
					
					
					if(projects[i].JIEKJE == undefined){ // 4余额
						
						tdPrefix += '<td jiekje=' + projects[i].JIEKJE + '>' + 0 + '</td>';

					}else{
						
						tdPrefix += '<td jiekje=' + projects[i].JIEKJE + '>' + projects[i].JIEKJE + '</td>';
					}
					
					if(projects[i].QISRQ == undefined){ //5起始日期
						
						tdPrefix += '<td qisrq=' + projects[i].QISRQ + '>' + "" + '</td>';

						
					}else{
						
						tdPrefix += '<td qisrq=' + projects[i].QISRQ + '>' + projects[i].QISRQ + '</td>';
					}
					
					if(projects[i].ZHONGZRQ == undefined){ //6终止日期
						
						tdPrefix += '<td zhongzrq=' + projects[i].ZHONGZRQ + '>' + ""+ '</td>';
					}else{
						
						tdPrefix += '<td zhongzrq=' + projects[i].ZHONGZRQ + '>' + projects[i].ZHONGZRQ + '</td>';
					}
					
					if(projects[i].HETLL == undefined){ ///7合同利率
						
						tdPrefix += '<td hetll=' + projects[i].HETLL + '>' + 0 + '</td>';

						
					}else{
						
						tdPrefix += '<td hetll=' + projects[i].HETLL + '>' + projects[i].HETLL  + '</td>';
						
					}
					
					if(projects[i].JIEKZCB == undefined){ //8总成本
						
						tdPrefix += '<td jiekzcb=' + projects[i].JIEKZCB + '>' + 0+ '</td>';

					}else{
						
						tdPrefix += '<td jiekzcb=' + projects[i].JIEKZCB + '>' + projects[i].JIEKZCB + '</td>';
					}
					
					
	              if(projects[i].JIEKDH == undefined){ //9付息方式
						
						tdPrefix += '<td jiekdh=' + projects[i].JIEKDH + '>' + "无" + '</td>';

						
					}else{
						
						tdPrefix += '<td jiekdh=' + projects[i].JIEKDH + '>' + projects[i].JIEKDH + '</td>';
					}
					
					
					//
					
	                if(projects[i].YUJHKRQ == undefined){ //10预计还款日
						
						tdPrefix += '<td yujhkrq=' + projects[i].YUJHKRQ + '>' + "无" + '</td>';

						
					}else{
						
						tdPrefix += '<td yujhkrq=' + projects[i].YUJHKRQ + '>' + projects[i].YUJHKRQ + '</td>';
					}
					
					
					
					
                  if(projects[i].SHIJHKRQ == undefined){ //11实际还款日
						
						tdPrefix += '<td shijhkrq=' + projects[i].SHIJHKRQ + '>' + "无" + '</td>';

					}else{
						
						tdPrefix += '<td shijhkrq=' + projects[i].SHIJHKRQ + '>' + projects[i].SHIJHKRQ + '</td>';
						
					}
					
					if(projects[i].HUANKJE == undefined){ //12还款金额
						
						tdPrefix += '<td huankje=' + projects[i].HUANKJE + '>' + "无" + '</td>';

					}else{
						
						
						tdPrefix += '<td huankje=' + projects[i].HUANKJE + '>' + projects[i].HUANKJE + '</td>';
					}
					
					
					if(projects[i].HUANKFS == undefined){ //13还款方式
						
						tdPrefix += '<td huankfs=' + projects[i].HUANKFS + '>' + "无" + '</td>';

						
					}else{
						
						tdPrefix += '<td huankfs=' + projects[i].HUANKFS + '>' + projects[i].HUANKFS + '</td>';
					}
					
                   if(projects[i].DUIYYWDJ == undefined){ //14还款方式
						
						tdPrefix += '<td duiyywdj=' + projects[i].DUIYYWDJ + '>' + "无" + '</td>';

						
					}else{
						
						tdPrefix += '<td duiyywdj=' + projects[i].DUIYYWDJ + '>' + projects[i].DUIYYWDJ + '</td>';
					}
                   
                   
                   if(projects[i].BEIZ == undefined){ //15还款方式
						
						tdPrefix += '<td beiz=' + projects[i].BEIZ + '>' + "无" + '</td>';

						
					}else{
						
						tdPrefix += '<td beiz=' + projects[i].BEIZ + '>' + projects[i].BEIZ + '</td>';
					}
					
					trHtmls += '<tr class="tr_data">' + tdPrefix  + '</tr>';
				}
			}
			
		}
		$('.summary_head').after(trHtmls);
	}
	

	
	
	var megreBlock = function() {
		for (var COMPANYNAME in blocks) {
			var len = $('td[companyName=' + COMPANYNAME + ']').length;
			$('td[companyName=' + COMPANYNAME + ']').eq(0).attr('rowspan', len);
			for (var i = 1; i < len; i++) {
				$('td[companyName=' + COMPANYNAME + ']').eq(1).remove();
			}
			
			var companys = blocks[COMPANYNAME];
			megreCompany(companys);
			
//			var banks = blocks[COMPANYNAME];
//			megreShouxed(banks);
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
	
	var megreShouxed = function(banks) {
		for (var bank in banks) {
			var len = 4;
			$('td[shouxed=' + bank + ']').eq(0).attr('rowspan', len);
			for (var i = 1; i < len; i++) {
				$('td[shouxed=' + bank + ']').eq(1).remove();
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
		
		$.getJSON('/greedc/servlets/Loan-dataGridRepay.json?name=CV_LOADREPAYINFO', {
			time: new Date().getTime(),
			startDate: startDate,
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
			megreBlock();
			
		});
//		
	});
	
	var init = function() {
		
	};

});