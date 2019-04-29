/**
 * 授信统计明细表
 */
$(document).ready(function(){
	var blocks = {};
	var tdRowspan = {};
	var columns=new Array();
	var datas=new Array();
	var year;

	var myDate = new Date();
	year = myDate.getFullYear();
	
	$('#title').text( '利息台账表');
	
	$("#choose").on("change",function(){
		
		if ($("#choose").val() == '1') {
			
			year=2019;
			datas=[];
			getData();
		}
		
		if ($("#choose").val() == '2') {
			
			year=2020;
			datas=[];
			getData();
			
		}
		
		if ($("#choose").val() == '3') {
			
			year=2021;
			datas=[];
			getData();
			
		}
		
		if ($("#choose").val() == '4') {
			
			year=2022;
			datas=[];
			getData();
		}
		
		if ($("#choose").val() == '5') {
			
			year=2023;
			datas=[];
			getData();
		}
		
		if ($("#choose").val() == '6') {
			
			year=2016;
			datas=[];
			getData();
		}
		
		if ($("#choose").val() == '7') {
			
			year=2017;
			datas=[];
			getData();
		}
		
		if ($("#choose").val() == '8') {
			
			year=2018;
			datas=[];
			getData();
		}
	  		
		});
	
	
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	

			           var column1={};
			            column1["title"]='借款单号';
			            column1["field"]="jiekdh";
			            column1["width"]=fixWidth(0.15);
			            columns.push(column1);
			            
				       var column2={};
				        column2["title"]='公司名称';
				        column2["field"]="gongsmc";
				        column2["width"]=fixWidth(0.15);
				        columns.push(column2);
				        
					   var column3={};
					    column3["title"]='金融机构';
					    column3["field"]="jinrjg";
					    column3["width"]=fixWidth(0.15);
					    columns.push(column3);
					        
					   var column4={};
						 column4["title"]='授信额度';
						 column4["field"]="shouxed";
						 column4["width"]=fixWidth(0.08);
						 columns.push(column4);
						 
						 
						 var column5={};
						 column5["title"]='借款金额';
						 column5["field"]="jiekje";
						 column5["width"]=fixWidth(0.08);
						 columns.push(column5);						 
						 
						 var column6={};
						 column6["title"]='起始日期';
						 column6["field"]="qisrq";
						 column6["width"]=fixWidth(0.08);
						 columns.push(column6);
						 
						 var column7={};
						 column7["title"]='终止日期';
						 column7["field"]="zongzrq";
						 column7["width"]=fixWidth(0.08);
						 columns.push(column7);
						 
						 var column8={};
						 column8["title"]='总成本';
						 column8["field"]="jiekzcb";
						 column8["width"]=fixWidth(0.08);
						 columns.push(column8);
						 
						 var column9={};
						 column9["title"]='1月';
						 column9["field"]="month1";
						 column9["width"]=fixWidth(0.08);
						 columns.push(column9);
						 
						 var column10={};
						 column10["title"]='2月';
						 column10["field"]="month2";
						 column10["width"]=fixWidth(0.08);
						 columns.push(column10);
						 
						 var column11={};
						 column11["title"]='3月';
						 column11["field"]="month3";
						 column11["width"]=fixWidth(0.08);
						 columns.push(column11);
						 
						 var column12={};
						 column12["title"]='4月';
						 column12["field"]="month42";
						 column12["width"]=fixWidth(0.08);
						 columns.push(column12);
						 
						 var column13={};
						 column13["title"]='5月';
						 column13["field"]="month5";
						 column13["width"]=fixWidth(0.08);
						 columns.push(column13);
						 
						 var column14={};
						 column14["title"]='6月';
						 column14["field"]="month6";
						 column14["width"]=fixWidth(0.08);
						 columns.push(column14);
						 
						 var column15={};
						 column15["title"]='7月';
						 column15["field"]="month7";
						 column15["width"]=fixWidth(0.08);
						 columns.push(column15);
						 
						 var column16={};
						 column16["title"]='8月';
						 column16["field"]="month8";
						 column16["width"]=fixWidth(0.08);
						 columns.push(column16);
						 
						 var column17={};
						 column17["title"]='9月';
						 column17["field"]="month9";
						 column17["width"]=fixWidth(0.08);
						 columns.push(column17);
						 
						 var column18={};
						 column18["title"]='10月';
						 column18["field"]="month10";
						 column18["width"]=fixWidth(0.08);
						 columns.push(column18);
						 
						 var column19={};
						 column19["title"]='11月';
						 column19["field"]="month11";
						 column19["width"]=fixWidth(0.08);
						 columns.push(column19);
						 
						 var column20={};
						 column20["title"]='12月';
						 column20["field"]="month12";
						 column20["width"]=fixWidth(0.08);
						 columns.push(column20);
					   	 
			
		               var column21={};
			             column21["title"]='合計';
			             column21["field"]="total";
			             column21["width"]=fixWidth(0.08);
			             columns.push(column21);
	
		
		function fixWidth(percent)  
		{  
		    return document.body.clientWidth * percent ;//根据自身情况更改

		}  
	
		getData();
	

	 function getData() {
			$.getJSON('/greedc/servlets/Loan-dataGridInterest1.json?name=cv_loadinterest', {
				time: new Date().getTime(),
				key: 'id',
				sort: 'id asc',
				page: '1',
				rows: '10',
				year: year
			}, function(projects) {
			
				if(projects.rows.length!=0){
				for (var i = 0; i<projects.rows.length; i++) {
					
					
				    var data={};   
		            data["jiekdh"]=projects.rows[i].JIEKDH;
		            data["gongsmc"]=projects.rows[i].COMPANYNAME;
		            data["jinrjg"]=projects.rows[i].BANKNAME;
		            data["shouxed"]=toMoney(projects.rows[i].SHOUXED);
		            data["jiekje"]=toMoney(projects.rows[i].JIEKJE);
		            data["qisrq"]=projects.rows[i].QISRQ;
		            data["zongzrq"]=projects.rows[i].ZHONGZRQ;
		            data["jiekzcb"]=toMoney(projects.rows[i].JIEKZCB);
		            data["id"]=projects.rows[i].ID;
		            data["total"]=toMoney(projects.rows[i].TOTAL);
		            
		            getCompanyData(data);
		            
//		            datas.push(data);
					         
				
				}
				
			}else{
				
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
				
			
				
			}
				
				
				});
			
		
		}
	 
	 
	 function getCompanyData(data) {
			$.getJSON('/greedc/servlets/Loan-dataGridInterest.json?name=uf_borrowing_dt2', {
				time: new Date().getTime(),
				key: 'id',
				sort: 'id asc',
				page: '1',
				rows: '10',
				year: year
			}, function(projects) {
			
		
				for (var j = 0; j<projects.length; j++) {
					
					 if(projects[j].MAINID == data.id){
					   
					        if(projects[j].QIJIANY == 1){
                   	 
							 
					         data["month1"]=toMoney(projects[j].LIXJE);
                   	 
                            }
					        
					        if(projects[j].QIJIANY == 2){
			                   	 
								 
						         data["month2"]=toMoney(projects[j].LIXJE);
	                   	 
	                          }
					        
					        
					        if(projects[j].QIJIANY == 3){
			                   	 
								 
						         data["month3"]=toMoney(projects[j].LIXJE);
	                   	 
	                          }
					        
					        if(projects[j].QIJIANY == 4){
			                   	 
								 
						         data["month4"]=toMoney(projects[j].LIXJE);
	                   	 
	                          }
					        
					        if(projects[j].QIJIANY == 5){
			                   	 
								 
						         data["month5"]=toMoney(projects[j].LIXJE);
	                   	 
	                          }
					        
					        if(projects[j].QIJIANY == 6){
			                   	 
								 
						         data["month6"]=toMoney(projects[j].LIXJE);
	                   	 
	                          }
					        
					        if(projects[j].QIJIANY == 7){
			                   	 
								 
						         data["month7"]=toMoney(projects[j].LIXJE);
	                   	 
	                          }
					        
					        if(projects[j].QIJIANY == 8){
			                   	 
								 
						         data["month8"]=toMoney(projects[j].LIXJE);
	                   	 
	                          }
					        
					        if(projects[j].QIJIANY == 9){
			                   	 
								 
						         data["month9"]=toMoney(projects[j].LIXJE);
	                   	 
	                          }
					        
					        if(projects[j].QIJIANY == 10){
			                   	 
								 
						         data["month10"]=toMoney(projects[j].LIXJE);
	                   	 
	                          }
					        
					        if(projects[j].QIJIANY == 11){
			                   	 
								 
						         data["month11"]=toMoney(projects[j].LIXJE);
	                   	 
	                          }
					        
					        if(projects[j].QIJIANY == 12){
			                   	 
								 
						         data["month12"]=toMoney(projects[j].LIXJE);
	                   	 
	                          }
                    
					
				}else{}
			 
				
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
		
		
		$('#btnExport').bind('click', function(){
			
			 $("#dg").datagrid('toExcel',"利息台账统计表.xls");
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

