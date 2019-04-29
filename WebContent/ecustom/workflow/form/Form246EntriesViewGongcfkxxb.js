$(document).ready(function(){
	var _dataId = $('input#dataId').val();
	var _reqUrl = '/ecustom/servlets/BigField-getValue.json';
	$.getJSON(_reqUrl, {seqNo: _dataId}, function(resp) {
		for (_name in resp) {
			var _value = Number(resp[_name]);
			if (!isNaN(_value)) {
				if (_name == 'proPay3_1') {
					$('#' + _name).text(_value.toFixed(0));
				} else {
					$('#' + _name).text(_value.toFixed(2));
				}
				$('#' + _name).css('text-align', 'right');
			} else {
				$('#' + _name).text(resp[_name]);
			}
		}
	});
});