package ecustom.exception;

/**
 * 数据找不到时抛出的异常。
 * @author William
 */
public class ParameterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1029235031623622736L;

	public final static String LOGINID_NOT_FOUND = "登录名%s不存在";
	public final static String WORKCODE_NOT_FOUND = "工号%s不存在";

	public ParameterNotFoundException(String message) {
		super(message);
	}
}
