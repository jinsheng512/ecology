package greedc.servlets;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ecustom.easyui.vo.TreeNodeVo;
import ecustom.ecology8.commons.RecordUtil;
import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import greedc.budgetplan.entities.CBPAccountTree;
import greedc.budgetplan.entities.UFBPCompanyAccount;
import greedc.budgetplan.entities.UFBPConfig;
import greedc.budgetplan.entities.UFBPExternalPay;
import greedc.budgetplan.entities.UFBPExternalPayDt1;
import greedc.budgetplan.entities.VExternalPay;

public class BPAccountServlet extends BaseServlet {

	/**
	 * 获取所有科目，响应树形结构数据。
	 * @return
	 */
	public List<TreeNodeVo> getAccountAll() {
		
		UFBPConfig config = RecordUtil.find(UFBPConfig.class, "ISOPEN = ?", 1);
		if (config == null) {
			throw new RuntimeException("没有可用配置！");
		}
		
		String accountType = String.format("%s,%s,%s,%s",
				config.getBussIncAccount(),
				config.getInBussIncAccount(),
				config.getBussExpAccount(),
				config.getInBussExpAccount());
		
		String type = getParameter("type"); //获取type的value
		String whereSql = "ACCOUNTTYPE = ? AND ID IN (" + accountType + ") ORDER BY DATAORDER, CHILDDATAORDER";
		List<CBPAccountTree> accounts = RecordUtil.search(CBPAccountTree.class, whereSql, type);
		
		List<TreeNodeVo> trees = new ArrayList<TreeNodeVo>();
		for (CBPAccountTree account : accounts) {
			TreeNodeVo treeNode = null;
			for (TreeNodeVo tempTree : trees) {
				if (account.getAccountName().equals(tempTree.getText())) {
					treeNode = tempTree;
					break;
				}
			}
			if (treeNode == null) {
				treeNode = new TreeNodeVo();
				treeNode.setId(account.getId() + "");
				treeNode.setText(account.getAccountName());
				treeNode.setState("closed");
				trees.add(treeNode);
			}
			
			if (account.getChildId() != null) {
				TreeNodeVo childNode = new TreeNodeVo();
				childNode.setId(account.getId() + "_" + account.getChildId() + "");
				childNode.setText(account.getChildAccountName());
				childNode.setState("open");
				childNode.getAttributes().put("childid", account.getChildId());
				treeNode.getChildren().add(childNode);
			}
		}
		return trees;
	}
	
	/**
	 * 获取指定公司已经勾选的科目。
	 * @return
	 */
	public List<UFBPCompanyAccount> getCompanyAccount() {
		int companyId = getParameterIntegerCK("companyId");
		String whereSql = "COMPANY=?";
		List<UFBPCompanyAccount> companyAccounts = RecordUtil.search(
				UFBPCompanyAccount.class, whereSql, companyId);
		return companyAccounts;
	}
	
	public Response saveCompanyAccount() throws Exception {
		int companyId = getParameterIntegerCK("companyId");
		String ids = getParameterCK("ids");
		String whereSql = "CHILDID IN (" + ids + ")";
		
		// 根据已勾选二级科目ID从数据库获取科目信息
		List<CBPAccountTree> accounts = RecordUtil.search(CBPAccountTree.class, whereSql);
		
		// 加载该公司已经勾选的科目
		whereSql = "COMPANY=?";
		List<UFBPCompanyAccount> oldCompanyAccounts = RecordUtil.search(
				UFBPCompanyAccount.class, whereSql, companyId);
		
		for (CBPAccountTree account : accounts) {
			boolean exists = false;
			for (int i = 0; i < oldCompanyAccounts.size(); i++) {
				if (oldCompanyAccounts.get(i).getAccountL2().equals(account.getChildId())) {
					exists = true;
					oldCompanyAccounts.remove(i);	// 留下匹配不上的科目，最终将匹配不上的科目执行删除。
					break ;
				}
			}
			// 给公司添加勾选科目
			if (!exists) {
				UFBPCompanyAccount companyAccount = new UFBPCompanyAccount();
				companyAccount.setAccountL1(account.getId());
				companyAccount.setAccountL2(account.getChildId());
				companyAccount.setCompany(companyId);
				RecordUtil.save(companyAccount);
			}
		}
		
		// 删除之前有勾选但是本次没有勾选的科目
		if (oldCompanyAccounts.size() > 0) {
			String delIds = "";
			for (UFBPCompanyAccount companyAccount : oldCompanyAccounts) {
				delIds += "," + companyAccount.getId();
			}
			
			whereSql = "ID IN (" + delIds.substring(1) + ") AND COMPANY=?";
			RecordUtil.deleteAll(UFBPCompanyAccount.class, whereSql, companyId);
		}
		return new Response(true, "保存成功！");
	}
	
