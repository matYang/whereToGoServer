package exception;

import org.restlet.data.Status;

public class ValidationException extends ParentException{

	private static final long serialVersionUID = 3127196085207317575L;

	public ValidationException(){
		super("Validation Failed");
	}

	public ValidationException(String exceptionText){
		super(exceptionText);
	}
	
	@Override
	public Status getResponseStatus(){
		return Status.CLIENT_ERROR_BAD_REQUEST;
	}

}