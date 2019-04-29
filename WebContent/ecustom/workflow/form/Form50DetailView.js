var ECSTM = ECSTM || {};

$(document).ready(function(){

	var _canEditNodes = ['682', '683', '684', '685', '686', '687', '688', '689', '690'];
	var _nodeId = $('input[name=nodeid]', window.parent.document).val();
	var dgDetail = $('#dgDetail').datagrid();
	var cbxGoux = $('#goux').combobox();
	var currSeqNo = 0;
	
	var getDetailData = function(_goux) {
		var _url = '/greedc/servlets/EBProjectEntry-findByMainSeqNo.json';
		$.getJSON(_url, {
			seqNo: currSeqNo,
			goux: _goux || -1
		}, function(resp){
			var _data = resp.obj;
			dgDetail.datagrid({
				showFooter: true,
				data: _data
			});
			// if (_canEditNodes.indexOf(_nodeId) >= 0) {
				for (var i = 0; i < _data.length; i++) {
					dgDetail.datagrid('beginEdit', i);
				}
				ECSTM.isChange = false;
			// }
			
			var _row = {bianmxh: '合计：', hetje: 0, tiaozhje: 0, yuecljje: 0, benyjdfk: 0, je: 0, benyjhfke: 0};
			for (var i = 0; i < _data.length; i++) {
				_row.hetje += isNaN(_data[i].hetje) ? 0 : _data[i].hetje*1;
				_row.tiaozhje += isNaN(_data[i].tiaozhje) ? 0 : _data[i].tiaozhje*1;
				_row.yuecljje += isNaN(_data[i].yuecljje) ? 0 : _data[i].yuecljje*1;
				_row.benyjdfk += isNaN(_data[i].benyjdfk) ? 0 : _data[i].benyjdfk*1;
				_row.je += isNaN(_data[i].je) ? 0 : _data[i].je*1;
				_row.benyjhfke += isNaN(_data[i].benyjhfke) ? 0 : _data[i].benyjhfke*1;
			}
			
			_row.yuecljbl = (_row.yuecljje/_row.hetje * 100).toFixed(2) + '%';
			_row.yifkbl = (_row.je/_row.hetje * 100).toFixed(2) + '%';
			_row.leijbl = (_row.benyjhfke/_row.hetje * 100).toFixed(2) + '%';
			
			$('#dgDetail').datagrid('reloadFooter', [_row]);
		});
	};
	
	var showEBProjectDetail = function(_row){
		$('#win').window({
			height: $(document).height(),
			width: $(document).width()
		});
		$('td#projectName').text(_row.projectName);
		$('td#payCo').text(_row.payCo);
		$('td#statisticalDept').text(_row.statisticalDept);
		$('span#year').text(_row.year);
		$('span#month').text(_row.month);
		
		cbxGoux.combobox('setValue', '-1');
		currSeqNo = _row.seqNo;
		getDetailData();
		$('#win').window('open');
	};

	var getChanges = function() {
		var _rows = dgDetail.datagrid('getRows');
		for (var i = 0; i < _rows.length; i++) {
			dgDetail.datagrid('endEdit', i);
		}
		return dgDetail.datagrid('getChanges');
	};

	var saveChanges = function(_rows) {
		var _vos = '';
		for (var i = 0; i < _rows.length; i++) {
			_vos += ',{seqNo: "' + _rows[i].seqNo + '", goux: "' + _rows[i].goux + '"}';
		}
		if (_vos.length > 0) {
			_vos = _vos.substring(1); 
		}
		_vos = '[' + _vos + ']';

		$.getJSON('/greedc/servlets/EBProjectEntry-updateGoux.json', {
			vos: _vos
		}, function(_resp) {
			if (_resp.status) {
				ECSTM.isChange = false;
				$('#win').window('close');
			} else {
				$.messager.alert('提示', '保存修改失败！<br/>' + _resp.message, 'error');
			}
		});
	};

	$('#dg').datagrid({
		showFooter: true,
		onDblClickRow: function(index, row) {
			// alert(window.parent.js_clienttype);
			if ('Webclient,android,iPhone'.indexOf(window.parent.js_clienttype) < 0) {
				showEBProjectDetail(row);
			}
		}
	});

	$('#win').window({
		onBeforeClose: function() {
			// 如果没有修改过勾选状态，则允许关闭明细表
			if (!ECSTM.isChange) {
				return true;
			}

			$.messager.confirm('确认', '是否保存修改？', function(_r){
				if (_r) {
					saveChanges(getChanges());
				} else {
					ECSTM.isChange = false;
					$('#win').window('close');
				}
			});
			return false;
		}
	});

	$('#btnSave').bind('click', function(){
		saveChanges(getChanges());
	});

	$('#goux').combobox({
		onChange: function(value, oldValue){
			getDetailData(value);
		}
	});

	var _url = '/greedc/servlets/ExpenseBudget-findByRequestId.json';
	$.getJSON(_url, {requestId: $('#requestId').val()}, function(resp){
		var _data = resp.obj;
		$('#dg').datagrid({
			singleSelect: true,
			data: _data
		});
		
		var _row = {projectName: '合计：', accPayable: 0, payTotal: 0, conNum: 0};
		for (var i = 0; i < _data.length; i++) {
			_row.accPayable += isNaN(_data[i].accPayable) ? 0 : _data[i].accPayable*1;
			_row.payTotal += isNaN(_data[i].payTotal) ? 0 : _data[i].payTotal*1;
			_row.conNum += isNaN(_data[i].conNum) ? 0 : _data[i].conNum*1;
		}
		$('#dg').datagrid('reloadFooter',[_row]);
	});
});

ECSTM.mainGouxChange = function(_value) {

};

ECSTM.gouxData = {"0": "会计驳回", "1": "复核", "2": "修改", "3": "执行"};
ECSTM.editorGoux = {
		type: 'combobox',
		options: {
			panelHeight: 'auto',
			valueField: 'value',
			textField: 'text',
			data: [
				{value: "2", text: "修改"},
				{value: "3", text: "执行"},
				{value: "0", text: "会计驳回"},
				{value: "1", text: "复核"}
				],
				onChange: function(value) {
					ECSTM.isChange = true;
				},
				required: true
		}
};
ECSTM.fmtGoux = function(value, row) {
	return ECSTM.gouxData[value + ''];
};