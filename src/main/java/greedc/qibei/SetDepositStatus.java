package greedc.qibei;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;

import com.google.gson.JsonObject;

import ecustom.commons.HttpRequestUtil;

/**
 * 保证金审批
 * @author Administrator
 *
 */

public class SetDepositStatus extends BaseBean implements Action {
	
	private static BaseBean baseBean=new BaseBean();

   private static Log log = LogFactory.getLog(SetDepositStatus.class.getName());
   private  RecordSet rs;

   private String remark;

   private String danjbh;

private String status;

   public String execute(RequestInfo requestinfo) {
	   
	   
	   
	   
	baseBean.writeLog("tagtag run action1保证金审批 :"+getClass()+",requestid:"+requestinfo.getRequestid()+",wfid:"+requestinfo.getWorkflowid()+",src:"+requestinfo.getRequestManager().getSrc());
		 rs = new RecordSet();
      requestinfo.getRequestManager().getSrc();
      requestinfo.getRequestid();
      requestinfo.getWorkflowid();
      requestinfo.getRequestManager().getNextNodetype();
      String pich = "";  //批次号
      String src=requestinfo.getRequestManager().getSrc();
      
  	Property[] properties = requestinfo.getMainTableInfo().getProperty();// 获取表单主字段信息
	for (int i = 0; i < properties.length; i++) {// 主表数据
		String name = properties[i].getName();//字段名
		String value = Util.null2String(properties[i].getValue());//值
		baseBean.writeLog("mian:"+ "name:"+name+",value:"+value);
		if(name.equalsIgnoreCase("pich")){
			pich=value;
		} 
		
		if(name.equalsIgnoreCase("danjbh")){
			danjbh=value;
		} 
		
	}
	
	requestinfo.getDetailTableInfo().getDetailTable();
	
	Row[] rows = requestinfo.getDetailTableInfo().getDetailTable(0).getRow();
	for (Row row : rows) {
		Cell[] fields = row.getCell();
		for (Cell field : fields) {
			String fieldName = field.getName();
			String fieldValue = field.getValue();
			baseBean.writeLog("mian:"+ "name:"+fieldName+",value:"+fieldValue);
		}
	}
	
//	String sqlString = "select * from ( select * from workflow_requestlog where requestid="+rid+""+" order by  operatedate desc ,operatetime desc) where rownum=1";
	    baseBean.writeLog("tagtag run actddddd :"+"参数:"+requestinfo.getRequestManager().getRemark());
		baseBean.writeLog("tagtag1 run sql :"+"输出1:"+remark);
		 String txtcontent = requestinfo.getRequestManager().getRemark().replaceAll("</?[^>]+>", ""); //剔出<html>的标签           txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符  
		    try {
				remark = URLEncoder.encode(txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "").replaceAll(" ", ""),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if("submit".equalsIgnoreCase(src)){//提交
			
			status = "1";
			
		}else{
			
			status = "2";
		}
		
		
	 // 转换为键值对  
        String params ="appkey="+"qibei"+"&applyno="+danjbh+"&status="+status+"&reason="+remark;
     
		baseBean.writeLog("tagtag run action3 :"+"参数:"+params);
		
		JsonObject isok =HttpRequestUtil.postJson("http://haikongbl.haikongjinrong.com:8888/api/fin/loan/rzdepositdone", params, true);
//	
		baseBean.writeLog("tagtag run action4 :"+"返回信息:"+isok);
		return Action.SUCCESS;
		
      
     
   }
   
   public static void main(String[] args) {
		
//	   appkey=qibei&applyno=BZJDK2019-01-231328&status=0&reason=cessdfsdf
	   
	      String pcid = "1536918439";  //批
	      
	      String applyno = "BZJDK2019-01-231328";  //批
	      
	      String params ="appkey="+"qibei"+"&applyno="+applyno+"&status=1";
	      
			baseBean.writeLog("tagtag run action3 :"+"参数:"+params);
			JsonObject isok =HttpRequestUtil.postJson("http://59.38.35.85:8888/api/fin/loan/rzdepositdone", params, true);
			baseBean.writeLog("tagtag run action4 :"+"返回信息:"+isok);
   }
}
