package ecustom.exception;

/**
 * 值无效异常。
 * @author ecustom
 */
public class InvalidValueException extends RuntimeException {

	private static final long serialVersionUID = -8829289439916746021L;

	public InvalidValueException(String message) {
		super(message);
	}
}
