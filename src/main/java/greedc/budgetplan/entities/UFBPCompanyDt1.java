package greedc.budgetplan.entities;

import javax.persistence.Table;

@Table(name = "UF_BPCOMPANY_DT1", catalog = "ecology")
public class UFBPCompanyDt1 {

	private String baseright;	// 基础数据
	private String exportright;	// 导出报表
	private String playright;	// 资金计划
	private String projectright;	// 项目数据
	private Integer userid;	// 姓名
	
	public String getBaseright() {
		return baseright;
	}
	public void setBaseright(String baseright) {
		this.baseright = baseright;
	}
	public String getExportright() {
		return exportright;
	}
	public void setExportright(String exportright) {
		this.exportright = exportright;
	}
	public String getPlayright() {
		return playright;
	}
	public void setPlayright(String playright) {
		this.playright = playright;
	}
	public String getProjectright() {
		return projectright;
	}
	public void setProjectright(String projectright) {
		this.projectright = projectright;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
}
