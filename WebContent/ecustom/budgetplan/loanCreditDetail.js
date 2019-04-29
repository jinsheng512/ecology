/**
 * 授信统计明细表
 */
$(document).ready(function(){
	var blocks = {};
	var tdRowspan = {};
	var columns=new Array();
	var datas=new Array();
	var com;
	
	$('#title').text( '借款明细表');
	
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	
	$.getJSON('/greedc/servlets/Loan-dataGridShou.json?name=uf_jkcompany', {
		time: new Date().getTime(),
		key: 'id',
		sort: 'id asc',
		page: '1',
		rows: '10'
			
	}, function(COM) {
		
	                   com  = COM;
			           
			           var column1={};
			            column1["title"]='金融机构';
			            column1["field"]="JINRJG";
			            column1["width"]=fixWidth(0.15);
			            columns.push(column1);
			            
				       var column2={};
				        column2["title"]='授信额度';
				        column2["field"]="SHOUXED";
				        column2["width"]=fixWidth(0.1);
				        columns.push(column2);
				        
//					   var column3={};
//					    column3["title"]='借款金額';
//					    column3["field"]="JIEKJE";
//					    column3["width"]=fixWidth(0.1);
//					    columns.push(column3);
//					        
//					   var column4={};
//						 column4["title"]='余額';
//						 column4["field"]="YUE";
//						 column4["width"]=fixWidth(0.1);
//						 columns.push(column4);
			
			         
		
		
	
		
	
		for (var i = 0; i<COM.rows.length; i++) {
			
			   var column={};
			            column["title"]=COM.rows[i].JKDWMC;
			            column["field"]=COM.rows[i].JKDWMC;
			            column["width"]=fixWidth(0.2);
			            columns.push(column);
			
			        
		
		}
	
		
		   var column5={};
			 column5["title"]='合計';
			 column5["field"]="TOTAL";
			 column5["width"]=fixWidth(0.1);
			 columns.push(column5);
	
		
		function fixWidth(percent)  
		{  
		    return document.body.clientWidth * percent ;//根据自身情况更改

		}  
	
	
		getData();
	});
	

	 function getData() {
			$.getJSON('/greedc/servlets/Loan-dataGridShou.json?name=CV_LOADCREDIT', {
				time: new Date().getTime(),
				key: 'id',
				sort: 'id asc',
				page: '1',
				rows: '10'
			}, function(projects) {
			
				for (var i = 0; i<projects.rows.length; i++) {
					
					
				    var data={};   
		            data["JINRJG"]=projects.rows[i].JINRJG;
		            data["SHOUXED"]=toMoney(projects.rows[i].SHOUXED);
		            data["JIEKJE"]=toMoney(projects.rows[i].JIEKJE);
//		            data["YUE"]=projects.rows[i].YUE;
		            data["TOTAL"]=toMoney(projects.rows[i].TOTAL);
//		            data["JINRJG"]=projects[i].JINRJG;
		            
		            getCompanyData(data);
		            
//		            datas.push(data);
					         
				
				}
				
			
				
				
				});
			
		
		}
	 
	 
	 function getCompanyData(data) {
			$.getJSON('/greedc/servlets/Loan-dataGridShou.json?name=cc', {
				time: new Date().getTime(),
				key: 'id',
				sort: 'id asc',
				page: '1',
				rows: '10'
			}, function(projects) {
			
				  
					for (var i = 0; i<com.rows.length; i++) {
						
						
							    
							  
							 
								for (var j = 0; j<projects.rows.length; j++) {
									
									 if(projects.rows[j].JINRJG == data.JINRJG){
									   
									        if(com.rows[i].JKDWMC == projects.rows[j].JKDWMC ){
				                    	 
											 
											 data[com.rows[i].JKDWMC]=toMoney(projects.rows[j].JIEKJES);
											 console.log(com.rows[i].JKDWMC);
											 console.log(data[com.rows[i].JKDWMC]);
				                    	 
				                           }
				                     
									
								}else{}
							 
								
						 }
								
								 if(data[com.rows[i].JKDWMC]==undefined){
									 
									 data[com.rows[i].JKDWMC]=0.00;
									 
								 }
	                    	 
//                    	    
                           
						
					}
				
				 datas.push(data);
				
				
				
				
				$('#dg').datagrid({loadFilter:pagerFilter}).datagrid({
					collapsible: true,
					singleSelect: true,
					width: $(document).width() - 10,
			        pagination: true,
			        remoteFilter: true,
			        showFooter: true,
			        fitcolumns:false,
					mode: 'remote',
					columns: [columns],
				
				});
				$('#dg').datagrid('loadData',datas);
				
				});
			
	 }
	 
		$('#btnExport').bind('click', function(){
			
			 $("#dg").datagrid('toExcel',"授信明细统计表.xls");
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
	 
	 function pagerFilter(data){
         if (typeof data.length == 'number' && typeof data.splice == 'function'){    // 判断数据是否是数组
             data = {
                 total: data.length,
                 rows: data
             }
         }
         var dg = $(this);
         var opts = dg.datagrid('options');
         var pager = dg.datagrid('getPager');
         pager.pagination({
             onSelectPage:function(pageNum, pageSize){
                 opts.pageNumber = pageNum;
                 opts.pageSize = pageSize;
                 pager.pagination('refresh',{
                     pageNumber:pageNum,
                     pageSize:pageSize
                 });
                 dg.datagrid('loadData',data);
             }
         });
         if (!data.originalRows){
             data.originalRows = (data.rows);
         }
         var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
         var end = start + parseInt(opts.pageSize);
         data.rows = (data.originalRows.slice(start, end));
         return data;
     }



});