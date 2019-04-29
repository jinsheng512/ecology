package weaver.cpt.wfactions;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.cpt.capital.CapitalComInfo;
import weaver.cpt.capital.CptShare;
import weaver.cpt.maintenance.CapitalAssortmentComInfo;
import weaver.cpt.util.CptWfUtil;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;

/**
 * 
 * @author crazyDream
 * @createdDate 2014-11-7
 * @function : fetch action
 *
 */
public class CptFetchAction implements Action,Runnable {
	private static BaseBean baseBean=new BaseBean();
	private static Object lock=new  Object();
	private CptWfUtil cptWfUtil=new CptWfUtil();
	private RequestInfo request=null;
	private JSONObject wfObject=null;
	
	public String execute(RequestInfo request) {
		this.request=request;
		
		try {
			wfObject=cptWfUtil.getCptwfInfo(request.getWorkflowid());
			if((wfObject.getInt("zctype")!=wfObject.getInt("sltype"))||"".equals( wfObject.getString("zcname"))||"".equals(wfObject.getString("slname")) ){//配置不对,直接退出
				request.getRequestManager().setMessageid("20088");
	        	request.getRequestManager().setMessagecontent("后台流程配置不正确,请检查后台流程配置!");
	        	return Action.SUCCESS;
			}
			
			if("1".equals( wfObject.getString("isasync"))){//异步
				new Thread(this).start();
			}else{
				doAction(request);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			baseBean.writeLog(e.getMessage());
		}
		
		
		
		return Action.SUCCESS;
	}

	public void run() {
		doAction(this.request);
	}

	private String doAction(RequestInfo request){
		
		synchronized (lock) {
			baseBean.writeLog("tagtag run action :"+getClass()+",requestid:"+request.getRequestid());
			
			ConnStatement statement=new ConnStatement();
			CapitalAssortmentComInfo CapitalAssortmentComInfo=new CapitalAssortmentComInfo();
			CptShare CptShare=new CptShare();
			ArrayList ids = new ArrayList();
			CapitalComInfo CapitalComInfo=null;
			DepartmentComInfo DepartmentComInfo=null;
			ResourceComInfo resourceComInfo=null;
			try {
				CapitalComInfo = new CapitalComInfo();
				DepartmentComInfo = new DepartmentComInfo();
				resourceComInfo=new ResourceComInfo();
				
				RecordSet rs1=new RecordSet();
				RecordSet rs2=new RecordSet();
				char separator = Util.getSeparator() ;
				String requestid= request.getRequestid();
				String formid=""+ request.getRequestManager().getFormid();
				String currentdate=TimeUtil.getCurrentDateString();
				String currenttime=TimeUtil.getOnlyCurrentTimeString();
				int createrid = request.getRequestManager().getCreater();
				
				String shenqr="";//申请人
				String zc="";//申请资产
				String shenqbm="";//申请部门
				String zczl="";//资产资料
				String zicz="";//资产组
				String shenqrq="";//申请日期
				String sl="";//申请资产数量
				String cfdd="";//存放地点
				String bz="";//备注
				
				String caiwbh="";//财务编号
				String faph="";//发票号
				
				//暂存表单的数据
				JSONArray jsonArray=new JSONArray();
				
				
				Property[] properties = request.getMainTableInfo().getProperty();// 获取表单主字段信息
				for (int i = 0; i < properties.length; i++) {// 主表数据
					String name = properties[i].getName();//字段名
					String value = Util.null2String(properties[i].getValue());//值
					
					if(wfObject.getInt("sqrtype")==0 && name.equalsIgnoreCase(wfObject.getString("sqrname"))){
						shenqr=value;
						shenqbm=resourceComInfo.getDepartmentID(shenqr);
					}else if (wfObject.getInt("zctype")==0 && name.equalsIgnoreCase(wfObject.getString("zcname"))) {
						zc=value;
					}else if (wfObject.getInt("sltype")==0 && name.equalsIgnoreCase(wfObject.getString("slname"))) {
						sl=value;
					}else if (wfObject.getInt("rqtype")==0 && name.equalsIgnoreCase(wfObject.getString("rqname"))) {
						shenqrq=value;
					}else if (wfObject.getInt("cfddtype")==0 && name.equalsIgnoreCase(wfObject.getString("cfddname"))) {
						cfdd=value;
					}else if (wfObject.getInt("bztype")==0 && name.equalsIgnoreCase(wfObject.getString("bzname"))) {
						bz=value;
					}
					
				}
				
				if(wfObject.getInt("zctype")==0 && wfObject.getInt("sltype")==0){//主表
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("sqr", shenqr);
					jsonObject.put("sqbm", shenqbm);
					jsonObject.put("zc", zc);
					jsonObject.put("sl", sl);
					jsonObject.put("rq", shenqrq);
					jsonObject.put("cfdd", cfdd);
					jsonObject.put("bz", bz);
					
					jsonArray.put(jsonObject);
					
				}else if((wfObject.getInt("zctype")==1 && wfObject.getInt("sltype")==1)
						||(wfObject.getInt("zctype")==2 && wfObject.getInt("sltype")==2)
						||(wfObject.getInt("zctype")==3 && wfObject.getInt("sltype")==3)
						||(wfObject.getInt("zctype")==4 && wfObject.getInt("sltype")==4)){//明细表
					DetailTable[] detailtable = request.getDetailTableInfo().getDetailTable();// 获取明细表
					int dtnum=(wfObject.getInt("zctype")-1);
					if (detailtable.length > dtnum) {
						DetailTable dt = detailtable[dtnum];
						
						baseBean.writeLog("tagtag run action4 :"+ dtnum +"," +detailtable[0].getTableDBName()+","  + detailtable.length);
						
						Row[] s = dt.getRow();
						
						char flag=2;
						String para = "";
						for (int j = 0; j < s.length; j++) {
							Row r = s[j];
							Cell c[] = r.getCell();
							for (int k = 0; k < c.length; k++) {
								Cell c1 = c[k];
								String name = c1.getName().toLowerCase();//字段名
								String value = Util.null2String(c1.getValue());//值
								
								if(name.equalsIgnoreCase(wfObject.getString("sqrname"))){
									shenqr=value;
									shenqbm=resourceComInfo.getDepartmentID(shenqr);
								}else if (name.equalsIgnoreCase(wfObject.getString("zcname"))) {
									zc=value;
								}else if (name.equalsIgnoreCase(wfObject.getString("slname"))) {
									sl=value;
								}else if (name.equalsIgnoreCase(wfObject.getString("rqname"))) {
									shenqrq=value;
								}else if (name.equalsIgnoreCase(wfObject.getString("cfddname"))) {
									cfdd=value;
								}else if (name.equalsIgnoreCase(wfObject.getString("bzname"))) {
									bz=value;
								}else if (name.equalsIgnoreCase("caiwbh")) {
									caiwbh=value;
									baseBean.writeLog("tagtag run action5 :"+ caiwbh );
								}else if (name.equalsIgnoreCase("faph")) {
									faph=value;
									baseBean.writeLog("tagtag run action6 :"+ faph );
								}
							}
							JSONObject jsonObject=new JSONObject();
							jsonObject.put("sqr", shenqr);
							jsonObject.put("sqbm", shenqbm);
							jsonObject.put("zc", zc);
							jsonObject.put("sl", sl);
							jsonObject.put("rq", shenqrq);
							jsonObject.put("cfdd", cfdd);
							jsonObject.put("bz", bz);
							
							jsonObject.put("caiwbh", caiwbh);
							jsonObject.put("faph", faph);
							
							jsonArray.put(jsonObject);
						}
					}
				}
				
				//开始执行业务逻辑
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jsonObject=jsonArray.getJSONObject(i);
					
					char flag=Util.getSeparator();
					String para="";
					String sptcount="";
					
					String zic=jsonObject.getString("zc");
					String shul=jsonObject.getString("sl");
					String riq=jsonObject.getString("rq");
					shenqbm=jsonObject.getString("sqbm");
					shenqr=jsonObject.getString("sqr");
					String location=jsonObject.getString("cfdd");
					String oldlocation="";
                    bz=jsonObject.getString("bz");
                    
                    String cwbh=jsonObject.getString("caiwbh");
					String fph=jsonObject.getString("faph");
					
					baseBean.writeLog("tagtag run action7 :"+ faph );
					
					if(Util.getIntValue(zic,0)<=0||Util.getDoubleValue(shul,0.0)<=0){
						continue;
					}
					
					rs1.executeSql(" select * from CptCapital where id="+zic);
					if (rs1.next()) {
						sptcount=rs1.getString("sptcount");
						oldlocation=rs1.getString("location");
					}
                    if("".equals(location)){
                        location=oldlocation;
                    }
					
					if(sptcount.equals("1")){
				        para = zic;
				        para +=flag+riq;
				        para +=flag+shenqbm;
				        para +=flag+shenqr;
				        para +=flag+"1";
				        para +=flag+requestid;
				        para +=flag+"";
				        para +=flag+"0";
				        para +=flag+"2";
				        para +=flag+bz;
				        para +=flag+location;
				        para +=flag+sptcount;
				        
				       
				        
				        baseBean.writeLog("tagtag run action8 :"+ faph );

				        rs1.executeProc("CptUseLogUse_Insert2",para);
				        rs1.executeSql("update CptUseLog set resourceid='"+createrid+"' where id in (select MAX(id) from CptUseLog)");
				        rs1.executeSql("select subcompanyid1 from hrmresource where id="+shenqr);
				        rs1.next();
						String subString = rs1.getString(1);
						rs1.executeSql("update cptcapital set usesubcompanyid = "+subString+" where id="+zic);
						
				        
				    }else{
				        para = zic;
				        para +=flag+riq;
				        para +=flag+shenqbm;
				        para +=flag+shenqr;
				        para +=flag+shul;
				        para +=flag+requestid; 
				        para +=flag+"";    
				        para +=flag+"0";  
				        para +=flag+"2";
				        para +=flag+bz;
				        para +=flag+location;
				        para +=flag+"0";
				     
				        baseBean.writeLog("tagtag run action9 :"+ faph );

				        rs1.executeProc("CptUseLogUse_Insert2",para);
				        rs1.next();
				        String rtvalue = rs1.getString(1);    
				        //数量错误
				        if(rtvalue.equals("-1")){
				        	request.getRequestManager().setMessageid("20088");
				        	request.getRequestManager().setMessagecontent(CapitalComInfo.getCapitalname(zic)+" 领用数量不够!");
				        	
				        	return Action.SUCCESS;
				        } else {
				        	rs1.executeSql("update CptUseLog set resourceid='"+createrid+"' where id in (select MAX(id) from CptUseLog)");
				        }
				    }
					
					rs2.executeProc("HrmInfoStatus_UpdateCapital",""+shenqr);
				    CapitalComInfo.removeCapitalCache();
					CptShare.setCptShareByCpt(zic);
				    
				    if(!location.equals("")){
                        rs2.executeSql("update CptCapital set location='"+location+"' where id="+zic);
				    }

				    //更新折旧开始时间
				    String sqltemp="";
				    boolean isoracle="oracle".equalsIgnoreCase( rs1.getDBType());
				    if(!isoracle){
				        sqltemp="update CptCapital set deprestartdate='"+riq+"' where id="+zic+" and (deprestartdate is null or deprestartdate='')";
				    }else{
				        sqltemp="update CptCapital set deprestartdate='"+riq+"' where id="+zic+" and deprestartdate is null";
				    }
				    rs2.executeSql(sqltemp);
				    
				    //更新
				    rs2.executeSql("update cptcapital set fnamark = '"+cwbh+ "',invoice ='"+ fph+"' where id = " + zic);
					baseBean.writeLog("tagtag run action9 :");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				baseBean.writeLog(e.getMessage());
			}
			
			
			return Action.SUCCESS;
		}
	}

}
