package main;

import engine.LFSR;
import massey.Core;

public class TestMassey {

	public static void main(String[] args) {

		String s = "0110010101";
		LFSR lfsr = Core.findLFSR(s);

		System.out.println("___________\n");
		System.out.println(s);
		String suite = lfsr.genere(10);
		System.out.println(suite);
		System.out.println(suite.compareTo(s));
		System.out.println("\n\n______________________\nDeuxième test");
		int P[] = { 0, 1, 2, 4,6,8,10,15,27};
		lfsr = new LFSR(27, P, Integer.parseInt("174FE2", 16));
		s = lfsr.genere(69);
		LFSR l = Core.findLFSR(s);
		suite = l.genere(69);
		System.out.println(s);
		System.out.println(suite);
		System.out.println(suite.compareTo(s));
		

	}

}
