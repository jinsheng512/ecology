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
 * 行政类合同录入流程
 * 归档
 */
public class CustomModifyCinfoAction extends BaseBean implements Action{
	private Log log = LogFactory.getLog(CustomContractInfoAction.class.getName());
	
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
		
		String id = "";
		String biaodbh = "";//表单编号  
		String shenqr  = "";//申请人    
		String suozbm  = "";//所在部门  
		String suozfb  = "";//所在分部  
		String zhiw    = "";//职位      
		String shenqrq = "";//申请日期  
		String hetmc   = "";//合同名称  
		String hetf    = "";//合同方    
		String hetje   = "";//合同金额  
		String zhidlxr = "";//指定联系人
		String dianh   = "";//电话     
		String hetsz   = "";//合同收支 
		String wancrq  = "";//完成日期 
		String hetxz   = "";//合同性质 
		String zhuynr  = "";//主要内容 
		String jinbr   = "";//经办人   
		String suosbm  = "";//所属部门 
		String suoszw  = "";//所属职位 
		String zhuanjr = "";//转接人   
		String fuj     = "";//附件 
		String fujs    = "";//附件数  
		String zhidr   = "";//制单人  
		String tianbsj = "";//填表时间
		String hetlx   = "";//合同类型
		String guanlht = "";//关联合同
		String htid = ""; // 新增合同id（需要流程里面添加隐藏字段）
		String leix = "";//类型版本
		String shifgd = "";//是否归档
		String zangj = "";//暂估价对
		
