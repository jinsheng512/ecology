package greedc.budgetplan.entities;

import javax.persistence.Table;

/**
 * BP06-资金计划配置
 * @author William
 */
@Table(name = "UF_BPCONFIG", catalog = "ecology")
public class UFBPConfig {
	
	private Integer id;	// 标识
	private Integer requestId;	// 请求标识
	private String configName;	// 配置名
	private String bussExpAccount;	// 外部经营性支出科目
	private String bussIncAccount;	// 外部经营性收入科目
	private String financeIncAccount;	// 筹资收入科目
	private String financeExpAccount;	// 筹资支出科目
	private String inBussExpAccount;	// 内部经营性支出科目
	private String inBussIncAccount;	// 内部经营性收入科目
	private String cashIncAccount;	// 现金收入科目
	private String cashExpAccount;	// 现金支出科目
	private String projectExpAccount;	// 项目投资支出科目
	private Double dataOrder;	// 排序
	private Integer isOpen;	// 是否启用
	private Integer companyPlanId;	//数据填报审批流程Id
	private String rolePlan;	// 公司资金计划员角色
	private String roleProj;	// 公司项目管理角色
	private String roleSubmit;	// 填报数据报表导出角色
	private String roleBase;	// 公司基础数据管理角色
	
	public Integer getCompanyPlanId() {
		return companyPlanId;
	}
	public void setCompanyPlanId(Integer companyPlanId) {
		this.companyPlanId = companyPlanId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getBussExpAccount() {
		return bussExpAccount;
	}
	public void setBussExpAccount(String bussExpAccount) {
		this.bussExpAccount = bussExpAccount;
	}
	public String getBussIncAccount() {
		return bussIncAccount;
	}
	public void setBussIncAccount(String bussIncAccount) {
		this.bussIncAccount = bussIncAccount;
	}
	public String getFinanceIncAccount() {
		return financeIncAccount;
	}
	public void setFinanceIncAccount(String financeIncAccount) {
		this.financeIncAccount = financeIncAccount;
	}
	public String getFinanceExpAccount() {
		return financeExpAccount;
	}
	public void setFinanceExpAccount(String financeExpAccount) {
		this.financeExpAccount = financeExpAccount;
	}
	public String getInBussExpAccount() {
		return inBussExpAccount;
	}
	public void setInBussExpAccount(String inBussExpAccount) {
		this.inBussExpAccount = inBussExpAccount;
	}
	public String getInBussIncAccount() {
		return inBussIncAccount;
	}
	public void setInBussIncAccount(String inBussIncAccount) {
		this.inBussIncAccount = inBussIncAccount;
	}
	public String getCashIncAccount() {
		return cashIncAccount;
	}
	public void setCashIncAccount(String cashIncAccount) {
		this.cashIncAccount = cashIncAccount;
	}
	public String getCashExpAccount() {
		return cashExpAccount;
	}
	public void setCashExpAccount(String cashExpAccount) {
		this.cashExpAccount = cashExpAccount;
	}
	public String getProjectExpAccount() {
		return projectExpAccount;
	}
	public void setProjectExpAccount(String projectExpAccount) {
		this.projectExpAccount = projectExpAccount;
	}
	public Double getDataOrder() {
		return dataOrder;
	}
	public void setDataOrder(Double dataOrder) {
		this.dataOrder = dataOrder;
	}
	public Integer getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	public String getRolePlan() {
		return rolePlan;
	}
	public void setRolePlan(String rolePlan) {
		this.rolePlan = rolePlan;
	}
	public String getRoleProj() {
		return roleProj;
	}
	public void setRoleProj(String roleProj) {
		this.roleProj = roleProj;
	}
	public String getRoleSubmit() {
		return roleSubmit;
	}
	public void setRoleSubmit(String roleSubmit) {
		this.roleSubmit = roleSubmit;
	}
	public String getRoleBase() {
		return roleBase;
	}
	public void setRoleBase(String roleBase) {
		this.roleBase = roleBase;
	}
}
