/**
 * 
 */

$(document).ready(function() {
	$('#dg').datagrid({
		collapsible: true,
		singleSelect: true,
		width: $(document).width() - 10,
        pagination: true,
        remoteFilter: true,
        showFooter: true,
        onLoadSuccess: compute,//加载完毕后执行计算
		mode: 'remote',
		url: '/greedc/servlets/EasyUI-dataGridPage.json',
		queryParams: {
			name: 'cv_subjectlast',
			key: 'jkdwmc',
			sort: 'jkdwmc asc',
			field: 'jkdwmc,duanjk,changqjk,yingfzq,feildfz,qityfk,qitfldfz,total',
			defFilter: '',
			time: new Date().getTime()
		},
	
	
	});
	

	$('#btnExport').bind('click', function(){
		
		 $("#dg").datagrid('toExcel',"科目统计表.xls");
//		
	});
	
	$.extend($.fn.datagrid.methods, {
	      toExcel: function(jq, filename){
	        return jq.each(function(){
	          var uri = 'data:application/vnd.ms-excel;base64,'
	          , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
	          , base64 = function (s) { return window.btoa(unescape(encodeURIComponent(s))) }
	          , format = function (s, c) { return s.replace(/{(\w+)}/g, function (m, p) { return c[p]; }) }

	          var alink = $('<a style="display:none"></a>').appendTo('body');
	          var view = $(this).datagrid('getPanel').find('div.datagrid-view');
	          
	          var table = view.find('div.datagrid-view2 table.datagrid-btable').clone();
	          var tbody = table.find('>tbody');
	          view.find('div.datagrid-view1 table.datagrid-btable>tbody>tr').each(function(index){
	            $(this).clone().children().prependTo(tbody.children('tr:eq('+index+')'));
	          });
	          
	          var head = view.find('div.datagrid-view2 table.datagrid-htable').clone();
	          var hbody = head.find('>tbody');
	          view.find('div.datagrid-view1 table.datagrid-htable>tbody>tr').each(function(index){
	            $(this).clone().children().prependTo(hbody.children('tr:eq('+index+')'));
	          });    
	          hbody.prependTo(table);
	          
	          var ctx = { worksheet: name || 'Worksheet', table: table.html()||'' };
	          alink[0].href = uri + base64(format(template, ctx));
	          alink[0].download = filename;
	          alink[0].click();
	          alink.remove();
	        })
	      }
	});
	
});


function compute() {//计算函数
    var rows = $('#dg').datagrid('getRows')//获取当前的数据行
    var ptotal = 0//计算listprice的总和
    ,duanjk=0//统计unitcost的总和
    ,changqjk=0//统计unitcost的总和
    ,yingfzq=0//统计unitcost的总和
    ,feildfz=0//统计unitcost的总和
    ,qityfk=0//统计unitcost的总和
    ,qitfldfz=0//统计unitcost的总和
    ,all=0;//统计unitcost的总和
    for (var i = 0; i < rows.length; i++) {
    	duanjk += parseInt(rows[i]['duanjk']);
    	changqjk += parseInt(rows[i]['changqjk']);
    	yingfzq += parseInt(rows[i]['yingfzq']);
    	feildfz += parseInt(rows[i]['feildfz']);
        qityfk += parseInt(rows[i]['qityfk']);
        qitfldfz += parseInt(rows[i]['qitfldfz']);
        all += parseInt(rows[i]['total']);
    }
    //新增一行显示统计信息
    $('#dg').datagrid('appendRow', { jkdwmc: '<b>合计</b>', duanjk: duanjk, changqjk: changqjk ,yingfzq : yingfzq,feildfz : feildfz,qityfk : qityfk,qitfldfz : qitfldfz,total : all});
}


function compute1() {//计算函数
    var rows = $('#dg1').datagrid('getRows')//获取当前的数据行
    var ptotal = 0//计算listprice的总和
    ,duanjk=0//统计unitcost的总和
    ,changqjk=0//统计unitcost的总和
    ,yingfzq=0//统计unitcost的总和
    ,feildfz=0//统计unitcost的总和
    ,qityfk=0//统计unitcost的总和
    ,qitfldfz=0//统计unitcost的总和
    ,all=0;//统计unitcost的总和
    for (var i = 0; i < rows.length; i++) {
    	duanjk += parseInt(rows[i]['duanjk']);
    	changqjk += parseInt(rows[i]['changqjk']);
    	yingfzq += parseInt(rows[i]['yingfzq']);
    	feildfz += parseInt(rows[i]['feildfz']);
        qityfk += parseInt(rows[i]['qityfk']);
        qitfldfz += parseInt(rows[i]['qitfldfz']);
        all += parseInt(rows[i]['total']);
    }
    //新增一行显示统计信息
    $('#dg1').datagrid('appendRow', { daiklx: '<b>合计</b>', duanjk: duanjk, changqjk: changqjk ,yingfzq : yingfzq,feildfz : feildfz,qityfk : qityfk,qitfldfz : qitfldfz,total : all});
}


