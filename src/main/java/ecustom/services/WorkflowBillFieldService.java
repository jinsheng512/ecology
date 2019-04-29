package ecustom.services;

import java.util.List;

import ecustom.dao.WorkflowBillFieldDao;
import ecustom.entities.WorkflowBillField;

public class WorkflowBillFieldService {

	public List<WorkflowBillField> getByFormId(int formId) {
		WorkflowBillFieldDao dao = new WorkflowBillFieldDao();
		return dao.getByFormId(formId);
	}

}
