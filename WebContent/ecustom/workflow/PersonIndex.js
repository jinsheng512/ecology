var type, userId, personIndex, isOpenEditor, newIndex;
var editors = {};
var deleteIds = [];
var datas = {
	fenl: [{value: '0', text: 'A-关键业务'}, {value: '1', text: 'B-基本业务'}, {value: '2', text: 'C-临时业务'}]
};

$(document).ready(function() {
	type = getQueryParam('type') || 'selfMark';	// selfMark-自评，managerMark-部门负责人评分，view-显示页面
	userId = getQueryParam('userId');
	initSeason();
	getServerData_Index();
	debugger;
});

/**
 * 加载考核季度。
 */
var initSeason = function(){
	var season = $('#season').val();
	var year = new Date().getFullYear();
	if (season == 'null' || season.length <= 0) {
		season = parseInt((new Date().getMonth()) / 3) - 1;
		if (season < 0) {
			season = 3;
			year = year - 1;
		}
		$('#season').val(season);
		$('#year').val(year);
	}
	var seasonData = {'0': '第一季度', '1': '第二季度', '2': '第三季度', '3': '第四季度'};
	$('#seasonName').text(seasonData['' + season]);
	$('#yearName').text(year + '年');
};

var getServerData_Index = function() {
	var url = '/greedc/servlets/PersonIndex-getPersonIndex.json?time=' + new Date().getTime();
	$.getJSON(url, {
		season: $('#season').val(), userId: userId, type: type, year: $('#year').val()
	}, function(resp) {
		if (!resp.status) {
			$.messager.alert('异常提示', resp.message, 'error');
			return ;
		}
		personIndex = resp.obj;
		type = resp.attr.type;
		initPage_Index(resp.obj || {});
		initEditor();	// 加载编辑功能

		initPage_IM(resp.attr.imList || []);
		initPage_BA(resp.attr.baList || []);

		sumAndSetFooter();	// 合计两个表格的数据并设置表格的Footer
		setButtonDisplay('init');	// 设置按钮权限

		newIndex = -1;
	});
};

/**
 * 加载页面：主表
 */
var initPage_Index = function(index) {
	$('#youlpdName').text(index.youlpdName);	// 显示优良评定
	$('#beikhrName').text(index.beikhrName);
	$('#bumfzrName').text(index.bumfzrName);
	$('#fazyq').textbox('setValue', index.fazyq);
	$('#chengj').textbox('setValue', index.chengj);
	$('#gongzyj').textbox('setValue', index.gongzyj);
	$('#xingwps').textbox('setValue', index.xingwps);
	$('#zongtps').textbox('setValue', index.zongtps);
};

/**
 * 加载页面：关键业务
 */
var initPage_IM = function(data) {
	$('#dgIM').datagrid({
		data: data,
		collapsible: true,
		showFooter: true,
		nowrap: false,
		columns: [[
			{field:'id', title:'编号', checkbox: true, hidden: type=='viewMark'},
			{field:'mainId', title:'主表ID', hidden: true},
			{field:'order', title:'序号', align: 'center', width: '40'},
			{field:'fenl', title:'分类', editor: editors.fenlIM, formatter: function(value, row, index) {
				return datas.fenl[value*1] ? datas.fenl[value*1].text : value;
			}, width: '85', align: 'center'},
			{field:'yewmb', title:'关键指标', width: '25%', editor: editors.yewmbIM},
			{field:'gongzmb', title:'行动步骤', editor: editors.gongzmbIM},
			{field:'quanz', title:'权重（%）', editor: editors.quanzIM, align: 'center', width: '70'},
			{field:'wancqk', title:'完成情况<br/>（百分制）', editor: editors.wancqkIM, align: 'center', width: '65'},
			{field:'zip', title:'自评分', align: 'center', editor: editors.zipIM, width: '65'},
			{field:'shendpf', title:'审定评分', editor: editors.shendpfIM, align: 'center', width: '65'}
		]]
	});
};

/**
 * 加载页面：行为能力
 */
