package exceptions;

public class InvalidIDException extends Exception {
	private static final long serialVersionUID = 124L;
	private static String cause = "Invalid ID Format";
	
	public InvalidIDException() {
		super(cause);
	}

	public InvalidIDException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InvalidIDException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InvalidIDException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}


