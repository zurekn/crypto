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

	public void addElement(int var, int degre) throws Exception{
		if(getDegree() < degre)
			if(polynomial[degre] != 0)
			throw new Exception("Can't add Element at the degre ["+degre+"], it already exist");
		polynomial[degre] = var;
	}
	
}
