package exception;

import org.restlet.data.Status;

public class UserNotFoundException extends ParentException {

	private static final long serialVersionUID = 3513729066696587101L;

	public UserNotFoundException() {
		super("Validation Failed");
	}

	public UserNotFoundException(String exceptionText) {
		super(exceptionText);
	}

	@Override
	public Status getResponseStatus() {
		return Status.CLIENT_ERROR_NOT_FOUND;
	}

}
