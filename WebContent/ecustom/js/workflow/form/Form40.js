// <script language="javascript" src="/wui/theme/ecology8/jquery/js/zDialog_wev8.js"></script>
// <script language="javascript" src="/ecustom/js/workflow/form/FormUtil.js"></script>
// <script language="javascript" src="/ecustom/js/workflow/form/Form40.js"></script>

var pageObj = {};
var fieldMap = {};

/**
 * 部门评分表。
 */
$(document).ready(function(){
	pageObj.workflowId = $('input[name=workflowid]').val() * 1;
	pageObj.nodeId = $('input[name=nodeid]').val() * 1;
	pageObj.requestId = $('input[name=requestid]').val() * 1;
	pageObj.updateYljg = undefined;
	fieldMap = {
		kaojd: 'field9250',	// 考核季度
		status: 'field9428',
		year: 'field9708',
		bummc: 'field9248',	// 部门名称
		stay: 'field9688', // 是否停留
		markRole: 'field9590', // 评分规则
		mx1: {
			pfmx: 'field9260',	//评分明细
			sdfkpi: 'field9256',	// 审定分KPI得分
			sdfxwnl: 'field9257',	// 审定分行为能力得分
			sdfzf: 'field9258',	// 审定分总分
			xingm: 'field9252',	// 姓名
			yljg: 'field9259',	// 优良结果
			zpfkpi: 'field9253',	// 自评分KPI得分
			zpfxwnl: 'field9254',	// 自评分行为能力得分
			zpfzf: 'field9255'	//自评分总分
		}
	};

	$('#' + fieldMap.stay).val(0);	// 是否停留设置否，这样的话用户可以可以流转到下一个节点。

	var eventFieldIds = ['field9248', 'field9708', 'field9250'];
	ECSTM.addFormValueChangeEvent(function(eId, eSpanId, viewType){
		if (eventFieldIds.indexOf(eId) >= 0) {
			var deptId = $('#' + fieldMap.bummc).val();
			if (deptId && pageObj.deptIds.indexOf(deptId*1) < 0) {
				alert('对不起，您没有给此部门评分的权限');
				$('#' + fieldMap.bummc).val('');
				$('#' + fieldMap.bummc + 'span').text('');
				return ;
			}

			if (confirm('是否重新加载明细数据？')) {
				deleteRowIndexs();
				listRowsIndex();
			}
		}
	});

	if (pageObj.requestId <= 0) {
		pageObj.isBumfzrNode = true;
		initPage();
	} else {
		var url = '/greedc/servlets/Request-getCurrNodeId.json?time=' + new Date().getTime();
		$.getJSON(url, {
			requestId: pageObj.requestId
		}, function(resp){
			if (resp.status) {
				pageObj.currNodeId = resp.message * 1;	// 获取流程所在节点
				pageObj.isBumfzrNode = pageObj.currNodeId == 4182;	// 设置“是否部门负责人”节点
				initPage();
			}
		});
	}
});

var initPage = function() {
	// 部门评分流程 --> 部门负责人
	if (pageObj.isBumfzrNode) {
		listRowsIndex();
		ECSTM.beforeSubmit(checkInputBeforeSubmit);
	} else {
		var mxRowLen = $('#indexnum0').val();
		for (var mxi = 0; mxi < mxRowLen; mxi++) {
			$('#' + fieldMap.mx1.pfmx + '_' + mxi + 'span').html('<a href="#" onclick="showIndex(' + $('#' + fieldMap.mx1.xingm + '_' + mxi).val() + ')">评分明细</a>');	// 评分明细
		}
	}
	initDepts();
	initRoles();	// 加载评分规则。
};

/**
 * 根据评分人查询可以评分的部门。
 */
var initDepts = function() {
	var url = '/greedc/servlets/Matrix-listDeptIdsByPingfr.json?time=' + new Date().getTime();
	$.getJSON(url, {}, function(resp){
		if (!resp.status) {
			alert(resp.message);
			return ;
		}
		pageObj.deptIds = resp.obj;
	});
};

/**
 * 加载评分规则。
 */
