package weaver.formmode.interfaces.diyaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
 * 非工程付款流程 
 */
public class CustomPaystatusAction extends BaseBean implements Action{
	private Log log = LogFactory.getLog(CustomPaystatusAction.class.getName());
	
	private String formmodeid = "";
	@Override
	public String execute(RequestInfo request) { 
		
		int formmodeid = Util.getIntValue(this.getFormmodeid(), 0);
		String formmodeTable = "uf_het";
		RecordSet recordSet = new RecordSet();
		recordSet.execute("select * from workflow_bill where ID = (select formid from modeinfo where id='"+formmodeid+"')");
		if(recordSet.next()){
			formmodeTable = recordSet.getString("tablename");
		}
		String returns = executeData(request,formmodeTable,formmodeid);
		return returns;

		
		/*
		MainTableInfo maintable = request.getMainTableInfo();// 主表信息
		Enumeration ele = maintable.enumerateProperty();
		while(ele.hasMoreElements()) {
			weaver.soa.workflow.request.Property pro = (Property) ele.nextElement();
			String name = pro.getName(); // 字段名称
			String value = pro.getValue(); // 字段的值
		}
		
		DetailTableInfo detailtableinfo =  request.getDetailTableInfo();//明细表对象
		for(int i = 0; i< detailtableinfo.getDetailTableCount(); i++) {  //遍历多个明细表
			DetailTable dttmp = detailtableinfo.getDetailTable(i); 
			dttmp.
			for(int rowindex = 0; rowindex<dttmp.getRowCount(); rowindex ++){//遍历明细表的多行数据
				Row rowtmp = dttmp.getRow(rowindex);
				for(int cellindex = 0; cellindex<rowtmp.getCellCount(); cellindex++) {//遍历明细表的各个字段
					Cell celltmp = rowtmp.getCell(cellindex);
					String name = celltmp.getName(); // 明细字段名称
					String value = celltmp.getValue();//明细字段值
				}
			}
		}
		*/
	}
	
	
	private String executeData(RequestInfo request,String Maintable,int modeid){
		
		
		String billid = request.getRequestid();
		RequestManager requestManager = request.getRequestManager();
		User user=requestManager.getUser();
		String src=requestManager.getSrc();
		String nextnodetype=requestManager.getNextNodetype();
		
		if("submit".equalsIgnoreCase(src)){//提交
			String id = "";
			String biaodbh = "";//表单编号
			String shenqr  = "";//申请人
			String shenqrq = "";//申请日期  
			String fukgs   = "";//付款公司  
			String bum     = "";//部门 
			String zhiw    = "";//职位
			String hetmc   = "";//合同名称
			String shoukdw = "";//收款单位
			String shouzfzh= "";//收款方帐号
			String fuj     = "";//附件 
			String suosgs  = "";//所属公司
			String hetbh   = "";//合同编号  
			String kaihh   = "";//开户行
			String hetzje  = "";//合同总金额  
			String leijyfje= "";//累计已付金额  
			String weifje  = "";//未付金额  
			String bencsqje= "";//本次申请金额  
			String xiaox   = "";//小写
			String leijfksqje  = "";//累计付款申请金额
			String yongksx = "";// 用款事项
			//------请在下面编写业务逻辑代码------
			Map<String,String> mainMap = new HashMap<String,String>();
			MainTableInfo maintable = request.getMainTableInfo();// 主表信息
			Enumeration ele = maintable.enumerateProperty();
			while(ele.hasMoreElements()) {
				weaver.soa.workflow.request.Property pro = (Property) ele.nextElement();
				String name = pro.getName(); // 字段名称
				String value = pro.getValue(); // 字段的值
				if(name.toLowerCase().equals("id")){
					id = value;
				}else if(name.toLowerCase().equals("biaodbh")){
					biaodbh = value;
				}else if(name.toLowerCase().equals("shenqr")){
					shenqr = value;
				}else if(name.toLowerCase().equals("shenqrq")){
					shenqrq = value;
				}else if(name.toLowerCase().equals("fukgs")){
					fukgs = value;
				}else if(name.toLowerCase().equals("bum")){
					bum = value;
				}else if(name.toLowerCase().equals("zhiw")){
					zhiw = value;
				}else if(name.toLowerCase().equals("hetmc")){
					hetmc = value;
				}else if(name.toLowerCase().equals("shoukdw")){
					shoukdw = value;
				}else if(name.toLowerCase().equals("shouzfzh")){
					shouzfzh = value;
				}else if(name.toLowerCase().equals("fuj")){
					fuj = value;
				}else if(name.toLowerCase().equals("suosgs")){
					suosgs = value;
				}else if(name.toLowerCase().equals("hetbh")){
					hetbh = value;
				}else if(name.toLowerCase().equals("kaihh")){
					kaihh = value;
				}else if(name.toLowerCase().equals("hetzje")){
					hetzje = value;
				}else if(name.toLowerCase().equals("leijyfje")){
					leijyfje = value;
				}else if(name.toLowerCase().equals("weifje")){
					weifje = value;
				}else if(name.toLowerCase().equals("bencsqje")){
					bencsqje = value;
				}else if(name.toLowerCase().equals("xiaox")){
					xiaox = value;
				}else if(name.toLowerCase().equals("leijfksqje")){
					leijfksqje = value;
				}else if(name.toLowerCase().equals("yongksx")){
					yongksx = value;
				}
			}
			mainMap.put("id", id);
			mainMap.put("biaodbh", biaodbh);
			mainMap.put("shenqr", shenqr);
			mainMap.put("shenqrq", shenqrq);
			mainMap.put("fukgs", fukgs);
			mainMap.put("bum", bum);
			mainMap.put("zhiw", zhiw);
			mainMap.put("hetmc", hetmc);
			mainMap.put("shoukdw", shoukdw);
			mainMap.put("shouzfzh", shouzfzh);
			mainMap.put("fuj", fuj);
			mainMap.put("suosgs", suosgs);
			mainMap.put("hetbh", hetbh);
			mainMap.put("kaihh", kaihh);
			mainMap.put("hetzje", hetzje);
			mainMap.put("leijyfje", leijyfje);
			mainMap.put("weifje", weifje);
			mainMap.put("bencsqje", bencsqje);
			mainMap.put("xiaox", xiaox);
			mainMap.put("leijfksqje", leijfksqje);
			mainMap.put("yongksx", yongksx);
			List<Map<String,String>> detailList = new ArrayList<Map<String,String>>();
			detailList.add(mainMap);
			//插入数据
			boolean isok = insertDataToMode(detailList,user,Maintable,modeid,billid);
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
	
	private boolean insertDataToMode(List<Map<String,String>> fieldList,User user,String Maintable,int modeid,String wbillid){
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
			String shenqr  = rowMap.get("shenqr");//申请人
			String caozrwb = resourceComInfo.getLastname(shenqr);  //lastname
			String shenqrq = rowMap.get("shenqrq");//申请日期  
			String fukgs   = rowMap.get("fukgs");//付款公司  
			String bum     = rowMap.get("bum");//部门 
			String zhiw    = rowMap.get("zhiw");//职位
			String hetmc   = rowMap.get("hetmc");//合同名称
			String shoukdw = rowMap.get("shoukdw");//收款单位
			String shouzfzh= rowMap.get("shouzfzh");//收款方帐号
			String fuj     = rowMap.get("fuj");//附件 
			String suosgs  = rowMap.get("suosgs");//所属公司
			String hetbh   = rowMap.get("hetbh");//合同编号  
			String kaihh   = rowMap.get("kaihh");//开户行
			String hetzje  = rowMap.get("hetzje");//合同总金额  
			String leijyfje= rowMap.get("leijyfje");//累计已付金额  
			String weifje  = rowMap.get("weifje");//未付金额  
			String bencsqje= rowMap.get("bencsqje");//本次申请金额  
			String xiaox   = rowMap.get("xiaox");//小写
			String leijfksqje  = rowMap.get("leijfksqje");//累计付款申请金额
			String yongksx = rowMap.get("yongksx");// 用款事项
			
			int dataid = Util.getIntValue(hetbh,0);
			
			//付款单号,付款日期,付款公司,付款金额,用款事项,收款单位,开户行,收款方账号,操作人,操作日期
			String insertSql_C = "fukdh,fukrq,fukgs,fukje,yongksy,shoukdw,kaihh,shoukfzh,caozr,caozrq,caozrwb";
			String insertSql_V = "'"+biaodbh+"','"+shenqrq+"','"+suosgs+"','"+bencsqje+"','"+yongksx+"','"+shoukdw+"','"+kaihh+"','"+shouzfzh+"','"+shenqr+"','"+modedatacreatedate+"','"+caozrwb+"' ";
			
			insertSql_C += ",mainid";
			insertSql_V += ",'"+dataid+"'";
			//插入付款明细表
        	String sql = "insert into "+Maintable+"_dt2 ("+insertSql_C+") values ("+insertSql_V+")";
        	rs2.execute(sql);
        	//同时计算更新主表基本信息的“累计已付金额”、“未付金额”
        	/*
        	sql = "update "+Maintable+" set leijyfje='',weifje='' where id='"+dataid+"'";
        	rs2.execute(sql);
        	*/
        	weaver.formmode.setup.ModeRightInfo modeRightInfo = new weaver.formmode.setup.ModeRightInfo();
        	modeRightInfo.editModeDataShare(user.getUID(),modeid,dataid);//新建的时候添加共享
        	modeRightInfo.editDocShareForModeField(user.getUID(),modeid,dataid); // 文档添加权限
        	
        	//记录操作日志
        	ModeViewLog ModeViewLog = createModeViewLog(modeid,user.getUID(),dataid);
		}
		return true;
	}
	
	private ModeViewLog createModeViewLog(int modeid,int operateuserid,int relatedid){
		ModeViewLog ModeViewLog = new ModeViewLog();
		ModeViewLog.resetParameter();
		ModeViewLog.setClientaddress("0.0.0.0");
		ModeViewLog.setModeid(modeid);
		ModeViewLog.setOperatedesc("CustomPaystatusAction接口处理（非工程付款流程写入合同基本信息-付款明细表）");
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
