package engine;

import io.FileRW;

public class LFSR {
	public static int DEBUG =0;

	private int longueur;
	private int P;
	private int[] coefficients;
	private int etat;
	private int R;

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
		this.coefficients = coefficients;
		String s = "";
		for(int i = Integer.SIZE ; i >= 0 ; i--){
			if(i >= longueur)
				s+="0";
			else
				s+="1";
		}
		etat = Integer.parseInt(s,2) & etat;
		
		this.etat = etat;
		this.R = etat;
		init(coefficients, etat);
	}

	public int[] getCoef() {
		return coefficients;
	}

	public int getR(){
		return R;
	}
	
	public int getLongeur(){
		return longueur;
	}
	
	public void reset() {
		etat = R;
		System.out.println("Lfsr reinitialised");
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
					.println("Nouvel état :\t" + Integer.toBinaryString(etat));
			System.out.println("Bit à insérer :\t"
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
	
	public void decrypt(String sourcePath, String destPath){
		encrypt(sourcePath,destPath);
	}
	
	public long getPeriod(long max){
		this.reset();
		long period = 0;
		long i = 0;
		while(i < max && period ==0){
			retroaction();
			
			if(etat == R)
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
