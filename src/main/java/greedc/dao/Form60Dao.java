package greedc.dao;

import greedc.entities.Form60;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;

public class Form60Dao {

	public boolean updateForm(Form60 entity, RecordSetTrans rst) throws Exception {
		String sql = "UPDATE FORMTABLE_MAIN_60 SET BIANGXX=?, CHENGBXX=?, FUKSQXX=?, FUKXX=?,"
				+ " GONGCFKXX=?, GUANLXX=?, KOUKXX=?, WEIYXX=?, HETXX=?, JIANGLXX=? WHERE REQUESTID=?";
		return rst.executeUpdate(sql, entity.getBiangxx(), entity.getChengbxx(), entity.getFuksqxx(),
				entity.getFukxx(), entity.getGongcfkxx(), entity.getGuanlxx(), entity.getKoukxx(),
				entity.getWeiyxx(), entity.getHetxx(), entity.getJianglxx(), entity.getRequestId());
	}

	public Form60 getFormByRequestId(int requestId) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT REQUESTID, BIANGXX, CHENGBXX, FUKSQXX, FUKXX, GUANLXX, KOUKXX, WEIYXX,"
				+ " GONGCFKXX, HETXX, JIANGLXX FROM FORMTABLE_MAIN_60 WHERE REQUESTID=?";
		if (rs.executeQuery(sql, requestId) && rs.next()) {
			Form60 vo = new Form60();
			vo.setRequestId(requestId);
			vo.setBiangxx(rs.getInt("BIANGXX"));
			vo.setChengbxx(rs.getInt("CHENGBXX"));
			vo.setFuksqxx(rs.getInt("FUKSQXX"));
			vo.setFukxx(rs.getInt("FUKXX"));
			vo.setGuanlxx(rs.getInt("GUANLXX"));
			vo.setKoukxx(rs.getInt("KOUKXX"));
			vo.setJianglxx(rs.getInt("JIANGLXX"));
			vo.setWeiyxx(rs.getInt("WEIYXX"));
			vo.setGongcfkxx(rs.getInt("GONGCFKXX"));
			vo.setHetxx(rs.getInt("HETXX"));
			return vo;
		}
		return null;
	}
}
