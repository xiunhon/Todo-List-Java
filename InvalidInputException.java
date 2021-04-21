/**
 * @author Tien N.
 * customized exception to print warning message when user's input is invalid
 */
public class InvalidInputException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message;
	public InvalidInputException() {
		this.message = "";
	}
	public InvalidInputException(String pMessage) {
		this.message = pMessage;
	}
	@Override
	public String getMessage() {
		return message;
	}
}//end of class
