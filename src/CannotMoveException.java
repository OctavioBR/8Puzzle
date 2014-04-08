public class CannotMoveException extends Exception {
	private String message;
	public CannotMoveException(String message) {
		super();
		this.message = message;
	}
}
