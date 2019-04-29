package greedc.services;

import greedc.dao.Form40Dao;

public class Form40Service {

	/**
	 * 检查部门负责人考核流程是否存在。
	 * @param deptId
	 * @param year
	 * @param season
	 * @return
	 */
	public boolean checkExists(int deptId, int year, int season) {
		Form40Dao dao = new Form40Dao();
		return dao.checkExists(deptId, year, season);
	}
}
