package exception;

import org.restlet.data.Status;

public class ParentException extends RuntimeException {
	
	private static final long serialVersionUID = 5722948151754854312L;

	public ParentException() {
		super("Request Failed");
	}

	public ParentException(String exceptionText) {
		super(exceptionText);
	}
	
	public Status getResponseStatus(){
		return Status.CLIENT_ERROR_BAD_REQUEST;
	}
}
