package ecustom.services;

import java.util.List;
import java.util.Map;

import ecustom.dao.SqlDao;

public class SqlService {
	public Map<String, String> selectOne(String sql) {
		SqlDao dao = new SqlDao();
		return dao.selectOne(sql);
	}

	public List<Map<String, String>> selectList(String sql) {
		SqlDao dao = new SqlDao();
		return dao.selectList(sql);
	}
}
