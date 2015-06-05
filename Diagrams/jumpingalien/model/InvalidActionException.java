package jumpingalien.model;

@SuppressWarnings("serial")
public class InvalidActionException extends RuntimeException {
	
	public InvalidActionException(Throwable cause){
		super(cause);
	}
	
	public InvalidActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidActionException(String message) {
		super(message);
	}
}