var initRoles = function() {
	var url = '/greedc/servlets/PersonIndex-listRoles.json?time=' + new Date().getTime();
	$.getJSON(url, {}, function(resp){
		if (!resp.status) {
			alert(resp.message);
			return ;
		}
		pageObj.roles = resp.obj;
		var roleText = '';
		for (var i = 0; i < pageObj.roles.length; i++) {
			roleText += '评分结果为 ' + pageObj.roles[i].youlpdName;
			roleText += ' 的比例' + (pageObj.roles[i].rate ? '不能超过 ' + pageObj.roles[i].rate + '%； ' : '为不限；');
		}
		$('#' + fieldMap.markRole).hide();
		$('#' + fieldMap.markRole).val(roleText);
		$('#' + fieldMap.markRole + 'span').text(roleText);
	});
};

/**
 * 提交前验证。
 */
var checkInputBeforeSubmit = function(submitFn, obj){
	var roles = pageObj.roles, count, allowCount;
	var mxRowLen = $('#indexnum0').val();
	for (var i = 0; i < roles.length; i++) {
		count = getYouljgCount(roles[i].youlpd);
		allowCount = (mxRowLen * roles[i].rate / 100).toFixed(0);
		if (count != 1) {
			// glRole == 1 表示 <= Rate
			if (roles[i].glRole == 1 && count > allowCount) {
				alert('评分结果为 ' + roles[i].youlpdName + ' 的人数不能超过 ' + roles[i].rate + '%');
				return ;
			}
			// glRole == 0 表示 >= Rate
			else if (roles[i].glRole == 0 && count < allowCount) {
				alert('评分结果为 ' + roles[i].youlpdName + ' 的人数不能少于 ' + roles[i].rate + '%');
				return ;
			}
		}
	}

	var url = '/greedc/servlets/UFJidpfkhr-getCountByDept.json?time=' + new Date().getTime();
	var deptId = $('#' + fieldMap.bummc).val();
	$.getJSON(url, {
		deptId: deptId
	}, function(resp){
		if (!resp.status) {
			alert(resp.message);
			return ;
		}
		// 验证人数
		var mxRowLen = $('#indexnum0').val();
		var count = 0;
		for (var mxi = 0; mxi < mxRowLen; mxi++) {
			if ($('#' + fieldMap.mx1.xingm + '_' + mxi).val() || 0) {
				count++;
			}

		}
		if (count < resp.obj) {
			alert('还有 ' + (resp.obj-count) + ' 人未提交个人绩效。');
			return ;
		}
		submitFn(obj);	// 提交表单
	});
};

/**
 * 获取明细表中指定分数区间的人员数量。
 */
var getScoreCount = function(start, end) {
	var count = 0, score;
	var mxRowLen = $('#indexnum0').val();
	for (var mxi = 0; mxi < mxRowLen; mxi++) {
		score = $('#' + fieldMap.mx1.sdfzf + '_' + mxi).val() * 1;
		count += score >= start && score <= end ? 1 : 0;
	}
	return count;
};

/**
 * 获取明细表中指定优良结果的人员数量。
 */
var getYouljgCount = function(youljg) {
	var count = 0;
	var mxRowLen = $('#indexnum0').val();
	for (var mxi = 0; mxi < mxRowLen; mxi++) {
		count += $('#' + fieldMap.mx1.yljg + '_' + mxi).val() * 1 == youljg ? 1 : 0;
	}
	return count;
};

var deleteRowIndexs = function() {
	window.isdel = function() {
		return true;
	};
	if ($('input[name=check_node_0]').length) {
		$('input[name=check_node_0]').attr('checked', true);
		deleteRow0(0);
	}
};

/**
 * 获取部门负责人相关的个人绩效考核信息
 */
var listRowsIndex = function() {
	var season = $('#' + fieldMap.kaojd).val();
	var year = $('#' + fieldMap.year).find("option:selected").text();

	var url = '/greedc/servlets/PersonIndex-listDeptAppr.json?time=' + new Date().getTime();
	$.getJSON(url, {
		year: year,
		season: season,
		deptId: $('#' + fieldMap.bummc).val()
	}, function(resp){
		if (!resp.status) {
			alert(resp.message);
			return ;
		}
		var indexs = resp.obj;
		var indexRowLen = indexs.length;
		var mxRowLen = $('#indexnum0').val();
		var ini, mxi;
		var pingfrId = $('#field9768').val() * 1;
		for (ini = 0; ini < indexRowLen; ini++) {
			if (pingfrId == indexs[ini].beikhr) {	// 不能给自己评分
				continue ;
			}
			var exists = false;
			for (mxi = 0; mxi < mxRowLen && !exists; mxi++) {
				exists = indexs[ini].beikhr*1 == $('#' + fieldMap.mx1.xingm + '_' + mxi).val()*1;
			}
			if (exists) {
				setRowIndex(indexs[ini], mxi - 1);
			} else {
				addRow0(0);
				setRowIndex(indexs[ini], $('#indexnum0').val() - 1);
			}
		}
	});
};

