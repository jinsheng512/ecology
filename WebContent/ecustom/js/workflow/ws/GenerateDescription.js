$(document).ready(function(){
	var workflowId = $('#workflowId').val();
	
	$('#pg').propertygrid({
		title: '流程基本信息',
		url: '/ecustom/servlets/GenerateDescription-getBase.json?workflowId=' + workflowId,
		showHeader: false,
		collapsible:true,
		columns: [[
			{field:'name', title:'名称', width: '150px', resizable:false},
			{field:'value',title:'值', width: '500px', resizable:false}
		]]
	});

	$('#dg').datagrid({
		title: '字段信息（双击单元格可查看完整信息）',
		rownumbers: true,
		collapsible:true,
		groupField: 'tableName',
		view: groupview,
		singleSelect: true,
		url: '/ecustom/servlets/GenerateDescription-getFields.json?workflowId=' + workflowId,
		groupFormatter: function(value, rows){
			var retText = '主表';
			if (value && value.indexOf('_') > 0) {
				var numi = value.toUpperCase().indexOf('DT') + 2;
				retText = '明细表 ' + (value.substring(numi) - 1) + ' - ' + value;
			}
			return retText;
		},
		onDblClickCell: function(index, field, value) {
			$.messager.alert('字段信息', value);
		}
	});
});

var fmt = {
	paramLabelName: function(value, row, index) {
		if (!value) {
			return row.labelName;
		}
		return value;
	}
};