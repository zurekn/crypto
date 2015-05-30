package engine;

import java.util.Arrays;

import io.FileRW;

public class LFSR {
	public static int DEBUG =0;

	private int longueur;
	private int P;
	private int etat;
	private int R;

	private int[] coefficients;
	private int etatInit;

	private void init(int[] coefficients, int etat) {
		this.P = 0;
		this.etat = etat;
		for (int i = 0; i < coefficients.length; i++) {
			P = P | ((1 << longueur) >> coefficients[i]);
		}
		if (DEBUG > 0) {
			System.out.println("P:\t\t" + Integer.toBinaryString(P));
			System.out.println("Etat:\t\t" + Integer.toBinaryString(etat));
		}
	}
	

	public LFSR(int longueur, int[] coefficients, int etat) {
		this.longueur = longueur;
		this.R = 0;
		this.etat = etat;
		this.coefficients = coefficients;
		this.etatInit = etat;
		init(coefficients, etat);
	}

	public int getEtatInit(){
		return etatInit;
	}
	
	public int getLongeur(){
		return longueur;
	}
	
	public void reset() {
		init(coefficients, etatInit);
		System.out.println("Lfsr reinitialised");
	}

	public int[] getCoef(){
		return coefficients;
	}
	
	/**
	 * Décalage à droite et récupération du bit de sortie
	 * 
	 * a=0; n = P & etat;
	 * 
	 * for(int i=0; i <= l-1 ; i++){ a=a+n&1; n = n>>1
	 * 
	 * } retro << l | n >> 1
	 * 
	 * @return
	 */
	public int retroaction() {
		int dernierBit = etat & 1;
		int nouveauBit = 0;
		int n = etat & P;
		if (DEBUG > 3) {
			System.out.println("n:\t" + Integer.toBinaryString(n));
		}
		for (int i = 0; i <= longueur - 2; i++) {
			nouveauBit = nouveauBit + n & 1;
			n = n >> 1;
			if (DEBUG > 3) {
				System.out.println("\t" + Integer.toBinaryString(n));
			}
		}
		etat = (etat >> 1) | (nouveauBit << longueur - 1);

		if (DEBUG > 2) {
			System.out
					.println("Nouvel etat :\t" + Integer.toBinaryString(etat));
			System.out.println("Bit � ins�rer :\t"
					+ Integer.toBinaryString(nouveauBit << (longueur - 1))
					+ "\t"
					+ Integer.toBinaryString(nouveauBit << (longueur - 1))
							.length());
		}
		return dernierBit;
	}

	public String genere(int n) {
		StringBuilder sB = new StringBuilder();
		for (; n > 0; n--)
			sB.append(retroaction());
		return sB.toString();
	}


	public void encrypt(String sourcePath, String destPath) {
		byte[] data = FileRW.readBinaryFile(sourcePath);
		for(int i=0; i < data.length;i++){
			int tmp = (data[i] & 0xFF);
			data[i]= (byte) (tmp^Integer.parseInt(genere(8),2));
		}
		
		FileRW.writeBinaryFile(data, destPath);
	}
	
	@Deprecated
	public String encrypt(LFSR lfsr, String sourcePath, String destPath){
		StringBuilder suiteLFSR = new StringBuilder();
		StringBuilder sb;
		byte[] data = FileRW.readBinaryFile(sourcePath);
		String s;
		for(int i=0; i < data.length;i++){
			int tmp = (data[i] & 0xFF);
			sb = new StringBuilder();
			while(sb.length()<8){
			s = this.genere(1);
				if(lfsr.genere(1).equals("1")){
					sb.append(s);
					suiteLFSR.append(s);
				}
			}
			data[i]= (byte) (tmp^Integer.parseInt(sb.toString(),2));
		}
		
		FileRW.writeBinaryFile(data, destPath);
		return suiteLFSR.toString();
	}
	
	
	
	public void decrypt(String sourcePath, String destPath){
		encrypt(sourcePath,destPath);
	}
	
	@Deprecated
	public void decrypt(LFSR lfsr, String sourcePath, String destPath){
		encrypt(lfsr, sourcePath,destPath);
	}
	
	public long getPeriod(long max){
		this.reset();
		long period = 0;
		long i = 0;
		while(i < max && period ==0){
			retroaction();
			
			if(etat == etatInit)
				period = i+1;
			
			i++;
		}
		this.reset();
		return period;
	}
	
	public static float getFrequency(String motifBinaire, String SuiteChiffrante){
		if(motifBinaire.length() < 1 || motifBinaire.length() > 10)
			return -1;
		//TODO probleme avec les 000... 111...
		return ((float)SuiteChiffrante.split(motifBinaire).length)/SuiteChiffrante.length();
	}
	
}
