package exception;

public class DifferentBasisException extends Exception{

	public DifferentBasisException(String args){
		super();
		System.err.println(args);
	}
}
