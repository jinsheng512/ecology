package greedc.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ecustom.ecology8.commons.RecordUtil;
import weaver.conn.RecordSet;
import weaver.formmode.service.FormInfoService;
import weaver.formmode.view.ModeViewLog;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.hrm.resource.ResourceComInfo;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.DetailTableInfo;
import weaver.soa.workflow.request.MainTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
import weaver.workflow.request.RequestManager;
/**
 * 请假
 */
public class CustomApplyHoliday  extends BaseBean implements Action{
	private Log log = LogFactory.getLog(CustomApplyHoliday.class.getName());
    private static BaseBean baseBean=new BaseBean();
	
	private String formmodeid = "";
	private RecordSet recordSet;
	@Override
	public String execute(RequestInfo request) { 
		baseBean.writeLog("tagtag run action1 :"+ this.getFormmodeid());
		
		int formmodeid = Util.getIntValue(this.getFormmodeid(), 0);
		String formmodeTable = "uf_holidaynew";
	    recordSet = new RecordSet();
		recordSet.execute("select * from workflow_bill where ID = (select formid from modeinfo where id='"+formmodeid+"')");
		if(recordSet.next()){
			formmodeTable = recordSet.getString("tablename");
		}
		
		
		String returns = executeData(request,formmodeTable,formmodeid);
		return returns;

	}
	
	
	private String executeData(RequestInfo request,String Maintable,int modeid){
		
		baseBean.writeLog("tagtag run action2 :"+getClass()+",requestid:"+request.getRequestid()+",Maintable:"+ Maintable+",modeid:"+ modeid);
		String billid = request.getRequestid();
		RequestManager requestManager = request.getRequestManager();
		User user=requestManager.getUser();
		String src=requestManager.getSrc();
		String nextnodetype=requestManager.getNextNodetype();
		
		if("submit".equalsIgnoreCase(src)){//提交
			String id = "";
			String qingjiaid = "";

			String biaodbh = "";//表单编号
			String qingjksrq = "";//开始时间
			String qingjjsrq = "";//结束时间
			String xiujts  = "";//休假天数
			String xiujyy = "";//休假原因
            String qingjlb = "";//休假类别
            String qingjlbStr = "";//休假类别
			String shenqr = "";//申请人
			String shenqrq = "";//申请时间

			

			String qijn  = "";//qijn
			String name1 = "";//人员  
			String suosbm   = "";//所属部门
			String suosgs     = "";//所属公司 
			String yingxnj    = "";//应休年假
			String yixnj   = "";//已休年假
			String weixnj = "";//未休年假
			String yixsj= "";//已休事假
			String yixbj     = "";//已休病假 
			String yixhj  = "";//已休婚假
			String yixiusj   = "";//已休丧假  
			String yixpeihj   = "";//已休陪护假
			String yixcj  = "";//已休产假


			//------请在下面编写业务逻辑代码------
			Map<String,String> mainMap = new HashMap<String,String>();
			MainTableInfo maintable = request.getMainTableInfo();// 主表信息
			Enumeration ele = maintable.enumerateProperty();
			while(ele.hasMoreElements()) {
				weaver.soa.workflow.request.Property pro = (Property) ele.nextElement();
				String name = pro.getName(); // 字段名称
				String value = pro.getValue(); // 字段的值
				
				baseBean.writeLog("mian:"+ "name:"+name+",value:"+value);
				
				if(name.toLowerCase().equals("id")){
					id = value;
				}else if(name.toLowerCase().equals("biaodbh")){
					biaodbh = value;
				}else if(name.toLowerCase().equals("qingjksrq")){
					qingjksrq = value;
				}else if(name.toLowerCase().equals("qingjjsrq")){
					qingjjsrq = value;
				}else if(name.toLowerCase().equals("shenqr")){
					shenqr = value;	
				}else if(name.toLowerCase().equals("baogrq")){
					shenqrq = value;
				}else if(name.toLowerCase().equals("qingjsjcd")){
					xiujts = value;
				}else if(name.toLowerCase().equals("qingjyy")){
					xiujyy = value;
				}else if(name.toLowerCase().equals("qingjlb")){
					qingjlb = value;
				}else if(name.toLowerCase().equals("qingjiaid")){
					qingjiaid = value;
				}
				
			
			}
			mainMap.put("id", id);
			mainMap.put("biaodbh", biaodbh);
			mainMap.put("qingjjsrq", qingjjsrq);
			mainMap.put("qingjksrq", qingjksrq);
			mainMap.put("shenqr", shenqr);
			mainMap.put("shenqrq", shenqrq);
			mainMap.put("xiujts", xiujts);
			mainMap.put("xiujyy", xiujyy);
			mainMap.put("qingjlb", qingjlb);
			mainMap.put("qingjiaid", qingjiaid);
			
			recordSet.executeSql("select * from uf_holidaynew where name='"+shenqr+"'");
			if(recordSet.next()){
				
				yixbj = recordSet.getString("yixbj");  //病假
				yixsj = recordSet.getString("yixsj");  //事假
				yixhj = recordSet.getString("yixhj");  //婚假
				yixiusj = recordSet.getString("yixiusj");  //丧假
				yixcj = recordSet.getString("yixcj");  //产假
				yixpeihj = recordSet.getString("yixpeihj"); //陪护假
				yixnj = recordSet.getString("yixnj");  //年假
				yingxnj = recordSet.getString("yingxnj");  //应休年假
			}
			//更新数据 
			
			if (qingjlb.equals("0")) {  //病假
				qingjlbStr = "病假";
				RecordUtil.executeUpdate("update uf_holidaynew set  yixbj = '"+(Double.valueOf(yixbj)+Double.valueOf(xiujts)) +"' where name='"+shenqr+"'");
				
			}
			
			if (qingjlb.equals("1")) {  //事假
				qingjlbStr = "事假";
				RecordUtil.executeUpdate("update uf_holidaynew set  yixsj = '"+(Double.valueOf(yixsj)+Double.valueOf(xiujts)) +"' where name='"+shenqr+"'");
				
			}
			
			if (qingjlb.equals("2")) {  //婚假
				qingjlbStr = "婚假";
				RecordUtil.executeUpdate("update uf_holidaynew set  yixhj = '"+(Double.valueOf(yixhj)+Double.valueOf(xiujts)) +"' where name='"+shenqr+"'");
				
			}
			
			if (qingjlb.equals("3")) {  //丧假
				qingjlbStr = "丧假";
				RecordUtil.executeUpdate("update uf_holidaynew set  yixiusj = '"+(Double.valueOf(yixiusj)+Double.valueOf(xiujts)) +"' where name='"+shenqr+"'");
				
			}
			
			if (qingjlb.equals("4")) {  //产假
				qingjlbStr = "产假";
				RecordUtil.executeUpdate("update uf_holidaynew set  yixcj = '"+(Double.valueOf(yixcj)+Double.valueOf(xiujts)) +"' where name='"+shenqr+"'");
				
			}
			
			if (qingjlb.equals("5")) {  //看护假
				qingjlbStr = "看护假";
				RecordUtil.executeUpdate("update uf_holidaynew set  yixpeihj = '"+(Double.valueOf(yixpeihj)+Double.valueOf(xiujts)) +"' where name='"+shenqr+"'");
				
			}
			
			if (qingjlb.equals("6")) {  //年假
				qingjlbStr = "年假";
				RecordUtil.executeUpdate("update uf_holidaynew set  yixnj = '"+(Double.valueOf(yixnj)+Double.valueOf(xiujts)) +"' where name='"+shenqr+"'");
				recordSet.executeSql("select * from uf_holidaynew where name='"+shenqr+"'");
				if(recordSet.next()){   
					
				yixnj = recordSet.getString("yixnj");  //年假
				
				}
				if((Double.valueOf(yingxnj)-Double.valueOf(yixnj))>=0){
					RecordUtil.executeUpdate("update uf_holidaynew set  weixnj = '"+((Double.valueOf(yingxnj)-Double.valueOf(yixnj))) +"' where name='"+shenqr+"'");
				}else{
					RecordUtil.executeUpdate("update uf_holidaynew set  weixnj = '0' where name='"+shenqr+"'");
				}
				
			}
			
		
			List<Map<String,String>> detailList = new ArrayList<Map<String,String>>();
			detailList.add(mainMap);

			
             
//			插入数据
			boolean isok = insertDataToMode(detailList,user,Maintable,modeid,billid,qingjlbStr);
			if(!isok){
				String errorcode="1111111"; // 工作流信息保存错误
				requestManager.setMessage(errorcode);
    			requestManager.setMessageid(""+System.currentTimeMillis());
    			requestManager.setMessagecontent("流程提交数据异常（非工程付款流程写入合同基本信息-付款明细表），请联系管理员查看！");
				return Action.SUCCESS;
			}
		}else if("reject".equalsIgnoreCase(src)){//退回
			
		}
        return Action.SUCCESS;
	}
	
