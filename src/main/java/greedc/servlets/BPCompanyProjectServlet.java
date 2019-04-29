package greedc.servlets;

import java.util.List;

import ecustom.ecology8.commons.RecordUtil;
import ecustom.servlets.BaseServlet;
import greedc.budgetplan.entities.UFBPCompanyProject;

public class BPCompanyProjectServlet extends BaseServlet {

	/**
	 * 获取BP03-公司项目。
	 * @return
	 */
	public List<UFBPCompanyProject> getCompanyProject() {
		int companyId = getParameterIntegerCK("companyId");
		String whereSql = "COMPANY=? AND ISOPEN=1 ORDER BY DATAORDER";
		List<UFBPCompanyProject> companyProject = RecordUtil.search(
				UFBPCompanyProject.class, whereSql, companyId);
		return companyProject;
	}
	
	
}