		//------请在下面编写业务逻辑代码------
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
			}else if(name.toLowerCase().equals("suozbm")){
				suozbm = value;
			}else if(name.toLowerCase().equals("suozfb")){
				suozfb = value;
			}else if(name.toLowerCase().equals("zhiw")){
				zhiw = value;
			}else if(name.toLowerCase().equals("shenqrq")){
				shenqrq = value;
			}else if(name.toLowerCase().equals("hetmc")){
				hetmc = value;
			}else if(name.toLowerCase().equals("hetf")){
				hetf = value;
			}else if(name.toLowerCase().equals("hetje")){
				hetje = value;
			}else if(name.toLowerCase().equals("zhidlxr")){
				zhidlxr = value;
			}else if(name.toLowerCase().equals("dianh")){
				dianh = value;
			}else if(name.toLowerCase().equals("hetsz")){
				hetsz = value;
			}else if(name.toLowerCase().equals("wancrq")){
				wancrq = value;
			}else if(name.toLowerCase().equals("hetxz")){
				hetxz = value;
			}else if(name.toLowerCase().equals("zhuynr")){
				zhuynr = value;
			}else if(name.toLowerCase().equals("jinbr")){
				jinbr = value;
			}else if(name.toLowerCase().equals("suosbm")){
				suosbm = value;
			}else if(name.toLowerCase().equals("suoszw")){
				suoszw = value;
			}else if(name.toLowerCase().equals("zhuanjr")){
				zhuanjr = value;
			}else if(name.toLowerCase().equals("fuj")){
				fuj = value;
			}else if(name.toLowerCase().equals("fujs")){
				fujs = value;
			}else if(name.toLowerCase().equals("zhidr")){
				zhidr = value;
			}else if(name.toLowerCase().equals("tianbsj")){
				tianbsj = value;
			}else if(name.toLowerCase().equals("hetlx")){
				hetlx = value;
			}else if(name.toLowerCase().equals("guanlht")){
				guanlht = value;
			}else if(name.toLowerCase().equals("htid")){
				htid = value;
			}else if(name.toLowerCase().equals("leix")){
				leix = value;
			}else if(name.toLowerCase().equals("shifgd")){
				shifgd = value;
			}else if(name.toLowerCase().equals("zangj")){
				zangj = value;
			}
		}
		
		if("submit".equalsIgnoreCase(src)){//提交
			Map<String,String> mainMap = new HashMap<String,String>();
			mainMap.put("id", id);
			mainMap.put("biaodbh", biaodbh);
			mainMap.put("shenqr",  shenqr);  
			mainMap.put("suozbm",  suozbm);  
			mainMap.put("suozfb",  suozfb);  
			mainMap.put("zhiw",    zhiw);    
			mainMap.put("shenqrq", shenqrq); 
			mainMap.put("hetmc",   hetmc);   
			mainMap.put("hetf",    hetf);    
			mainMap.put("hetje",   hetje);   
			mainMap.put("zhidlxr", zhidlxr); 
			mainMap.put("dianh",   dianh);   
			mainMap.put("hetsz",   hetsz);   
			mainMap.put("wancrq",  wancrq);  
			mainMap.put("hetxz",   hetxz);   
			mainMap.put("zhuynr",  zhuynr);  
			mainMap.put("jinbr",   jinbr);   
			mainMap.put("suosbm",  suosbm);  
			mainMap.put("suoszw",  suoszw);  
			mainMap.put("zhuanjr", zhuanjr); 
			mainMap.put("fuj",     fuj);     
			mainMap.put("fujs",    fujs);    
			mainMap.put("zhidr",   zhidr);   
			mainMap.put("tianbsj", tianbsj); 
			mainMap.put("hetlx",   hetlx);   
			mainMap.put("guanlht", guanlht);  
			mainMap.put("htid", htid);  
			mainMap.put("leix", leix);
			mainMap.put("shifgd", shifgd);
			mainMap.put("zangj", zangj);
			List<Map<String,String>> detailList = new ArrayList<Map<String,String>>();
			detailList.add(mainMap);
			
			if("1".equals(hetxz)){ // 合同性质为“补充合同”时，流程归档写入合同变更信息
				//插入数据
				boolean isok = insertDataToMode(detailList,user,Maintable,modeid,billid);
				if(!isok){
					requestManager.setMessage("1111111");
	    			requestManager.setMessageid(""+System.currentTimeMillis());
	    			requestManager.setMessagecontent("流程提交数据异常（行政类合同录入流程写入合同基本信息），请联系管理员查看！");
					return Action.FAILURE_AND_CONTINUE;
				}
			}
			//流程是否归档字段更新到表单建模主表中
			
			//归档时；更新合同状态为“已审批”；
			RecordSet rs2 = new RecordSet();
        	String sql = "update "+Maintable+" set danjzt='1',shifgd='"+shifgd+"' where id='"+htid+"'";
        	writeLog("CustomModifyCinfoAction:238:sql="+sql);
        	rs2.execute(sql);
		}else if("reject".equalsIgnoreCase(src)){//退回
			//退回到提单节点时，更新合同状态为已取消
			RecordSet rs2 = new RecordSet();
        	String sql = "update "+Maintable+" set danjzt='2' where id='"+htid+"'";
        	writeLog("CustomModifyCinfoAction:244:sql="+sql);
        	rs2.execute(sql);
		}
        return Action.SUCCESS;
	}
	
	private boolean insertDataToMode(List<Map<String,String>> detailList,User user,String Maintable,int modeid,String wbillid){
		FormInfoService formInfoService = new FormInfoService();
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
		for(int i=0;i<detailList.size();i++){
			Map<String,String> rowMap = detailList.get(i);
			String id = rowMap.get("id");
			String biaodbh = rowMap.get("biaodbh");//表单编号  
			String shenqr  = rowMap.get("shenqr");//申请人    
			String suozbm  = rowMap.get("suozbm");//所在部门  
			String suozfb  = rowMap.get("suozfb");//所在分部  
			String zhiw    = rowMap.get("zhiw");//职位      
			String shenqrq = rowMap.get("shenqrq");//申请日期  
			String hetmc   = rowMap.get("hetmc");//合同名称  
			String hetf    = rowMap.get("hetf");//合同方    
			String hetje   = rowMap.get("hetje");//合同金额  
			String zhidlxr = rowMap.get("zhidlxr");//指定联系人
			String dianh   = rowMap.get("dianh");//电话     
			String hetsz   = rowMap.get("hetsz");//合同收支 
			String wancrq  = rowMap.get("wancrq");//完成日期 
			String hetxz   = rowMap.get("hetxz");//合同性质 
			String zhuynr  = rowMap.get("zhuynr");//主要内容 
			String jinbr   = rowMap.get("jinbr");//经办人   
			String suosbm  = rowMap.get("suosbm");//所属部门 
			String suoszw  = rowMap.get("suoszw");//所属职位 
			String zhuanjr = rowMap.get("zhuanjr");//转接人   
			String fuj     = rowMap.get("fuj");//附件 
			String fujs    = rowMap.get("fujs");//附件数  
			String zhidr   = rowMap.get("zhidr");//制单人  
			String tianbsj = rowMap.get("tianbsj");//填表时间
			String hetlx   = rowMap.get("hetlx");//合同类型
			String guanlht = rowMap.get("guanlht");//关联合同
			//String htid = rowMap.get("htid");//生成合同（表单建模里面的id）
			String leix = rowMap.get("leix");//类型版本
			String shifgd = rowMap.get("shifgd");//类型版本
			String zangj = rowMap.get("zangj");//类型版本
			
			int dataid = Util.getIntValue(guanlht,0);
			
			//变更单号,合同名称,所属公司,合同类型,合同方,合同金额,指定联系人,电话,合同收支,完成日期,附件,变更人,变更日期
			String insertSql_C = "biangdh,hetmc,suosgs,hetlx,hetf,hetje,zhidlxr,dianh,hetsz,wancrq,fuj,biangr,biangrq";
			String insertSql_V = "'"+biaodbh+"','"+hetmc+"','"+suozfb+"','"+hetlx+"','"+hetf+"','"+hetje+"','"+zhidlxr+"','"+dianh+"','"+hetsz+"','"+wancrq+"','"+fuj+"','"+shenqr+"','"+modedatacreatedate+"'";
			
			insertSql_C += ",mainid"; 
			insertSql_V += ",'"+dataid+"'";
			//插入付款明细表
        	String sql = "insert into "+Maintable+"_dt1 ("+insertSql_C+") values ("+insertSql_V+")";
        	writeLog("CustomModifyCinfoAction:311:sql="+sql);
        	rs2.execute(sql);
        	
        	/*
        	//同时更新合同基本信息（单据状态,合同编码,合同名称,所属公司,合同类型,合同方,合同金额,指定联系人,电话,合同收支,完成日期,合同性质,累计已付金额(关联付款流程自动计算),未付金额(关联付款流程自动计算),附件,创建人,创建日期）
			String updateSql = "danjzt='1',hetbh='"+biaodbh+"',hetmc='"+hetmc+"',suosfb='"+suozfb+"',hetlx='"+hetlx
			                  +"',hezf='"+hetf+"',hetzje='"+hetje+"',zhidlxr='"+zhidlxr+"',dianh='"+dianh+"',hetsz='"+hetsz+"',wancrq='"+wancrq+"',hetxz='"+hetxz+"',fuj='"+fuj+"'";
			sql = "update "+Maintable+" set "+updateSql+" where id='"+dataid+"'";
			writeLog("CustomModifyCinfoAction::sql="+sql);
			rs2.execute(sql);
        	
        	weaver.formmode.setup.ModeRightInfo modeRightInfo = new weaver.formmode.setup.ModeRightInfo();
        	modeRightInfo.editModeDataShare(user.getUID(),modeid,dataid);//新建的时候添加共享
        	modeRightInfo.editDocShareForModeField(user.getUID(),modeid,dataid); // 文档添加权限
        	*/
        	
        	String updateSql = "chuangjr='"+shenqr+"',zhuanjr='"+zhuanjr+"',jingbr='"+jinbr+"',jingbbm='"+suosbm+"',leixbb='"+leix+"',shifgd='"+shifgd+"',zangj='"+zangj+"',danjzt='0',hetbh='"+biaodbh+"',hetmc='"+hetmc+"',suosfb='"+suozfb+"',hetlx='"+hetlx
            +"',hezf='"+hetf+"',hetzje='"+hetje+"',zhidlxr='"+zhidlxr+"',dianh='"+dianh+"',hetsz='"+hetsz+"',wancrq='"+wancrq+"',hetxz='"+hetxz+"',fuj='"+fuj+"'";
			weaver.formmode.data.ModeDataIdUpdate mdu = new weaver.formmode.data.ModeDataIdUpdate();
			weaver.formmode.setup.ModeRightInfo modeRightInfo = new weaver.formmode.setup.ModeRightInfo(); 
			dataid = mdu.getModeDataNewId(Maintable,modeid,user.getUID(),user.getType(),modedatacreatedate,modedatacreatetime);
			sql = "update "+Maintable+" set "+updateSql+" where id='"+dataid+"'";
			writeLog("CustomModifyCinfoAction:333:sql="+sql);
			rs2.execute(sql);
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
		ModeViewLog.setOperatedesc("CustomModifyCinfoAction接口处理（行政类合同录入流程写入合同基本-变更信息）");
		ModeViewLog.setOperatetype("1");//操作的类型： 1：新建 2：修改 3：删除 4：查看
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
