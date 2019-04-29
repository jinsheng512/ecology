package greedc.qibei;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;

import com.google.gson.JsonObject;

import ecustom.commons.HttpRequestUtil;

/**
 * 合同会签
 * @author Administrator
 *
 */
public class SetPcsignStatus  implements Action {
	
	private static BaseBean baseBean=new BaseBean();

   private static Log log = LogFactory.getLog(SetPcsignStatus.class.getName());
//   private static RecordSet rs = new RecordSet();

   private String jingbr;


   public String execute(RequestInfo requestinfo) {
	   
		baseBean.writeLog("tagtag run action合同会签 :"+getClass()+",requestid:"+requestinfo.getRequestid()+",wfid:"+requestinfo.getWorkflowid()+",src:"+requestinfo.getRequestManager().getSrc());
	   
      String src = requestinfo.getRequestManager().getSrc();
      String rid = requestinfo.getRequestid();
      String wfid = requestinfo.getWorkflowid();
      String wfstatus = requestinfo.getRequestManager().getNextNodetype();
      String status = "";  //审批状态
      String pich = "";  //批次号
      
  	Property[] properties = requestinfo.getMainTableInfo().getProperty();// 获取表单主字段信息
	for (int i = 0; i < properties.length; i++) {// 主表数据
		String name = properties[i].getName();//字段名
		String value = Util.null2String(properties[i].getValue());//值
		baseBean.writeLog("mian:"+ "name:"+name+",value:"+value);
		if(name.equalsIgnoreCase("pich")){
			pich=value;
		}
		
		if(name.equalsIgnoreCase("jingbr")){
			jingbr=value;
		}
		
	}
		
	if(pich.equals("")||jingbr.equals("")){
		requestinfo.getRequestManager().setMessageid("1001");
		requestinfo.getRequestManager().setMessagecontent("数据不能为空");
		return Action.FAILURE_AND_CONTINUE;
	}
         // 转换为键值对  
         String params = "pcid=" + pich+"&appkey="+"qibei" ;
//		
		
		baseBean.writeLog("tagtag run action2 :"+"参数:"+params);
		
	
		JsonObject isok =HttpRequestUtil.postJson("http://59.38.35.85:8888/api/fin/loan/pcsign", params, true);
		baseBean.writeLog("tagtag run action3 :"+"返回信息:"+isok);
		return Action.SUCCESS;
		
		
   }
   
   
   public static void main(String[] args) {
		
	      String rid = "5652";
	      String status = "1";  //审批状态
	      String pcid = "1535692001";  //批
	      
	      String params = "pcid=" + pcid+"&appkey="+"qibei" ;
			baseBean.writeLog("tagtag run action3 :"+"参数:"+params);
			JsonObject isok =HttpRequestUtil.postJson("http://59.38.35.85:8888/api/fin/loan/pcsign", params, true);
			baseBean.writeLog("tagtag run action4 :"+"返回信息:"+isok);
}
   
   
}
