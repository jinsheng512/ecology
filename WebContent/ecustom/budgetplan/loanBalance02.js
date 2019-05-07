/**
 * 授信统计明细表
 */
$(document).ready(function(){
	var blocks = {};
	var tdRowspan = {};
	var columns=new Array();
	var datas=new Array();
	var com;
	var BLOCK;
	var BLOCKNUM;
	var FEIJGZJNUM=0;
	var JGZJNUM=0;
	var bankid;
	var jinrjg;
	var jiang;
	var items;
	
	
	$('#title').text( '板块余额统计');
	
//	var startDate = $('#startDate').val();
	
	
	var myDate = new Date();
	var startDate = myDate.toLocaleDateString().split('/').join('-');//将1970/08/08转化成1970-08-08

//	var startDate = '2019-02-28';
	

	

	
	
	
	$.getJSON('/greedc/servlets/Loan-getSearchDataByBanlance.json?', {
		time: new Date().getTime(),
		
	}, function(projects) {
		
//		for (var i = 0; i < projects.jkcompanyList.length; i++) {
//			
//			$("#company").append('<option value='+projects.jkcompanyList[i].id + '>'+projects.jkcompanyList[i].jkdwmc +'</option>');
//		}
		
		for (var i = 0; i < projects.bankList.length; i++) {
			
			$("#bank").append('<option value='+projects.bankList[i].id + '>'+projects.bankList[i].jinrmc +'</option>');
		}
		

	});
	
	
	$("#bank").on("change",function(){
		
		bankid = $("#bank").val();
		
	});
	
	$('#btnSearch').bind('click', function(){
		
		 datas = [];
		
		 startDate = $('#startDate').val();
		 initData();
		
		 
	});
	
	$.getJSON('/greedc/servlets/Loan-dataGridShou.json?name=uf_yeaccounttype', {
		time: new Date().getTime(),
		key: 'id',
		sort: 'shifjgh asc',
		page: '1',
		rows: '10'
			
	}, function(COM) {
		
	                   com  = COM;
	                   
				       var column1={};
				        column1["title"]='金融机构';
				        column1["field"]="BANK";
				        column1["width"]=fixWidth(0.1);
				        columns.push(column1);
	                   
				        
				        var column2={};
			            column2["title"]='公司';
			            column2["field"]="COMPANY";
			            column2["width"]=fixWidth(0.15);
			            columns.push(column2);
			           
//			           var column3={};
//			            column3["title"]='金融机构';
//			            column3["field"]="KAIHH";
//			            column3["width"]=fixWidth(0.15);
//			            columns.push(column3);
			            
	
		for (var i = 0; i<COM.rows.length; i++) {
			
			if(COM.rows[i].SHIFJGH=='0'){
				FEIJGZJNUM++;
			   var column={};
			            column["title"]=COM.rows[i].ZHANGHXZMC;
			            column["field"]=COM.rows[i].ZHANGHXZMC;
			            column["width"]=fixWidth(0.08);
			            columns.push(column);
			}
			        
		
			
			
		}
		
		
        
        var column66={};
         column66["title"]='小计';
         column66["field"]="ALL_NO";
         column66["width"]=fixWidth(0.08);
         columns.push(column66);
		
		
		for (var i = 0; i<COM.rows.length; i++) {
			
			if(COM.rows[i].SHIFJGH=='1'){
				JGZJNUM++;
			   var column={};
			            column["title"]=COM.rows[i].ZHANGHXZMC;
			            column["field"]=COM.rows[i].ZHANGHXZMC;
			            column["width"]=fixWidth(0.08);
			            columns.push(column);
			}
			        
		
			
			
		}
		
        var column77={};
        column77["title"]='小计';
        column77["field"]="ALL_YES";
        column77["width"]=fixWidth(0.08);
        columns.push(column77);
	
		
		   var column5={};
			 column5["title"]='合計';
			 column5["field"]="TOTAL";
			 column5["width"]=fixWidth(0.08);
			 columns.push(column5);
	
				
	
//				getData(startDate,jinrjg);
		
	});
	

	 
		function fixWidth(percent)  
		{  
		    return document.body.clientWidth * percent ;//根据自身情况更改

		}  
		
		
	     initData();
	 	function  initData(){
	 		
	 		$.getJSON('/greedc/servlets/Loan-dataGridTotal02.json?name=CV_JINRJG_02', {//金融机构
	 			time: new Date().getTime(),
	 			key: 'id',
	 			sort: 'id asc',
	 			bankid: bankid,
	 			page: '1',
	 			startDate: startDate,
	 			rows: '30'
	 		}, function(projects) {
	 			
	 			jinrjg = projects;
	 			
	 			
		 		$.getJSON('/greedc/servlets/Loan-dataGridJianG02.json?name=CV_JIANG_DATA_02', {
		 			time: new Date().getTime(),
		 			key: 'id',
		 			sort: 'id asc',
		 			startDate: startDate,
		 			page: '1',
		 			rows: '30'
		 		}, function(projects) {
		 			
		 			jiang = projects;
		 			
		 			
			 		$.getJSON('/greedc/servlets/Loan-dataGridJinRjg02.json?name=CV_JINRJG_DATA', {
			 			time: new Date().getTime(),
			 			key: 'id',
			 			sort: 'id asc',
			 			startDate: startDate,
			 			page: '1',
			 			rows: '30'
			 		}, function(projects) {
			 			
			 			items = projects;
			 			
			 			getData(startDate,jinrjg);
			 		});
		 		});
	 			
	 		
	 			
	 		});
	 		

	 		
	 		

	 		
	 		
	 	}
	
	
	 function getData(startDate,jinrjg) {
			
				if(jinrjg.rows.length!=0){
					
				
				for (var i = 0; i<jinrjg.rows.length; i++) {
					
					
				    var data= new Array();   
//		            data["KAIHH"]=projects.rows[i].KAIHH;
		            if(jinrjg.rows[i].SHIFDKJAJHZYH==0){
		            	data["TAG"]="贷款及按揭合作银行";

		            }else{
		            	
		            	data["TAG"]="其他";
		            }
		            data["TOTAL"]=(jinrjg.rows[i].TOTAL_YUANB);
		            
		            if(jinrjg.rows[i].TOTAL_YUANB ==undefined){
		            	
		            	data["TOTAL"]=0.00;
		            }
//		            data["TOTAL_BLOCK"]=toMoney(projects.rows[i].TOTAL_YUANB);
		            
		         	data["BANK"]= jinrjg.rows[i].JINRMC;
		         	data["COMPANY"]= jinrjg.rows[i].SUBCOMPANYNAME;

		            
		            getJianGData(data,jiang);
//		            refreshData();

					         
				
			}
				
				}else{
					refreshData();
				}
				
			
				
				
//				});
			
			 
		}
	 
	 
	 function getJianGData(data,jiang) {  //小计

				for (var i = 0; i<jiang.rows.length; i++) {
					


					 if(jiang.rows[i].JINRMC == data.BANK && jiang.rows[i].SUBCOMPANYNAME == data.COMPANY){
						 
						 
						    if(jiang.rows[i].SHIFJGH==0){
				            	 data["ALL_NO"]=(jiang.rows[i].TOTAL_JIANG);
				            }
						    
						    if(jiang.rows[i].SHIFJGH==1){
				            	
				            	 data["ALL_YES"]=(jiang.rows[i].TOTAL_JIANG);
				            }
				          
						}else{}
					 
					
				}
				
				if(data["ALL_NO"]==undefined){
					
					data["ALL_NO"]=0.00;
				}
				
				if(data["ALL_YES"]==undefined){
					
					data["ALL_YES"]=0.00;
				}
				
				
				
				   getCompanyData(data,items);
				 
				
//				});
			
		
		}
	 
	 
	 function getCompanyData(data,items) {  //
//			$.getJSON('/greedc/servlets/Loan-dataGridJinRjg01.json?name=CV_JINRJG_DATA', {
//				time: new Date().getTime(),
//				key: 'id',
//				sort: 'id asc',
//				startDate: startDate,
//				page: '1',
//				rows: '10'
//			}, function(projects) {
			
				  
					for (var i = 0; i<com.rows.length; i++) {
						
						
							    
							  
							 
								for (var j = 0; j<items.rows.length; j++) {
									
									 if(items.rows[j].JINRMC == data.BANK && items.rows[j].SUBCOMPANYNAME == data.COMPANY){
									   
									        if(com.rows[i].ZHANGHXZMC == items.rows[j].ZHANGHXZMC ){
				                    	 
											 
											 data[com.rows[i].ZHANGHXZMC]=(items.rows[j].YUANB);
										
				                    	 
				                           }
				                     
									
								}else{}
							 
								
						 }
								
								 if(data[com.rows[i].ZHANGHXZMC]==undefined){
									 
									 data[com.rows[i].ZHANGHXZMC]=0.00;
									 
								 }
	                    	 
//                    	    
                           
						
					}
					
				
					 datas.push(data);
						
					 refreshData();
						
						
						
//	 });
	 
			
		
					}
	 
		function refreshData() {
			
			$('#dg').datagrid('loadData',datas);
			
//			$('#dg').datagrid({
				
				$('#dg').datagrid({loadFilter:pagerFilter}).datagrid({
				collapsible: true,
				singleSelect: true,
				width: $(document).width() - 10,
		        pagination: true,
		        pageSize: 30,
                pageList: [30, 50, 200],
		        remoteFilter: true,
//		        onLoadSuccess: compute,//加载完毕后执行计算
		        showFooter: true,
		        fitcolumns:false,
		        rownumbers:false,
				mode: 'remote',
				columns: [[{"title":"","colspan":2,"id":"test01"},
				           {"title":"不可用余额","colspan":FEIJGZJNUM+1,"id":"test02"},
				  		 {"title":"可用余额","colspan":JGZJNUM+1,"id":"test03"}, 
				  		 {"title":"","colspan":1,"id":"test04"} ],columns],
//				  		 {"title":"板块","colspan":BLOCKNUM+1,"id":"test05"}],columns],
				onLoadSuccess: function(datas) {
					
					
					  var rows = $('#dg').datagrid('getRows');
					    var ff = "";
					    var j= new Array();
					    var k=0; //开始位置
//					    var g=0;
					    var p=0;
					   for (var i = 0; i < jinrjg.rows.length-1; i++) {
						   
						 
						     ff =jinrjg.rows[i+1]['JINRMC']
						 
							if(ff != jinrjg.rows[i]['JINRMC'])  {
								  j.push(i);
								  
								  insertRow(k,(i)+(j.length));
								  k=(i)+1+ (j.length);  //上一个小计加1，就是下一个小计的开始
//								  g=(i+1)+(j.length);
						         
							} 
						     
						     p=(k+1)+(j.length);
						    
					
						   
					   }
					   
						  if(bankid=="" || bankid==undefined){ //没有选择模块
							   
							  insertRow(k,p);  //最后模块小计
							 appendTotalRow();
						    	 
					  }else{
						  
						  insertRow1();  //最后模块小计,选择了板块
					  }
						 
					    	 
					
					
//					appendTotalRow();
				        //所有列进行合并操作
				        //$(this).datagrid("autoMergeCells");
				        //指定列进行合并操作(数组中写要合并的列名(field属性值))
					mergeCellsByField("dg", "BANK");

					$(this).datagrid('fixRowHeight') 
					 
				    }
			
			
			});
			
		}
		

		function insertRow1() {//计算函数
		    var rows = $('#dg').datagrid('getRows')//获取当前的数据行
		    var ptotal = 0//计算listprice的总和
		    ,utotal=0//统计unitcost的总和
		    ,ytotal=0//统计unitcost的总和
		    ,ntotal=0//统计unitcost的总和
		    ,btotal=0//统计unitcost的总和
		    ,all=0;//统计unitcost的总和
		    var  jsonData = {COMPANY : '<b>合计</b>'};
		    	
		    	
		    	for (var j = 0; j<com.rows.length; j++) {
		    		
		    		ptotal = 0 ;
		    		utotal =0 ;
		    		ytotal =0 ;
		    		ntotal =0 ;
		    		  for (var i = 0; i < rows.length; i++) {
		    			  
		    		  ptotal += parseFloat(rows[i][com.rows[j].ZHANGHXZMC]);
		    		  utotal += parseFloat(rows[i]['TOTAL']);
    				  ytotal += parseFloat(rows[i]['ALL_YES']);
    				  ntotal += parseFloat(rows[i]['ALL_NO']);
//	    			 
		    		  }
		    		
		    		  jsonData[com.rows[j].ZHANGHXZMC] = ptotal;
//		    		  fn(com.rows[j][ZHANGHXZMC],ptotal);
//		    		  total.push( data);
		    		  jsonData['TOTAL'] =utotal;
		    		  jsonData['ALL_YES'] =ytotal;
		    		  jsonData['ALL_NO'] =ntotal;
		    		 
		    	}
		    	
		    	console.log(jsonData);

		       
		    
		    //新增一行显示统计信息
		    $('#dg').datagrid('appendRow',  jsonData);
		}
		
		function appendTotalRow() {//计算函数
		    var rows = $('#dg').datagrid('getRows')//获取当前的数据行
		    var ptotal = 0//计算listprice的总和
		    ,utotal=0//统计unitcost的总和
		    ,ytotal=0//统计unitcost的总和
		    ,ntotal=0//统计unitcost的总和
		    ,btotal=0//统计unitcost的总和
		    ,all=0;//统计unitcost的总和
		    var  jsonData = {COMPANY : '<b>合计</b>'};
		    	
		    	
		    	for (var j = 0; j<com.rows.length; j++) {
		    		
		    		ptotal = 0 ;
		    		utotal =0 ;
		    		ytotal =0 ;
		    		ntotal =0 ;
		    		  for (var i = 0; i < rows.length; i++) {
		    			  
		    		  ptotal += parseFloat(rows[i][com.rows[j].ZHANGHXZMC]);
		    		  utotal += parseFloat(rows[i]['TOTAL']);
    				  ytotal += parseFloat(rows[i]['ALL_YES']);
    				  ntotal += parseFloat(rows[i]['ALL_NO']);
//	    			 
		    		  }
		    		
		    		  jsonData[com.rows[j].ZHANGHXZMC] = ptotal/2;
//		    		  fn(com.rows[j][ZHANGHXZMC],ptotal);
//		    		  total.push( data);
		    		  jsonData['TOTAL'] =utotal/2;
		    		  console.log("saaa"+ ptotal/2);
		    		  jsonData['ALL_YES'] =ytotal/2;
		    		  jsonData['ALL_NO'] =ntotal/2;
		    		 
		    	}
		    	

		       
		    
		    //新增一行显示统计信息
		    $('#dg').datagrid('appendRow',  jsonData);
		}

		 
		function insertRow(k,index) {//计算函数
		    var rows = $('#dg').datagrid('getRows')//获取当前的数据行
		    var ptotal = 0//计算listprice的总和
		    ,utotal=0//统计unitcost的总和
		    ,ytotal=0//统计可用余额的总和
		    ,ntotal=0//统计不可用余额的总和
		    ,btotal=0//统计unitcost的总和
		    ,all=0;//统计unitcost的总和
		    var  jsonData = {COMPANY : '<b>小计</b>'};
		 
		    	
		    	
		    	for (var j = 0; j<com.rows.length; j++) {
		    		
		    		ptotal = 0 ;
		    		utotal =0 ;
		    		ytotal =0 ;
		    		ntotal =0 ;
		    		  for (var i = k; i < rows.length; i++) {
		    			  
		    			  if(i==index){
		    				  
//		    				  i = index+1;
		    				  break;
		    				 
		    				
		    			  }
		    			  
		    			  ptotal += parseFloat(rows[i][com.rows[j].ZHANGHXZMC]);
	    				  utotal += parseFloat(rows[i]['TOTAL']);
	    				  ytotal += parseFloat(rows[i]['ALL_YES']);
	    				  ntotal += parseFloat(rows[i]['ALL_NO']);
//	    			 
		    		  }
		    		
		    		  jsonData[com.rows[j].ZHANGHXZMC] = ptotal;
//		    		  fn(com.rows[j][ZHANGHXZMC],ptotal);
//		    		  total.push( data);
		    		  jsonData['TOTAL'] =utotal;
		    		  jsonData['ALL_YES'] =ytotal;
		    		  jsonData['ALL_NO'] =ntotal;
		    		 
		    	}
		    	
//		    	k=index;
		    	

		       
		    
		    //新增一行显示统计信息
				 $('#dg').datagrid('insertRow',{
					   
					    index:index,   // 索引从0开始
					    row: jsonData
					});
		}
		
		
		
		
		
		function fn(key,val){
	           var  data = {};
	               var  key = key;
	               var  val = val;
	               data[key] = val;
	           
	           return data;
	       }
		
	$('#startDate').bind('click', function(){
		WdatePicker({
			el: 'startDate',
			readOnly: true,
//			onpicked: function(){alert(456);},
		});
	});

 
 
	$('#btnExport').bind('click', function(){
 		 
		 $("#dg").datagrid('toExcel',"板块余额统计表.xls");
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
	 
	 /**
      * EasyUI DataGrid根据字段动态合并单元格
      * 参数 tableID 要合并table的id
      * 参数 colList 要合并的列,用逗号分隔(例如："name,department,office");
      */
     function mergeCellsByField(tableID, colList) {
         var ColArray = colList.split(",");
         var tTable = $("#" + tableID);
         var TableRowCnts = tTable.datagrid("getRows").length;
         var tmpA;
         var tmpB;
         var PerTxt = "";
         var CurTxt = "";
         var buyprocode = "";
         var newbuyprocode = "";
         var alertStr = "";
         for (j = ColArray.length - 1; j >= 0; j--) {
             PerTxt = "";
             buyprocode = "";
             tmpA = 1;
             tmpB = 0;

             for (i = 0; i <= TableRowCnts; i++) {
                 if (i == TableRowCnts) {
                     CurTxt = "";
                     newbuyprocode = "";
                 }
                 else {
                     CurTxt = tTable.datagrid("getRows")[i][ColArray[j]];
                     newbuyprocode = tTable.datagrid("getRows")[i]["buyprojectCode"];
                 }
                 if (PerTxt == CurTxt && buyprocode == newbuyprocode) {
                     tmpA += 1;
                 }
                 else {
                     tmpB += tmpA;

                     tTable.datagrid("mergeCells", {
                         index: i - tmpA,
                         field: ColArray[j],
                         rowspan: tmpA,
                         colspan: null
                     });
                     tTable.datagrid("mergeCells", { //根据ColArray[j]进行合并
                         index: i - tmpA,
                         field: "Ideparture",
                         rowspan: tmpA,
                         colspan: null
                     });

                     tmpA = 1;
                 }
                 PerTxt = CurTxt;
                 buyprocode = newbuyprocode;
             }
         }

     }
//

	
	var generateHiddenInput = function(name, value) {
		return '<input type="hidden" name="' + name + '" value=\'' + value + '\' />';
	};
	
	var colorRgbToHex = function(rgb){
		if (rgb == 'rgba(0, 0, 0, 0)' || rgb == 'transparent') {
			return '';
		}
		var aColor = rgb.replace(/(?:\(|\)|rgb|RGB)*/g, "").split(",");
		var strHex = "#";
		for(var i=0; i < aColor.length; i++){
			var hex = Number(aColor[i]).toString(16);
			if(hex === "0"){
				hex += hex;		
			}
			strHex += hex;
		}
		if(strHex.length !== 7){
			strHex = rgb;		
		}
		return strHex;
	};
	
 
	


});