$(document).ready(function(){
	var DATA = {
		projectNames: {},
		rows: {},
		totalRow: {}
	};

	var divide = function(dividend, divided, defValue){
		if (divided != 0) {
			return dividend / divided;
		}
		return defValue;
	};

	var FN = {
		getServData: function(url, params, fn) {
			params.time = new Date().getTime();
			$.getJSON(url, params, fn);
		},
		servData: {
			rowsByBuilding: function() {
				var startDate = $('#startDate').val();
				var endDate = $('#endDate').val();
				var url = '/greedc/servlets/InventoryReport-getRowsByBuilding.json';
				var params = {
					startDate: startDate,
					endDate: endDate
				};
				FN.getServData(url, params, function (resp) {
					FN.processServData(resp);
				});
			}
		},
		analysisData: function() {
			var rows = DATA.servData;
			DATA.projectNames = {};
			DATA.rows = {};
			$('#project>option').remove();
			$('#project').append('<option value="all">所有项目</option>');
			for (var i = 0; i < rows.length; i++) {
				var projectName = rows[i]['projectName'];
				var area = rows[i]['area'] || '-';
				var productType = rows[i]['productType'];
				var building = rows[i]['building'];
				if (!DATA.projectNames[projectName]) {
					DATA.projectNames[projectName] = {};
					$('#project').append('<option value="' + projectName + '">' + projectName + '</option>');
				}
				if (!DATA.projectNames[projectName][area]) {
					DATA.projectNames[projectName][area] = {};
				}
				if (!DATA.projectNames[projectName][area][productType]) {
					DATA.projectNames[projectName][area][productType] = [];
				}
				DATA.projectNames[projectName][area][productType].push(building);
				DATA.rows[projectName + area + productType + building] = rows[i];
			}
		},
		addRow: function(row1, row2) {
			row1 = row1 || {};
			row2 = row2 || {};
			var retRow = {
				totalQuantity: (row1.totalQuantity || 0) + (row2.totalQuantity || 0),
				totalArea: (row1.totalArea || 0) + (row2.totalArea || 0),
				salesQuantity: (row1.salesQuantity || 0) + (row2.salesQuantity || 0),
				salesArea: (row1.salesArea || 0) + (row2.salesArea || 0),
				salesAmount: (row1.salesAmount || 0) + (row2.salesAmount || 0),
				persistQuantity: (row1.persistQuantity || 0) + (row2.persistQuantity || 0),
				persistArea: (row1.persistArea || 0) + (row2.persistArea || 0),
				inventoryQuantity: (row1.inventoryQuantity || 0) + (row2.inventoryQuantity || 0),
				inventoryArea: (row1.inventoryArea || 0) + (row2.inventoryArea || 0)
			};
			retRow.avgPrice = divide(retRow.salesAmount, retRow.salesArea, 0);
			return retRow;
		},
		sumRows: function(projectName, area, productType, building) {
			var row = DATA.rows[projectName + area + productType + building];
			DATA.totalRow.total = FN.addRow(DATA.totalRow.total, row);
			DATA.totalRow[projectName] = FN.addRow(DATA.totalRow[projectName], row);
			DATA.totalRow[projectName + area] = FN.addRow(DATA.totalRow[projectName + area], row);
			DATA.totalRow[projectName + area + productType] = FN.addRow(DATA.totalRow[projectName + area + productType], row);
		},
		generateRow: function(needStartTr, tdTag, name, colspan, row) {
			var cellspanHtml = '';
			var classAttr = '';
			if (colspan > 1) {
				cellspanHtml = ' colspan="' + colspan + '"';
				classAttr = ' class="total_' + colspan + '"';
			} else if ('合计' == name) {
				classAttr = ' class="total_1"';
			}
			var html = needStartTr ? '<tr class="data">' : '';
			html += '<th' + cellspanHtml + '>' + name + '</th>'
			html += '<' + tdTag + classAttr + ' style="text-align: right;">' + formatNumber(row.totalQuantity, 0) + '</' + tdTag + '>';
			html += '<' + tdTag + classAttr + ' style="text-align: right;">' + formatNumber(row.totalArea, 2) + '</' + tdTag + '>';
			html += '<' + tdTag + classAttr + ' style="text-align: right;">' + formatNumber(row.salesQuantity, 0) + '</' + tdTag + '>';
			html += '<' + tdTag + classAttr + ' style="text-align: right;">' + formatNumber(row.salesArea, 2) + '</' + tdTag + '>';
			html += '<' + tdTag + classAttr + ' style="text-align: right;">' + toMoney(row.salesAmount, 2) + '</' + tdTag + '>';
			html += '<' + tdTag + classAttr + ' style="text-align: right;">' + toMoney(row.avgPrice, 2) + '</' + tdTag + '>';
			html += '<' + tdTag + classAttr + ' style="text-align: right;">' + formatNumber(row.persistQuantity, 0) + '</' + tdTag + '>';
			html += '<' + tdTag + classAttr + ' style="text-align: right;">' + formatNumber(row.persistArea, 2) + '</' + tdTag + '>';
			html += '<' + tdTag + classAttr + ' style="text-align: right;">' + formatNumber(row.inventoryQuantity, 0) + '</' + tdTag + '>';
			html += '<' + tdTag + classAttr + ' style="text-align: right;">' + formatNumber(row.inventoryArea, 2) + '</' + tdTag + '>';
			html += '</tr>';
			return html;
		},
		generateRows_building: function(projectName, area, productType) {
			var rowOption = {};
			rowOption.html = '';
			rowOption.count = 0;
			var buildings = DATA.projectNames[projectName][area][productType];
			for (var i = 0; i < buildings.length; i++) {
				var row = DATA.rows[projectName + area + productType + buildings[i]];
				row.avgPrice = divide(row.salesAmount, row.salesArea, 0);
				rowOption.html += FN.generateRow(rowOption.html != '', 'td', buildings[i], '', row);
				rowOption.count++;
				FN.sumRows(projectName, area, productType, buildings[i]);
			}
			rowOption.html += FN.generateRow(true, 'th', '合计', 1, DATA.totalRow[projectName + area + productType]);
			rowOption.count++;
			return rowOption;
		},
		generateRows_productType: function(projectName, area) {
			var rowOption = {};
			rowOption.count = 0;
			rowOption.html = '';
			var productTypes = DATA.projectNames[projectName][area];
			for (var productType in productTypes) {
				var row = FN.generateRows_building(projectName, area, productType);
				rowOption.html += (rowOption.html == '') ? '' : '<tr class="data">';
				rowOption.html += '<th rowspan="' + row.count + '">' + productType + '</th>';
				rowOption.html += row.html;
				rowOption.count += row.count;
			}
			rowOption.html += FN.generateRow(true, 'th', '合计', 2, DATA.totalRow[projectName + area]);
			rowOption.count++;
			return rowOption;
		},
		generateRows_area: function(projectName) {
			var rowOption = {};
			rowOption.count = 0;
			rowOption.html = '';
			for (var area in DATA.projectNames[projectName]) {
				var row = FN.generateRows_productType(projectName, area);
				rowOption.html += (rowOption.html == '') ? '' : '<tr class="data">';
				rowOption.html += '<th rowspan="' + row.count + '">' + area + '</th>';
				rowOption.html += row.html;
				rowOption.count += row.count;
			}
			rowOption.html += FN.generateRow(true, 'th', '合计', 3, DATA.totalRow[projectName]);
			rowOption.count++;
			return rowOption;
		},
		generateRows_project: function() {
			$('table#dg>tbody>tr.data').remove();
			DATA.totalRow = {};
			var rowOption = {};
			var selectProject = $('#project').val();
			rowOption.html = '';
			for (var i = 0; i < projectOrder.length; i++) {
				if (DATA.projectNames[projectOrder[i]] != undefined
					&& (selectProject == 'all' || selectProject == projectOrder[i])) {
					var row = FN.generateRows_area(projectOrder[i]);
					rowOption.html += '<tr class="data">';
					rowOption.html += '<th rowspan="' + row.count + '">' + projectOrder[i] + '</th>';
					rowOption.html += row.html;
				}
			}
			rowOption.html += FN.generateRow(true, 'th', '合计', 4, DATA.totalRow.total);
			$('#dg').append(rowOption.html);
		},
		processServData: function (resp) {
			DATA.servData = resp;
			FN.analysisData();
			FN.generateRows_project();
		},
		event: {
			dateChange: function() {
				FN.servData.rowsByBuilding();
			}
		}
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

	$('#startDate').bind('click', function(){
		WdatePicker({
			el: 'startDate',
			readOnly: true,
			onpicked: FN.event.dateChange
		});
	});

	$('#endDate').bind('click', function(){
		WdatePicker({
			el: 'endDate',
			readOnly: true,
			onpicked: FN.event.dateChange
		});
	});

	$('#btnExportExcel').bind('click', function(){
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
		$('#exportData').val(exportRowsStr);
		$('#fileName').val('格力地产库存报表（楼栋）');
		$('#formExport').submit();
	});

	$('#project').bind('change', function(){
		FN.generateRows_project();
	});

	FN.servData.rowsByBuilding();
});