package greedc.services;

import java.util.List;

import greedc.dao.PersonIndexDt1Dao;
import greedc.entities.PersonIndexDt1;

public class PersonIndexDt1Service {
	
	/**
	 * 获取关键业务列表。
	 * @param mainId
	 * @return
	 */
	public List<PersonIndexDt1> getList(int mainId) {
		PersonIndexDt1Dao dao = new PersonIndexDt1Dao();
		return dao.getList(mainId);
	}

}
