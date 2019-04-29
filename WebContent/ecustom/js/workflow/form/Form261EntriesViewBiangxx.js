$(document).ready(function(){
	var _dataId = $('input#dataId').val();
	var _reqUrl = '/ecustom/servlets/BigField-getValue.json';
	$.getJSON(_reqUrl, {seqNo: _dataId}, function(resp) {
		$('#dg').datagrid({
			data: resp,
			singleSelect: true
		});
	});
});