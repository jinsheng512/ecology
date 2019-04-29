package greedc.actions;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
import ecustom.ecology8.commons.RecordUtil;
import ecustom.ecology8.workflow.entities.WorkflowRequestBase;

public class LoanChange04Action implements Action{

	/**
	 * 利息回写
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
	private String lixn;
	private String lixy;
	private String lixje;
	private String beiz;
	private String jkdid;
	
	@Override
	public String execute(RequestInfo requestInfo) {
		
		
		RecordSet RecordSet=new RecordSet();
		
		baseBean.writeLog("requestInfo:"+ requestInfo.getDetailTableInfo().getDetailTable(0).getRow(0).getCell(0).getName() +"sss"+ requestInfo.getDetailTableInfo().getDetailTable(0).getRow(0).getCell(0).getValue());
		
		
	  	Property[] properties = requestInfo.getMainTableInfo().getProperty();// 获取表单主字段信息
		for (int i = 0; i < properties.length; i++) {// 主表数据
			String name = properties[i].getName();//字段名
			String value = Util.null2String(properties[i].getValue());//值
			baseBean.writeLog("mian:"+ "name:"+name+",value:"+value);
			if(name.equalsIgnoreCase("diyhtzt")){
				diyhtzt=value;
			} 
			
		}
		
		Row[] rows = requestInfo.getDetailTableInfo().getDetailTable(0).getRow();
		for (Row row : rows) {
			Cell[] fields = row.getCell();
			for (Cell field : fields) {
				String fieldName = field.getName();
				String fieldValue = field.getValue();
				baseBean.writeLog("detail:"+ "name:"+fieldName+",value:"+fieldValue);
				
				if(fieldName.equalsIgnoreCase("lixn")){
					lixn=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("lixy")){
					lixy=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("lixje")){
					lixje=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("beiz")){
					beiz=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("jkdid")){
					jkdid=fieldValue;
				} 
				
			
				
			}
			
			String insertSql_C = "mainid,qijiann,qijiany,lixje,beiz";
			String insertSql_V = "'"+jkdid+"','"+lixn+"','"+lixy+"','"+lixje+"','"+beiz+"' ";
			
			
			if(!lixn.equals("") || !lixy.equals("") || !lixje.equals("") || !beiz.equals("")){
				
				String sql = "insert into uf_Borrowing_dt2 ("+insertSql_C+") values ("+insertSql_V+")";
				RecordSet.execute(sql);
			}
			
		
		}
		
		
		
		return Action.SUCCESS;
	}
}
