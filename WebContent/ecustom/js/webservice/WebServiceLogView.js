$(document).ready(function(){
	$('#dg').datagrid({
		title: 'WebService运行日志',
		rownumbers: true,
		collapsible:true,
		singleSelect: true,
		url: '/ecustom/servlets/WebServiceLog-findAll.json',
		onDblClickCell: function(index, name, value) {
			if (name == 'requestBody' || name == 'responseBody') {
				$('#divWSBody').html();
				$('#win').window({
					title: name == 'requestBody' ? '请求报文' : '响应报文'
				});
				$('#divWSBody').html('数据加载中，请稍后...');
				$('#win').window('open');
				$.get('/ecustom/servlets/WebServiceLog-getWSBody.text', {seqNo: value}, function(resp){
					$('#divWSBody').html(htmlEncode(resp));
				});
			}
		}
	});
	
	function htmlEncode(html) {
		if (html.length == 0) {
			return "";
		}
		html = html.replace(/&/g, "&amp;");
		html = html.replace(/</g, "&lt;");
		html = html.replace(/>/g, "&gt;");
		html = html.replace(/ /g, "&nbsp;");
		html = html.replace(/\"/g, "&quot;");
		html = html.replace(/\n/g, "<br>");
		return html;
	}
});

var fmt = {
	wsBody: function(value, row, index) {
		return '双击查看';
	}
};