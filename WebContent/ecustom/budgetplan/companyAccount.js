/**
 * 
 */
$(document).ready(function(){
	
	var companyAccounts = [];
	
	/**
	 * 加载时，显示之前已经勾选过的科目。
	 */
	var checkTree = function(treeName) {
		for (var i = 0; i < companyAccounts.length; i++) {
			var treeId = companyAccounts[i].accountL1 + '_' + companyAccounts[i].accountL2;
			var node = $('#' + treeName).tree('find', treeId);
			if (node) {
				$("#" + treeName).tree("check", node.target);
			}
		}
	};
	
	var initAccountTree = function() {
		$.getJSON('/greedc/servlets/BPAccount-getAccountAll.json', {
			type: 0,
			time: new Date().getTime()
		}, function(resp) {
			$('#treeInAccount').tree({
				data: resp,
				onlyLeafCheck: true
			});
			$('#treeInAccount').tree('expandAll');
			checkTree('treeInAccount');
		});
		
		$.getJSON('/greedc/servlets/BPAccount-getAccountAll.json', {
			type: 1,
			time: new Date().getTime()
		}, function(resp) {
			$('#treeOutAccount').tree({
				data: resp,
				onlyLeafCheck: true
			});
			$('#treeOutAccount').tree('expandAll');
			checkTree('treeOutAccount');
		});
	};
	
	/**
	 * 获取之前已经勾选过的科目信息
	 */
	$.getJSON('/greedc/servlets/BPAccount-getCompanyAccount.json', {
		companyId: $('#companyId').val(),
		time: new Date().getTime()
	}, function(resp){
		companyAccounts = resp;
		initAccountTree();
	});
	
	$.getJSON('/greedc/servlets/BPCompany-getCompanyInfo.json', {
		companyId: $('#companyId').val(),
		time: new Date().getTime()
	}, function(resp) {
		$('#blockName').html(resp['blockName']);
		$('#companyName').html(resp['subCompanyName']);
	});
	
	window.save = function() {
		var companyId = $('#companyId').val();
		if (!companyId) {
			$.messager.alert('提示', '没有指定公司！');
			return ;
		}
		
		var nodesStr = '';
		var nodes = $('#treeInAccount').tree('getChecked');
		for (var i = 0; i < nodes.length; i++) {
			nodesStr += ',' + nodes[i].attributes.childid;
		}

		var nodes = $('#treeOutAccount').tree('getChecked');
		for (var i = 0; i < nodes.length; i++) {
			nodesStr += ',' + nodes[i].attributes.childid;
		}
		
		if (!nodesStr) {
			$.messager.alert('提示', '请勾选要保存科目！');
			return ;
		}
		
		nodesStr = nodesStr.substring(1, nodesStr.length);
		
		$.getJSON('/greedc/servlets/BPAccount-saveCompanyAccount.json', {
			companyId: $('#companyId').val(),
			ids: nodesStr,
			time: new Date().getTime()
		}, function(resp) {
			$.messager.confirm('提示', resp.message + '<br/>是否重新加载该页面？', function(r){
				if (r) {
					window.location.reload();
				}
			});
		});
	};
});