package exception;

@SuppressWarnings("serial")
public class ExistingElementException extends Exception{

	public ExistingElementException(String args){
		super();
		System.err.println(args);
	}
}