package jumpingalien.model;

@SuppressWarnings("serial")
public class InvalidPositionException extends RuntimeException {

	
	public InvalidPositionException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPositionException(String message) {
		super(message);
	}
}
