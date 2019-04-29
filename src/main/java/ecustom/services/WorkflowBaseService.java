package ecustom.services;

import ecustom.dao.WorkflowBaseDao;

public class WorkflowBaseService {

	public int[] getDocCategoryById(int id) {
		WorkflowBaseDao dao = new WorkflowBaseDao();
		String idStr = dao.getDocCategoryById(id);
		if (idStr != null && idStr.trim().length() > 0) {
			String[] idStrs = idStr.split(",");
			int [] ids = new int[idStrs.length];
			for (int i = 0; i < idStrs.length; i++) {
				ids[i] = Integer.parseInt(idStrs[i]);
			}
			return ids;
		}
		return null;
	}
}
