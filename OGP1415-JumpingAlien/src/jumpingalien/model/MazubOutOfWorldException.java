package jumpingalien.model;

@SuppressWarnings("serial")
public class MazubOutOfWorldException extends RuntimeException {
	
	public MazubOutOfWorldException(Throwable cause){
		super(cause);
	}
	
	public MazubOutOfWorldException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MazubOutOfWorldException(String message) {
		super(message);
	}

}
