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
 * @function : back action
 *
 */
public class CptBackAction implements Action,Runnable {
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
			int createrid = request.getRequestManager().getCreater();
			try {
				CapitalComInfo = new CapitalComInfo();
				DepartmentComInfo = new DepartmentComInfo();
				resourceComInfo=new ResourceComInfo();
				
				RecordSet RecordSetM=new RecordSet();
				RecordSet RecordSet=new RecordSet();
				char separator = Util.getSeparator() ;
				String requestid= request.getRequestid();
				String formid=""+ request.getRequestManager().getFormid();
				String currentdate=TimeUtil.getCurrentDateString();
				String currenttime=TimeUtil.getOnlyCurrentTimeString();
				
				
				String shenqr="";//申请人
				String zc="";//申请资产
				String shenqbm="";//申请部门
				String zczl="";//资产资料
				String zicz="";//资产组
				String shenqrq="";//申请日期
				String sl="";//申请资产数量
				String cfdd="";//存放地点
				String bz="";//备注
				
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
					bz=jsonObject.getString("bz");
					
					if(Util.getIntValue(zic,0)<=0){
						continue;
					}
					
					String sql="";
					sql="select sptcount,resourceid,departmentid,costcenterid,stateid from CptCapital where id="+ zic;
					RecordSetM.executeSql(sql);
					RecordSetM.next(); 
					sptcount = RecordSetM.getString("sptcount");

					String resourceid = Util.null2String(RecordSetM.getString("resourceid"));
					String departmentid = Util.null2String(RecordSetM.getString("departmentid"));
					String costcenterid = Util.null2String(RecordSetM.getString("costcenterid"));
					String stateid = Util.null2String(RecordSetM.getString("stateid"));

					//领用人不为空
					String capitalnum = "1";
					if(!"".equals(resourceid) && !"0".equals(resourceid) && stateid.equals("4")){
						capitalnum = "0";
					}

					para = zic;
					para +=separator+riq;
					para +=(separator+""+shenqbm);
					para +=(separator+""+shenqr);
					para +=separator+capitalnum;
					para +=separator+location;
					para +=separator+requestid;
					para +=separator+"";
					para +=separator+"0";
					para +=separator+"1";
					para +=separator+bz;
					para +=separator+"0";
					para +=separator+sptcount;
					RecordSet.executeProc("CptUseLogBack_Insert",para);
					RecordSet.executeSql("update CptUseLog set resourceid='"+createrid+"' where id in (select MAX(id) from CptUseLog)");
				
					if(!"".equals(resourceid) && !"0".equals(resourceid) && stateid.equals("4")) //领用人不为空
						RecordSet.execute("update CptCapital set resourceid = '"+resourceid+"',departmentid = '"+departmentid+"',costcenterid = '"+costcenterid+"',stateid = '2' where id = " + zic);
				
					CptShare.setCptShareByCpt(zic);//更新detail表 

                    if(!location.equals("")){
                        RecordSet.executeSql("update CptCapital set location='"+location+"' where id="+zic);
                    }
                    
                    //更新入库方式，1退库资产
   				 RecordSet.executeSql("update CptCapital set rukfs='1' where id="+zic);
					
				}
				
				
				CapitalComInfo.removeCapitalCache();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				baseBean.writeLog(e.getMessage());
			}
			
			
			return Action.SUCCESS;
		}
	}

}
