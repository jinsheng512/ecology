// <script language="javascript" src="/wui/theme/ecology8/jquery/js/zDialog_wev8.js"></script>
// <script language="javascript" src="/ecustom/js/workflow/form/Flow144_4182.js"></script>
var number = undefined;
var pfobj = undefined;
var rowLength = undefined;
var jxobj= undefined;
var submitFun = undefined;

jQuery(document).ready(function() {
	submitFun = window.doSubmit;

	//----------------部门绩效加载------------------------//
	jQuery.ajax({
		type: "post",
		url: "/greedc/servlets/JiXiao-getJXList.json",
		dataType : 'json',
		success: function(_resp){
			if(_resp.status){
				console.info(_resp.message);
				var obj = JSON.parse(_resp.message);//由JSON字符串转换为JSON对象
				number=obj.bmsize;
				rowLength=obj.size;

				jxobj=obj;

				//------------------------评分规则加载---------------------------//
				jQuery.ajax({
					type: "post",
					url: "/greedc/servlets/JiXiao-getYL.json",
					data : {'number':number},
					dataType : 'json',
					success: function(_resp){
						if(_resp.status){
							console.info(_resp.message);
							pfobj = JSON.parse(_resp.message);

						}else{
							alert(_resp.message);
						}
					},
					error: function(_resp){
						alert("评分数据加载失败!");
					}
				});


				for(var i=0;i<obj.size;i++){
					addRow0(0);

					var bkhr="#field8756_"+i;//姓名//需修改
					var bkhrSpan="#field8756_"+i+"span";//姓名//需修改
					var spanValue="javaScript:openhrm("+obj.rows[i].bkhr+");";//姓名ID//需修改
					var kpi_z="#field8828_"+i;//需修改
					var xw_z="#field8829_"+i;//需修改
					var zf_z="#field8830_"+i;//需修改
					var kpi_s="#field8831_"+i;//需修改
					var xw_s="#field8832_"+i;//需修改
					var zf_s="#field8833_"+i;//需修改
					var yl="#field8834_"+i;//需修改
					var link="#field8835_"+i+"span"//需修改
					var ht="<a href='#' onclick='desc("+obj.rows[i].bkhr+","+i+")'>评分明细</a>"


					$(bkhrSpan).find("a").eq(1).html(obj.rows[i].name);
					$(bkhrSpan).find("a").eq(1).attr("href",spanValue);

					$(bkhr).attr("value",obj.rows[i].bkhr);
					$(bkhr).attr("readonly","readonly"); 	 	

					$(kpi_z).attr("value",obj.rows[i].kpi_z);
					$(kpi_z).attr("readonly","readonly"); 

					$(xw_z).attr("value",obj.rows[i].xw_z);
					$(xw_z).attr("readonly","readonly"); 

					$(zf_z).attr("value",obj.rows[i].zf_z);
					$(zf_z).attr("readonly","readonly"); 

					$(kpi_s).attr("value",obj.rows[i].kpi_s);

					$(xw_s).attr("value",obj.rows[i].xw_s);

					$(zf_s).attr("value",obj.rows[i].zf_s);

					$(yl).attr("value",obj.rows[i].level);

					$(link).html(ht);

				}



			}else{
				alert(_resp.message);
			}
		},
		error: function(_resp){
			alert("数据加载失败!");
		}
	});

	role();//规则校验

});



//-----提交校验开始----------
/**
doSave()      //保存
doSubmit() //提交
doReject()//退回
doDelete()//删除
 */
