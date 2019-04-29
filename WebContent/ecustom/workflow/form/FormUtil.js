var ECSTM = {};
var $ = jQuery;

ECSTM.addFormValueChangeEvent = function(fnCallBack) {
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

ECSTM.beforeSubmit = function(checkFn) {
	ECSTM.doSubmitNoBack = window.doSubmitNoBack;
	ECSTM.doSubmitBack = window.doSubmitBack;
	window.doSubmitNoBack = function(obj) {
		ECSTM.beforeSubmitAction = ECSTM.doSubmitNoBack;
		ECSTM.beforeSubmitObject = obj;
		if (checkFn()) {
			ECSTM.doSubmitNoBack(obj);
		}
	};
	window.doSubmitBack = function(obj) {
		ECSTM.beforeSubmitAction = ECSTM.doSubmitBack;
		ECSTM.beforeSubmitObject = obj;
		if (checkFn()) {
			ECSTM.doSubmitBack(obj);
		}
	};
};

ECSTM.createFieldSpan = function(_fieldId) {
	var _spanId = _fieldId + 'span';
	if ($('#' + _spanId).length <= 0) {
		$("#" + _fieldId).after('<span = id="' + _spanId + '"></span>')
	}
}