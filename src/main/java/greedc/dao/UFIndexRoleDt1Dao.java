package greedc.dao;

import greedc.GreedcUtil;
import greedc.entities.UFIndexRoleDt1;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;

public class UFIndexRoleDt1Dao {

	public List<UFIndexRoleDt1> list() {
		String sql = "SELECT T1.ID, T1.MAINID, T1.GESCORE, T1.LESCORE, T1.YOULPD, T1.RATE, T1.ISPRIOR, T1.GLROLE, T2.SELECTNAME " +
				"FROM UF_INDEXROLE_DT1 T1 INNER JOIN WORKFLOW_SELECTITEM T2 ON T2.SELECTVALUE=T1.YOULPD AND T2.FIELDID=8726 " +
				"ORDER BY T1.ISPRIOR DESC, T1.LESCORE DESC, T1.GESCORE DESC";
		RecordSet rs = new RecordSet();
		List<UFIndexRoleDt1> list = new ArrayList<UFIndexRoleDt1>();
		if (rs.executeQuery(sql)) {
			while (rs.next()) {
				UFIndexRoleDt1 entry = new UFIndexRoleDt1();
				entry.setId(GreedcUtil.getInteger(rs.getString("ID")));
				entry.setMainId(GreedcUtil.getInteger(rs.getString("MAINID")));
				entry.setGescore(GreedcUtil.getInteger(rs.getString("GESCORE")));
				entry.setLescore(GreedcUtil.getInteger(rs.getString("LESCORE")));
				entry.setYoulpd(GreedcUtil.getInteger(rs.getString("YOULPD")));
				entry.setRate(GreedcUtil.getInteger(rs.getString("RATE")));
				entry.setIsPrior(GreedcUtil.getInteger(rs.getString("ISPRIOR")));
				entry.setYoulpdName(rs.getString("SELECTNAME"));
				entry.setGlRole(GreedcUtil.getInteger(rs.getString("GLROLE")));
				list.add(entry);
			}
		}
		return list;
	}
}
