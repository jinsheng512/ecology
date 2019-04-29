package greedc.services;

import ecustom.services.BigFieldService;
import ecustom.util.GsonUtil;
import greedc.dao.DatagridColumnDao;
import greedc.entities.DatagridColumn;

import java.util.Collections;
import java.util.List;

public class DatagridColumnService {

	public List<DatagridColumn> getColumns(int billId, String fields) {
		List<DatagridColumn> colList = new DatagridColumnDao().getColumns(-44, fields);
		Collections.sort(colList);
		return colList;
	}
	
	@SuppressWarnings("unchecked")
	public List<DatagridColumn> getForm44EntriesColumns(int requestId) {
		int entryKesId = new Form44Service().getByRequestId(requestId).getEntriesKeySeqNo();
		String fields = new BigFieldService().getValue(entryKesId);
		List<DatagridColumn> colList = GsonUtil.toObjectList(fields, DatagridColumn.class);
		Collections.sort(colList);
		return colList;
	}
}
