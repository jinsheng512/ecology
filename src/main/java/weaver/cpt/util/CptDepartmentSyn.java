package weaver.cpt.util;

import weaver.conn.RecordSet;
import weaver.interfaces.schedule.BaseCronJob;

/**
 * @author wangxp 同步资产使用部门
 * 
 */
public class CptDepartmentSyn extends BaseCronJob {
	private RecordSet rs1 = new RecordSet();


	public void execute() {
		
	    DepartmentSyn();
	}  
	
	public void DepartmentSyn() {
		String sql1 = " update cptcapital set departmentid = (select departmentid from hrmresource a where a.id = resourceid) where isdata=2 and resourceid>0 ";	
		rs1.executeSql(sql1);		
	}

}