/**
 * 设置明细行数据
 */
var setRowIndex = function(index, i) {
	$('#' + fieldMap.mx1.pfmx + '_' + i + 'span').html('<a href="#" onclick="showIndex(' + index.beikhr + ')">评分明细</a>');	// 评分明细
	$('#' + fieldMap.mx1.sdfkpi + '_' + i).val(index.ywzbshj);	// 审定分KPI得分
	$('#' + fieldMap.mx1.sdfxwnl + '_' + i).val(index.xwnlshj);	// 审定分行为能力得分
	$('#' + fieldMap.mx1.sdfzf + '_' + i).val(index.chengj);	// 审定分总分
	$('#' + fieldMap.mx1.xingm + '_' + i).val(index.beikhr);	// 姓名
	$('#' + fieldMap.mx1.xingm + '_' + i + 'span').text(index.beikhrName);	// 姓名
	$('#' + fieldMap.mx1.zpfkpi + '_' + i).val(index.ywzbzhj);	// 自评分KPI得分
	$('#' + fieldMap.mx1.zpfxwnl + '_' + i).val(index.xwnlzhj);	// 自评分行为能力得分
	$('#' + fieldMap.mx1.zpfzf + '_' + i).val(index.chengjzp);	// 自评分总分
	if (pageObj.updateYljg && pageObj.updateYljg == index.beikhr) {
		$('#' + fieldMap.mx1.yljg + '_' + i).val(index.youlpd);	// 优良结果
	}
	// $('#' + fieldMap.mx1.yljg + '_' + i).hide();
	// $('#' + fieldMap.mx1.yljg + '_' + i + 'span').text($('#' + fieldMap.mx1.yljg + '_' + i).find("option:selected").text());	// 优良结果
	$('#' + fieldMap.mx1.xingm + '_' + i + '_browserbtn').hide();	// 姓名浏览按钮
	$('#' + fieldMap.mx1.xingm + '_' + i + '_browserbtn').hide();	// 姓名浏览按钮
	$('#' + fieldMap.mx1.xingm + '_' + i + '__').attr('readonly', true);	// 姓名输入框
	$('#' + fieldMap.mx1.sdfkpi + '_' + i).attr('readonly', true);	// 审定分KPI得分
	$('#' + fieldMap.mx1.sdfxwnl + '_' + i).attr('readonly', true);	// 审定分行为能力得分
	$('#' + fieldMap.mx1.sdfzf + '_' + i).attr('readonly', true);	// 审定分总分
	$('#' + fieldMap.mx1.zpfkpi + '_' + i).attr('readonly', true);	// 自评分KPI得分
	$('#' + fieldMap.mx1.zpfxwnl + '_' + i).attr('readonly', true);	// 自评分行为能力得分
	$('#' + fieldMap.mx1.zpfzf + '_' + i).attr('readonly', true);	// 自评分总分
};

/**
 * 显示个人绩效明细。
 */
var dlg = null;
var showIndex = function(beikhr) {
	var year = $('#' + fieldMap.year).find("option:selected").text();
	var season = $('#' + fieldMap.kaojd).val();
	var type = pageObj.isBumfzrNode ? 'managerMark' : 'viewMark';
	var url = "/ecustom/workflow/PersonIndex.jsp?season=" + season
		+ '&userId=' + beikhr + '&type=' + type + '&year=' + year;
	dlg = new window.Dialog();	// 定义Dialog对象
	dlg.currentWindow = window;
	dlg.Model = true;
	dlg.Width = 1200;	//定义长度
	dlg.Height = 780;
	dlg.URL = url;
	dlg.Title = '个人绩效（先保存评分，再关闭）';
	dlg.CancelEvent = function() {
		pageObj.updateYljg = beikhr;	// 可更新优良结果
		listRowsIndex();
		dlg.close();
		dlg = undefined;
	};
	dlg.show();
};

var closeIndex = function() {
	if (dlg) {
		dlg.close();
		window.parent.location.reload();
	}
};