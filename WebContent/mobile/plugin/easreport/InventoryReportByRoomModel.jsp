<%@ page import="java.util.*, ecustom.util.*" %><%--
  User: William
  Date: 2017-9-6
  库存报表-户型
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String endDate = CustomUtil.dateFormat(new Date(), "yyyy-MM-dd");
	Calendar canlendar = Calendar.getInstance();
	canlendar.add(Calendar.MONTH, -1);
	String startDate = CustomUtil.dateFormat(canlendar.getTime(), "yyyy-MM-dd");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" user-scalable="no">
	<script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/ecustom/js/echarts.min.js"></script>
	<style type="text/css">
		* {
			font-size: 11px;
		}
	</style>
</head>
<body>
<table style="width: 100%;">
	<tr>
		<td width="50%">
			<span>项目：</span>
			<select id="project"></select>
		</td>
		<td width="50%">
			<span>地块(区)：</span>
			<select id="area"></select>
		</td>
	</tr>
	<tr>
		<td width="50%">
			<span>产品类型：</span>
			<select id="productType"></select>
		</td>
		<td width="50%">
			<span>显示类型：</span>
			<select id="type">
				<option value="amount">金额</option>
				<option value="area">面积</option>
				<option value="quantity">套数</option>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<span>日期范围：</span>
			<input type="date" id="startDate" style="width: 95px;" value="<%=startDate %>">
			<span> - </span>
			<input type="date" id="endDate" style="width: 95px;" value="<%=endDate %>">
		</td>
	</tr>
