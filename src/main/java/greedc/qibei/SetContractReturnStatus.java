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
 * 合同打回
 * @author Administrator
 *
 */

public class SetContractReturnStatus extends BaseBean implements Action {
	
	private static BaseBean baseBean=new BaseBean();

   private static Log log = LogFactory.getLog(SetContractReturnStatus.class.getName());
   private  RecordSet rs;

   private String remark;

   public String execute(RequestInfo requestinfo) {
	   
	   
	   
	   
	baseBean.writeLog("tagtag run action1合同打回 :"+getClass()+",requestid:"+requestinfo.getRequestid()+",wfid:"+requestinfo.getWorkflowid()+",src:"+requestinfo.getRequestManager().getSrc());
		 rs = new RecordSet();
      requestinfo.getRequestManager().getSrc();
      requestinfo.getRequestid();
      requestinfo.getWorkflowid();
      requestinfo.getRequestManager().getNextNodetype();
      String pich = "";  //批次号
      
  	Property[] properties = requestinfo.getMainTableInfo().getProperty();// 获取表单主字段信息
	for (int i = 0; i < properties.length; i++) {// 主表数据
		String name = properties[i].getName();//字段名
		String value = Util.null2String(properties[i].getValue());//值
		baseBean.writeLog("mian:"+ "name:"+name+",value:"+value);
		if(name.equalsIgnoreCase("pich")){
			pich=value;
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
	
	    baseBean.writeLog("tagtag run actddddd :"+"参数:"+requestinfo.getRequestManager().getRemark());
		baseBean.writeLog("tagtag1 run sql :"+"输出1:"+remark);
//		try {
//			remark = URLEncoder.encode(requestinfo.getRequestManager().getRemark().substring(requestinfo.getRequestManager().getRemark().lastIndexOf(";\">")+3, requestinfo.getRequestManager().getRemark().indexOf("</span>")),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		 String txtcontent = requestinfo.getRequestManager().getRemark().replaceAll("</?[^>]+>", ""); //剔出<html>的标签           txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符  
	    try {
			remark = URLEncoder.encode(txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "").replaceAll(" ", ""),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 // 转换为键值对  
        String params = "pcid=" + pich+"&appkey="+"qibei"+"&reason="+remark ;
     
		baseBean.writeLog("tagtag run action3 :"+"参数:"+params);
		
		JsonObject isok =HttpRequestUtil.postJson("http://haikongbl.haikongjinrong.com:8888/api/fin/loan/pcrefuse", params, true);
//	
		baseBean.writeLog("tagtag run action4 :"+"返回信息:"+isok);
		return Action.SUCCESS;
		
      
     
   }
   
   public static void main(String[] args) {
		
	      String pcid = "1536918439";  //批
	      String txtcontent="<a> 不同  意   <br/>";
	      
	      txtcontent = txtcontent.replaceAll("</?[^>]+>", ""); //剔出<html>的标签           txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符  

		  String remark = (txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "").replace(" ", ""));
//		  try {
//			remark = URLEncoder.encode(remark.replace(" ", ""),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
	      String params = "pcid=" + pcid+"&appkey="+"qibei"+"&reason="+remark ;
	      
			baseBean.writeLog("tagtag run action3 :"+"参数:"+params);
			JsonObject isok =HttpRequestUtil.postJson("http://haikongbl.haikongjinrong.com:8888/api/fin/loan/pcrefuse", params, true);
			baseBean.writeLog("tagtag run action4 :"+"返回信息:"+isok);
   }
}
