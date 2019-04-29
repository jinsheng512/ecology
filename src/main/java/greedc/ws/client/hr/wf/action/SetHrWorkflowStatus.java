package greedc.ws.client.hr.wf.action;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.client.Client;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class SetHrWorkflowStatus implements Action {

   private static Log log = LogFactory.getLog(SetHrWorkflowStatus.class.getName());
   private static RecordSet rs = new RecordSet();


   public String execute(RequestInfo requestinfo) {
      String src = requestinfo.getRequestManager().getSrc();
      String rid = requestinfo.getRequestid();
      String wfid = requestinfo.getWorkflowid();
      String wfstatus = requestinfo.getRequestManager().getNextNodetype();
      String status = "";
      if("".equals(src)) {
         log.error("状态回写失败，流程审批状态查询异常（src）！workflowid=" + wfid);
         return "0";
      } else {
         if("reject".equals(src)) {
            src = "退回";
         } else if("submit".equals(src)) {
            src = "通过";
         }

         rs.executeSql("select currentnodetype,status from workflow_requestbase where requestid=" + rid);

         while(rs.next()) {
            status = Util.null2String(rs.getString("status"));
         }

         if("".equals(wfstatus)) {
            log.error("状态回写失败，流程状态查询异常！workflowid=" + wfid + ";Nodetype=" + requestinfo.getRequestManager().getNodetype() + ";Nodeid=" + requestinfo.getRequestManager().getNodeid() + ";NextNodeid=" + requestinfo.getRequestManager().getNextNodeid());
            return "0";
         } else {
            String endpoint = "";
            rs.executeSql("select endpoint from uf_greehrsynprop where rownum=1");

            while(rs.next()) {
               endpoint = Util.null2String(rs.getString("endpoint"));
            }

            try {
               Client client = new Client(new URL(endpoint));
               Object[] result = client.invoke("SetFlowStatus", new Object[]{wfid, rid, status + " > " + src, wfstatus, src});
               if(!"".equals(result[0].toString())) {
                  log.error("接口回写结果：" + result[0].toString());
               }

               return "1";
            } catch (MalformedURLException var11) {
               log.error("流程状态回写失败！" + var11);
               return "0";
            } catch (Exception var12) {
               log.error("流程状态回写失败！" + var12);
               return "0";
            }
         }
      }
   }
}
