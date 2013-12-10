package exceptions;

public class InvalidTypeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 123L;
	public static final String cause = "Invalid type identifier";
	public InvalidTypeException() {
		super(cause);
	}

	public InvalidTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
