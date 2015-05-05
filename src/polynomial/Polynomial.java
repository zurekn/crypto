package polynomial;

public class Polynomial {
	private int[] polynomial = {0};

	
	public Polynomial(){}
	
	public Polynomial(int[] p){
		this.polynomial = p;
	}
	
	private int getDegree(){
		return polynomial.length-1;
	}

}
