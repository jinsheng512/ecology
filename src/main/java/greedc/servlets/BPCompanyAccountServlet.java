package greedc.servlets;

import java.util.List;

import ecustom.ecology8.commons.RecordUtil;
import ecustom.servlets.BaseServlet;
import greedc.budgetplan.entities.CBPCompanyAccount;

public class BPCompanyAccountServlet extends BaseServlet {

	 /**
	 * 获取指定公司已经勾选的科目。
	 * @return
	 */
	public List<CBPCompanyAccount> getCompanyAccount() {
		int companyId = getParameterIntegerCK("companyId");
		String whereSql = "COMPANY=?";
		List<CBPCompanyAccount> companyAccounts = RecordUtil.search(
				CBPCompanyAccount.class, whereSql, companyId);
		return companyAccounts;
	}
	
	
}
