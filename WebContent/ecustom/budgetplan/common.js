/**
 * 添加HTML表单值改变事件。
 * @param fnCallBack 回调函数，用户修改表单数据时调用
 */
var addFormValueChangeEvent = function(fnCallBack) {
	var oldCheckinput2 = window.checkinput2;
	var oldWfbrowvaluechange = window.wfbrowvaluechange;
	var oldOnWorkFlowShowTime = window.onWorkFlowShowTime;

	var _fieldId, _rowIndex, _value;

	// 文本框、下拉选择
	window.checkinput2 = function(eId, eSpanId, viewType) {
		// eId: field5847_0, eSpanId: field5847_0span, viewType: 0
		var fieldIdInfo = eId.split('_');
		_fieldId = fieldIdInfo[0];
		_rowIndex = fieldIdInfo[1];
		_value = $('#' + eId).val();
		fnCallBack(_fieldId, _rowIndex, _value);
		viewType = $('#' + eId).attr('viewtype');
		oldCheckinput2(eId, eSpanId, viewType);
	};

	// 浏览按钮（人力资源、部门、日期）
	window.wfbrowvaluechange = function(obj, fieldid, rowindex) {
		// obj: [object HTMLInputElement], fieldid: 5832, rowindex:
		_fieldId = 'field' + fieldid;
		_rowIndex = rowindex;
		_value = isNaN(parseInt(rowindex)) ? $('#' + _fieldId).val() : $('#' + _fieldId + '_' + rowindex).val();
		fnCallBack(_fieldId, _rowIndex, _value);
		oldWfbrowvaluechange(obj, fieldid, rowindex);
	};

	// 浏览按钮（时间）
	window.onWorkFlowShowTime = function(spanname, inputname, ismand, callBackFn) {
		oldOnWorkFlowShowTime(spanname, inputname, ismand, function(){
			var fieldIdInfo = inputname.split('_');
			_fieldId = fieldIdInfo[0];
			_rowIndex = fieldIdInfo[1];
			_value = $('#' + inputname).val();
			fnCallBack(_fieldId, _rowIndex, _value);
			callBackFn();
		});
	};
};

/**
 * 给字段添加必填验证。
 */
var addInputCheckField = function(fieldId, spanImgId) {
	$('#' + fieldId).attr('viewtype', '1');
	var fieldStr = $('input[name=needcheck]').val();
	if (fieldStr.charAt(fieldStr.length - 1) != ',') {
		fieldStr += ',';
	}
	$('input[name=needcheck]').val(fieldStr + fieldId + ',');
	$('#' + spanImgId).html('<img src="/images/BacoError_wev8.gif" align="absMiddle">');
};

/**
 * 提交前验证。
 */
var beforeSubmit = function(_fn) {
	var doSubmitOld = window.doSubmit;
	window.doSubmit = function(obj) {
		_fn(doSubmitOld, obj);
	};
};

/**
 * 移除字段必填验证。
 */
var removeInputCheckField = function(fieldId, spanImgId) {
	$('#' + fieldId).attr('viewtype', '0');
	var fieldStr = $('input[name=needcheck]').val();
	$('input[name=needcheck]').val(fieldStr.replace(new RegExp(fieldId + ',', "gm"), ''));
	$('#' + spanImgId).html('');
};

var setFieldValue = function(fieldId, value, span) {
	$('#' + fieldId).val(value);
	if ('hidden' == $('#' + fieldId).attr('type')) {
		$('#' + fieldId + 'span').text(span);
	} else {
		checkinput2(fieldId, fieldId + 'span');
	}
};