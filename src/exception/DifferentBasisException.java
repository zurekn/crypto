package exception;

@SuppressWarnings("serial")
public class DifferentBasisException extends Exception{

	public DifferentBasisException(String args){
		super();
		System.err.println(args);
	}
}
