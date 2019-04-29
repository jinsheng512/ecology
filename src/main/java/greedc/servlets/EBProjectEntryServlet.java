package greedc.servlets;

import java.util.List;

import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import ecustom.util.GsonUtil;
import greedc.eas.services.EBProjectEntryService;
import greedc.eas.vo.EBProjectEntryVo;

/**
 * 项目付款计划会签单-费用清单。
 * @author William
 */
public class EBProjectEntryServlet extends BaseServlet {

	public Response findByMainSeqNo() {
		int mainSeqNo = CustomUtil.getInt(getParameter("seqNo", true));
		int goux = CustomUtil.getInt(getParameter("goux", true));
		EBProjectEntryService service = new EBProjectEntryService();
		List<EBProjectEntryVo> vos = service.findByMainSeqNo(mainSeqNo, goux);
		return new Response(true, "OK", vos);
	}
	
	public Response updateGoux() {
		String vosStr = getParameter("vos", true);
		List<EBProjectEntryVo> vos = GsonUtil.toObjectList(vosStr, EBProjectEntryVo.class);
		EBProjectEntryService service = new EBProjectEntryService();
		if (vos != null && !vos.isEmpty()) {
			service.updateGoux(vos);
		}
		return new Response(true);
	}
	
	public Response getRejectCountsByRequestId() {
		int requestId = CustomUtil.getInt(getParameter("requestId", true));
		EBProjectEntryService service = new EBProjectEntryService();
		return new Response(true, service.getRejectCount(requestId) + "");
	}
}
