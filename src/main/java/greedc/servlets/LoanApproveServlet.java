package greedc.servlets;

import java.util.List;

import org.apache.log4j.Logger;

import weaver.general.BaseBean;
import weaver.soa.workflow.request.RequestService;
import ecustom.ecology8.commons.RecordUtil;
import ecustom.ecology8.hrm.entities.HrmResource;
import ecustom.ecology8.webservice.workflow.WSField;
import ecustom.ecology8.webservice.workflow.WSRequestInfo;
import ecustom.ecology8.webservice.workflow.WSRow;
import ecustom.ecology8.webservice.workflow.WSTable;
import ecustom.ecology8.webservice.workflow.WorkflowServiceXmlImpl;
import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import greedc.budgetplan.entities.LoanFinancing;
import greedc.budgetplan.entities.LoanFinancingDt1;
import greedc.budgetplan.entities.LoanFinancingDt2;

/**
 * 公司数据填报请求服务类。
 * @author William
 */
public class LoanApproveServlet extends BaseServlet {

	private final static Logger log = Logger.getLogger(LoanApproveServlet.class);
	private static BaseBean baseBean=new BaseBean();
	private int requestId;
	
	public  Response createRequest() throws Exception {
		
		int billId = getParameterIntegerCK("billid");
		baseBean.writeLog("*******开始1*********:"+billId);
		
		LoanFinancing loanFinancing = RecordUtil.findById(LoanFinancing.class, billId);
		baseBean.writeLog("*******开始2*********:"+loanFinancing.getJiekdh() +loanFinancing.getStatus());
		
	
		if (loanFinancing.getStatus() != 0 && loanFinancing.getStatus() == 3) {
			throw new RuntimeException("审批流程已经存在，requestId = " + loanFinancing.getRequestId() + ", status = " + loanFinancing.getStatus());
		}
		
		List<LoanFinancingDt1> dt1List = RecordUtil.search(LoanFinancingDt1.class,
				"MAINID=?", loanFinancing.getId());
		
		baseBean.writeLog("*******开始3*********:"+dt1List.size());
		
		List<LoanFinancingDt2> dt2List = RecordUtil.search(LoanFinancingDt2.class,
				"MAINID=?", loanFinancing.getId());
		
		baseBean.writeLog("*******开始4*********:"+dt2List.size());
	

		WSRequestInfo wsRequestInfo = new WSRequestInfo();
		HrmResource creator = RecordUtil.findById(HrmResource.class, 501);
		
		baseBean.writeLog("*******开始5*********:"+creator.getLastName());
		

		//流程主信息
		wsRequestInfo.setCreateTime(CustomUtil.dateFormat("yyyy-MM-dd"));
		wsRequestInfo.setCreatorId(501);
		wsRequestInfo.setIsNextFlow(1);
		wsRequestInfo.setRequestName("融资贷款审批流程-" + creator.getLastName() + "-" + wsRequestInfo.getCreateTime());
		wsRequestInfo.setWorkflowId(1621);
		

		// 设置表信息
		wsRequestInfo.setMainFields(new WSField[] {
				new WSField("beiz", "" + loanFinancing.getBeiz()),
				new WSField("daiklx", loanFinancing.getDaiklx()),
				new WSField("danbfs", "" + loanFinancing.getDanbfs()),
				new WSField("danbht", loanFinancing.getDanbht()),	//填报日期
				new WSField("diy", "" + loanFinancing.getDiy()),	//填报人
				new WSField("fuxfs", loanFinancing.getFuxfs()),	//审批日期
				new WSField("gongsmc", loanFinancing.getGongsmc()),	//审批人
				new WSField("hetll",  "" +loanFinancing.getHetll()),	//填报截止日期
				new WSField("jiekdh", "" + loanFinancing.getJiekdh()),	//状态
				new WSField("jiekll", "" + loanFinancing.getJiekll()),	//上月期初余额
				new WSField("jinrjg", "" + loanFinancing.getJinrjg()),	//本月计划额
				new WSField("kuaijkm", loanFinancing.getKuaijkm()),	//外部经营收入超额原因
				new WSField("lurrq", loanFinancing.getLurrq()),	//筹资收入超额原因
				new WSField("qisrq", loanFinancing.getQisrq()),	//经营性支出超额原因
				new WSField("shouxhth", loanFinancing.getShouxhth()),	//项目投资支出超额原因
				new WSField("xiangm", loanFinancing.getXiangm()),	//筹资支出超额原因
				new WSField("zhongzrq",""+ loanFinancing.getZhongzrq()),	//外部经营收入超额
				new WSField("guwf", ""+ loanFinancing.getGuwf()),	//筹资收入超额
				new WSField("jiekje","" + loanFinancing.getJiekje()),	//经营性支出超额
				new WSField("jiekzcb","" + loanFinancing.getJiekzcb()),	//项目投资支出超额
				new WSField("shouxed" ,"" + loanFinancing.getShouxed()),	//筹资支出超额
				new WSField("yikje" ,"" + loanFinancing.getYikje()),	//筹资支出超额
				new WSField("yue" ,"" + loanFinancing.getYue()),	//筹资支出超额
				new WSField("status", "" + 3),	//0.启用，1停用，2已审批，3.审批中
		});

		//取明细信息
		WSTable[] detailTables = new WSTable[2];
		
		// 明细表1
		detailTables[0] = new WSTable();
		WSRow[] rows = new WSRow[dt1List.size()];
		for ( int i = 0; i< dt1List.size();i++ ) {
			rows[i] = new WSRow();
			WSField[] rowFields = new WSField[] {
					new WSField("beiz",  "" + dt1List.get(i).getBeiz()), // 科目名称
					new WSField("duiyywdj",  "" + dt1List.get(i).getDuiyywdj()),  // 上月计划额
					new WSField("huankfs", "" + dt1List.get(i).getHuankfs()), // 上月发生额
					new WSField("shijhkrq",  "" + dt1List.get(i).getShijhkrq()) ,  // 本月计划额
					new WSField("yujhkrq",  "" + dt1List.get(i).getYujhkrq()), // 差额
					new WSField("huankje",  dt1List.get(i).getHuankje()+""),  // 备注
					new WSField("zhuangt",  dt1List.get(i).getZhuangt()+""),  // 备注
			
					
			};
			rows[i].setFields(rowFields);
		}
		detailTables[0].setRows(rows);
		
		// 明细表2
		detailTables[1] = new WSTable();
		rows = new WSRow[dt2List.size()];
		for ( int i = 0; i< dt2List.size();i++ ) {
			rows[i] = new WSRow();
			WSField[] rowFields = new WSField[] {
					new WSField("beiz",  "" + dt2List.get(i).getBeiz()), // 科目名称
					new WSField("lixje",  "" + dt2List.get(i).getLixje()),  // 上月计划额
					new WSField("qijiann", "" + dt2List.get(i).getQijiann()), // 上月发生额
					new WSField("qijiany",  "" + dt2List.get(i).getQijiany()) ,  // 本月计划额
			};
			rows[i].setFields(rowFields);
		}
		detailTables[1].setRows(rows);
		
		
		
		wsRequestInfo.setDetailTables(detailTables);
	
		baseBean.writeLog("*******开始6*********:"+detailTables.toString());
		
		baseBean.writeLog("*******开始7*********:"+wsRequestInfo.toString());

		WorkflowServiceXmlImpl wfService = new WorkflowServiceXmlImpl();
		RequestService requestService = new RequestService();
		 requestId = Integer.parseInt(requestService.createRequest(wfService.toRequestInfo(wsRequestInfo)));
		log.info("创建流程成功，requestId = " + requestId);
		baseBean.writeLog("*******开始8*********:"+requestId);
		
		if(requestId <= 0 ){
			
			baseBean.writeLog("*******开始9*********:"+requestId);
			throw new Exception("创建流程失败："  + requestId);
		}
		
		RecordUtil.executeUpdate("UPDATE UF_BORROWING SET REQUESTID=? , STATUS=? WHERE ID=?", requestId,3,billId);
	
		return new Response(true, "创建流程成功，RequestId = " + requestId);
	}
	
	
	/**
	 * 数据填报反审核的功能。
	 * @return
	 * @throws Exception 
	 */
	public Response cancelAudit() throws Exception {
		int billId = getParameterIntegerCK("billid");
		
		LoanFinancing loanFinancing = RecordUtil.findById(LoanFinancing.class, billId);
		
		if (loanFinancing  == null) {
			throw new RuntimeException("填报数据不存在，UF_BORROWING.ID = " + billId);
		}
		
		loanFinancing.setStatus(0);	// 设置为未提交
		loanFinancing.setRequestId(null);	// RequestId置空
		
		RecordUtil.updateExact(loanFinancing, billId, "STATUS,REQUESTID");
		
		return new Response(true, "反审核成功！");
	}
	
	
}
