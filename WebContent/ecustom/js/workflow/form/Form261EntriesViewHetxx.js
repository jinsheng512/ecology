$(document).ready(function(){
	var _dataTypes = {jinrdtcb: 'checkbox', shifjgclht: 'checkbox'};
	var _dataId = $('input#dataId').val();
	var _reqUrl = '/ecustom/servlets/BigField-getValue.json';
	$.getJSON(_reqUrl, {seqNo: _dataId}, function(resp) {
		var _payObject = resp[0];
		for (_name in _payObject) {
			if (_dataTypes[_name] == 'checkbox') {
				setCheckBoxVal(_name, _payObject[_name]);
			} else if (_name == 'conEntry') {
				setDgCon(_payObject[_name]);
			} else {
				$('div#' + _name).text(_payObject[_name]);
			}
		}
	});
});

var setDgCon = function(_detail) {
	for (var i = 0; i < _detail.length; i++) {
		$('#dgCon').datagrid('insertRow',{
			index: i,
			row: _detail[i]
		});
	}
};

var setCheckBoxVal = function(id, val) {
	if (val == '1') {
		$('input#' + id).attr('checked', 'checked');
	}
};