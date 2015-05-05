package polynomial;

public class Polynomial {
	private int[] polynomial = {0};
	private int basis;
	
	public Polynomial(){}
	
	public Polynomial(int[] p){
		this.polynomial = p;
		this.basis = 2;
	}
	
	public Polynomial(int[] p, int basis){
		this(p);
		this.basis = basis ;
	}
	
	public int getDegree(){
		return polynomial.length-1;
	}
	
	public int getBasis(){
		return this.basis ;
	}

	
}
