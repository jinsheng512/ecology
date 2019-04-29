package greedc.dao;

import greedc.GreedcUtil;
import greedc.entities.PersonIndexDt2;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;

public class PersonIndexDt2Dao extends BaseDao {

	public void save(List<PersonIndexDt2> list, RecordSetTrans rst) throws Exception {
		for (PersonIndexDt2 entry : list) {
			save(entry, rst);
		}
	}
	
	public boolean save(PersonIndexDt2 entry, RecordSetTrans rst) throws Exception {
		String sql = "INSERT INTO UF_PERSONINDEX_DT2(ID, MAINID, XINGWNL, SHUOM, ZIP, SHENDPF, ORDERINDEX) VALUES (?, ?, ?, ?, ?, ?, ?)";
		boolean exeFlag = rst.executeUpdate(sql, entry.getId(), entry.getMainId(), entry.getXingwnl(),
				entry.getShuom(), entry.getZip(), entry.getShendpf(), entry.getOrder());
		entry.setId(super.getCurrId("UF_PERSONINDEX_DT2_ID", rst));
		return exeFlag;
	}

	public List<PersonIndexDt2> getList(int mainId) {
		List<PersonIndexDt2> list = new ArrayList<PersonIndexDt2>();
		String sql = "SELECT ID, MAINID, XINGWNL, ZIP, SHENDPF, SHUOM, ORDERINDEX"
				+ " FROM UF_PERSONINDEX_DT2 WHERE MAINID=? ORDER BY ORDERINDEX";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, mainId)) {
			while (rs.next()) {
				PersonIndexDt2 entry = new PersonIndexDt2();
				entry.setId(GreedcUtil.getInteger(rs.getString("ID")));
				entry.setMainId(GreedcUtil.getInteger(rs.getString("MAINID")));
				entry.setXingwnl(rs.getString("XINGWNL"));
				entry.setZip(GreedcUtil.getInteger(rs.getString("ZIP")));
				entry.setShendpf(GreedcUtil.getInteger(rs.getString("SHENDPF")));
				entry.setShuom(rs.getString("SHUOM"));
				entry.setOrder(GreedcUtil.getInteger(rs.getString("ORDERINDEX")));
				list.add(entry);
			}
		}
		return list;
	}

	public void updateSelfMark(List<PersonIndexDt2> baList, RecordSetTrans rst) throws Exception {
		for (PersonIndexDt2 ba : baList) {
			updateSelfMark(ba, rst);
		}
	}

	public void updateSelfMark(PersonIndexDt2 ba, RecordSetTrans rst) throws Exception {
		String sql = "UPDATE UF_PERSONINDEX_DT2 SET ZIP=? WHERE ID=?";
		rst.executeUpdate(sql, ba.getZip(), ba.getId());
	}

	public void updateManagerMark(PersonIndexDt2 ba, RecordSetTrans rst) throws Exception {
		String sql = "UPDATE UF_PERSONINDEX_DT2 SET SHENDPF=? WHERE ID=?";
		rst.executeUpdate(sql, ba.getShendpf(), ba.getId());
	}
}
