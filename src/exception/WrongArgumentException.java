package exception;

@SuppressWarnings("serial")
public class WrongArgumentException extends Exception{

	public WrongArgumentException(String args){
		super();
		System.err.println(args);
	}
}