</table>
</div>
<div id="main" style="width: 100%; height:440px;"></div>
</body>
<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
	var myChart;

	var DATA = {
		legendType: {
			amount: ['销售金额'],
			area: ['库存面积', '保留面积', '销售面积', '总面积'],
			quantity: ['库存套数', '保留套数', '销售套数', '总套数']
		},
		seriesType: {
			amount: [
				{name: '销售金额', type: 'bar', data: [], barMaxWidth: 15, label: {normal: {show: true, position: 'right'}}}
			],
			area: [
				{name: '库存面积', type: 'bar', data: [], barMaxWidth: 15, label: {normal: {show: true, position: 'right'}}},
				{name: '保留面积', type: 'bar', data: [], barMaxWidth: 15, label: {normal: {show: true, position: 'right'}}},
				{name: '销售面积', type: 'bar', data: [], barMaxWidth: 15, label: {normal: {show: true, position: 'right'}}},
				{name: '总面积', type: 'bar', data: [], barMaxWidth: 15, label: {normal: {show: true, position: 'right'}}}
			],
			quantity: [
				{name: '库存套数', type: 'bar', data: [], barMaxWidth: 15, label: {normal: {show: true, position: 'right'}}},
				{name: '保留套数', type: 'bar', data: [], barMaxWidth: 15, label: {normal: {show: true, position: 'right'}}},
				{name: '销售套数', type: 'bar', data: [], barMaxWidth: 15, label: {normal: {show: true, position: 'right'}}},
				{name: '总套数', type: 'bar', data: [], barMaxWidth: 15, label: {normal: {show: true, position: 'right'}}}
			]
		}
	};

	var analysisData = function(rows) {
		DATA.projectNames = {};
		DATA.rows = {};
		DATA.totalRow = {};
		for (var i = 0; i < rows.length; i++) {
			var projectName = rows[i]['projectName'];
			var area = rows[i]['area'] || '-';
			var productType = rows[i]['productType'];
			var roomModel = rows[i]['roomModel'];
			if (!DATA.projectNames[projectName]) {
				DATA.projectNames[projectName] = {};
			}
			if (!DATA.projectNames[projectName][area]) {
				DATA.projectNames[projectName][area] = {};
			}
			if (!DATA.projectNames[projectName][area][productType]) {
				DATA.projectNames[projectName][area][productType] = [];
			}
			DATA.projectNames[projectName][area][productType].push(roomModel);
			DATA.rows[projectName + area + productType + roomModel] = rows[i];
		}
	};
	
	var option = {
		title: {},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'shadow'
			}
		},
		legend: {
			orient: 'horizontal',
			right: 2,
			top: 0,
			bottom: 0,
			left: 2,
			data: []
		},
		grid: {
			top: '25',
			left: '1%',
			right: '60',
			bottom: '28',
			containLabel: true
		},
		xAxis: {
			type: 'value',
			boundaryGap: [0, 0.01],
			axisLabel: {
				rotate: 35	//倾斜度 -90 至 90 默认为0
			}
		},
		yAxis: {
			type: 'category',
			data: []
		},
		series: []
	};

	var setFilterCrtl = function(type) {
		type = type || 'project';
		if ('project' == type) {
			$('#project>option').remove();
			for (var projectName in DATA.projectNames) {
				$('#project').append('<option value="' + projectName + '">' + projectName + '</option>');
			}
			setFilterCrtl('area', $('#project>option').eq(0).val());
		} else if ('area' == type) {
			$('#area>option').remove();
			var areas = DATA.projectNames[$('#project').val()]
			for (var area in areas) {
				$('#area').append('<option value="' + area + '">' + area + '</option>');
			}
			setFilterCrtl('productType', $('#area>option').eq(0).val());
		} else if ('productType' == type) {
			$('#productType>option').remove();
			var productTypes = DATA.projectNames[$('#project').val()][$('#area').val()];
			for (var productType in productTypes) {
				$('#productType').append('<option value="' + productType + '">' + productType + '</option>');
			}
			showInBar();
		}
	};

	var clearSeriesData = function() {
		for (var type in DATA.seriesType) {
			var series = DATA.seriesType[type];
			for (var i = 0; i < series.length; i++) {
				series[i].data = [];
			}
		}
	};

	var addChangeLine = function(value) {
		var retStr = '';
		var splitNum = 2;
		while (value.length > splitNum) {
			retStr += value.substring(0, splitNum) + '\n';
			value = value.substring(splitNum, value.length);
		}
		return retStr + value;
	}
	
	var showInBar = function() {
		var projectName = $('#project').val();
		var area = $('#area').val();
		var productType = $('#productType').val();
		var rows = DATA.projectNames[projectName][area][productType];
		var type = $('#type').val();
		clearSeriesData();
		option.legend.data = DATA.legendType[type];
		option.yAxis.data = [];
		option.series = DATA.seriesType[type];
		for (var i = 0; i < rows.length; i++) {
			var roomModel = rows[i];
			var row = DATA.rows[projectName + area + productType + roomModel]
			option.yAxis.data.push(addChangeLine(roomModel));
			if ('amount' == type) {
				option.series[0].data.push(row.salesAmount || '');
			} else if ('area' == type) {
				option.series[0].data.push(row.inventoryArea || '');
				option.series[1].data.push(row.persistArea || '');
				option.series[2].data.push(row.salesArea || '');
				option.series[3].data.push(row.totalArea || '');
			} else if ('quantity' == type) {
				option.series[0].data.push(row.inventoryQuantity || '');
				option.series[1].data.push(row.persistQuantity || '');
				option.series[2].data.push(row.salesQuantity || '');
				option.series[3].data.push(row.totalQuantity || '');
			}			
		}
		if (myChart) {
			myChart.dispose();
		};
		
		// $('#main').height(option.yAxis.data.length * 60 + 60);
		myChart = echarts.init(document.getElementById('main'));
		myChart.setOption(option);
	};

	var processServData = function(resp) {
		analysisData(resp);
		setFilterCrtl();
		showInBar();
	};

	var getServData_rowsByRoomModel = function() {
		$('table#dg>tbody>tr.data').remove();
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		var url = '/greedc/servlets/InventoryReport-getRowsByRoomModel.json';
		var params = {
			time: new Date().getTime(),
			startDate: startDate,
			endDate: endDate
		};
		$.getJSON(url, params, function (resp) {
			processServData(resp);
		});
	};

	$('#project').bind('change', function(){
		setFilterCrtl('area');
	});
	
	$('#area').bind('change', function(){
		setFilterCrtl('productType');
	});
	
	$('#productType, #type').bind('change', function(){
		showInBar();
	});
	
	$('#startDate, #endDate').bind('change', function(){
		getServData_rowsByRoomModel();
	});

	getServData_rowsByRoomModel();
</script>
</html>