var initPage_BA = function(data) {
	if (!data || data.length == 0) {
		$.messager.alert('提示', '您的部门还没有分配行为能力指标库。', 'error');
	}

	$('#dgBA').datagrid({
		data: data,
		nowrap: false,
		collapsible: true,
		nowrap: false,
		showFooter: true,
		singleSelect: true,
		columns: [[
			{field:'id', title:'编号', hidden: true},
			{field:'mainId', title:'主表ID', hidden: true},
			{field:'order', title:'序号', align: 'center', width: '40'},
			{field:'xingwnl', title:'行为能力指标', width: '85'},
			{field:'shuom', title:'行为能力指标说明'},
			{field:'zip', title:'自评（1-5分）', editor: editors.zipBA, align: 'center', width: '90'},
			{field:'shendpf', title:'审定评分', editor: editors.shendpfBA, align: 'center', width: '65'}
		]]
	});
};

/**
 * 合计。
 */
var sumAndSetFooter = function() {
	var rows = $('#dgIM').datagrid('getRows');
	var im = {
		fenl: '合计', quanz: 0, zip: 0, shendpf: 0
	};
	for (var i = 0; i < rows.length; i++) {
		rows[i].zip = ((rows[i].quanz || 0) / 100 * (rows[i].wancqk || 0)).toFixed(1);
		rows[i].order = i + 1;
		im.quanz += rows[i].quanz * 1 || 0;
		im.zip += rows[i].zip * 1 || 0;
		im.shendpf += rows[i].shendpf * 1 || 0;
	}
	im.zip = (im.zip).toFixed(1) * 1;
	im.shendpf = (im.shendpf).toFixed(1) * 1;
	$('#dgIM').datagrid('reloadFooter', [im]);


	rows = $('#dgBA').datagrid('getRows');
	var ba = {
		xingwnl: '合计', zip: 0, shendpf: 0
	};
	for (var i = 0; i < rows.length; i++) {
		rows[i].order = i + 1;
		ba.zip += rows[i].zip * 1 || 0;
		ba.shendpf += rows[i].shendpf * 1 || 0;
	}
	ba.zip = (ba.zip).toFixed(1) * 1;
	ba.shendpf = (ba.shendpf).toFixed(1) * 1;
	$('#dgBA').datagrid('reloadFooter', [ba]);

	$('#chengj').textbox('setValue', (im.shendpf * 0.7).toFixed(1) * 1 + ba.shendpf);
};

var initEditor = function() {
	if (type == 'selfMark') {
		editors.zipBA = {type: 'numberbox', options: {required: true, min: 1, max: 5, onChange: zipBAChange}};
		editors.fenlIM = {type: 'combobox', options: {
			required: true,
			valueField: 'value', textField: 'text',
			data: datas.fenl,
			panelHeight: 'auto'
		}};
		editors.yewmbIM = {type: 'textbox', options: {required: true, multiline: true}};
		editors.gongzmbIM = {type: 'textbox', options: {required: true, multiline: true}};
		editors.quanzIM = {type: 'numberbox', options: {required: true, min: 1, max: 100, onChange: quanzIMChange}};
		editors.wancqkIM = {type: 'numberbox', options: {required: true, min: 1, max: 100, onChange: wancqkIMChange}};
		editors.zipIM = {type: 'numberbox', options: {required: true, min: 1, max: 100, onChange: zipIMChange, readonly: true, precision: 1}};
	} else if (type == 'managerMark') {
		editors.shendpfIM = {type: 'numberbox', options: {required: true, min: 1, max: 100, onChange: shendpfIMChange}};
		editors.shendpfBA = {type: 'numberbox', options: {required: true, min: 1, max: 5, onChange: shendpfBAChange}};
	}

};

var quanzIMChange = function(newValue, oldValue) {
	if (!oldValue && isOpenEditor) return ;
	var footer = $('#dgIM').datagrid('getFooterRows')[0];
	footer.quanz += newValue - oldValue;
	$('#dgIM').datagrid('reloadFooter', [footer]);
	zipIMChange();
};

var wancqkIMChange = function(newValue, oldValue) {
	if (!oldValue && isOpenEditor) return ;
	var footer = $('#dgIM').datagrid('getFooterRows')[0];
	footer.wancqkI += newValue - oldValue;
	$('#dgIM').datagrid('reloadFooter', [footer]);
	zipIMChange();
};

