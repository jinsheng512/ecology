/**
 * 板块余额统计（主管）
 */
$(document).ready(function(){
	var blocks = {};          //板块
	var tdRowspan = {};
	var columns=new Array();  //动态列
	var datas=new Array();    //动态数据
	var com;                  //账户性质名称
	var BLOCK;
	var BLOCKNUM;
	var FEIJGZJNUM=0;         //可用余额模块数量 
	var JGZJNUM=0;            //不可用余额模块数量 
	var blockid;              //板块ID
	var jinrjg;               //板块，公司数据和合计
	var jiang;                //可用余额和不可用余额小计
	var items;                //动态列数据
	var totalData;                //动态列数据
	var userid;                //当前用户
	
	//板块小计思路，首先获取到板块的数据列表，如果第一条数据板块和第二条数据所属板块相同，不插入小计，直到遇到下一个不相同板块后。才插入小计行，
	
	
	$('#title').text( '板块余额统计');
	
	
	var myDate = new Date();
	var startDate = myDate.toLocaleDateString().split('/').join('-');//将1970/08/08转化成1970-08-08
	

	$.getJSON('/greedc/servlets/Loan-getSearchDataByBanlance.json?', {
		time: new Date().getTime(),
		
	}, function(projects) {
		
		for (var i = 0; i < projects.blockList.length; i++) {
			
			$("#block").append('<option value='+projects.blockList[i].id + '>'+projects.blockList[i].bankmc +'</option>');
		}
		
		if(projects.user.uid==undefined){
			
		}else{
			userid= projects.user.uid;
			initData();
		}
		

		
//         initData();
	});
	
	
	$("#block").on("change",function(){
		
		blockid = $("#block").val();
		
	});
	
	$('#btnSearch').bind('click', function(){
		
		$("#btnSearch").attr("disabled",true);
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
				        column1["title"]='板块';
				        column1["field"]="BLOCK";
				        column1["width"]=fixWidth(0.08);
				        columns.push(column1);
	                   
				        
				        var column2={};
			            column2["title"]='公司';
			            column2["field"]="COMPANY";
			            column2["width"]=fixWidth(0.18);
			            columns.push(column2);
			           
			            
	
		for (var i = 0; i<COM.rows.length; i++) {
			
			if(COM.rows[i].SHIFJGH=='1'){
				FEIJGZJNUM++;
			   var column={};
			            column["title"]=COM.rows[i].ZHANGHXZMC;
			            column["field"]=COM.rows[i].ZHANGHXZMC;
			            column["align"]="right";
			            column["width"]=fixWidth(0.08);
			            columns.push(column);
			}
			        
		
			
			
		}
		
		
        
        var column66={};
         column66["title"]='小计';
         column66["field"]="ALL_NO";
         column66["width"]=fixWidth(0.08);
         column66["align"]="right";
         columns.push(column66);
		
		
		for (var i = 0; i<COM.rows.length; i++) {
			
			if(COM.rows[i].SHIFJGH=='0'){
				JGZJNUM++;
			   var column={};
			            column["title"]=COM.rows[i].ZHANGHXZMC;
			            column["field"]=COM.rows[i].ZHANGHXZMC;
			            column["align"]="right";
			            column["width"]=fixWidth(0.08);
			            columns.push(column);
			}
			        
		
			
			
		}
		
        var column77={};
        column77["title"]='小计';
        column77["field"]="ALL_YES";
        column77["width"]=fixWidth(0.08);
        column77["align"]="right";
        columns.push(column77);
	
		
		   var column5={};
			 column5["title"]='合計';
			 column5["field"]="TOTAL";
			 column5["width"]=fixWidth(0.08);
			 column5["align"]="right";
			 columns.push(column5);
	
				$('#dg').datagrid({loadFilter:pagerFilter}).datagrid({
					collapsible: true,
					singleSelect: true,
					width: $(document).width() - 10,
			        pagination: false,
			        pageSize: 600,
	                pageList: [200, 250, 600],
			        remoteFilter: true,
			        onLoadSuccess: compute,//加载完毕后执行计算
			        showFooter: true,
			        fitcolumns:false,
			        rownumbers:false,
					mode: 'remote',
					columns: [[{"title":"","colspan":2,"id":"test01"},
					           {"title":"可用余额","colspan":FEIJGZJNUM+1,"id":"test02"},
					  		 {"title":" 不可用余额","colspan":JGZJNUM+1,"id":"test03"}, 
					  		 {"title":"","colspan":1,"id":"test04"} ],columns],
					onLoadSuccess: function(datas) {
						
						  var rows = $('#dg').datagrid('getRows');
						    var ff = "";
						    var j= new Array();
						    var k=0;
						    var g=0;
						    var p=0;
						   for (var i = 0; i < jinrjg.rows.length-1; i++) {
						
					           ff =jinrjg.rows[i+1]['BANKMC']
						 
							if(ff != jinrjg.rows[i]['BANKMC'])  {
								  j.push(i);
								  
								  insertRow(k,(i)+(j.length));
//					
								  k=(i)+1+ (j.length); ; //最后一模块起始位置
								  p=(jinrjg.rows.length-i)+k; //最后一列位置
							}
						    
//					    	 p=(k+1)+(j.length); //最后一列位置
					    
					   }
						   
				  if(blockid=="" || blockid==undefined){
					   
					  if(jinrjg.rows.length<=1){
						  
					  }else{
						  if(k==0){
							  insertRow2();  
						  }else{
							  
							  insertRow(k,p);  //最后模块小计
						  }
						  
					  }
					  appendTotalRow();
						  $("#dg").datagrid("resize",{
							    height: 16*36, 
							  
							    
						    });   
					    	 
				  }else{
					  
					  insertRow1();  //最后模块小计,选择了板块
				  }

					        //指定列进行合并操作(数组中写要合并的列名(field属性值))
						mergeCellsByField("dg", "BLOCK");

						   
						$("#btnSearch").attr("disabled",false);
						
						
					
						
						
					    }
				
				
				});
	
		
	});
	

	 
		function fixWidth(percent)  
		{  
		    return document.body.clientWidth * percent ;//根据自身情况更改

		}  
		
//	     initData();
	 	function  initData(){
	 		
	 		$.getJSON('/greedc/servlets/Loan-dataGridTotal01.json?name=CV_JINRJG_01', {//板块
	 			time: new Date().getTime(),
	 			key: 'id',
	 			sort: 'id asc',
	 			blockid: blockid,
	 			userid: userid,
	 			page: '1',
	 			startDate: startDate,
	 			rows: '1000'
	 		}, function(projects) {
	 			
	 			jinrjg = projects;
	 			if(projects.rows.length==0){
	 				
	 				$("#btnSearch").attr("disabled",false);
	 			}
	 			
	 			
		 		$.getJSON('/greedc/servlets/Loan-dataGridJianG01.json?name=CV_JIANG_DATA_01', { //  板块是否可用小计
		 			time: new Date().getTime(),
		 			key: 'id',
		 			sort: 'id asc',
		 			startDate: startDate,
		 			page: '1',
		 			rows: '1000'
		 		}, function(projects) {
		 			
		 			jiang = projects;
		 			
		 			
			 		$.getJSON('/greedc/servlets/Loan-dataGridJinRjg01.json?name=CV_JINRJG_DATA', {  // 
			 			time: new Date().getTime(),
			 			key: 'id',
			 			sort: 'id asc',
			 			startDate: startDate,
			 			page: '1',
			 			rows: '1000'
			 		}, function(projects) {
			 			
			 			items = projects;
			 			
			 			
			 			$.getJSON('/greedc/servlets/Loan-dataGridJianG_block03.json?name=CV_JIANG_DATA_01', {
				 			time: new Date().getTime(),
				 			key: 'id',
				 			sort: 'id asc',
				 			startDate: startDate,
				 			page: '1',
				 			rows: '1000'
				 		}, function(projects) {
				 			
				 			totalData = projects;
				 			
				 			getData(startDate,jinrjg);
				 		}
			 		);
			 			
			 			
			 		});
		 		});
	 			
	 		
	 			
	 		});
	 		

	 	}
	
	
	 function getData(startDate,jinrjg) {

			
				if(jinrjg.rows.length!=0){
					
				
				for (var i = 0; i<jinrjg.rows.length; i++) {
					
					
				    var data= new Array();   
		            
		         	data["BLOCK"]= jinrjg.rows[i].BANKMC;
		         	data["COMPANY"]= jinrjg.rows[i].SUBCOMPANYNAME;

		            
		            getJianGData(data,jiang);

					         
				
			}
				
					refreshData();
				}
				
			 
		}
	 
	 
	 function getJianGData(data,jiang) {  //小计

			
			
				for (var i = 0; i<jiang.rows.length; i++) {
					


					 if(jiang.rows[i].BANKMC == data.BLOCK && jiang.rows[i].SUBCOMPANYNAME == data.COMPANY){
						 
						 
						    if(jiang.rows[i].SHIFJGH==1){
				            	 data["ALL_NO"]=toMoney(jiang.rows[i].TOTAL_JIANG);
				            }
						    
						    if(jiang.rows[i].SHIFJGH==0){
				            	
				            	 data["ALL_YES"]=toMoney(jiang.rows[i].TOTAL_JIANG);
				            }
				          
						}else{}
					 
					
				}
				
				if(data["ALL_NO"]==undefined){
					
					data["ALL_NO"]=0.00;
				}
				
				if(data["ALL_YES"]==undefined){
					
					data["ALL_YES"]=0.00;
				}
				
				
				
				getTotalData(data,totalData);
				 
				
//				});
			
		
		}
	 function getTotalData(data,totalData) {  //小计

			
			
			for (var i = 0; i<totalData.rows.length; i++) {
				


				 if(totalData.rows[i].BANKMC == data.BLOCK && totalData.rows[i].SUBCOMPANYNAME == data.COMPANY){
					 
					 
					   
			            	 data["TOTAL"]=toMoney(totalData.rows[i].TOTAL_JIANG);
			           
				 }
				
			}
			
			if(data["TOTAL"]==undefined){
				
				data["TOTAL"]=0.00;
			}
			
			
			
			   getCompanyData(data,items);
			 
			
//			});
		
	
	}
	 
	 
	 
	 
	 function getCompanyData(data,items) {  //
			
				  
					for (var i = 0; i<com.rows.length; i++) {
						
						
							    
							  
							 
								for (var j = 0; j<items.rows.length; j++) {
									
									 if(items.rows[j].BANKMC == data.BLOCK && items.rows[j].SUBCOMPANYNAME == data.COMPANY){
									   
									        if(com.rows[i].ZHANGHXZMC == items.rows[j].ZHANGHXZMC ){
				                    	 
											 
											 data[com.rows[i].ZHANGHXZMC]=toMoney(items.rows[j].BENWB);
										
				                    	 
				                           }
				                     
									
								}else{}
							 
								
						 }
								
								 if(data[com.rows[i].ZHANGHXZMC]==undefined){
									 
									 data[com.rows[i].ZHANGHXZMC]=0.00;
									 
								 }
	                    	 
//                    	    
                           
						
					}
					
				
					 datas.push(data);
						
		
					}
	 
		function refreshData() {
			
			$('#dg').datagrid('loadData',datas);
			
		
		}
		
		function compute() {//计算函数
			
			
		}
		
		function insertRow(k,index) {//计算函数
		    var rows = $('#dg').datagrid('getRows')//获取当前的数据行
		    var ptotal = 0//计算listprice的总和
		    ,utotal=0//统计unitcost的总和
		    ,ytotal=0//统计可用余额的总和
		    ,ntotal=0//统计不可用余额的总和
		    ,all=0;//统计unitcost的总和
		    var  jsonData = {"align": "right",COMPANY : '<b>小计</b>'};
		 
		    	
		    	
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
		    			
		    			  ptotal += parseFloat(clearFormat(rows[i][com.rows[j].ZHANGHXZMC]));
	    				  utotal += parseFloat(clearFormat(rows[i]['TOTAL']));
	    				  ytotal += parseFloat(clearFormat(rows[i]['ALL_YES']));
	    				  ntotal += parseFloat(clearFormat(rows[i]['ALL_NO']));
//	    			 
		    		  }
		    		
		    		  
		    		  jsonData[com.rows[j].ZHANGHXZMC] = toMoney(ptotal);
//		    		  fn(com.rows[j][ZHANGHXZMC],ptotal);
//		    		  total.push( data);
		    		  jsonData['TOTAL'] =toMoney(utotal);
		    		  jsonData['ALL_YES'] =toMoney(ytotal);
		    		  jsonData['ALL_NO'] =toMoney(ntotal);
		    		 
		    	}
		    	
//		    	k=index;
		    	

		       
		    
		    //新增一行显示统计信息
				 $('#dg').datagrid('insertRow',{
					   
					    index:index,   // 索引从0开始
					    row: jsonData
					});
		}
		
		function insertRow1() {//计算函数
		    var rows = $('#dg').datagrid('getRows')//获取当前的数据行
		    var ptotal = 0//计算listprice的总和
		    ,utotal=0//统计unitcost的总和
		    ,ytotal=0//统计unitcost的总和
		    ,ntotal=0//统计unitcost的总和
		    ,all=0;//统计unitcost的总和
		    var  jsonData = {"align": "right",COMPANY : '<b>合计</b>'};
		    	
		    	
		    	for (var j = 0; j<com.rows.length; j++) {
		    		
		    		ptotal = 0 ;
		    		utotal =0 ;
		    		ytotal =0 ;
		    		ntotal =0 ;
		    		  for (var i = 0; i < rows.length; i++) {
		    			  
		    		  ptotal += parseFloat(clearFormat(rows[i][com.rows[j].ZHANGHXZMC]));
		    		  utotal += parseFloat(clearFormat(rows[i]['TOTAL']));
    				  ytotal += parseFloat(clearFormat(rows[i]['ALL_YES']));
    				  ntotal += parseFloat(clearFormat(rows[i]['ALL_NO']));
//	    			 
		    		  }
		    		
		    		  jsonData[com.rows[j].ZHANGHXZMC] =toMoney(ptotal);
//		    		  fn(com.rows[j][ZHANGHXZMC],ptotal);
//		    		  total.push( data);
		    		  jsonData['TOTAL'] =toMoney(utotal);
		    		  jsonData['ALL_YES'] =toMoney(ytotal);
		    		  jsonData['ALL_NO'] =toMoney(ntotal);
		    		 
		    	}
		    	

		       
		    
		    //新增一行显示统计信息
		    $('#dg').datagrid('appendRow',  jsonData);
		}
		
		function insertRow2() {//计算函数
		    var rows = $('#dg').datagrid('getRows')//获取当前的数据行
		    var ptotal = 0//计算listprice的总和
		    ,utotal=0//统计unitcost的总和
		    ,ytotal=0//统计unitcost的总和
		    ,ntotal=0//统计unitcost的总和
		    ,all=0;//统计unitcost的总和
		    var  jsonData = {"align": "right",COMPANY : '<b>小计</b>'};
		    	
		    	
		    	for (var j = 0; j<com.rows.length; j++) {
		    		
		    		ptotal = 0 ;
		    		utotal =0 ;
		    		ytotal =0 ;
		    		ntotal =0 ;
		    		  for (var i = 0; i < rows.length; i++) {
		    			  
		    		  ptotal += parseFloat(clearFormat(rows[i][com.rows[j].ZHANGHXZMC]));
		    		  utotal += parseFloat(clearFormat(rows[i]['TOTAL']));
    				  ytotal += parseFloat(clearFormat(rows[i]['ALL_YES']));
    				  ntotal += parseFloat(clearFormat(rows[i]['ALL_NO']));
//	    			 
		    		  }
		    		
		    		  jsonData[com.rows[j].ZHANGHXZMC] =toMoney(ptotal);
//		    		  fn(com.rows[j][ZHANGHXZMC],ptotal);
//		    		  total.push( data);
		    		  jsonData['TOTAL'] =toMoney(utotal);
		    		  jsonData['ALL_YES'] =toMoney(ytotal);
		    		  jsonData['ALL_NO'] =toMoney(ntotal);
		    		 
		    	}
		    	

		       
		    
		    //新增一行显示统计信息
		    $('#dg').datagrid('appendRow',  jsonData);
		}
		
		
		function appendTotalRow() {//计算函数
		    var rows = $('#dg').datagrid('getRows')//获取当前的数据行
		    var ptotal = 0//计算listprice的总和
		    ,utotal=0//统计unitcost的总和
		    ,ytotal=0//统计unitcost的总和
		    ,ntotal=0//统计unitcost的总和
 		    ,all=0;//统计unitcost的总和
		    var  jsonData = {"align": "right",COMPANY : '<b>合计</b>'};
		    	
		    	
		    	for (var j = 0; j<com.rows.length; j++) {
		    		
		    		ptotal = 0 ;
		    		utotal =0 ;
		    		ytotal =0 ;
		    		ntotal =0 ;
		    		  for (var i = 0; i < rows.length; i++) {
		    			  
		    		  ptotal += parseFloat(clearFormat(rows[i][com.rows[j].ZHANGHXZMC]));
		    		  utotal += parseFloat(clearFormat(rows[i]['TOTAL']));
    				  ytotal += parseFloat(clearFormat(rows[i]['ALL_YES']));
    				  ntotal += parseFloat(clearFormat(rows[i]['ALL_NO']));
//	    			 
		    		  }
		    		
		    		  jsonData[com.rows[j].ZHANGHXZMC] = toMoney(ptotal/2);
//		    		  fn(com.rows[j][ZHANGHXZMC],ptotal);
//		    		  total.push( data);
		    		  jsonData['TOTAL'] =toMoney(utotal/2);
		    		  jsonData['ALL_YES'] =toMoney(ytotal/2);
		    		  jsonData['ALL_NO'] =toMoney(ntotal/2);
		    		 
		    	}
		    	
		       
		    
		    //新增一行显示统计信息
		    $('#dg').datagrid('appendRow',  jsonData);
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
		
		var clearFormat = function(str) {
			
		return	str.toString().replace(/,/g,"");
			
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