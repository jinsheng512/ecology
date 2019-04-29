$(document).ready(function(){
	var _dataId = $('input#dataId').val();
	var _reqUrl = '/ecustom/servlets/BigField-getValue.json';
	$.getJSON(_reqUrl, {seqNo: _dataId}, function(resp) {
		var _payObject = resp[0];
		for (_name in _payObject) {
			var _value = Number(_payObject[_name]);
			if (!isNaN(_value)) {
				if (_name == 'proPay3_1') {
					$('#' + _name).text(_value.toFixed(0));
				} else {
					$('#' + _name).text(_value.toFixed(2));
				}
				$('#' + _name).css('text-align', 'right');
			} else {
				$('#' + _name).text(_payObject[_name]);
			}
		}
	});
});