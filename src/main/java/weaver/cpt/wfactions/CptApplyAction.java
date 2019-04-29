package weaver.cpt.wfactions;


import java.math.BigDecimal;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

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
import weaver.system.code.CodeBuild;

/**
 * 
 * @author crazyDream
 * @createdDate 2014-11-7
 * @function : apply action
 *
 */
public class CptApplyAction implements Action,Runnable {
	private static BaseBean baseBean=new BaseBean();
	private static Object lock=new  Object();
	private CptWfUtil cptWfUtil=new CptWfUtil();
	private RequestInfo request=null;
	private JSONObject wfObject=null;
	
	public String execute(RequestInfo request) {
		this.request=request;
		
		try {
			wfObject=cptWfUtil.getCptwfInfo(request.getWorkflowid());
			
			if((wfObject.getInt("zczltype")!=wfObject.getInt("sltype"))||"".equals( wfObject.getString("zczlname"))||"".equals(wfObject.getString("slname")) ){//配置不对,直接退出
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
			
			CapitalAssortmentComInfo CapitalAssortmentComInfo=new CapitalAssortmentComInfo();
			CptShare CptShare=new CptShare();
			CapitalComInfo CapitalComInfo=null;
			DepartmentComInfo DepartmentComInfo=null;
			ResourceComInfo resourceComInfo=null;
			try {
				CapitalComInfo = new CapitalComInfo();
				DepartmentComInfo = new DepartmentComInfo();
				resourceComInfo=new ResourceComInfo();
				
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
				String ggxh="";//规格型号
				String jg="";//价格
				String caigdbh="";//采购单编号
				String usesubcompanyid= "";//使用分部
				
				String gongysxz="";   //供应商选择
				
				
				//暂存表单的数据
				JSONArray jsonArray=new JSONArray();
				
				
				
				
				Property[] properties = request.getMainTableInfo().getProperty();// 获取表单主字段信息
				for (int i = 0; i < properties.length; i++) {// 主表数据
					String name = properties[i].getName();//字段名
					String value = Util.null2String(properties[i].getValue());//值
					baseBean.writeLog("mian:"+ "name:"+name+",value:"+value);
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
					}else if (wfObject.getInt("zczltype")==0 && name.equalsIgnoreCase(wfObject.getString("zczlname"))) {
						zczl=value;
					}else if (wfObject.getInt("ggxhtype")==0 && name.equalsIgnoreCase(wfObject.getString("ggxhname"))) {
						ggxh=value;
					}else if (wfObject.getInt("jgtype")==0 && name.equalsIgnoreCase(wfObject.getString("jgname"))) {
						jg=value;
					}else if (name.equalsIgnoreCase("bianh")) {
						caigdbh=value;
						baseBean.writeLog("tagtag run action2 :"+getClass()+",caigdbh:"+caigdbh);
					}else if (name.equalsIgnoreCase("shusgs")) {
						usesubcompanyid=value;
						baseBean.writeLog("tagtag run a :"+getClass()+",shuosgs:"+usesubcompanyid);
					}
					
					
					
					
				}
				if(wfObject.getInt("zczltype")==0 && wfObject.getInt("sltype")==0){//主表
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("sqr", shenqr);
					jsonObject.put("sqbm", shenqbm);
					jsonObject.put("zc", zc);
					jsonObject.put("sl", sl);
					jsonObject.put("rq", shenqrq);
					jsonObject.put("cfdd", cfdd);
					jsonObject.put("bz", bz);
					jsonObject.put("zczl", zczl);
					jsonObject.put("ggxh", ggxh);
					jsonObject.put("jg", jg);
					jsonObject.put("caigdbh", caigdbh);
					jsonObject.put("usesubcompanyid", usesubcompanyid);
					baseBean.writeLog("tagtag run action3 :"+getClass()+",caigdbh:"+caigdbh);
					
					jsonArray.put(jsonObject);
					
				}else if((wfObject.getInt("zczltype")==1 && wfObject.getInt("sltype")==1)
						||(wfObject.getInt("zczltype")==2 && wfObject.getInt("sltype")==2)
						||(wfObject.getInt("zczltype")==3 && wfObject.getInt("sltype")==3)
						||(wfObject.getInt("zczltype")==4 && wfObject.getInt("sltype")==4)){//明细表
					DetailTable[] detailtable = request.getDetailTableInfo().getDetailTable();// 获取明细表
					int dtnum=(wfObject.getInt("zczltype")-1);
					if (detailtable.length > dtnum) {
						baseBean.writeLog("tagtag run action4 :"+getClass()+",requestid:"+request.getRequestid());
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
								baseBean.writeLog("key-value:"+getClass()+",name:"+name+",value:"+value);
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
								}else if (name.equalsIgnoreCase(wfObject.getString("zczlname"))) {
									zczl=value;
								}else if (name.equalsIgnoreCase(wfObject.getString("ggxhname"))) {
									ggxh=value;
								}else if (name.equalsIgnoreCase(wfObject.getString("jgname"))) {
									jg=value;
								}else if (name.equalsIgnoreCase("caigdbh")) {
//									caigdbh=value;
									baseBean.writeLog("tagtag run action5 :"+getClass()+",caigdbh:"+caigdbh);
								}else if (name.equalsIgnoreCase("gongysxz")) {
									gongysxz=value;
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
							jsonObject.put("zczl", zczl);
							jsonObject.put("ggxh", ggxh);
							jsonObject.put("jg", jg);
							jsonObject.put("caigdbh", caigdbh);
							jsonObject.put("usesubcompanyid", usesubcompanyid);
							
							jsonObject.put("gongysxz", gongysxz);
							
							jsonArray.put(jsonObject);
						}
					}
				}
				
				//开始执行业务逻辑
				RecordSet RecordSetInner=new RecordSet();
				RecordSet temprs=new RecordSet();
				RecordSet rs=new RecordSet();
				RecordSet RecordSet1=new RecordSet();
				CodeBuild CodeBuild=new CodeBuild();
				String para1 = "";
				String resourceid = "0";		//申购人
				String stateid  = "1";

				String capitalid = "";
				String tempmark = "";
				String isinner = "";
				String startdate = "";
				String enddate = "";
				String deprestartdate = "";
				String depreenddate = "";
				String manudate = "";
				//String location = "";
				String num = "";
				String tempstr = "";
				String para = "";
				String sptcount = "";
				String rltid = "";
				String relatefee = "";//流转相关金额
				String capitalgroupid = "";
				String selectdate ="";//购置日期
				String counttype = "";
				String capitaltypeid = "";//资产类型
				String blongsubcompany = "";//所属分部
				String blongdepartment = "";//所属部门
				String cgdbh;
				int suosgs;
//				String gongysxz;

				ArrayList ids = new ArrayList();
				
				
				for(int ii=0;ii<jsonArray.length();ii++){
					JSONObject jsonObject=(JSONObject)jsonArray.get(ii);
					baseBean.writeLog("tagtag run action6 :"+getClass()+",jsonArray.length():"+jsonArray.length());
					char flag=Util.getSeparator();
					
					String ziczl=jsonObject.getString("zczl");
					String zic=jsonObject.getString("zc");
					String danj=jsonObject.getString("jg");
					String shul=jsonObject.getString("sl");
					String riq=jsonObject.getString("rq");
					selectdate=riq;
					shenqbm=jsonObject.getString("sqbm");
					shenqr=jsonObject.getString("sqr");
					cgdbh=jsonObject.getString("caigdbh");
					suosgs=jsonObject.getInt("usesubcompanyid");
					String location=jsonObject.getString("cfdd");
					String capitalspec=jsonObject.getString("ggxh");
					bz=jsonObject.getString("bz");
					gongysxz=jsonObject.getString("gongysxz");
					blongsubcompany=DepartmentComInfo.getSubcompanyid1(shenqbm);
					
					if(Util.getIntValue(ziczl,0)<=0||Util.getDoubleValue(shul,0.0)<=0){
						continue;
					}
					
					
					//=======================down===================

					//从从表中获得
					
					
						capitalid = ziczl;
						num = shul;
					    double innum = Util.getDoubleValue(num);
					    
					    
					    BigDecimal inprice = new BigDecimal(""+Util.getDoubleValue( danj,0.0));
						selectdate=riq;

						relatefee = inprice.multiply(new BigDecimal(num)).toString();
						
						RecordSetInner.executeProc("CptCapital_SelectByID",capitalid);
					    if(RecordSetInner.next()){
					    	tempmark = RecordSetInner.getString("mark");
					    	sptcount = RecordSetInner.getString("sptcount");
					    	capitalgroupid = RecordSetInner.getString("capitalgroupid");
							capitaltypeid = RecordSetInner.getString("capitaltypeid");
					    }

					    //判断是否固资或低耗1:固资2:低耗
					    String tempstr2 = "2,3,4,5,6,7,8,9";
					    String rootgroupid = capitalgroupid;
					    while(true){
							if((CapitalAssortmentComInfo.getSupAssortmentId(rootgroupid)).equals("0")){
								break;
							}
							rootgroupid = CapitalAssortmentComInfo.getSupAssortmentId(rootgroupid);
						}
						
					    if(inprice.compareTo(new BigDecimal("2000"))==1){   //单独核算的资产(固资或低耗)
					        counttype = "1";
					    }else{
					        counttype = "2";
					    }


						blongsubcompany = DepartmentComInfo.getSubcompanyid1(shenqbm);
						blongdepartment = shenqbm;
						
						
						//非单独核算的资产编号
						RecordSetInner.executeProc("CptCapital_SelectByDataType",capitalid+separator+shenqbm);
					    if(!sptcount.equals("1") && RecordSetInner.next()){
					        tempmark = RecordSetInner.getString("mark");
					    }else if(!sptcount.equals("1")){
					    	tempmark = CodeBuild.getCurrentCapitalCode(DepartmentComInfo.getSubcompanyid1(shenqbm),shenqbm,capitalgroupid,capitaltypeid,selectdate,riq,capitalid);
					    }
						
						//如果是非单独核算并且部门有此资产那么编号不变
					    baseBean.writeLog("tagtag run action7 :"+getClass()+",requestid:"+request.getRequestid());
						para = riq;//入库日期
						para +=separator+"";//流转至部门
						para +=separator+resourceid; //流转至人
						para +=separator+shenqr; //入库人
						para +=separator+num; //流转数量
						para +=separator+location;
						para +=separator+requestid;
						para +=separator+"";//相关公司(入库无)
						para +=separator+relatefee;//相关金额
						para +=separator+stateid;//流转后的状态(使用或库存)
						para +=separator+bz;//流转原因(暂空)
						para +=separator+tempmark;//自动生成的资产编号
						para +=separator+capitalid;//datetype
						para +=separator+startdate;
						para +=separator+enddate;
						para +=separator+deprestartdate;
						para +=separator+depreenddate;
						para +=separator+manudate;
						para += separator+shenqr;
						para += separator+currentdate;
						para += separator+currenttime;

						//复制卡片
					    if(sptcount.equals("1")){
					        //单独核算
					        //复制一项
						    for (int i=0;i<(int)Util.getDoubleValue(shul, 0.0);i++) {
						    	
						    	//单独核算获得资产编号
						    	 baseBean.writeLog("33333333333 :"+getClass()+",shul:"+shul);
						    	baseBean.writeLog("tagtag run action8 :"+getClass()+",requestid:"+request.getRequestid());
								tempmark =CodeBuild.getCurrentCapitalCode(DepartmentComInfo.getSubcompanyid1(shenqbm),shenqbm,capitalgroupid,capitaltypeid,selectdate,riq,capitalid);
								
						    	para = riq;//入库日期
								para +=separator+"";//流转至部门
								para +=separator+resourceid; //流转至人
								para +=separator+shenqr; //入库人
								para +=separator+"1"; //流转数量
								para +=separator+location;
								para +=separator+requestid;
								para +=separator+"";//相关公司(入库无)
								para +=separator+relatefee;//相关金额
								para +=separator+stateid;//流转后的状态(使用或库存)
								para +=separator+bz;//流转原因(暂空)
								para +=separator+tempmark;//自动生成的资产编号
								para +=separator+capitalid;//datetype
								para +=separator+startdate;
								para +=separator+enddate;
								para +=separator+deprestartdate;
								para +=separator+depreenddate;
								para +=separator+manudate;
								para += separator+shenqr;
								para += separator+currentdate;
								para += separator+currenttime;
						    	
						    	
						        para1 =capitalid;
						        para1 +=separator+"";
						        para1 +=separator+""+inprice;
						        para1 +=separator+capitalspec;
						        para1 +=separator+location;
						        para1 +=separator+"";
						        para1 +=separator+riq;//入库日期
						        para1 +=separator+selectdate;//购置日期
//						        para1 +=separator+""+cgdbh;
//					            para1 +=separator+""+suosgs;
	
						        RecordSetInner.executeProc("CptCapital_Duplicate",para1);
						        RecordSetInner.next();
						        rltid =RecordSetInner.getString(1);
	
						        para = rltid+separator+para;
						        para += separator+""+inprice;
						        para += separator+"";
						        para += separator+counttype;
						        para += separator+isinner;
						        //更新信息,加入入库信息
						        RecordSetInner.executeProc("CptUseLogInStock_Insert",para);
						        
						        RecordSetInner.executeSql("update CptUseLog set resourceid='"+createrid+"' where id in (select MAX(id) from CptUseLog)");
	
						        RecordSetInner.executeSql("update cptcapital set olddepartment = " + shenqbm + ",blongsubcompany='"+ blongsubcompany +"', blongdepartment='"+ blongdepartment +"',contractno='"+""+"' where id = " + rltid);
								
								String sqlstr = "select * from cptcapitalparts where cptid = " + capitalid;
								
								RecordSetInner.executeSql("update cptcapital set caigdbh = '"+cgdbh+ "',usesubcompanyid='"+ suosgs+"',customerid='"+ gongysxz+"', rukfs='0' where id = " + rltid);

								  baseBean.writeLog("33333333333 :"+getClass()+",rltid:"+rltid);
							
								//new BaseBean().writeLog(sqlstr);
								temprs.executeSql(sqlstr);
								while(temprs.next()){
								  sqlstr = "insert into cptcapitalparts (cptid,partsname,partsspec,partssum,partsweight,partssize) select " +rltid+",partsname,partsspec,partssum,partsweight,partssize from cptcapitalparts where id = " + temprs.getString("id");
								  rs.executeSql(sqlstr);
								  //new BaseBean().writeLog(sqlstr);
								  baseBean.writeLog("tagtag run action9 :"+getClass()+",requestid:"+request.getRequestid());
								}
								sqlstr = "select * from cptcapitalequipment where cptid = " + capitalid;
								temprs.executeSql(sqlstr);
								//new BaseBean().writeLog(sqlstr);
								while(temprs.next()){
								  sqlstr = "insert into cptcapitalequipment (cptid,equipmentname,equipmentspec,equipmentsum,equipmentpower,equipmentvoltage) select "+rltid+",equipmentname,equipmentspec,equipmentsum,equipmentpower,equipmentvoltage from cptcapitalequipment where id = " + temprs.getString("id");
								  rs.executeSql(sqlstr);
								  baseBean.writeLog("tagtag run action10 :"+getClass()+",requestid:"+request.getRequestid());
								  //new BaseBean().writeLog(sqlstr);
								}
								
								
						        //给资产加上权限未经测试
						        String ProcPara ="";
						        String sharetype="";
						        String seclevel="";
						        String rolelevel="";
						        String sharelevel= "";
						        String userid= "";
						        String sharedepartmentid="";
						        String roleid= "";
						        String foralluser= "";
						        String subcompanyid="";
	
						        //判断资产的跟类rootgroupid的权限
						        RecordSetInner.executeSql("select * from CptAssortmentShare where assortmentid="+rootgroupid);
						        while (RecordSetInner.next()){
						            sharetype= RecordSetInner.getString("sharetype");
						            seclevel= RecordSetInner.getString("seclevel");
						            rolelevel= RecordSetInner.getString("rolelevel");
						            sharelevel= RecordSetInner.getString("sharelevel");
						            userid= RecordSetInner.getString("userid");
						            sharedepartmentid= RecordSetInner.getString("departmentid");
						            roleid= RecordSetInner.getString("roleid");
						            foralluser= RecordSetInner.getString("foralluser");
						            subcompanyid=RecordSetInner.getString("subcompanyid");
						            
						            ProcPara = rltid;
						            ProcPara += separator+sharetype;
						            ProcPara += separator+seclevel;
						            ProcPara += separator+rolelevel;
						            ProcPara += separator+sharelevel;
						            ProcPara += separator+userid;
						            ProcPara += separator+sharedepartmentid;
						            ProcPara += separator+roleid;
						            ProcPara += separator+foralluser;
						            ProcPara += separator+subcompanyid;
						            ProcPara += separator+rootgroupid;
	
						            RecordSet1.executeProc("CptShareInfo_Insert_dft",ProcPara);//把资产加入到CptCapitalShareInfo表里
						            baseBean.writeLog("tagtag run action 11:"+getClass()+",requestid:"+request.getRequestid());
						        }
						        CptShare.setCptShareByCpt(rltid);//更新detail表
	
						        ids.add(rltid);
						        
						    }
					    }else{
					        //非单独核算
					        RecordSetInner.executeProc("CptCapital_SelectByDataType",capitalid+separator+shenqbm);
					        if(RecordSetInner.next()){
					            //该部门已有该资产
					            //费用平均
					            rltid = RecordSetInner.getString("id");
					            BigDecimal oldprice = new BigDecimal(RecordSetInner.getString("startprice"));
					            BigDecimal oldnum   = new BigDecimal(RecordSetInner.getString("capitalnum"));
					            inprice = inprice.multiply(new BigDecimal(num));
					            inprice = inprice.add(oldprice.multiply(oldnum));
					            inprice = inprice.divide(oldnum.add(new BigDecimal(num)),2,BigDecimal.ROUND_UP);
					            //inprice = (oldprice*oldnum+inprice*Util.getDoubleValue(num))/(oldnum+innum);

					            para = rltid+separator+para;
					            para += separator+""+inprice;
					            para += separator+"";
					            para += separator+counttype;
					            para += separator+isinner;

					            //更新信息,加入入库信息
					            RecordSetInner.executeProc("CptUseLogInStock_Insert",para);
					            
					            RecordSetInner.executeSql("update CptUseLog set resourceid='"+createrid+"' where id in (select MAX(id) from CptUseLog)");

					            //修改资产卡片的参考价格为入库价格
					            para1 =rltid;
					            para1 +=separator+""+inprice;
					            para1 +=separator+capitalspec;
					            para1 +=separator+"";
					            para1 +=separator+location;
					            para1 +=separator+"";
					            para1 +=separator+riq;
//					            para1 +=separator+""+cgdbh;
//					            para1 +=separator+""+suosgs;
					            
					            RecordSetInner.executeSql("update cptcapital set caigdbh = '"+cgdbh+ "',usesubcompanyid='"+ suosgs+"',customerid='"+ gongysxz+"', rukfs='0' where id = " + rltid);
					            
					            baseBean.writeLog("tagtag run action 111:"+getClass()+",requestid:"+request.getRequestid()+ "_para1="+ para1+ "_cgdbh="+cgdbh + "_suosgs="+suosgs+ "_inprice="+inprice+ "_capitalspec="+capitalspec+ "_location="+location + "_rltid="+rltid+ "_riq="+riq);
					            RecordSetInner.executeProc("CptCapital_UpdatePrice",para1);  
					            baseBean.writeLog("tagtag run action 12:"+getClass()+",requestid:"+request.getRequestid()+ "_"+ para1+ "_"+cgdbh + "_"+suosgs);
					        }else{
					            //该部门没有该资产
					            //复制一项
							    para1 = capitalid;
							    para1 += separator + "";
							    para1 += separator + "" + inprice;
							    para1 += separator + capitalspec;
							    para1 += separator + location;
							    para1 += separator + "";
							    para1 += separator + riq;// 入库日期
							    para1 += separator + selectdate;// 购置日期
//							    para1 += separator + "" + cgdbh;
//							    para1 += separator + "" + suosgs;

					            RecordSetInner.executeProc("CptCapital_Duplicate",para1);
					            RecordSetInner.next();
					            rltid =RecordSetInner.getString(1);

					            para = rltid+separator+para;
					            para += separator+""+inprice;
					            para += separator+"";
					            para += separator+counttype;
					            para += separator+isinner;
					            
					            //更新入库方式，1退库资产
					            RecordSetInner.executeSql("update cptcapital set caigdbh = '"+cgdbh+ "',usesubcompanyid='"+ suosgs+"',customerid='"+ gongysxz+"' , rukfs='0' where id = " + rltid);
					            baseBean.writeLog("tagtag run action 20:"+getClass()+",requestid:"+request.getRequestid()+ "_"+ para1+ "_"+cgdbh + "_"+suosgs+ "_"+rltid);

					            //更新信息,加入入库信息
					            RecordSetInner.executeProc("CptUseLogInStock_Insert",para);
					            
					            RecordSetInner.executeSql("update CptUseLog set resourceid='"+createrid+"' where id in (select MAX(id) from CptUseLog)");

					            RecordSetInner.executeSql("update cptcapital set olddepartment = " + shenqbm + ",blongsubcompany='"+ blongsubcompany +"', blongdepartment='"+ blongdepartment +"',contractno='"+""+"' ,capitalnum='"+innum+"'  where id = " + rltid);

								String sqlstr = "select * from cptcapitalparts where cptid = " + capitalid;
								
								//new BaseBean().writeLog(sqlstr);
								temprs.executeSql(sqlstr);
								while(temprs.next()){
								  sqlstr = "insert into cptcapitalparts (cptid,partsname,partsspec,partssum,partsweight,partssize) select " +rltid+",partsname,partsspec,partssum,partsweight,partssize from cptcapitalparts where id = " + temprs.getString("id");
								  rs.executeSql(sqlstr);
								  //new BaseBean().writeLog(sqlstr);
								  baseBean.writeLog("tagtag run action 13:"+getClass()+",requestid:"+request.getRequestid());
								}
								sqlstr = "select * from cptcapitalequipment where cptid = " + capitalid;
								temprs.executeSql(sqlstr);
								//new BaseBean().writeLog(sqlstr);
								while(temprs.next()){
								  sqlstr = "insert into cptcapitalequipment (cptid,equipmentname,equipmentspec,equipmentsum,equipmentpower,equipmentvoltage) select "+rltid+",equipmentname,equipmentspec,equipmentsum,equipmentpower,equipmentvoltage from cptcapitalequipment where id = " + temprs.getString("id");
								  rs.executeSql(sqlstr);
								  //new BaseBean().writeLog(sqlstr);
								  baseBean.writeLog("tagtag run action 14:"+getClass()+",requestid:"+request.getRequestid());
								}

					            //给资产加上权限未经测试
					            String ProcPara ="";
					            String sharetype="";
					            String seclevel="";
					            String rolelevel="";
					            String sharelevel= "";
					            String userid= "";
					            String sharedepartmentid="";
					            String roleid= "";
					            String foralluser= "";
					            String subcompanyid= "";
					            //判断资产的跟类rootgroupid的权限
					            RecordSetInner.executeSql("select * from CptAssortmentShare where assortmentid="+rootgroupid);
					            while (RecordSetInner.next()){
					                sharetype= RecordSetInner.getString("sharetype");
					                seclevel= RecordSetInner.getString("seclevel");
					                rolelevel= RecordSetInner.getString("rolelevel");
					                sharelevel= RecordSetInner.getString("sharelevel");
					                userid= RecordSetInner.getString("userid");
					                sharedepartmentid= RecordSetInner.getString("departmentid");
					                roleid= RecordSetInner.getString("roleid");
					                foralluser= RecordSetInner.getString("foralluser");
					                subcompanyid= RecordSetInner.getString("subcompanyid");

					                ProcPara = rltid;
					                ProcPara += separator+sharetype;
					                ProcPara += separator+seclevel;
					                ProcPara += separator+rolelevel;
					                ProcPara += separator+sharelevel;
					                ProcPara += separator+userid;
					                ProcPara += separator+sharedepartmentid;
					                ProcPara += separator+roleid;
					                ProcPara += separator+foralluser;
					                ProcPara += separator+subcompanyid;
					                ProcPara += separator+rootgroupid;

					                RecordSet1.executeProc("CptShareInfo_Insert_dft",ProcPara);//把资产加入到CptCapitalShareInfo表里                
					                baseBean.writeLog("tagtag run action 15:"+getClass()+",requestid:"+request.getRequestid());
					            }
					            CptShare.setCptShareByCpt(rltid);//更新detail表(已经改成插资产默认共享6,7)

					            ids.add(rltid);
					            baseBean.writeLog("tagtag run action 16:"+getClass()+",requestid:"+request.getRequestid());
					        }
					    }

					
				    
				    //===================up=========================
				    
				}
				
				try {
					CapitalComInfo.addCapitalCache(ids);
					baseBean.writeLog("tagtag run action 17:"+getClass()+",requestid:"+request.getRequestid());
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				
			} catch (Exception e) {
				e.printStackTrace();
				baseBean.writeLog(e.getMessage());
			}
			
			
			return Action.SUCCESS;
		}
	}

}
