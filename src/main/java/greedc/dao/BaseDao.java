package greedc.dao;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;

public class BaseDao {
	
	public Integer getCurrId(String seqName, RecordSetTrans rst) throws Exception {
		String sql = "SELECT " + seqName + ".CURRVAL FROM DUAL";
		rst.executeQuery(sql);
		rst.next();
		return rst.getInt(1);
	}
	
	/**
	 * 根据查询语句，将查询结果的第一列转换成Integer列表。
	 * @param sql
	 * @param wheres
	 * @return
	 */
	public List<Integer> getIntList(String sql, Object... wheres) {
		RecordSet rs = new RecordSet();
		List<Integer> ints = new ArrayList<Integer>();
		if (rs.executeQuery(sql, wheres)) {
			while (rs.next()) {
				ints.add(rs.getInt(1));
			}
		}
		return ints;
	}
	
	/**
	 * 根据查询语句，将查询结果的第一列转换成Integer列表。
	 * @param sql
	 * @param wheres
	 * @return
	 */
	public int getInt(String sql, Object... wheres) {
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, wheres) && rs.next()) {
			return rs.getInt(1);
		}
		return -1;
	}
}
