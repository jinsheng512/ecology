package greedc.services;

import greedc.dao.Form44Dao;
import greedc.entities.Form44;

public class Form44Service {
	
	public Form44 getByRequestId(int requestId) {
		Form44Dao dao = new Form44Dao();
		return dao.getByRequestId(requestId);
	}
}
