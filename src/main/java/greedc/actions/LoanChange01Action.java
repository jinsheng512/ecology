package greedc.actions;

import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
import ecustom.ecology8.commons.RecordUtil;
import ecustom.ecology8.workflow.entities.WorkflowRequestBase;

public class LoanChange01Action implements Action{

	/**
	 * 解压
	 * 
	 */
	
	private static BaseBean baseBean=new BaseBean();
	private String diywmc;
	private String jieyrq;
	private String quanszm;
	private String diyzt;
	
	@Override
	public String execute(RequestInfo requestInfo) {
		
		baseBean.writeLog("requestInfo:"+ requestInfo.getDetailTableInfo().getDetailTable(0).getRow(0).getCell(0).getName() +"sss"+ requestInfo.getDetailTableInfo().getDetailTable(0).getRow(0).getCell(0).getValue());
		
		Row[] rows = requestInfo.getDetailTableInfo().getDetailTable(0).getRow();
		for (Row row : rows) {
			Cell[] fields = row.getCell();
			for (Cell field : fields) {
				String fieldName = field.getName();
				String fieldValue = field.getValue();
				baseBean.writeLog("detail:"+ "name:"+fieldName+",value:"+fieldValue);
				
				if(fieldName.equalsIgnoreCase("diyzt")){
					diyzt=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("jieyrq")){
					jieyrq=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("quanszm")){
					quanszm=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("diywmc")){
					diywmc=fieldValue;
				} 
				
			}
			RecordUtil.executeUpdate("UPDATE uf_mortgage_dt1 SET diyzt=?,jieyrq=? WHERE quanszm=? and diywmc=? ",
					diyzt, jieyrq,quanszm,diywmc);
			
		
		}
		
		
		
		return Action.SUCCESS;
	}
}
