package greedc.actions;

import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
import ecustom.ecology8.commons.RecordUtil;
import ecustom.ecology8.workflow.entities.WorkflowRequestBase;

public class LoanChange02Action implements Action{

	/**
	 * 停用
	 * 
	 */
	
	private static BaseBean baseBean=new BaseBean();
	private String diywmc;
	private String jieyrq;
	private String quanszm;
	private String diyzt;
	private String bianghdbfs;
	private String jiekhth;
	private String diyhtzt;
	private String diyhthId;
	private String jkdid;
	
	@Override
	public String execute(RequestInfo requestInfo) {
		
		baseBean.writeLog("requestInfo:"+ requestInfo.getDetailTableInfo().getDetailTable(0).getRow(0).getCell(0).getName() +"sss"+ requestInfo.getDetailTableInfo().getDetailTable(0).getRow(0).getCell(0).getValue());
		
		
	  	Property[] properties = requestInfo.getMainTableInfo().getProperty();// 获取表单主字段信息
		for (int i = 0; i < properties.length; i++) {// 主表数据
			String name = properties[i].getName();//字段名
			String value = Util.null2String(properties[i].getValue());//值
			baseBean.writeLog("mian:"+ "name:"+name+",value:"+value);
			if(name.equalsIgnoreCase("diyhtzt")){
				diyhtzt=value;
			} 
			
			if(name.equalsIgnoreCase("diyhth")){
				diyhthId=value;
			} 
			
		}
		
		RecordUtil.executeUpdate("UPDATE uf_mortgage SET status2=? WHERE id=?  ",
				 diyhtzt,diyhthId);
		
		Row[] rows = requestInfo.getDetailTableInfo().getDetailTable(0).getRow();
		for (Row row : rows) {
			Cell[] fields = row.getCell();
			for (Cell field : fields) {
				String fieldName = field.getName();
				String fieldValue = field.getValue();
				baseBean.writeLog("detail:"+ "name:"+fieldName+",value:"+fieldValue);
				
				if(fieldName.equalsIgnoreCase("bianghdbfs")){
					bianghdbfs=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("jkdid")){
					jkdid=fieldValue;
				} 
				
			
				
				
				
			}
			RecordUtil.executeUpdate("UPDATE uf_Borrowing SET danbfs=? WHERE id=?  ",
					bianghdbfs,jkdid);
			
			
			
		
		}
		
		
		
		return Action.SUCCESS;
	}
}