function compute2() {//计算函数
    var rows = $('#dg2').datagrid('getRows')//获取当前的数据行
    var ptotal = 0//计算listprice的总和
    ,duanjk=0//统计unitcost的总和
    ,changqjk=0//统计unitcost的总和
    ,yingfzq=0//统计unitcost的总和
    ,feildfz=0//统计unitcost的总和
    ,qityfk=0//统计unitcost的总和
    ,qitfldfz=0//统计unitcost的总和
    ,all=0;//统计unitcost的总和
    for (var i = 0; i < rows.length; i++) {
    	duanjk += parseInt(rows[i]['duanjk']);
    	changqjk += parseInt(rows[i]['changqjk']);
    	yingfzq += parseInt(rows[i]['yingfzq']);
    	feildfz += parseInt(rows[i]['feildfz']);
        qityfk += parseInt(rows[i]['qityfk']);
        qitfldfz += parseInt(rows[i]['qitfldfz']);
        all += parseInt(rows[i]['total']);
    }
    //新增一行显示统计信息
    $('#dg2').datagrid('appendRow', { danbfs: '<b>合计</b>', duanjk: duanjk, changqjk: changqjk ,yingfzq : yingfzq,feildfz : feildfz,qityfk : qityfk,qitfldfz : qitfldfz,total : all});
}
 
    function show_sub(v){     
    	if (v == '1') {
    		$('#fundsdiv').show();
			$('#fundsdiv1').hide();
			$('#fundsdiv2').hide();
	        
			$('#dg').datagrid({
				collapsible: true,
				singleSelect: true,
				width: $(document).width() - 10,
		        pagination: true,
		        remoteFilter: true,
		        showFooter: true,
		        onLoadSuccess: compute,//加载完毕后执行计算
				mode: 'remote',
				url: '/greedc/servlets/EasyUI-dataGridPage.json',
				queryParams: {
					name: 'cv_subjectlast',
					key: 'jkdwmc',
					sort: 'jkdwmc asc',
					field: 'jkdwmc,duanjk,changqjk,yingfzq,feildfz,qityfk,qitfldfz,total',
					defFilter: '',
					time: new Date().getTime()
				},
			
			
			});
			
	        }
    	if (v == '2') {
    		$('#fundsdiv').hide();
			$('#fundsdiv1').show();
			$('#fundsdiv2').hide();
	        
			$('#dg1').datagrid({
				collapsible: true,
				singleSelect: true,
				width: $(document).width() - 10,
		        pagination: true,
		        remoteFilter: true,
		        showFooter: true,
		        onLoadSuccess: compute1,//加载完毕后执行计算
				mode: 'remote',
				url: '/greedc/servlets/EasyUI-dataGridPage.json',
				queryParams: {
					name: 'cv_subjectlast1',
					key: 'daiklx',
					sort: 'daiklx asc',
					field: 'daiklx,duanjk,changqjk,yingfzq,feildfz,qityfk,qitfldfz,total',
					defFilter: '',
					time: new Date().getTime()
				},
			
			
			});
			
	        }
    	
    	if (v == '3') {
    		$('#fundsdiv').hide();
			$('#fundsdiv1').hide();
			$('#fundsdiv2').show();
	        

			
			$('#dg2').datagrid({
				collapsible: true,
				singleSelect: true,
				width: $(document).width() - 10,
		        pagination: true,
		        remoteFilter: true,
		        showFooter: true,
		        onLoadSuccess: compute2,//加载完毕后执行计算
				mode: 'remote',
				url: '/greedc/servlets/EasyUI-dataGridPage.json',
				queryParams: {
					name: 'cv_subjectlast2',
					key: 'danbfs',
					sort: 'danbfs asc',
					field: 'danbfs,duanjk,changqjk,yingfzq,feildfz,qityfk,qitfldfz,total',
					defFilter: '',
					time: new Date().getTime()
				},
			
			
			});
			
	        }
    }     

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

var openReport = function(reportType, yearMonth) {
	window.open('/ecustom/budgetplan/groupFundsPlan.jsp?yearMonth=' + yearMonth + '&reportType=' + reportType);
};

var fmt_operate = function(value, row, index) {
	var buttons = '<a href="javascript:void()" class="easyui-linkbutton" onclick="openReport(1, \'' + row.lastYearMonth + '\')">月报</a> ';
	buttons += '<a href="javascript:void()" class="easyui-linkbutton" onclick="openReport(2, \'' + row.lastYearMonth + '\')">季报</a> ';
	buttons += '<a href="javascript:void()" class="easyui-linkbutton" onclick="openReport(3, \'' + row.lastYearMonth + '\')">年报</a>';
	return buttons;
};