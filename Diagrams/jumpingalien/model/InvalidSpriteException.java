package jumpingalien.model;

@SuppressWarnings("serial")
public class InvalidSpriteException extends RuntimeException {
	
	public InvalidSpriteException(Throwable cause){
		super(cause);
	}
	
	public InvalidSpriteException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidSpriteException(String message) {
		super(message);
	}
}