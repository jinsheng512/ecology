package greedc.dao;

import greedc.GreedcUtil;
import greedc.entities.PersonIndexDt1;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;

public class PersonIndexDt1Dao extends BaseDao {

	public List<PersonIndexDt1> getList(int mainId) {
		List<PersonIndexDt1> list = new ArrayList<PersonIndexDt1>();
		String sql = "SELECT ID, MAINID, FENL, YEWMB, GONGZMB, QUANZ, WANCQK, ZIP, SHENDPF, ORDERINDEX"
				+ " FROM UF_PERSONINDEX_DT1 WHERE MAINID=? ORDER BY FENL, ORDERINDEX";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, mainId)) {
			while (rs.next()) {
				PersonIndexDt1 entry = new PersonIndexDt1();
				entry.setId(GreedcUtil.getInteger(rs.getString("ID")));
				entry.setMainId(GreedcUtil.getInteger(rs.getString("MAINID")));
				entry.setFenl(rs.getString("FENL"));
				entry.setYewmb(rs.getString("YEWMB"));
				entry.setGongzmb(rs.getString("GONGZMB"));
				entry.setQuanz(GreedcUtil.getInteger(rs.getString("QUANZ")));
				entry.setWancqk(GreedcUtil.getInteger(rs.getString("WANCQK")));
				entry.setZip(GreedcUtil.getDouble(rs.getString("ZIP")));
				entry.setShendpf(GreedcUtil.getInteger(rs.getString("SHENDPF")));
				entry.setOrder(GreedcUtil.getInteger(rs.getString("ORDERINDEX")));
				list.add(entry);
			}
		}
		return list;
	}

	/**
	 * 保存关键业务。
	 * @param imList
	 * @param rst
	 * @throws Exception 
	 */
	public void saveSelfMark(List<PersonIndexDt1> imList, RecordSetTrans rst) throws Exception {
		for (PersonIndexDt1 im : imList) {
			saveSelfMark(im, rst);
		}
	}
	
	/**
	 * 保存关键业务。
	 * @param imList
	 * @param rst
	 * @throws Exception 
	 */
	public void saveSelfMark(PersonIndexDt1 im, RecordSetTrans rst) throws Exception {
		String sql = "INSERT INTO UF_PERSONINDEX_DT1(MAINID, FENL, YEWMB, GONGZMB, QUANZ, WANCQK, ZIP, SHENDPF, ORDERINDEX)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		rst.executeUpdate(sql, im.getMainId(), im.getFenl(), im.getYewmb(), im.getGongzmb(),
				im.getQuanz(), im.getWancqk(), im.getZip(), im.getShendpf(), im.getOrder());
		im.setId(super.getCurrId("UF_PERSONINDEX_DT1_ID", rst));
	}

	/**
	 * 更新自评分。
	 * @param im
	 * @param rst
	 * @throws Exception
	 */
	public void updateSelfMark(PersonIndexDt1 im, RecordSetTrans rst) throws Exception {
		String sql = "UPDATE UF_PERSONINDEX_DT1 SET MAINID=?, FENL=?, YEWMB=?, GONGZMB=?, QUANZ=?,"
				+ " WANCQK=?, ZIP=?, ORDERINDEX=? WHERE ID=?";
		rst.executeUpdate(sql, im.getMainId(), im.getFenl(), im.getYewmb(), im.getGongzmb(),
				im.getQuanz(), im.getWancqk(), im.getZip(), im.getOrder(), im.getId());
	}

	public void delete(List<Integer> deleteIds, RecordSetTrans rst) throws Exception {
		for (Integer id : deleteIds) {
			delete(id, rst);
		}
	}
	
	public void delete(Integer id, RecordSetTrans rst) throws Exception {
		String sql = "DELETE FROM UF_PERSONINDEX_DT1 WHERE ID=?";
		rst.executeUpdate(sql, id);
	}

	public void updateManagerMark(PersonIndexDt1 im, RecordSetTrans rst) throws Exception {
		String sql = "UPDATE UF_PERSONINDEX_DT1 SET SHENDPF=? WHERE ID=?";
		rst.executeUpdate(sql, im.getShendpf(), im.getId());
	}
}
