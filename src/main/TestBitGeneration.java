package main;

import engine.LFSR;

public class TestBitGeneration {
	public static final int N = 1000000;
	public static final int ESSAIES = 100;

	public static void main(String[] args) {
		long startTime, endTime;
		int p[] = { 1, 5, 6 };
		LFSR lfsr = new LFSR(16, p, Integer.parseInt("AEF1", 16));
		double sum = 0.;
		for (int i = 0; i < ESSAIES; i++) {
			startTime = System.nanoTime();
			lfsr.genere(N);
			endTime = System.nanoTime();
			sum += endTime - startTime;
		}
		System.out.println("Nombre de bits générés : " + N);

		System.out.println("Temps moyen : " + (sum / ESSAIES));
		System.out.println("Vitesse : " + (N/((sum / ESSAIES)/1000000000)));

	}

}
