/**
 * 
 */
$(document).ready(function(){
	debugger;
	var companyId = $('#companyId').val();   //获取id=companyId的value值；
	var year = $('#cmbYear').val();
	var blockId = $('#blockId').val();
	var status = 0;	// 状态为 0 时，可编辑
	
	var onDataGridEdit = function() {
		// 判断是否有编辑权限
		if (status != 0 || isNaN(companyId) || companyId <= 0) {
			return ;
		}
		
		/*扩展自 $.fn.panel.defaults。通过 $.fn.datagrid.defaults 重写默认的 defaults。
		数据网格（datagrid）以表格格式显示数据，并为选择、排序、分组和编辑数据提供了丰富的支持。数据网格（datagrid）
		的设计目的是为了减少开发时间，且不要求开发人员具备指定的知识。它是轻量级的，但是功能丰富。它的特性包括单元格合并，
		多列页眉，冻结列和页脚，等等。*/
		var rows = $('#dg').datagrid('getRows');   
		for (var i = 0; i < rows.length; i++) {
			$('#dg').datagrid('beginEdit', i);
		}
		
		//通过 jQuery，您可以使用 hide() 和 show() 方法来隐藏和显示 HTML 元素
		
		$('#btnEdit').hide();
		$('#btnActive').hide();
		$('#btnSave').show();
		$('#btnCancel').show();
	};
	
	var onDataGridSave = function() {
		$('#dg').datagrid('acceptChanges');	// 接受表格所有改变
		reloadFooter();	// 重新计算合计信息
		var data = $('#dg').datagrid('getRows');
		for (var i = 0; i < data.length; i++) {
			if (!$('#dg').datagrid('validateRow', i)) {
				$.messager.alert('提示', '请完成所有必填项！');
				return ;
			}
		}
		
		for (var i = 0; i < data.length; i++) {
			data[i].season1 = data[i].season1 || null;  //？=?||0,当？为null，取0，？不为null或undefined，？=？
			data[i].season2 = data[i].season2 || null;
			data[i].season3 = data[i].season3 || null;
			data[i].season4 = data[i].season4 || null;
		}
		
		/*jQuery.post(url,data,success(data, textStatus, jqXHR),dataType);
		其中	url:必需。规定把请求发送到哪个 URL。
		   	data:可选。映射或字符串值。规定连同请求发送到服务器的数据。
		   	function(data,status,xhr):可选。规定当请求成功时运行的函数。额外的参数：
		        data - 包含来自请求的结果数据
		        status - 包含请求的状态（"success"、"notmodified"、"error"、"timeout"、"parsererror"）xhr - 包含 XMLHttpRequest 对象
		   	dataType:可选。规定预期的服务器响应的数据类型。默认地，jQuery 会智能判断。可能的类型：
		        "xml" - 一个 XML 文档
		        "html" - HTML 作为纯文本
		        "text" - 纯文本字符串
		        "script" - 以 JavaScript 运行响应，并以纯文本返回
		        "json" - 以 JSON 运行响应，并以 JavaScript 对象返回
		        "jsonp" - 使用 JSONP 加载一个 JSON 块，将添加一个 "?callback=?" 到 URL 来规定回调*/
		//这里是用来把JSON数据转成字符串
		                             
		$.post('/greedc/servlets/BPAccount-saveExternalPay.json', {
			data: JSON.stringify(data),
			companyId: companyId,
			year: year,
			time: new Date().getTime()
		}, function(resp){
			if (resp.status == false) {
				$.messager.alert('提示', resp.message, 'warning'); //弹窗提示信息
				return ;
			}
			$.messager.confirm('提示', resp.message + '<br/>是否重新加载该页面？', function(r){
				if (r) {
					window.location.reload(); // 刷新
				}
				initButton();
			});
		}, 'json');

	};
	
	var reloadFooter = function() {
		var seasonAmount = {accountName: '合计', season1: 0, season2: 0, season3: 0, season4: 0};
		var data = $('#dg').datagrid('getRows');
		for (var i = 0; i < data.length; i++) {
			seasonAmount.season1 += data[i].season1 * 1 || 0;   //？=?||0,当？为null，取0，？不为null或undefined，？=？
			seasonAmount.season2 += data[i].season2 * 1 || 0;
			seasonAmount.season3 += data[i].season3 * 1 || 0;
			seasonAmount.season4 += data[i].season4 * 1 || 0;
		}
		$('#dg').datagrid('reloadFooter', [seasonAmount]);
	};
	
	/**
	 * 初始化按钮显示与隐藏
	 */
	var initButton = function() {
		// 如果是公司级，并且状态=0，显示可编辑/生效
		if (!isNaN(companyId) && companyId > 0 && status == 0) {
			$('#btnEdit').show();
			$('#btnActive').show();
			$('#btnSave').hide();
			$('#btnCancel').hide();
			return ;
		}
	};

	var initStatus = function() {
		if (!companyId) {
			$('#status').text('生效');
			return ;
		}
		$.getJSON('/greedc/servlets/BPAccount-saveExternalPayStatus.json', {
			companyId: companyId,
			year: year,
			time: new Date().getTime()
		}, function(resp) {
			status = resp.status || 0;
			var statusText = '草稿';
			if (status == 1) {
				statusText = '生效';
			}
			$('#status').text(statusText);
			initButton();
		});
	};
	
	var onDataGridActive = function() {
		$.messager.confirm('提示', '生效后数据不可修改，确认生效吗？', function(r){
			if (r) {
				//获取json数据转成字符串
				$.getJSON('/greedc/servlets/BPAccount-activeExternalPay.json', {
					companyId: companyId,
					year: year,
					time: new Date().getTime()
				}, function(resp) {
					window.location.reload();
				});
			}
		});
	};
	
	var onDataGridCancel = function() {
		 $('#dg').datagrid('rejectChanges');
		 initButton();
	};
	
	$('#btnEdit').bind('click', onDataGridEdit);
	$('#btnSave').bind('click', onDataGridSave);
	$('#btnActive').bind('click', onDataGridActive);
	$('#btnCancel').bind('click', onDataGridCancel);
	$('#cmbYear').bind('change', function(){
		window.location.href = 'companyExternalPay.jsp?blockId=' + blockId + '&companyId=' + companyId + '&year=' + $('#cmbYear').val();
	});
	
	
	if (companyId) {
		//通过 HTTP GET 请求载入 JSON 数据。
		$.getJSON('/greedc/servlets/BPCompany-getCompanyInfo.json', {
			companyId: companyId,
			time: new Date().getTime()
		}, function(resp) {
			$('#companyName').html(resp.subCompanyName);
		});
	} else if (blockId) {
		$.getJSON('/greedc/servlets/BPCompany-getBlockInfo.json', {
			blockId: blockId,
			time: new Date().getTime()
		}, function(resp) {
			$('#companyName').html(resp.blockName);
		});
	}
	
	$('#dg').datagrid({
		singleSelect: true,
		method: 'get',
		url: '/greedc/servlets/BPAccount-listExternalPay.json',
		queryParams: {
			companyId: companyId,
			blockId: blockId,
			year: year,
			time: new Date().getTime()
		},
		rownumbers: true,
		autoRowHeight: false,
		showFooter: true,
		onLoadSuccess: function(resp) {
			reloadFooter();
		}
	});

	initStatus();
});

var toMoney = function(num) {
	num = isNaN(num) ? 0 : num * 1;
	var lessZero = num < 0;
	var float = (num - parseInt(num)).toFixed(2).substring(1, 4);	// 取小数位
	var integer = Math.abs(parseInt(num)) + '';	// 取整数位
	var money = '', split = '';
	while (integer.length > 3) {
		split = money.length > 0 ? ',' : '';
		money = (integer.substring(integer.length - 3, integer.length)) + split + money;
		integer = integer.substring(0, integer.length - 3);
	}
	split = money.length > 0 ? ',' : '';
	money = integer + split + money + float;
	return (lessZero ? '-' : '') + money;
};