var zipIMChange = function(newValue, oldValue) {
	if (isOpenEditor) return ;
	var len = $('#dgIM').datagrid('getRows').length, wancqk, quanz, zip;
	var totalZip = 0;
	for (var i = 0; i < len; i++) {
		quanz = $($('#dgIM').datagrid('getEditor', {index:i, field:'quanz'}).target).numberbox('getValue');
		wancqk = $($('#dgIM').datagrid('getEditor', {index:i, field:'wancqk'}).target).numberbox('getValue');
		if (quanz && wancqk) {
			zip = ((quanz || 1) * (wancqk || 1) / 100).toFixed(1) * 1;
			$($('#dgIM').datagrid('getEditor', {index:i, field:'zip'}).target).numberbox('setValue', zip);
			totalZip += zip;
		}
	}
	var footer = $('#dgIM').datagrid('getFooterRows')[0];
	footer.zip = totalZip.toFixed(1) * 1;
	$('#dgIM').datagrid('reloadFooter', [footer]);
};

/**
 * 关键业务指标审定评分值改变事件。
 */
var shendpfIMChange = function(newValue, oldValue) {
	if (!oldValue && isOpenEditor) return ;
	var footer = $('#dgIM').datagrid('getFooterRows')[0];
	footer.shendpf += newValue - oldValue;
	if (footer.shendpf > 100) {
		$.messager.alert('提示', '总分不能超过100分。', 'info');
	}
	$('#dgIM').datagrid('reloadFooter', [footer]);
	refreshChengji();	// 刷新考核成绩。
};

var zipBAChange = function(newValue, oldValue) {
	if (!oldValue && isOpenEditor) return ;
	var footer = $('#dgBA').datagrid('getFooterRows')[0];
	footer.zip += newValue - oldValue;
	$('#dgBA').datagrid('reloadFooter', [footer]);
};

var shendpfBAChange = function(newValue, oldValue) {
	if (!oldValue && isOpenEditor) return ;
	var footer = $('#dgBA').datagrid('getFooterRows')[0];
	footer.shendpf += newValue - oldValue;
	$('#dgBA').datagrid('reloadFooter', [footer]);
	refreshChengji();	// 刷新考核成绩。
};

/**
 * 刷新考核成绩。
 */
var refreshChengji = function() {
	var imScore = $('#dgIM').datagrid('getFooterRows')[0].shendpf || 0;
	var baScore = $('#dgBA').datagrid('getFooterRows')[0].shendpf || 0;
	$('#chengj').textbox('setValue', (imScore * 0.7).toFixed(1) * 1 + baScore);
};

var getQueryParam = function(name) {
	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
	var r = window.location.search.substr(1).match(reg);
	return r ? unescape(r[2]) : '';
};

/**
 * 设置按钮的显示状态
 */
var setButtonDisplay = function(action) {
	var tbIM = [];
	action = action || 'init';
	$('#btnCancelEdit').hide();
	$('#btnEdit').hide();
	$('#btnSave').hide();
	$('#saveManagerMark').hide();
	$('#submit').hide();

	// 个人编辑绩效
	if (type == 'selfMark' && personIndex.status == 0) {
		if (action == 'init') {
			if (personIndex.status == 0) {
				$('#btnEdit').show();
				$('#submit').show();
			}
		} else {
			$('#btnCancelEdit').show();
			$('#btnSave').show();
			tbIM.push({iconCls:'icon-add', handler: addIM});
			tbIM.push({iconCls:'icon-remove', handler: removeIM});
		}
	} 
	// 部门负责人编辑
	else if (type == 'managerMark' && personIndex.status == 1) {
		if (action == 'init') {
			if (personIndex.status == 1) {
				$('#btnEdit').show();
				$('#btnReject').show();
			}
		} else {
			$('#btnCancelEdit').show();
			$('#saveManagerMark').show();
		}
	}

	$('#dgIM').datagrid({
		tools: tbIM
	});
};

/**
 * 退回事件
 */
var reject = function() {
	$.messager.confirm('确认', '是否确认退回？', function(r){
		if (r) {
			$.post('/greedc/servlets/PersonIndex-reject.json?time=' + new Date().getTime(), {
				id: personIndex.id,
				year: $('#year').val(),
				season: $('#season').val(),
				userId: personIndex.beikhr	// 被考核人ID
			}, function(resp){
				if (resp.status && window.parent.closeIndex) {
					window.parent.closeIndex();
				} else if (!resp.status) {
					$.messager.alert('提示', resp.message, resp.status ? 'info' : 'error');
				}
			}, "json");
		}
	});
};

