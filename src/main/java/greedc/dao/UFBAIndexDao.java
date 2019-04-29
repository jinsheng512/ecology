package greedc.dao;

import weaver.conn.RecordSet;
import greedc.entities.UFBAIndex;

public class UFBAIndexDao {

	public UFBAIndex getById(int id) {
		String sql = "SELECT ID,NAME,TYPE,IDESC,SCORE5,SCORE4,SCORE3,SCORE2,SCORE1"
				+ " FROM UF_BAINDEX WHERE ID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, id) && rs.next()) {
			UFBAIndex entry = new UFBAIndex();
			entry.setId(rs.getInt("ID"));
			entry.setName(rs.getString("NAME"));
			entry.setType(rs.getInt("TYPE"));
			entry.setDesc(rs.getString("IDESC"));
			entry.setScore5(rs.getString("SCORE5"));
			entry.setScore4(rs.getString("SCORE4"));
			entry.setScore3(rs.getString("SCORE3"));
			entry.setScore2(rs.getString("SCORE2"));
			entry.setScore1(rs.getString("SCORE1"));
			return entry;
		}
		return null;
	}
}
