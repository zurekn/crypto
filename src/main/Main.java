package main;

import polynomial.Polynomial;

public class Main {

	public static void main(String[] args) {
		int[] tab = {5,0,0,4,0,0,0,8,0,0,0,0,39,42};
		Polynomial p = new Polynomial(tab,16);
		System.out.println(p.toString());
	}

}