//规则校验 //需修改
function role(){

	var msg="";
	window.doSubmit = function(obj){
		//在此处写上自己逻辑
		var isOK=0;
		var a=0;
		var b=0;

		var numberP = new Array([pfobj.size]);

		console.info(pfobj);

		//获取各个等级数量
		for(var i=0;i<rowLength;i++){//明细循环	
			var yl="#field8834_"+i;
			var ylLevel=jQuery(yl).val();

			// for(var j=0;j<pfobj.size;j++){
			// var levels=pfobj.rows[j].youlpd;
			// if(ylLevel==levels){
			// 	numberP[j]++;
			// 	console.info(numberP[j]+"<<<<<2222<<<<<"+j);
			// 	break;
			// }

			// }


			if(ylLevel=='A+'){
				a++;	
			}else if(ylLevel=='A'){
				b++;
			}

		}



		for(var j=0;j<pfobj.size;j++){
			var levels=pfobj.rows[j].youlpd;
			if(levels=='A+'){
				var rateS=Math.round(number*pfobj.rows[j].rate/100);
				if(a>rateS){
					isOK=-1;
					msg="A+人数超标";
					console.info(rateS+"<<<a<");
				}

			}else if(levels=='A'){
				var rateS=Math.round(number*pfobj.rows[j].rate/100);
				if(b>rateS){
					isOK=-1;
					msg="A人数超标";
					console.info(rateS+"<<<b<");
				}



			}

		}
		console.info(a+"<<<a<"+b+"<<<b<<");



		if(isOK==-1){
			alert(msg);
			return false;
		}




		// var content="XXXXXXXXXXXXXX....";
		// showPrompt(content);
		// return false;

		upstatus(obj);




	}


}


//-----提交校验结束----------

var dlg=undefined;
var singleID=undefined;
var singleI=undefined;
//打开个人绩效
function desc(id,i){
	singleID=id;
	singleI=i;
	var url="/ecustom/workflow/JiXiao.jsp?resource=bm&id="+id;
	dlg=new window.top.Dialog();//定义Dialog对象
	dlg.currentWindow = window;
	dlg.Model=true;
	dlg.Width=710;//定义长度
	dlg.Height=789;
	dlg.URL=url;
	dlg.Title= '个人绩效（保存之后关闭）';
	dlg.CancelEvent=refreshParent;
	dlg.show();




}

//更新状态
function upstatus(obj){
	var result=0;
	var ids=jxobj.rows[0].id;

	for(var j=1;j<jxobj.size;j++){
		ids=ids+","+jxobj.rows[j].id;
	}
	console.info(ids);

	jQuery.ajax({
		type: "post",
		url: "/greedc/servlets/JiXiao-upStatus.json",
		dataType : 'json',
		data : {'ids':ids,'type':2},
		success: function(_resp){
			if(_resp.status){
				console.info(_resp.message);

				submitFun(obj);

				return result;

			}else{
				alert(_resp.message);
				result=-1;
				return result;
			}
		},
		error: function(_resp){
			alert("数据访问失败!");
			result=-1;
			return result;
		}
	});


}



//刷新页面
function refreshParent() {
//	parent.location.reload(); //父页面刷新
	var i=singleI;
	//----------------个人评分------------------//
	jQuery.ajax({
		type: "post",
		url: "/greedc/servlets/JiXiao-getPList.json",
		dataType : 'json',
		data : {'resource':'bm','id':singleID},
		success: function(_resp){
			if(_resp.status){
				console.info(_resp.message);
				var obj = JSON.parse(_resp.message); //由JSON字符串转换为JSON对象

				var kpi_sValue=obj.xwnlzhj;
				var xw_sValue=obj.xwnlshj;
				var zf_sValue=obj.zf_s;
				var ylValue=obj.level;
				var kpi_s="#field8831_"+i;//需修改
				var xw_s="#field8832_"+i;//需修改
				var zf_s="#field8833_"+i;//需修改
				var yl="#field8834_"+i;//需修改

				$(kpi_s).attr("value",kpi_sValue);

				$(xw_s).attr("value",xw_sValue);

				$(zf_s).attr("value",zf_sValue);
				$(yl).attr("value",ylValue);


			}else{
				alert(_resp.message);
			}
		},
		error: function(_resp){
			alert("数据访问失败!");
		}
	});


	dlg.close();  
	dlg=undefined;
	singleID=undefined;
	singleI=undefined;
}