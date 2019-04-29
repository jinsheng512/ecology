/**
 * 
 */

$(document).ready(function() {
	$('#dg').datagrid({
		collapsible: true,
		singleSelect: true,
		width: $(document).width() - 10,
        pagination: true,
        remoteFilter: true,
		mode: 'remote',
		url: '/greedc/servlets/EasyUI-dataGridPage.json',
		queryParams: {
			name: 'CV_GROUPFUNDS_SUMMARY',
			key: 'LASTYEARMONTH',
			sort: 'LASTYEARMONTH DESC',
			field: 'lastYearMonth,lastMonthBegin,thisMonthPlan,inPlan,inHappend,exPlan,exHappend,lastMonthendPln,lastMonthendHap',
			defFilter: '',
			time: new Date().getTime()
		}
	});
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

var openReport = function(reportType, yearMonth) {
	window.open('/ecustom/budgetplan/groupFundsPlan.jsp?yearMonth=' + yearMonth + '&reportType=' + reportType);
};

var fmt_operate = function(value, row, index) {
	var buttons = '<a href="javascript:void()" class="easyui-linkbutton" onclick="openReport(1, \'' + row.lastYearMonth + '\')">月报</a> ';
	buttons += '<a href="javascript:void()" class="easyui-linkbutton" onclick="openReport(2, \'' + row.lastYearMonth + '\')">季报</a> ';
	buttons += '<a href="javascript:void()" class="easyui-linkbutton" onclick="openReport(3, \'' + row.lastYearMonth + '\')">年报</a>';
	return buttons;
};