var edit = function() {
	setButtonDisplay('edit');	// 设置按钮权限
	isOpenEditor = true;
	for (var i = 0; i < 6; i++) {
		$('#dgBA').datagrid('beginEdit', i);
	}

	var len = $('#dgIM').datagrid('getRows').length;
	for (var i = 0; i < len; i++) {
		isOpenEditor = true;
		$('#dgIM').datagrid('beginEdit', i);
	}
	isOpenEditor = false;

	if (type == 'selfMark' && personIndex.status == 0) {
		$('#fazyq').textbox({readonly: false, required: true});
	} else if (type == 'managerMark' && personIndex.status == 1) {
		$('#zongtps').textbox({readonly: false, required: true});
		$('#gongzyj').textbox({readonly: false, required: true});
		$('#xingwps').textbox({readonly: false, required: true});
	}
};

var cancelEdit = function() {
	$('#dgIM').datagrid('rejectChanges');
	$('#dgBA').datagrid('rejectChanges');

	$('#fazyq').textbox({readonly: true, required: false});
	$('#gongzyj').textbox({readonly: true, required: false});
	$('#xingwps').textbox({readonly: true, required: false});
	$('#zongtps').textbox({readonly: true, required: false});

	setButtonDisplay('init');	// 设置按钮权限
	sumAndSetFooter();	// 合计两个表格的数据并设置表格的Footer
};

var save = function() {
	// 必填验证
	var validate = requiredFieldValidator();
	if (!validate) {
		$.messager.alert('提示', '必填栏位不能为空', 'info');
		return ;
	}

	$('#dgIM').datagrid('acceptChanges');
	$('#dgBA').datagrid('acceptChanges');
	sumAndSetFooter();	// 合计两个表格的数据并设置表格的Footer
	$('#fazyq').textbox({readonly: true, required: false});
	$('#gongzyj').textbox({readonly: true, required: false});
	$('#xingwps').textbox({readonly: true, required: false});
	$('#zongtps').textbox({readonly: true, required: false});

	var params = {};
	params.imRows = convertSaveIMData();
	params.baRows = convertSaveBAData();
	params.index = JSON.stringify({
		fazyq: $('#fazyq').textbox('getValue'),
		chengjzp: ($('#dgIM').datagrid('getFooterRows')[0].zip * 0.7 + ($('#dgBA').datagrid('getFooterRows')[0].zip * 1)).toFixed(1),
		ywzbzhj: $('#dgIM').datagrid('getFooterRows')[0].zip,
		xwnlzhj: $('#dgBA').datagrid('getFooterRows')[0].zip,
		season: $('#season').val(),
		userId: userId,
		status: personIndex.status,
		id: personIndex.id
	});
	params.deleteIds = JSON.stringify(deleteIds);
	params.type = type;

	$.post('/greedc/servlets/PersonIndex-save.json?time=' + new Date().getTime(), params, function(resp){
		$.messager.alert('提示', resp.message, resp.status ? 'info' : 'error');
		if (resp.status) {
			getServerData_Index();
		}
	}, "json");
};

var saveManagerMark = function() {
	var validate = requiredFieldValidator();
	if (!validate) {
		$.messager.alert('提示', '必填栏位不能为空', 'info');
		return ;
	}
	var shendpfIM = $('#dgIM').datagrid('getFooterRows')[0].shendpf;
	if (shendpfIM > 100) {
		$.messager.alert('提示', '工作业绩指标审定总分不能超过100分。', 'info');
		return ;
	}

	$('#fazyq').textbox({readonly: true, required: false});
	$('#gongzyj').textbox({readonly: true, required: false});
	$('#xingwps').textbox({readonly: true, required: false});
	$('#zongtps').textbox({readonly: true, required: false});

	$('#dgIM').datagrid('acceptChanges');
	$('#dgBA').datagrid('acceptChanges');
	sumAndSetFooter();	// 合计两个表格的数据并设置表格的Footer
	setButtonDisplay('init');	// 设置编辑状态

	var params = {};
	params.imRows = convertSaveIMData();
	params.baRows = convertSaveBAData();
	params.index = JSON.stringify({
		id: personIndex.id,
		chengj: $('#chengj').textbox('getValue'),
		gongzyj: $('#gongzyj').textbox('getValue'),
		xingwps: $('#xingwps').textbox('getValue'),
		zongtps: $('#zongtps').textbox('getValue'),
		ywzbshj: $('#dgIM').datagrid('getFooterRows')[0].shendpf,
		xwnlshj: $('#dgBA').datagrid('getFooterRows')[0].shendpf,
	});
	params.type = type;

	$.post('/greedc/servlets/PersonIndex-saveManagerMark.json?time=' + new Date().getTime(), params, function(resp){
		$.messager.alert('提示', resp.message, resp.status ? 'info' : 'error');
		if (resp.status) {
			getServerData_Index();
		}
	}, "json");
};

