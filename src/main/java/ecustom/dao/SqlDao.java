package ecustom.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weaver.conn.RecordSet;

public class SqlDao {
	public Map<String, String> selectOne(String sql) {
		RecordSet rs = new RecordSet();
		Map<String, String> row = new HashMap<String, String>();
		if (rs.execute(sql) && rs.next()) {
			String[] names = rs.getColumnName();
			for (String name : names) {
				row.put(name, rs.getString(name));
			}
		}
		return row;
	}

	public List<Map<String, String>> selectList(String sql) {
		RecordSet rs = new RecordSet();
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		if (rs.execute(sql)) {
			String[] names = rs.getColumnName();
			while (rs.next()) {
				Map<String, String> row = new HashMap<String, String>();
				for (String name : names) {
					row.put(name, rs.getString(name));
				}
				rows.add(row);
			}
		}
		return rows;
	}
}
