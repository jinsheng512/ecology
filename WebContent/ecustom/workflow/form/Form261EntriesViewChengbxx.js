$(document).ready(function(){
	var _dataId = $('input#dataId').val();
	$('#pg').propertygrid({
		columns: [[
			{field:'name', title:'成本项', width: 100, resizable: true},
            {field:'value', title:'金额', width: 100, resizable: true}
		]],
		singleSelect: true
	});
	
	var _reqUrl = '/ecustom/servlets/BigField-getValue.json';
	$.getJSON(_reqUrl, {seqNo: _dataId}, function(resp) {
		var _data = [];
		var _amount;
		for (var name in resp[0]) {
			if (name.indexOf('text') >= 0) {
				_amount = resp[0]['amount' + name.substring(4)];
				_data[_data.length] = {
					"name": resp[0][name],
					"value": isNaN(_amount) ? _amount : (_amount*1).toFixed(2)
				};
			}
		}
		$('#pg').propertygrid({
			data: _data
		});
	});
});