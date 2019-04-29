package greedc.services;

import java.util.List;

import greedc.dao.HrmResourceDao;

public class HrmResourceService {

	public List<Integer> getDeptIdsByBelong(int userId) {
		HrmResourceDao dao = new HrmResourceDao();
		return dao.getDeptIdsByBelong(userId);
	}
	
	public int getCountByDept(int deptId) {
		HrmResourceDao dao = new HrmResourceDao();
		return dao.getCountByDept(deptId);
	}
}
