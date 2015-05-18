package main;

import exception.WrongArgumentException;
import polynomial.Polynomial;
import polynomial.PolynomialCalculator;

public class Main {

	public static void main(String[] args) throws WrongArgumentException {
		/*int[] tab = {1,0,1,0,1,0,0,1,1};
		Polynomial p = new Polynomial(tab,2);
		int[] tab2 = {1,1,0,1,1,1};
		Polynomial q = new Polynomial(tab2,2);*/
		int[] tab = {0,3,5,6};
		Polynomial p = new Polynomial(tab);
		int[] tab2 = {0,2};
		Polynomial q = new Polynomial(tab2);
		System.out.println(p.toString());
		System.out.println("+\n"+q.toString());
		Polynomial res = PolynomialCalculator.add(p, q);
		System.out.println("=\n"+res.toString());
		//System.out.println("\n---------------\n"+PolynomialCalculator.GCD(p, q).toString());
		
	}

}