	private boolean insertDataToMode(List<Map<String,String>> fieldList,User user,String Maintable,int modeid,String wbillid, String qingjlbStr){
		FormInfoService formInfoService = new FormInfoService();
		ResourceComInfo resourceComInfo = null;
		try {
			resourceComInfo = new ResourceComInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		RecordSet rs =new RecordSet();
		RecordSet rs2 =new RecordSet();
		Calendar today = Calendar.getInstance();
        String modedatacreatedate = Util.add0(today.get(Calendar.YEAR), 4) + "-" +
        		Util.add0(today.get(Calendar.MONTH) + 1, 2) + "-" +
        		Util.add0(today.get(Calendar.DAY_OF_MONTH), 2);

        String modedatacreatetime = Util.add0(today.get(Calendar.HOUR_OF_DAY), 2) + ":" +
        		Util.add0(today.get(Calendar.MINUTE), 2) + ":" +
        		Util.add0(today.get(Calendar.SECOND), 2);
        //取数据库服务器的当前时间
        rs.executeProc("GetDBDateAndTime","");
        if(rs.next()){
        	modedatacreatedate = rs.getString("dbdate");
        	modedatacreatetime = rs.getString("dbtime");
        }
		for(int i=0;i<fieldList.size();i++){
			Map<String,String> rowMap = fieldList.get(i);
			String id = rowMap.get("id");
			String biaodbh = rowMap.get("biaodbh");//表单编号
			String qingjjsrq  = rowMap.get("qingjjsrq");//申请人
			String qingjksrq  = rowMap.get("qingjksrq");//申请人
			String shenqr  = rowMap.get("shenqr");//申请人
			
			String xiujts  = rowMap.get("xiujts");//申请人
			String xiujyy  = rowMap.get("xiujyy");//申请人
			String shenqrq  = rowMap.get("shenqrq");//申请人
			String qingjiaid  = rowMap.get("qingjiaid");//申请人
			String qingjlb  = rowMap.get("qingjlb");//申请人
			
//		
			
			//
			String insertSql_C = "danjbh,kaissj,jiessj,xiujts,shenqr,shenqsj,xiujyy,mainid,qingjlb";
			String insertSql_V = "'"+biaodbh+"','"+qingjksrq+"','"+qingjjsrq+"','"+xiujts+"','"+shenqr+"','"+shenqrq+"','"+xiujyy+"','"+qingjiaid+"','"+qingjlbStr+"' ";
			
			
			//插入付款明细表
        	String sql = "insert into "+Maintable+"_dt1 ("+insertSql_C+") values ("+insertSql_V+")";
        	rs2.execute(sql);
        	
//        	weaver.formmode.setup.ModeRightInfo modeRightInfo = new weaver.formmode.setup.ModeRightInfo();
//        	modeRightInfo.editModeDataShare(user.getUID(),modeid,dataid);//新建的时候添加共享
//        	modeRightInfo.editDocShareForModeField(user.getUID(),modeid,dataid); // 文档添加权限
//        	
//        	//记录操作日志
//        	ModeViewLog ModeViewLog = createModeViewLog(modeid,user.getUID(),dataid);
		}
		return true;
	}
	
	private ModeViewLog createModeViewLog(int modeid,int operateuserid,int relatedid){
		ModeViewLog ModeViewLog = new ModeViewLog();
		ModeViewLog.resetParameter();
		ModeViewLog.setClientaddress("0.0.0.0");
		ModeViewLog.setModeid(modeid);
		ModeViewLog.setOperatedesc("CustomApplyHoliday接口处理（非工程付款流程写入合同基本信息-付款明细表）");
		ModeViewLog.setOperatetype("2");//操作的类型： 1：新建 2：修改 3：删除 4：查看
		ModeViewLog.setOperateuserid(operateuserid);
		ModeViewLog.setRelatedid(relatedid);
		ModeViewLog.setRelatedname("修改");
		
		return ModeViewLog;
	}
	
	private void createLog(FormInfoService formInfoService,ModeViewLog ModeViewLog,String fieldid,String fieldvalue,String prefieldvalue,int modeid) throws Exception{
		int viewlogid = ModeViewLog.setSysLogInfo();
		Map<String, Object> logDetailMap = new HashMap<String,	Object>();
		logDetailMap.put("viewlogid", viewlogid);
		logDetailMap.put("fieldid", fieldid);
		logDetailMap.put("fieldvalue", fieldvalue);
		logDetailMap.put("prefieldvalue", prefieldvalue);
		logDetailMap.put("modeid", modeid);
		formInfoService.saveFieldLogDetail(logDetailMap);
	}

	public String getFormmodeid() {
		return formmodeid;
	}

	public void setFormmodeid(String formmodeid) {
		this.formmodeid = formmodeid;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

}