	/**
	 * 加载指定公司的经营性计划支出数据。
	 * @return
	 */
	public List<VExternalPay> listExternalPay() {
		Integer companyId = getParameterInteger("companyId");
		Integer blockId = getParameterInteger("blockId");
		
		if (companyId == null && blockId == null) {
			throw new RuntimeException("板块参数与公司参数至少有一个！");
		}
		
		List<VExternalPay> externalPays = null;
		if (companyId != null && companyId > 0) {
			int year = getParameterIntegerCK("year");
			String sql = "SELECT DT1.ID, DT1.ACCOUNTNAME, T3.* FROM UF_BPACCOUNT_DT1 DT1 " + 
					" INNER JOIN UF_BPACCOUNT T1 ON T1.ID=DT1.MAINID " + 
					" LEFT JOIN (SELECT T2.COMPANY, DT2.ACCOUNTID, T2.YEAR, DT2.SEASON1, DT2.SEASON2, DT2.SEASON3, DT2.SEASON4 " + 
					"   FROM UF_BPEXTERNALPAY T2 " + 
					"   INNER JOIN UF_BPEXTERNALPAY_DT1 DT2 ON DT2.MAINID=T2.ID" + 
					"   WHERE T2.COMPANY=? AND T2.YEAR=? " + 
					" ) T3 ON T3.ACCOUNTID=DT1.ID " + 
					" WHERE T1.ID IN (SELECT EXTERNALACCOUNT FROM UF_BPCONFIG WHERE ISOPEN=1)" +
					" ORDER BY DT1.DATAORDER";
			externalPays = RecordUtil.searchBySql(
					VExternalPay.class, sql, companyId, year);
		} else {
			int year = getParameterIntegerCK("year");
			String sql = "SELECT DT1.ID, DT1.ACCOUNTNAME, T3.*"
					+ " FROM UF_BPACCOUNT_DT1 DT1"
					+ " INNER JOIN UF_BPACCOUNT T1 ON T1. ID = DT1.MAINID"
					+ " LEFT JOIN ("
					+ " 	SELECT DT2.ACCOUNTID, T2. YEAR, SUM(DT2.SEASON1) SEASON1, SUM(DT2.SEASON2) SEASON2, SUM(DT2.SEASON3) SEASON3, SUM(DT2.SEASON4) SEASON4"
					+ " 	FROM UF_BPEXTERNALPAY T2"
					+ " 	INNER JOIN UF_BPEXTERNALPAY_DT1 DT2 ON DT2.MAINID = T2. ID"
					+ " 	INNER JOIN UF_BPCOMPANY T4 ON T4.COMPANY=T2.COMPANY"
					+ " 	WHERE T4.COMPANYBLOCK = ? AND T2. YEAR = ? AND T2.STATUS=1"
					+ " 	GROUP BY DT2.ACCOUNTID, T2. YEAR"
					+ " ) T3 ON T3.ACCOUNTID = DT1.ID"
					+ " WHERE T1. ID IN ( SELECT EXTERNALACCOUNT FROM UF_BPCONFIG WHERE ISOPEN = 1 )"
					+ " ORDER BY DT1.DATAORDER";
			externalPays = RecordUtil.searchBySql(
					VExternalPay.class, sql, blockId, year);
		}
		return externalPays;
	}
	
