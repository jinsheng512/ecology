package ecustom.services;

import ecustom.dao.HrmSubCompanyDao;
import ecustom.entities.HrmSubCompany;

public class HrmSubCompanyService {

	public HrmSubCompany getByCode(String code) {
		HrmSubCompanyDao dao = new HrmSubCompanyDao();
		return dao.getByCode(code);
	}
	
	public HrmSubCompany getById(int id) {
		HrmSubCompanyDao dao = new HrmSubCompanyDao();
		return dao.getById(id);
	}

}
