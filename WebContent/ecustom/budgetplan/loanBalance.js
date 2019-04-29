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
	
	$('#title').text( '余额明细表');
	
//	var startDate = $('#startDate').val();
	
	
	var myDate = new Date();
	var startDate = myDate.toLocaleDateString().split('/').join('-');//将1970/08/08转化成1970-08-08

	
	$('#btnSearch').bind('click', function(){
		
		 datas = [];
		
		 startDate = $('#startDate').val();
		
		getData(startDate);
		 
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
				        column1["title"]='分类';
				        column1["field"]="TAG";
				        column1["width"]=fixWidth(0.1);
				        columns.push(column1);
	                   
			           
			           var column2={};
			            column2["title"]='金融机构';
			            column2["field"]="KAIHH";
			            column2["width"]=fixWidth(0.15);
			            columns.push(column2);
			            
	
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
	
			 getBlockData();
		
	
	
		
	});
	
	 function getBlockData() {
			$.getJSON('/greedc/servlets/Loan-dataGridShou.json?name=uf_bankuai', {
				time: new Date().getTime(),
				key: 'id',
				sort: 'id asc',
				page: '1',
				rows: '10'
			}, function(blocks) {
			
				BLOCK = blocks;
				BLOCKNUM = blocks.rows.length;
				for (var i = 0; i<blocks.rows.length; i++) {
					
				
					   var column={};
					            column["title"]=blocks.rows[i].BANKMC;
					            column["field"]=blocks.rows[i].ID;
					            column["width"]=fixWidth(0.08);
					            columns.push(column);
					}
					       
				   var column88={};
					 column88["title"]='合計总额';
					 column88["field"]="TOTAL_BLOCK";
					 column88["width"]=fixWidth(0.08);
					 columns.push(column88);
				
				getData(startDate);
			});
	 }

	 
		function fixWidth(percent)  
		{  
		    return document.body.clientWidth * percent ;//根据自身情况更改

		}  
	
	 
	 function getData(startDate) {
			$.getJSON('/greedc/servlets/Loan-dataGridTotal.json?name=CV_JINRJG', {//金融机构
				time: new Date().getTime(),
				key: 'kaihuh',
				sort: 'kaihuh asc',
				page: '1',
				startDate: startDate,
				rows: '10'
			}, function(projects) {
			
				for (var i = 0; i<projects.rows.length; i++) {
					
					
				    var data={};   
		            data["KAIHH"]=projects.rows[i].KAIHH;
		            if(projects.rows[i].SHIFDKJAJHZYH==0){
		            	data["TAG"]="贷款及按揭合作银行";

		            }else{
		            	
		            	data["TAG"]="其他";
		            }
		            data["TOTAL"]=toMoney(projects.rows[i].TOTAL_YUANB);
		            data["TOTAL_BLOCK"]=toMoney(projects.rows[i].TOTAL_YUANB);
		            
		            getJianGData(data);
		            

					         
				
				}
				
			
				
				
				});
			
		
		}
	 
	 
	 function getJianGData(data) {
			$.getJSON('/greedc/servlets/Loan-dataGridJianG.json?name=CV_JIANG_DATA', {
				time: new Date().getTime(),
				key: 'kaihuh',
				sort: 'kaihuh asc',
				startDate: startDate,
				page: '1',
				rows: '10'
			}, function(projects) {
				
				
			
				for (var i = 0; i<projects.rows.length; i++) {
					


					 if(projects.rows[i].KAIHH == data.KAIHH){
						 
						 
						    if(projects.rows[i].SHIFJGH==0){
				            	 data["ALL_NO"]=toMoney(projects.rows[i].TOTAL_JIANG);
				            }
						    
						    if(projects.rows[i].SHIFJGH==1){
				            	
				            	 data["ALL_YES"]=toMoney(projects.rows[i].TOTAL_JIANG);
				            }
				          
						}else{}
					 
					
				}
				
				if(data["ALL_NO"]==undefined){
					
					data["ALL_NO"]=0.00;
				}
				
				if(data["ALL_YES"]==undefined){
					
					data["ALL_YES"]=0.00;
				}
				
				
				
				   getCompanyData(data);
				
				});
			
		
		}
	 
	 
	 function getCompanyData(data) {
			$.getJSON('/greedc/servlets/Loan-dataGridJinRjg.json?name=CV_JINRJG_DATA', {
				time: new Date().getTime(),
				key: 'kaihuh',
				sort: 'kaihuh asc',
				startDate: startDate,
				page: '1',
				rows: '10'
			}, function(projects) {
			
				  
					for (var i = 0; i<com.rows.length; i++) {
						
						
							    
							  
							 
								for (var j = 0; j<projects.rows.length; j++) {
									
									 if(projects.rows[j].KAIHH == data.KAIHH){
									   
									        if(com.rows[i].ZHANGHXZMC == projects.rows[j].ZHANGHXZMC ){
				                    	 
											 
											 data[com.rows[i].ZHANGHXZMC]=toMoney(projects.rows[j].YUANB);
										
				                    	 
				                           }
				                     
									
								}else{}
							 
								
						 }
								
								 if(data[com.rows[i].ZHANGHXZMC]==undefined){
									 
									 data[com.rows[i].ZHANGHXZMC]=0.00;
									 
								 }
	                    	 
//                    	    
                           
						
					}
					
					getBlockData(data);
				
				
			
	 });
	 
	 
	 function getBlockData(data) {
			$.getJSON('/greedc/servlets/Loan-dataGridBlock.json?name=CV_BLOCK_DATA', {
				time: new Date().getTime(),
				key: 'kaihuh',
				sort: 'kaihuh asc',
				startDate: startDate,
				page: '1',
				rows: '10'
			}, function(block) {
			
				  
					for (var i = 0; i<BLOCK.rows.length; i++) {
						
						
							    
							  
							 
								for (var j = 0; j<block.rows.length; j++) {
									
									 if(block.rows[j].KAIHH == data.KAIHH){
									   
									        if(BLOCK.rows[i].ID == block.rows[j].SUOSBK ){
				                    	 
											 
											 data[BLOCK.rows[i].ID]=toMoney(block.rows[j].YUANB);
										
				                    	 
				                           }
				                     
									
								}else{}
							 
								
						 }
								
								 if(data[BLOCK.rows[i].ID]==undefined){
									 
									 data[BLOCK.rows[i].ID]=0.00;
									 
								 }
	                    
					}
//                 	    
								 datas.push(data);
									
									
									
									
									$('#dg').datagrid({loadFilter:pagerFilter}).datagrid({
										collapsible: true,
										singleSelect: true,
										width: $(document).width() - 10,
								        pagination: true,
								        remoteFilter: true,
								        showFooter: true,
								        fitcolumns:false,
								        rownumbers:false,
										mode: 'remote',
										columns: [[{"title":"","colspan":2,"id":"test01"},
										           {"title":"可用资金","colspan":FEIJGZJNUM+1,"id":"test02"},
										  		 {"title":"不可用资金","colspan":JGZJNUM+1,"id":"test03"}, 
										  		 {"title":"","colspan":1,"id":"test04"}, 
										  		 {"title":"板块","colspan":BLOCKNUM+1,"id":"test05"}],columns],
										onLoadSuccess: function(data) {
										        //所有列进行合并操作
										        //$(this).datagrid("autoMergeCells");
										        //指定列进行合并操作(数组中写要合并的列名(field属性值))
//											mergeCellsByField("dg", "TAG");
											
//											 $('#test01').append("<div colspan=3>非监管资金</div>");
											
//											 $('#test02').attr("colspan","5");
//											 $('#test03').attr("colspan","4");
//											 $('#test04').attr("colspan","4");
										
											 
											  var rows = $('#dg').datagrid('getRows');
											  
											   for (var i = 0; i < rows.length; i++) {
												   
//												   data['TOTAL']=(parseInt(rows[i]['ALL_YES'])+ parseInt(rows[i]['ALL_NO']));
//												   console.log((rows[i]['ALL_NO']).replace(/[^1-9]/ig,""));
////												   console.log(parseInt((rows[i]['ALL_YES']).replace(/[^0-9]/ig,""))  +  parseInt((rows[i]['ALL_NO']).replace(/[^0-9]/ig,"")) );
//												   $('#TOTAL').html((parseInt(rows[i]['ALL_YES'])+ parseInt(rows[i]['ALL_NO'])));
											   }

											   
											 
										    }
									
									
									});
									$('#dg').datagrid('loadData',datas);
									
									
									
									
									});
	 }
			
		$('#startDate').bind('click', function(){
			WdatePicker({
				el: 'startDate',
				readOnly: true,
//				onpicked: function(){alert(456);},
			});
		});

	 
	 
		$('#btnExport').bind('click', function(){
	 		 
			 $("#dg").datagrid('toExcel',"贷款余额统计表.xls");
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