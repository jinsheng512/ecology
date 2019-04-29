package ecustom.webservice.server.qibei;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import weaver.conn.RecordSet;
import weaver.file.Prop;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.webservices.WorkflowDetailTableInfo;
import weaver.workflow.webservices.WorkflowMainTableInfo;
import weaver.workflow.webservices.WorkflowRequestInfo;
import weaver.workflow.webservices.WorkflowRequestTableField;
import weaver.workflow.webservices.WorkflowRequestTableRecord;
import weaver.workflow.webservices.WorkflowServiceImpl;
import ecustom.Log;
import ecustom.dao.WorkflowBaseDao;
import ecustom.entities.EcstWorkflowBase;
import ecustom.entities.HrmResource;
import ecustom.services.HrmResourceService;
import ecustom.util.CustomUtil;

/**
 * 自定义获取骑呗数据WebService实现接口。
 * @author William
 */
public class QiBeiServiceImpl extends BaseBean implements IQiBeiService{

	private Log log = new Log(QiBeiServiceImpl.class);
	private static BaseBean baseBean=new BaseBean();

	@Override
	public String getQiBeiData(String requestId,String qibdkje, String qibylje, String bencdkje,
			String bencdkjyje,List<DetailBean> detailList) {
		// TODO Auto-generated method stub
		System.out.println("ddd"+ qibdkje+ ","+ qibylje+","+ bencdkje+","+ bencdkjyje+","+ requestId);
		baseBean.writeLog("参数:"+qibdkje+ ","+ qibylje+","+ bencdkje+","+ bencdkjyje+","+ requestId+","+detailList.size());
		
		for (int i = 0; i < detailList.size(); i++) {
			baseBean.writeLog("参数1:" + detailList.get(i).getHetwb());
		}
		
//		String sql = "UPDATE formtable_main_108 SET qibdkje=?,qibylje=?,bencdkje=?,bencdkjyje=? WHERE requestId=?";
//		String sql1 = "UPDATE formtable_main_108_dt1 SET hetwb=? WHERE dingdh=?";
		
		String sql = "UPDATE formtable_main_114 SET qibdkje=?,qibylje=?,bencdkje=?,bencdkjyje=? WHERE requestId=?";
		String sql1 = "UPDATE formtable_main_114_dt1 SET hetwb=? WHERE dingdh=?";
		
		RecordSet rs = new RecordSet();
		
		rs.executeUpdate(sql, qibdkje, qibylje,bencdkje,bencdkjyje,requestId);
		
		for (int i = 0; i < detailList.size(); i++) {
			
			rs.executeUpdate(sql1, detailList.get(i).getHetwb(), detailList.get(i).getDingdh());
			}
		
		 return Action.SUCCESS;
		
		
		
		//骑呗打款金额  qibdkje
		//骑呗预留金额  qibylje
		//本次抵扣金额   bencdkje
		//本次抵扣结余金额  bencdkjyje
		
		
//		return "ddd"+ qibdkje+ ","+ qibylje+","+ bencdkje+","+ bencdkjyje;
	}

	
}
