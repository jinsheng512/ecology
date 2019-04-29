package weaver.cpt.util;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.schedule.BaseCronJob;

/**
 * @author wangxp 同步资产使用分部门
 * 
 */
public class CptBranchDepartmentSyn extends BaseCronJob {
	private RecordSet rs1 = new RecordSet();
	private static BaseBean baseBean=new BaseBean();


	public void execute() {
		
		baseBean.writeLog("**************进入*********************");
		String sql1 = " select a.id,a.mark,a.name,a.blongdepartment,a.blongsubcompany,a.resourceid,a.departmentid,a.usesubcompanyid,b.id,b.subcompanyid1"
                        +"from cptcapital a,hrmdepartment b "
                        +" where a.isdata='2' and a.departmentid=b.id and a.usesubcompanyid<>b.subcompanyid1 ";	
		rs1.executeSql(sql1);	
		
		boolean sqlFlag = rs1.executeSql(sql1);
		if(!sqlFlag){
			try {
				throw new Exception("sql出错，CptBranchDepartmentSyn");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			baseBean.writeLog("**************进行中*********************");
			syn();
		}
		
	   
	}  
	
	public void syn() {
		String sql2 = " update cptcapital set usesubcompanyid=(select subcompanyid1 from hrmdepartment where hrmdepartment.id= cptcapital.departmentid and cptcapital.usesubcompanyid<>hrmdepartment.subcompanyid1)where id in (select a.id from cptcapital a,hrmdepartment b where a.isdata='2' and a.departmentid=b.id and a.usesubcompanyid<>b.subcompanyid1 )";	
		rs1.executeSql(sql2);
		baseBean.writeLog("**************完成*********************");
	}

}
