package ecustom.services;

import ecustom.dao.HrmDepartmentDao;
import ecustom.entities.HrmDepartment;

public class HrmDepartmentService {

	public HrmDepartment getByCode(String code) {
		return new HrmDepartmentDao().getByCode(code);
	}

	public HrmDepartment getById(int id) {
		return new HrmDepartmentDao().getById(id);
	}

}
