package Controller;

public class UnexistingUserException extends Exception {
	public UnexistingUserException (String msg) {
		super(msg);
	}

}
