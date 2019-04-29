package greedc.services;

import greedc.dao.RequestBaseDao;

public class RequestService {

	public int getCurrNodeId(int requestId) {
		RequestBaseDao dao = new RequestBaseDao();
		int nodeId = dao.getCurrNodeId(requestId);
		return nodeId;
	}
}
