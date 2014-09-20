package exception;

import org.restlet.data.Status;

public class NotFoundException extends ParentException {

	private static final long serialVersionUID = 3513729066696587101L;

	public NotFoundException() {
		super("Resource Not Found");
	}

	public NotFoundException(String exceptionText) {
		super(exceptionText);
	}

	@Override
	public Status getResponseStatus() {
		return Status.CLIENT_ERROR_NOT_FOUND;
	}

}
