package exceptions;

public class IncompleteFieldException extends Exception {
	private static final long serialVersionUID = 125L;
	private static final String cause = "One or more fields are blank. Cannot submit";

	public IncompleteFieldException() {
		super(cause);
	}

	public IncompleteFieldException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public IncompleteFieldException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public IncompleteFieldException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
