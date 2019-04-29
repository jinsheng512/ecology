var GREEDC = {};

GREEDC.toMoney = function(value, row, index) {
	if (!isNaN(value)) {
		value = (value*1).toFixed(2);
	}
	return value;
};

GREEDC.tooltip = function(value, row, index) {
	return '<div title="' + value + '">' + value + '</div>';
};

$(document).ready(function(){
	// 表格基本设置
	$('#dg').datagrid({
		url: '/greedc/servlets/Flow163-getEntryData.json?requestId=' + $('#requestId').val(),
		collapsible: true,
		singleSelect: true,
		rownumbers: true,
		showFooter: true,
		onLoadSuccess: function(_data) {
			var rows = _data.rows;
			var _row = {FPER001: '合计：'};
			for (var i = 0; i < rows.length; i++) {
				for (name in rows[i]) {
					if (!isNaN(rows[i][name]) && name != 'FPER025') {
						_row[name] = (_row[name] || 0) + rows[i][name]*1;
					}
				}
			}
			
			for (name in _row) {
				if (!isNaN(_row[name]) && name != 'FPER025') {
					_row[name] = GREEDC.toMoney(_row[name]);
				}
			}
			
			$('#dg').datagrid('reloadFooter', [_row]);
		}
	});
});