package greedc.dao;

import ecustom.util.CustomUtil;
import greedc.entities.Form59;
import weaver.conn.RecordSetTrans;

/**
 * 项目付款申请单
 * @author William
 */
public class Form59Dao {

	private RecordSetTrans rst = null;

	public Form59Dao(RecordSetTrans rst) {
		this.rst = rst;
	}
	
	public boolean updateFormByReqId(Form59 entity) throws Exception {
		String fields = "";
		if (entity.getGongcfkxx() != null) {
			fields += ", GONGCFKXX=" + entity.getGongcfkxx();
		}
		if (CustomUtil.isBlank(fields)) {
			return true;
		}
		String sql = "UPDATE FORMTABLE_MAIN_59 SET " + fields.substring(2) + " WHERE REQUESTID=" + entity.getRequestId();
		return rst.execute(sql);
	}
}
