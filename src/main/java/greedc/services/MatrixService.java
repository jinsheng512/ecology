package greedc.services;

import greedc.dao.BaseDao;
import weaver.file.Prop;

import java.util.List;

/**
 * 矩阵服务类。
 * @author William
 */
public class MatrixService {

	/**
	 * 根据评分人获取部门列表。
	 * @return
	 */
	public List<Integer> listDeptIdsByPingfr(int pingfr) {
		String table = Prop.getPropValue("ecustom", "jdpf.table.pingfr");
		BaseDao dao = new BaseDao();
		List<Integer> deptIds = dao.getIntList("SELECT DEPT FROM " + table + " WHERE PINGFR=?", pingfr + "");
		return deptIds;
	}
	
	public int getPingfrByDept(int dept) {
		String table = Prop.getPropValue("ecustom", "jdpf.table.pingfr");
		BaseDao dao = new BaseDao();
		int userId = dao.getInt("SELECT PINGFR FROM " + table + " WHERE DEPT=?", dept);
		return userId;
	}
}
