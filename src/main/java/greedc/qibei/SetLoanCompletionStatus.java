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
 * 确认放款
 * @author Administrator
 *
 */

public class SetLoanCompletionStatus extends BaseBean implements Action {
	
	private static BaseBean baseBean=new BaseBean();

   private static Log log = LogFactory.getLog(SetLoanCompletionStatus.class.getName());


   public String execute(RequestInfo requestinfo) {
	   
	baseBean.writeLog("tagtag run action1 :"+getClass()+",requestid:"+requestinfo.getRequestid()+",wfid:"+requestinfo.getWorkflowid()+",src:"+requestinfo.getRequestManager().getSrc());
	   
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
		
	 // 转换为键值对  
       String params = "pcid=" + pich+"&appkey="+"qibei" ;
     
		baseBean.writeLog("tagtag run action3 :"+"参数:"+params);
		
		JsonObject isok =HttpRequestUtil.postJson("http://haikongbl.haikongjinrong.com:8888/api/fin/loan/confirmpay", params, true);
//	
		baseBean.writeLog("tagtag run action4 :"+"返回信息:"+isok);
		return Action.SUCCESS;
		
      
     
   }
   
   public static void main(String[] args) {
		
	      String pcid = "1537430223";  //批
	      
	      String params = "pcid=" + pcid+"&appkey="+"qibei" ;
			baseBean.writeLog("tagtag run action3 :"+"参数:"+params);
			JsonObject isok =HttpRequestUtil.postJson("http://haikongbl.haikongjinrong.com:8888/api/fin/loan/confirmpay", params, true);
			baseBean.writeLog("tagtag run action4 :"+"返回信息:"+isok);
   }
}
