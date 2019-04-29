/**
 * 
 */

var openReport = function(companyId, lastYearMonth, yearMonth) {
	window.open('/ecustom/budgetplan/companyFundsPlan.jsp?yearMonth=' + yearMonth + '&companyId=' + companyId + '&lastYearMonth=' + lastYearMonth);
};

var fmt_operate = function(value, row, index) {
	var buttons = '<a href="javascript:void()" class="easyui-linkbutton" onclick="openReport('
		+ '\''+ $('#companyId').val() + '\''
		+ ', \'' + row.lastYearMonth + '\''
		+ ', \'' + row.yearMonth + '\')">月报</a> ';

	return buttons;
};


$(document).ready(function() {
	
	var doSearch = function() {
		var yearMonth = $('#monthYear').val();
		var companyId = $('#companyId').val();

		var defFilter = 'COMPANY=' + companyId;
		if (yearMonth) {
			defFilter += ' {AND} LASTYEARMONTH=\'' + yearMonth + '\'';
		}

		$('#dg').datagrid({
			url: '/greedc/servlets/EasyUI-dataGridPage.json',
			queryParams: {
				name: 'CV_PLAN_SUMMARY',
				key: 'ID',
				sort: 'LASTYEARMONTH DESC',
				field: 'id,yearMonth,lastYearMonth,company,companyName,submitName,submitDate,lastMonthBegin,thisMonthPlan,inPlan,inHappend,exPlan,exHappend,lastMonthEndPln,lastMonthEndHap',
				defFilter: defFilter,
				time: new Date().getTime()
			}
		});
	};
	
	$('#btnSearch').bind('click', doSearch);
	
	$('#dg').datagrid({
		collapsible: true,
		singleSelect: true,
		width: $(document).width() - 10,
		pagination: true,
		remoteFilter: true,
		mode: 'remote'
	});

	doSearch();
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
