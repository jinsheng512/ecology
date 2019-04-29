// <script language="javascript" src="/ecustom/js/workflow/form/FormUtil.js"></script>

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

/**
 * 注册提交前事件。
 * @param _fn
 */
ECSTM.beforeSubmit = function(_fn) {
	var doSubmitNoBackOld = window.doSubmitNoBack;
	var doSubmitBackOld = window.doSubmitBack;
	var doAffirmanceNoBackOld = window.doAffirmanceNoBack;
	window.doSubmitNoBack = function(obj) {
		_fn(doSubmitNoBackOld, obj);
	};
	window.doSubmitBack = function(obj) {
		_fn(doSubmitBackOld, obj);
	};
	window.doAffirmanceNoBack = function(obj) {
		_fn(doAffirmanceNoBackOld, obj);
	};
};

/**
 * 注册保存前事件。
 */
ECSTM.beforeSave = function() {
    var doSave_nNewOld = window.doSave_nNew;
    window.doSave_nNew = function(obj) {
        _fn(doSave_nNewOld, obj);
    };
};

ECSTM.createFieldSpan = function(_fieldId) {
	var _spanId = _fieldId + 'span';
	if ($('#' + _spanId).length <= 0) {
		$("#" + _fieldId).after('<span = id="' + _spanId + '"></span>');
	}
}