	/**
	 * 保存指定公司的经营性计划支出数据。
	 * @return
	 * @throws Exception 
	 */
	public Response saveExternalPay() throws Exception {
		String data = getParameterCK("data");
		int companyId = getParameterIntegerCK("companyId");
		int year = getParameterIntegerCK("year");
		List<VExternalPay> externalPays = new Gson().fromJson(data,
				new TypeToken<List<VExternalPay>>() {}.getType());    
		
		UFBPExternalPay ePay = RecordUtil.find(UFBPExternalPay.class,   //RecordUtil是封装的一个工具类
				"COMPANY=? AND YEAR=?", companyId, year);
		if  (ePay != null && ePay.getStatus() == null) {
			ePay.setStatus(0);
		}
		
		if (ePay != null && ePay.getStatus() == 1) {
			throw new RuntimeException("当前状态不可编辑！");
		}
		
		if (ePay == null) {
			ePay = new UFBPExternalPay();
			ePay.setCompany(companyId);
			ePay.setYear(year);
			ePay.setStatus(0);
//			ePay.setCreater(getUser().getUID());
//			ePay.setCreateDate(CustomUtil.dateFormat(new Date(), "yyyy-MM-dd"));
//			ePay.setCreateTime(CustomUtil.dateFormat(new Date(), "HH:mm"));
			RecordUtil.save(ePay);
			
			// 插入成功之后，反查该记录，目的是拿到主表ID
			ePay = RecordUtil.find(UFBPExternalPay.class,
					"COMPANY=? AND YEAR=?", companyId, year);
		} else {
			ePay.setStatus(0);
			RecordUtil.update(ePay, ePay.getId(), new String[] {"ID", "REQUESTID", "COMPANY", "YEAR", "CREATER", "CREATEDATE", "CREATETIME"});
		}
		
		List<UFBPExternalPayDt1> dt1List = RecordUtil.search(UFBPExternalPayDt1.class,
				"MAINID=?", ePay.getId());
		for (VExternalPay vPay : externalPays) {
			boolean exists = false;
			for (UFBPExternalPayDt1 dt1 : dt1List) {
				if (vPay.getId() == dt1.getAccountIdInt()) {	// 判断明细表科目ID与二级科目ID是否相等
					exists = true;
					dt1.setAccountId(vPay.getId() + "");
					dt1.setSeason1(vPay.getSeason1());
					dt1.setSeason2(vPay.getSeason2());
					dt1.setSeason3(vPay.getSeason3());
					dt1.setSeason4(vPay.getSeason4());
					RecordUtil.update(dt1, dt1.getId(), new String[] {"MAINID"});
				}
			}
			if (!exists) {
				UFBPExternalPayDt1 dt1 = new UFBPExternalPayDt1();
				dt1.setMainId(ePay.getId());
				dt1.setAccountId(vPay.getId() + "");
				dt1.setSeason1(vPay.getSeason1());
				dt1.setSeason2(vPay.getSeason2());
				dt1.setSeason3(vPay.getSeason3());
				dt1.setSeason4(vPay.getSeason4());
				RecordUtil.save(dt1);
			}
		}
		
		return new Response(true, "保存成功！");
	}
	
	/**
	 * 获取指定公司的经营性计划支出状态。
	 * @return
	 * @throws Exception 
	 */
	public UFBPExternalPay saveExternalPayStatus() {
		int companyId = getParameterIntegerCK("companyId");
		int year = getParameterIntegerCK("year");
		
		UFBPExternalPay ePay = RecordUtil.find(UFBPExternalPay.class,
				"COMPANY=? AND YEAR=?", companyId, year);
		
		if (ePay == null) {
			ePay = new UFBPExternalPay();
			ePay.setCompany(companyId);
			ePay.setYear(year);
			ePay.setStatus(0);
		}
		
		return ePay;
	}
	
	/**
	 * 将指定公司的经营性计划支出设置为生效状态。
	 * @return
	 * @throws Exception 
	 */
	public Response activeExternalPay() throws Exception {
		int companyId = getParameterIntegerCK("companyId");
		int year = getParameterIntegerCK("year");
		
		UFBPExternalPay ePay = RecordUtil.find(UFBPExternalPay.class,
				"COMPANY=? AND YEAR=?", companyId, year);
		
		ePay.setStatus(1);
		
		RecordUtil.update(ePay, ePay.getId(), new String[] {"ID", "REQUESTID", "COMPANY", "YEAR", "CREATER", "CREATEDATE", "CREATETIME"});
		
		return new Response(true, "保存成功！");
	}
}
