package greedc.servlets;

import ecustom.ecology8.commons.RecordUtil;
import ecustom.servlets.BaseServlet;
import greedc.budgetplan.entities.UFBPConfig;

public class BPConfigServlet extends BaseServlet {

	public UFBPConfig getConfig() {
		UFBPConfig config = RecordUtil.find(UFBPConfig.class, "ISOPEN = ?", 1);
		return config;
	}
}
