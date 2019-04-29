package greedc.eas.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * OA返写给EAS的状态。
 * @author William
 */
public class EASBillStatusVo {

	private String type;
	private String number;
	private String status;
	private String auditNumber;
	private String project;
	private List<Map<String, String>> detail;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuditNumber() {
		return auditNumber;
	}
	public void setAuditNumber(String auditNumber) {
		this.auditNumber = auditNumber;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public List<Map<String, String>> getDetail() {
		if (detail == null) {
			detail = new ArrayList<Map<String,String>>();
		}
		return detail;
	}
	public void setDetail(List<Map<String, String>> detail) {
		this.detail = detail;
	}
}
