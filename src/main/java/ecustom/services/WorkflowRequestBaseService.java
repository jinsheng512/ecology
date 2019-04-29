package ecustom.services;

import java.util.Map;

import ecustom.dao.WorkflowRequestBaseDao;

public class WorkflowRequestBaseService {

	public Map<String, Object> getByRequestId(int requestId) {
		WorkflowRequestBaseDao dao = new WorkflowRequestBaseDao();
		return dao.getByRequestId(requestId);
	}
}
