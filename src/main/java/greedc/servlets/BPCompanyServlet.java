package greedc.servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ecustom.ecology8.commons.RecordUtil;
import ecustom.ecology8.hrm.entities.HrmRoleMembers;
import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import greedc.budgetplan.entities.CBPCompany;
import greedc.budgetplan.entities.UFBPCompanyBlock;
import greedc.budgetplan.entities.UFBPCompanyDt1;
import greedc.budgetplan.entities.UFBPConfig;

public class BPCompanyServlet extends BaseServlet {

	
	/**
	 * 获取公司信息。
	 * @return
	 */
	public CBPCompany getCompanyInfo() {
		int companyId = Integer.parseInt(getParameterCK("companyId"));
		CBPCompany company = RecordUtil.findById(CBPCompany.class, companyId);
		return company;
	}
	
	/**
	 * 根据板块信息。
	 * @return
	 */
	public UFBPCompanyBlock getBlockInfo() {
		int blockId = Integer.parseInt(getParameterCK("blockId"));
		UFBPCompanyBlock block = RecordUtil.findById(UFBPCompanyBlock.class, blockId);
		return block;
	}
	
	/**
	 * 权限重构。
	 * @return
	 * @throws Exception 
	 */
	public Response reBuildRight() throws Exception {
		List<UFBPCompanyDt1> userList = RecordUtil.searchAll(UFBPCompanyDt1.class);
		UFBPConfig config = RecordUtil.find(UFBPConfig.class,
				"ISOPEN=? ORDER BY DATAORDER", 1);
		if (config == null) {
			throw new RuntimeException("获取资金计划设置相信失败！");
		}
		List<HrmRoleMembers> oldRoleMems = RecordUtil.search(HrmRoleMembers.class,
				"ROLEID IN (?, ?, ?, ?)", config.getRoleBase(), config.getRolePlan(),
				config.getRoleProj(), config.getRoleSubmit());
		
		// 合并权限
		Map<Integer, UFBPCompanyDt1> userRights = new HashMap<Integer, UFBPCompanyDt1>();
		for (UFBPCompanyDt1 dt1 : userList) {
			if (!userRights.containsKey(dt1.getUserid())) {
				userRights.put(dt1.getUserid(), new UFBPCompanyDt1());
				userRights.get(dt1.getUserid()).setUserid(dt1.getUserid());
			}
			if ("1".equals(dt1.getBaseright())) {
				userRights.get(dt1.getUserid()).setBaseright("1");
			}
			if ("1".equals(dt1.getExportright())) {
				userRights.get(dt1.getUserid()).setExportright("1");
			}
			if ("1".equals(dt1.getPlayright())) {
				userRights.get(dt1.getUserid()).setPlayright("1");
			}
			if ("1".equals(dt1.getProjectright())) {
				userRights.get(dt1.getUserid()).setProjectright("1");
			}
		}

		int baseRight, exportRight, planRight, projectRight, roleId, roleUserId;
		boolean exsistBaseRight, exsistExportRight, exsistPlanRight, exsistProjectRight;
		for (Integer userId : userRights.keySet()) {
			UFBPCompanyDt1 dt1 = userRights.get(userId);
			baseRight = CustomUtil.getInt(dt1.getBaseright(), 0);
			exportRight = CustomUtil.getInt(dt1.getExportright(), 0);
			planRight = CustomUtil.getInt(dt1.getPlayright(), 0);
			projectRight = CustomUtil.getInt(dt1.getProjectright(), 0);
			userId = dt1.getUserid() == null ? 0 : dt1.getUserid();
			exsistBaseRight = exsistExportRight = exsistPlanRight = exsistProjectRight = false;
			for (int i = 0; i < oldRoleMems.size(); i++) {
				roleId = oldRoleMems.get(i).getRoleId();
				roleUserId = oldRoleMems.get(i).getResourceId();
				if (roleUserId == userId && roleId == CustomUtil.getInt(config.getRolePlan())) {
					if (planRight == 0) {
						// 如果前台没有勾选权限，则删除此人对应的权限
						RecordUtil.delete(HrmRoleMembers.class, oldRoleMems.get(i).getId());
					}
					oldRoleMems.remove(i);
					i--;
					exsistPlanRight = true;
					continue;
				} else if (roleUserId == userId && roleId == CustomUtil.getInt(config.getRoleProj())) {
					if (projectRight == 0) {
						// 如果前台没有勾选权限，则删除此人对应的权限
						RecordUtil.delete(HrmRoleMembers.class, oldRoleMems.get(i).getId());
					}
					oldRoleMems.remove(i);
					i--;
					exsistProjectRight = true;
					continue;
				} else if (roleUserId == userId && roleId == CustomUtil.getInt(config.getRoleSubmit())) {
					if (exportRight == 0) {
						// 如果前台没有勾选权限，则删除此人对应的权限
						RecordUtil.delete(HrmRoleMembers.class, oldRoleMems.get(i).getId());
					}
					oldRoleMems.remove(i);
					i--;
					exsistExportRight = true;
					continue;
				} else if (roleUserId == userId && roleId == CustomUtil.getInt(config.getRoleBase())) {
					if (baseRight == 0) {
						// 如果前台没有勾选权限，则删除此人对应的权限
						RecordUtil.delete(HrmRoleMembers.class, oldRoleMems.get(i).getId());
					}
					oldRoleMems.remove(i);
					i--;
					exsistBaseRight = true;
					continue;
				}
			}
			HrmRoleMembers newMember = new HrmRoleMembers();
			newMember.setResourceId(userId);
			newMember.setRoleLevel(2);
			if (!exsistPlanRight && planRight == 1) {
				newMember.setRoleId(CustomUtil.getInt(config.getRolePlan()));
				RecordUtil.save(newMember);
			}
			if (!exsistProjectRight && projectRight == 1) {
				newMember.setRoleId(CustomUtil.getInt(config.getRoleProj()));
				RecordUtil.save(newMember);
			}
			if (!exsistExportRight && exportRight == 1) {
				newMember.setRoleId(CustomUtil.getInt(config.getRoleSubmit()));
				RecordUtil.save(newMember);
			}
			if (!exsistBaseRight && baseRight == 1) {
				newMember.setRoleId(CustomUtil.getInt(config.getRoleBase()));
				RecordUtil.save(newMember);
			}
		}
		// 删除多余的权限
		for (int i = 0; i < oldRoleMems.size(); i++) {
			RecordUtil.delete(HrmRoleMembers.class, oldRoleMems.get(i).getId());
		}
		return new Response(true, "权限重构完成！");
	}
}
