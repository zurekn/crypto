package polynomial;

public class Polynomial {
	private int[] polynomial = {0};
	private int base;
	
	public Polynomial(){}
	
	public Polynomial(int[] p){
		this.polynomial = p;
	}
	
	public Polynomial(int[] p, int base){
		this(p);
		this.base = base ;
	}
	
	public int getDegree(){
		return polynomial.length-1;
	}
	
	public int getBase(){
		return this.base ;
	}

	
}
