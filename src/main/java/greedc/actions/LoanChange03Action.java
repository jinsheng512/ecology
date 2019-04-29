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

public class LoanChange03Action implements Action{

	/**
	 * 还款登记回写
	 * 
	 */
	
	private static BaseBean baseBean=new BaseBean();
	private String benchkje;
	private String huankfs;
	private String beiz;
	private String shijhkrq;
	private String huankuanid;
	private Double total_je =0.00;
	private String mainid;
	private String jiekje;
	@Override
	public String execute(RequestInfo requestInfo) {
		
		baseBean.writeLog("requestInfo:"+ requestInfo.getDetailTableInfo().getDetailTable(0).getRow(0).getCell(0).getName() +"sss"+ requestInfo.getDetailTableInfo().getDetailTable(0).getRow(0).getCell(0).getValue());
		
		
	  	Property[] properties = requestInfo.getMainTableInfo().getProperty();// 获取表单主字段信息
		for (int i = 0; i < properties.length; i++) {// 主表数据
			String name = properties[i].getName();//字段名
			String value = Util.null2String(properties[i].getValue());//值
			baseBean.writeLog("mian:"+ "name:"+name+",value:"+value);
			if(name.equalsIgnoreCase("diyhtzt")){
			} 
			
		}
		
		Row[] rows = requestInfo.getDetailTableInfo().getDetailTable(0).getRow();
		for (Row row : rows) {
			Cell[] fields = row.getCell();
			for (Cell field : fields) {
				String fieldName = field.getName();
				String fieldValue = field.getValue();
				baseBean.writeLog("detail:"+ "name:"+fieldName+",value:"+fieldValue);
				
				if(fieldName.equalsIgnoreCase("benchkje")){
					benchkje=fieldValue;
					if(benchkje.equals("")){
						
						benchkje="0";
					}
					baseBean.writeLog("1111111" + benchkje);
					total_je+=Double.valueOf(benchkje);
				} 
				
				if(fieldName.equalsIgnoreCase("huankfs")){
					huankfs=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("beiz")){
					beiz=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("huankuan")){
					huankuanid=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("huanksj")){
					shijhkrq=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("jkdid")){
					
					mainid=fieldValue;
				} 
				
				if(fieldName.equalsIgnoreCase("jiekje")){
					
					jiekje=fieldValue;
					
                   if(jiekje.equals("")){
						
                	   jiekje="0";
					}
					
				} 
				
				
				
			}
			RecordUtil.executeUpdate("UPDATE uf_Borrowing_dt1 SET huankje=?,huankfs=?,beiz=?,shijhkrq=? WHERE id=?",
					benchkje, huankfs,beiz,shijhkrq,huankuanid);
			
			baseBean.writeLog("total_je:"+total_je+",yue:"+(Double.valueOf(jiekje)));
			
			baseBean.writeLog("total_je:"+total_je+",yue:"+(Double.valueOf(jiekje) - total_je));
			
			RecordUtil.executeUpdate("UPDATE uf_Borrowing SET yikje=?,yue=? WHERE id=?",
					total_je, (Double.valueOf(jiekje) - total_je),mainid);
			
		
		}
		
		
		
		return Action.SUCCESS;
	}
}