/**
 * 提交
 */
var submit = function() {
	if (!validateIM() || !validateBA()) {
		return ;
	}
	$.messager.confirm('确认', '提交后就不能修改了，是否确认提交？', function(r){
		if (r) {
			$.post('/greedc/servlets/PersonIndex-submit.json?time=' + new Date().getTime(), {
				id: personIndex.id,
				year: $('#year').val(),
				season: $('#season').val(),
				userId: personIndex.beikhr
			}, function(resp){
				$.messager.alert('提示', resp.message, resp.status ? 'info' : 'error');
				if (resp.status) {
					getServerData_Index();
				}
			}, "json");
		}
	});
};

var convertSaveIMData = function() {
	var rows = JSON.parse(JSON.stringify($('#dgIM').datagrid('getRows')));
	if (type == 'managerMark') {
		for (var i = 0; i < rows.length; i++) {
			rows[i].yewmb = undefined;
			rows[i].gongzmb = undefined;
		}
	}
	return JSON.stringify(rows);
};

var convertSaveBAData = function() {
	var rows = JSON.parse(JSON.stringify($('#dgBA').datagrid('getRows')));
	for (var i = 0; i < rows.length; i++) {
		rows[i].xingwnl = undefined;
		rows[i].shuom = undefined;
	}
	return JSON.stringify(rows);
};

var requiredFieldValidator = function() {
	var result = true;

	// 关键业务非空验证
	var imLen = $('#dgIM').datagrid('getRows').length;
	for (var i = 0; i < imLen && result; i++) {
		result = $('#dgIM').datagrid('validateRow', i);
	}

	// 行为能力非空验证
	var baLen = $('#dgBA').datagrid('getRows').length;
	for (var i = 0; i < baLen && result; i++) {
		result = $('#dgBA').datagrid('validateRow', i);
	}

	result = result && $('#form').form('validate');

	return result;
};

/**
 * 验证关键业务指标。
 */
var validateIM = function() {
	// 权重验证
	if ($('#dgIM').datagrid('getFooterRows')[0].quanz != 100) {
		$.messager.alert('提示', '关键业务指标合计权重应该为 100', 'info');
		return false;
	}

	var rows = $('#dgIM').datagrid('getRows');
	var fenlQZ = [0, 0, 0];	// 分类权重，0-A类，1-B类，2-C类
	for (var i = 0; i < rows.length; i++) {
		fenlQZ[rows[i].fenl * 1] += rows[i].quanz * 1;
	}
	// if (fenlQZ[0] < 50 || fenlQZ[1] > 30 || fenlQZ[2] > 20) {
	if (fenlQZ[0] < 50) {
		var msg = 'A类权重总和应 ≥ 50，当前为 ' + fenlQZ[0];
		msg += '<br/>B类权重总和应 ≤ 30，当前为 ' + fenlQZ[1];
		msg += '<br/>C类权重总和应 ≤ 20，当前为 ' + fenlQZ[2];
		$.messager.alert('提示', msg, 'info');
		return false;
	}
	return true;
};

/**
 * 验证行为能力指标。
 */
var validateBA = function() {
	// 必填验证
	for (var i = 0; i < 6; i++) {
		if (!$('#dgBA').datagrid('validateRow', i)) {
			$.messager.alert('提示', '必填栏位不能为空', 'info');
			return false;
		}
	}
	return true;
};

var addIM = function() {
	var index = $('#dgIM').datagrid('getRows').length;
	$('#dgIM').datagrid('insertRow', {
		index: index,
		row: {id: newIndex--}
	});
	$('#dgIM').datagrid('beginEdit', index);
};

var removeIM = function() {
	var checkedRows = $('#dgIM').datagrid('getChecked');
	for (var i = 0; i < checkedRows.length; i++) {
		deleteIds.push(checkedRows[i].id);
	}

	var rows = $('#dgIM').datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		if (deleteIds.indexOf(rows[i].id) >= 0) {
			$('#dgIM').datagrid('deleteRow', i--);
		}
	}
};