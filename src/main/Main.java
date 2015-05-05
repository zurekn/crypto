package main;

import exception.DifferentBasisException;
import polynomial.Polynomial;
import polynomial.PolynomialCalculator;

public class Main {

	public static void main(String[] args) throws DifferentBasisException {
		int[] tab = {5,0,4,0,8,0,0,39,42};
		Polynomial p = new Polynomial(tab,2);
		int[] tab2 = {8,6,0,5,7,6};
		Polynomial q = new Polynomial(tab2,2);
		System.out.println(p.toString());
		System.out.println("+\n"+q.toString());
		System.out.println("=\n"+PolynomialCalculator.multiply(p, q).toString());
		
		
	}

}
