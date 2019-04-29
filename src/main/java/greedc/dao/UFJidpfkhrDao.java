package greedc.dao;

import ecustom.util.CustomUtil;
import weaver.conn.RecordSet;

/**
 * Created by 季度评分被考核人数据访问类 on 2017-6-26.
 */
public class UFJidpfkhrDao {

	/**
	 * 判断被考核人在建模表中是否存在。
	 * @param beikhrId
	 * @return true-存在，false-不存在
	 */
	public boolean checkExistBeikhr(int beikhrId) {
		String sql = "SELECT 1 FROM UF_JIDPFKHR WHERE KAOHR=?";
		RecordSet rs = new RecordSet();
		return rs.executeQuery(sql, beikhrId) && rs.next();
	}

	/**
	 * 根据部门标识获取被考核人人数。
	 * @param deptId	部门标识
	 * @return	被考核人人数
	 */
	public int count(int deptId) {
		String sql = "SELECT COUNT(1) FROM UF_JIDPFKHR WHERE SHUSBM=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, deptId) && rs.next()){
			return rs.getInt(1);
		}
		throw new RuntimeException("根据部门标识获取被考核人人数失败，sql - " + sql + ", " + deptId);
	}
}
