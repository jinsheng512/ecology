package ecustom.util;

import ecustom.util.CustomUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 请求处理工具类。
 * @author William
 */
public class RequestUtil<T extends Object> {

	/**
	 * 从HttpServletRequest对象中提取对象。
	 * @param request	请求对象
	 * @param clazz	将要提取的对象
	 * @return	提取成功后的对象
	 */
	public T toObject(HttpServletRequest request, Class<T> clazz) {
		try {
			T o = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			Method method;
			String value;
			for (Field field : fields) {
				method = clazz.getMethod("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), field.getType());
				value = request.getParameter(field.getName());
				if (field.getType().getName().equals(Double.class.getName())) {
					method.invoke(o, CustomUtil.isBlank(value) ? null : Double.parseDouble(value));
				} else if (field.getType().getName().equals(Integer.class.getName())) {
					method.invoke(o, CustomUtil.isBlank(value) ? null : Integer.parseInt(value));
				} else if (field.getType().getName().equals(String.class.getName())) {
					method.invoke(o, CustomUtil.isBlank(value) ? null : value);
				}
			}
			return o;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}