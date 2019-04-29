package greedc;

import java.net.URL;
import java.util.Arrays;

import weaver.file.Prop;
import weaver.workflow.webservices.WorkflowRequestTableField;
import ecustom.Log;
import ecustom.util.CustomUtil;
import greedc.ws.client.eas.EASLoginProxy;
import greedc.ws.client.eas.EASLoginProxyServiceLocator;
import greedc.ws.client.eas.WSContext;

/**
 * 格力地产项目工具类。
 * @author William
 */
public class GreedcUtil {
	
	private static Log log = new Log(GreedcUtil.class);

	/**
	 * EAS接口客户端登录。
	 * @throws Exception
	 */
	public WSContext wsLoginEAS() throws Exception {
		String userName = Prop.getPropValue("eas", "userName");
		String password = Prop.getPropValue("eas", "password");
		String slnName = Prop.getPropValue("eas", "slnName");
		String dcName = Prop.getPropValue("eas", "dcName");
		String language = Prop.getPropValue("eas", "language");
		int dbType = CustomUtil.getInt(Prop.getPropValue("eas", "dbType"), 1);
		String easLoginUrl = Prop.getPropValue("eas", "loginURL");
		System.out.println("easLoginUrl - " + easLoginUrl);
		
		// 执行接口登录操作
		URL easUrl = new URL(easLoginUrl);
		EASLoginProxy loginProxy = new EASLoginProxyServiceLocator().getEASLogin(easUrl);
		WSContext wsContext = loginProxy.login(userName, password, slnName, dcName, language, dbType);
		String sessionId = wsContext.getSessionId();
		System.out.println("sessionId = " + sessionId);
		return wsContext;
	}
	
	public static <T> T[] concat(T[] first, T[] second) {  
		T[] result = Arrays.copyOf(first, first.length + second.length);  
		System.arraycopy(second, 0, result, first.length, second.length);  
		return result;  
	}
	
	public static String getFieldValue(WorkflowRequestTableField[] fields, String fieldName) {
		for (WorkflowRequestTableField field : fields) {
			if (fieldName.equalsIgnoreCase(field.getFieldName())) {
				return field.getFieldValue();
			}
		}
		return null;
	}

	/**
	 * 添加字段，如果字段存在则修改字段的值，如果字段不存在则添加新字段并赋值。
	 * @param fields
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public static WorkflowRequestTableField[] addFiled(WorkflowRequestTableField[] fields,
			String fieldName, String fieldValue) {
		WorkflowRequestTableField newField = null;
		for (WorkflowRequestTableField field : fields) {
			if (fieldName.equalsIgnoreCase(field.getFieldName())) {
				newField = field;
				break;
			}
		}
		if (newField == null) {
			newField = new WorkflowRequestTableField();
			newField.setEdit(true);
			newField.setView(true);
			fields = GreedcUtil.concat(fields, new WorkflowRequestTableField[]{newField});
		}
		newField.setFieldName(fieldName);
		newField.setFieldValue(fieldValue);
		return fields;
	}
	
	public static Integer getInteger(String v) {
		return CustomUtil.isBlank(v) ? null : CustomUtil.getInt(v);
	}
	
	public static Double getDouble(String v) {
		return CustomUtil.isBlank(v) ? null : Double.parseDouble(v);
	}
}
