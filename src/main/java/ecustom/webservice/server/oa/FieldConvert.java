package ecustom.webservice.server.oa;

import weaver.workflow.webservices.WorkflowRequestTableField;
import ecustom.Log;
import ecustom.entities.HrmDepartment;
import ecustom.entities.HrmJobTitle;
import ecustom.entities.HrmResource;
import ecustom.entities.HrmSubCompany;
import ecustom.services.HrmDepartmentService;
import ecustom.services.HrmJobTitleService;
import ecustom.services.HrmResourceService;
import ecustom.services.HrmSubCompanyService;
import ecustom.util.CustomUtil;

public class FieldConvert {

	public final static String REPLACE_EMPCODE = "empcode:";
	public final static String REPLACE_DEPCODE = "depcode:";
	public final static String REPLACE_COMCODE = "comcode:";
	public final static String REPLACE_POSCODE = "poscode:";
	public final static String REPLACE_CODE = "code:";
	public final static String DEFAULT_CODE = "default";
	public final static String EMPTY_STRING = "";
	
	private HrmResourceService resService = new HrmResourceService();
	private HrmSubCompanyService comService = new HrmSubCompanyService();
	private HrmDepartmentService depService = new HrmDepartmentService();
	private HrmJobTitleService jobService = new HrmJobTitleService();

	private HrmResource creator = null;	// 创建人

	private Log log = new Log(FieldConvert.class);
	
	public FieldConvert (HrmResource creator) {
		this.creator = creator;
	}
	
	/**
	 * 转换字段中组织结构相关ID。
	 * @param fields
	 */
	public void convertField(WorkflowRequestTableField field) {
		String value = field.getFieldValue();
		if (value == null || value.equals("null")) {
			return ;
		}

		if (value.startsWith(REPLACE_EMPCODE)) {
			field.setFieldValue(getEmpId(value.substring(REPLACE_EMPCODE.length())));
		} else if (value.startsWith(REPLACE_DEPCODE)) {
			field.setFieldValue(getDepId(value.substring(REPLACE_DEPCODE.length())));
		} else if (value.startsWith(REPLACE_COMCODE)) {
			field.setFieldValue(getComId(value.substring(REPLACE_COMCODE.length())));
		} else if (value.startsWith(REPLACE_POSCODE)) {
			field.setFieldValue(getPosId(value.substring(REPLACE_POSCODE.length())));
		}
	}

	/**
	 * 根据员工编号获取员工ID。
	 * @param empCode
	 * @return
	 */
	private String getEmpId(String empCode) {
		if (empCode == null || "null".equalsIgnoreCase(empCode) || empCode.trim().length() == 0) {
			return "";
		}
		if (DEFAULT_CODE.equalsIgnoreCase(empCode)) {
			return creator.getId().toString();
		}
		HrmResource entity = resService.getByWorkCode(empCode);
		CustomUtil.checkNull(entity, "找不到员工编码 " + empCode);
		String empId = entity != null ? entity.getId() + "" : EMPTY_STRING;
		log.debug("Employee code: " + empCode + " --> " + empId);
		return empId;
	}

	/**
	 * 根据部门编号获取部门ID。
	 * @param depCode
	 * @return
	 */
	private String getDepId(String depCode) {
		if (depCode == null || "null".equalsIgnoreCase(depCode) || depCode.trim().length() == 0) {
			return "";
		}
		if (DEFAULT_CODE.equalsIgnoreCase(depCode)) {
			return creator.getDepartmentId().toString();
		}
		HrmDepartment entity = depService.getByCode(depCode);
		CustomUtil.checkNull(entity, "找不到部门编码 " + depCode);
		String depId = entity != null ? entity.getId() + "" : EMPTY_STRING;
		log.debug("Department code: " + depCode + " --> " + depId);
		return depId;
	}

	/**
	 * 根据公司编号获取公司ID。
	 * @param comCode
	 * @return
	 */
	private String getComId(String comCode) {
		if (comCode == null || "null".equalsIgnoreCase(comCode) || comCode.trim().length() == 0) {
			return "";
		}
		if (DEFAULT_CODE.equalsIgnoreCase(comCode)) {
			return creator.getSubCompanyId().toString();
		}
		HrmSubCompany entity = comService.getByCode(comCode);
		CustomUtil.checkNull(entity, "找不到分部编码 " + comCode);
		String comId = entity != null ? entity.getId() + "" : EMPTY_STRING;
		log.debug("Company code: " + comCode + " --> " + comId);
		return comId;
	}

	/**
	 * 根据岗位编号获取岗位ID。
	 * @param posCode
	 * @return
	 */
	private String getPosId(String posCode) {
		if (posCode == null || "null".equalsIgnoreCase(posCode) || posCode.trim().length() == 0) {
			return "";
		}
		if (DEFAULT_CODE.equalsIgnoreCase(posCode)) {
			return creator.getJobTitle().toString();
		}
		HrmJobTitle entity = jobService.getByCode(posCode);
		CustomUtil.checkNull(entity, "找不到岗位编码 " + posCode);
		String posId = entity != null ? entity.getId() + "" : EMPTY_STRING;
		log.debug("Position code: " + posCode + " --> " + posId);
		return posId;
	}
}
