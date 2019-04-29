package greedc.dao;

import greedc.entities.DatagridColumn;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;

public class DatagridColumnDao {
	
	public List<DatagridColumn> getColumns(int billId, String fields) {
		String sql = "SELECT SEQNO, BILLID, FTITLE, FFIELD, WIDTH, ALIGN, FORMATTER, FORDER"
				+ " FROM GREEDC_DATAGRID_FIELD WHERE FFIELD IN (%s) AND BILLID=?";
		sql = String.format(sql, fields);
		RecordSet rs = new RecordSet();
		List<DatagridColumn> vos = new ArrayList<DatagridColumn>();
		if (rs.executeQuery(sql, billId)) {
			while (rs.next()) {
				DatagridColumn entry = new DatagridColumn();
				entry.setSeqNo(rs.getInt("SEQNO"));
				entry.setBillId(rs.getInt("BILLID"));
				entry.setTitle(rs.getString("FTITLE"));
				entry.setField(rs.getString("FFIELD"));
				entry.setWidth(rs.getInt("WIDTH"));
				entry.setAlign(rs.getString("ALIGN"));
				entry.setFormatter(rs.getString("FORMATTER"));
				entry.setShowOrder(rs.getDouble("FORDER"));
				vos.add(entry);
			}
		}
		
		return vos;
	}
}
