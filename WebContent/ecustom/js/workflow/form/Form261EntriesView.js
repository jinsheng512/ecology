$(document).ready(function(){
	var _reqId = $('#requestId').val();
	var _reqUrl = '/greedc/servlets/Form261-getFormByRequestId.json';
	var _obj = null;
	var _map = {};
	
	$.getJSON(_reqUrl, {requestId: _reqId}, function(resp) {
		_obj = resp.obj;
		
		$('#frmHetxx').attr('src', 'Form261EntriesViewHetxx.jsp?dataId=' + _obj.hetxx);
		
		$('#tabs').tabs({
			onSelect: function(title, index) {
				
				if (_map[title]) {
					return ;
				}
				
				if ('变更信息' == title) {
					$('#frmBiangxx').attr('src', 'Form261EntriesViewBiangxx.jsp?dataId=' + _obj.biangxx);
				} else if ('成本信息' == title) {
					$('#frmChengbxx').attr('src', 'Form261EntriesViewChengbxx.jsp?dataId=' + _obj.chengbxx);
				} else if ('付款信息' == title) {
					$('#frmFukxx').attr('src', 'Form261EntriesViewFukxx.jsp?dataId=' + _obj.fukxx);
				} else if ('付款申请' == title) {
					$('#frmFuksqxx').attr('src', 'Form261EntriesViewFuksqxx.jsp?dataId=' + _obj.fuksqxx);
				} else if ('关联合同页签' == title) {
					$('#frmGuanlxx').attr('src', 'Form261EntriesViewGuanlxx.jsp?dataId=' + _obj.guanlxx);
				} else if ('违约金' == title) {
					$('#frmWeiyxx').attr('src', 'Form261EntriesViewWeiyxx.jsp?dataId=' + _obj.weiyxx);
				} else if ('扣款' == title) {
					$('#frmKoukxx').attr('src', 'Form261EntriesViewKoukxx.jsp?dataId=' + _obj.koukxx);
				} else if ('奖励' == title) {
					$('#frmJianglxx').attr('src', 'Form261EntriesViewJianglxx.jsp?dataId=' + _obj.jianglxx);
				} else if ('工程付款情况表' == title) {
					$('#frmGongcfkxxb').attr('src', 'Form261EntriesViewGongcfkxxb.jsp?dataId=' + _obj.gongcfkxx);
				}
				
				_map[title] = true;
			}
		});
	});
});

var resizeTabWidth = function() {
	$('ul.tabs').css('width', '100%');
};