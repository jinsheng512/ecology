/**
 * 
 */
$(document).ready(function(){
	var blocks = {};
	var tdRowspan = {};
	
	$('#title').text($('#year').val() + '年项目资金需求情况表');
	
	$.getJSON('/greedc/servlets/EasyUI-dataGrid.json?name=CV_COMPANYPROJECT_ALL', {
		time: new Date().getTime()
	}, function(projects) {
		for (var i = 0; i < projects.length; i++) {
			var blockName = projects[i].BLOCKNAME;
			var subcompanyName = projects[i].SUBCOMPANYNAME;
			var projectName = projects[i].PROJECTNAME;
			blocks[blockName] = blocks[blockName] || {};
			blocks[blockName][subcompanyName] = blocks[blockName][subcompanyName] || [];
			blocks[blockName][subcompanyName].push(projects[i]);
		}
		loadProjectAmount();
		viewProjects();
		megreBlock();
	});
	
	var loadProjectAmount = function() {
		var year = $('#year').val();
		$.getJSON('/greedc/servlets/EasyUI-dataGrid.json?name=CV_PROJECT_AMOUNT', {
			time: new Date().getTime(),
			defFilter: 'LASTYEARMONTH {LIKE} \'' + year + '__' + '\''
		}, function(projectAmounts) {
			viewProjectAmount(projectAmounts);
			summaryBlock();
			summaryGroup();
			summaryGroupPlanHppn();
		});
	};
	
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
		for (var block in blocks) {
			var companys = blocks[block];
			for (company in companys) {
				var projects = companys[company];
				for (i in projects) {
					var tdPrefix = '<td>' + index++ + '</td>';
					tdPrefix += '<td blockName=' + block + ' blockId=' + projects[i].BLOCKID + '>' + block + '</td>';
					tdPrefix += '<td companyName=' + company + '>' + company + '</td>';
					tdPrefix += '<td projectName=' + projects[i].PROJECTNAME + '>' + projects[i].PROJECTNAME + '</td>';
					var tdHtml = generateProjectHtmls(projects[i].PROJECTID, block);
					trHtmls += '<tr class="tr_data">' + tdPrefix + tdHtml + '</tr>';
				}
			}
			
			var tdPrefix = '<td>&nbsp;</td>';
			tdPrefix += '<td>&nbsp;</td>';
			tdPrefix += '<td>&nbsp;</td>';
			tdPrefix += '<td>总计</td>';
			var tdHtml = generateProjectHtmls('block', 'summary' + block);
			trHtmls += '<tr class="tr_block">' + tdPrefix + tdHtml + '</tr>';
			
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
	
	var generateProjectHtmls = function(projectId, blockName) {
		var tdHtml = '<td class="summary_' + projectId + '_plan_01" summaryBlockName="' + blockName + '_plan_01">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_01" summaryBlockName="' + blockName + '_hppn_01">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_plan_02" summaryBlockName="' + blockName + '_plan_02">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_02" summaryBlockName="' + blockName + '_hppn_02">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_plan_03" summaryBlockName="' + blockName + '_plan_03">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_03" summaryBlockName="' + blockName + '_hppn_03">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_plan_04" summaryBlockName="' + blockName + '_plan_04">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_04" summaryBlockName="' + blockName + '_hppn_04">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_plan_05" summaryBlockName="' + blockName + '_plan_05">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_05" summaryBlockName="' + blockName + '_hppn_05">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_plan_06" summaryBlockName="' + blockName + '_plan_06">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_06" summaryBlockName="' + blockName + '_hppn_06">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_plan_07" summaryBlockName="' + blockName + '_plan_07">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_07" summaryBlockName="' + blockName + '_hppn_07">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_plan_08" summaryBlockName="' + blockName + '_plan_08">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_08" summaryBlockName="' + blockName + '_hppn_08">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_plan_09" summaryBlockName="' + blockName + '_plan_09">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_09" summaryBlockName="' + blockName + '_hppn_09">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_plan_10" summaryBlockName="' + blockName + '_plan_10">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_10" summaryBlockName="' + blockName + '_hppn_10">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_plan_11" summaryBlockName="' + blockName + '_plan_11">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_11" summaryBlockName="' + blockName + '_hppn_11">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_plan_12" summaryBlockName="' + blockName + '_plan_12">&nbsp;</td>';
		tdHtml += '<td class="summary_' + projectId + '_hppn_12" summaryBlockName="' + blockName + '_hppn_12">&nbsp;</td>';
		return tdHtml;
	};
	
	var megreBlock = function() {
		for (var block in blocks) {
			var len = $('td[blockName=' + block + ']').length;
			$('td[blockName=' + block + ']').eq(0).attr('rowspan', len);
			for (var i = 1; i < len; i++) {
				$('td[blockName=' + block + ']').eq(1).remove();
			}
			
			var companys = blocks[block];
			megreCompany(companys);
		}
	};
	
	var megreCompany = function(companys) {
		for (var company in companys) {
			var len = $('td[companyName=' + company + ']').length;
			$('td[companyName=' + company + ']').eq(0).attr('rowspan', len);
			for (var i = 1; i < len; i++) {
				$('td[companyName=' + company + ']').eq(1).remove();
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
});