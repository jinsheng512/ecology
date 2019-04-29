package ecustom.services;

import ecustom.dao.HrmJobTitleDao;
import ecustom.entities.HrmJobTitle;

public class HrmJobTitleService {
	
	public HrmJobTitle getByCode(String code) {
		HrmJobTitleDao dao = new HrmJobTitleDao();
		return dao.getByCode(code);
	}

	public HrmJobTitle getById(int id) {
		HrmJobTitleDao dao = new HrmJobTitleDao();
		return dao.getById(id);
	}
}
