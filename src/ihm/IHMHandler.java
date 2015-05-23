package ihm;

import io.FileRW;

import java.io.File;
import java.io.IOException;

import massey.Core;
import engine.LFSR;
import polynomial.Polynomial;
import polynomial.PolynomialCalculator;

public class IHMHandler {

	private String currentAction = "Addition";
	private Polynomial P1;
	private Polynomial P2;
	private int P2bis;
	private LFSR lfsr;

	public enum Actions {

		Addition(1, "Addition"), Multiplication(2, "Multiplication"), Modulo(3,
				"Modulo"), Exponentiation(4, "Exponentiation");

		private int number = 0;
		private String action = "";

		// Constructeur
		Actions(int number, String action) {
			this.number = number;
			this.action = action;
		}

		public int getActions() {
			return number;
		}

		public String toString() {
			return action + "[" + number + "]";
		}
	}

	/* ======================= */
	/* =======GET & SET========= */
	/* ======================= */

	public String getP1() {
		return P1.toString();
	}

	public String getP2() {
		if (currentAction == Actions.Exponentiation.name()
				|| currentAction == Actions.Modulo.name())
			return "" + P2bis;
		return P2.toString();
	}

	public String getOpperation() {
		return currentAction;
	}

	public void setOpperation(String actionCommand) {
		currentAction = actionCommand;
	}

	/* ======================= */
	/* =======METHODS========= */
	/* ======================= */

	public String calculate(String P1, String P2) {
		if (P1.isEmpty() || P1.equals(" ") || P2.isEmpty() || P2.equals(" "))
			return null;

		String[] split = P1.split(",");
		int[] p1 = new int[split.length];
		for (int i = 0; i < split.length; i++) {
			p1[i] = Integer.parseInt(split[i]);
		}

		this.P1 = new Polynomial(p1);

		if (currentAction == Actions.Exponentiation.name()
				|| currentAction == Actions.Modulo.name()) {
			this.P2bis = Integer.parseInt(P2);
		} else {
			split = P2.split(",");
			p1 = new int[split.length];
			for (int i = 0; i < split.length; i++)
				p1[i] = Integer.parseInt(split[i]);
			this.P2 = new Polynomial(p1);
		}
		System.out.println(this.P1.toString());
		System.out.println("this .P2 = " + this.P2.toString());

		if (currentAction == Actions.Addition.name())
			return PolynomialCalculator.add(this.P1, this.P2).toString();
		if (currentAction == Actions.Multiplication.name())
			return PolynomialCalculator.multiply(this.P1, this.P2).toString();
		if (currentAction == Actions.Modulo.name())
			return PolynomialCalculator.pow(this.P1, this.P2bis).toString();
		if (currentAction == Actions.Exponentiation.name())
			return PolynomialCalculator.pow(this.P1, this.P2bis).toString();

		return "";
	}

	public String genere(String number, String lfsr, String length,
			String retro, String base) {
		try {
			String[] split = retro.split(",");
			int[] P = new int[split.length];
			for (int i = 0; i < P.length; i++)
				P[i] = Integer.parseInt(split[i]);
			int l = Integer.parseInt(length);
			int init = Integer.parseInt(lfsr, Integer.parseInt(base));
			this.lfsr = new LFSR(l, P, init);
			return this.lfsr.genere(Integer.parseInt(number));
		} catch (NumberFormatException e) {
			return "Error " + e.getMessage();
		}
	}

	public int crypt(String lfsr, String length, String retro, String base,
			String input, String output) {
		File in = new File(input);
		File out = new File(output);
		System.out.println("Crypt file ["+in.getAbsolutePath()+"] to ["+out.getAbsolutePath()+"]");
		if (!in.exists())
			return 0;
		if (!out.exists())
			try {
				out.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}

		try {
			String[] split = retro.split(",");
			int[] P = new int[split.length];
			for (int i = 0; i < P.length; i++)
				P[i] = Integer.parseInt(split[i]);
			int l = Integer.parseInt(length);
			int init = Integer.parseInt(lfsr, Integer.parseInt(base));
			this.lfsr = new LFSR(l, P, init);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
		this.lfsr.encrypt(input, output);
		System.out.println("fichier d'entrée = " + input);
		System.out.println("fichier de sortie = " + output);
		return 1;
	}

	public int decrypt(String lfsr, String length, String retro, String base,
			String input, String output) {
		File in = new File(input);
		File out = new File(output);
		System.out.println("Crypt file ["+in.getAbsolutePath()+"] to ["+out.getAbsolutePath()+"]");
		if (!in.exists())
			return 0;
		if (!out.exists())
			try {
				out.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}

		try {
			String[] split = retro.split(",");
			int[] P = new int[split.length];
			for (int i = 0; i < P.length; i++)
				P[i] = Integer.parseInt(split[i]);
			int l = Integer.parseInt(length);
			int init = Integer.parseInt(lfsr, Integer.parseInt(base));
			this.lfsr = new LFSR(l, P, init);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
		this.lfsr.decrypt(input, output);
		System.out.println("fichier d'entrée = " + input);
		System.out.println("fichier de sortie = " + output);
		return 1;
	}

	public String nextGen(String number) {
		try {
			return this.lfsr.genere(Integer.parseInt(number));
		} catch (NumberFormatException e) {
			return "Error " + e.getLocalizedMessage();
		}
	}

	public String decryptMassey(String path) {
		File file = new File(path);
		//on supose que cest un pdf
		byte []f = FileRW.readBinaryFile(path);
		if(checkEncryptedFile(f, path))
			System.out.println("Le fichier est crypter");
		else
			System.out.println("Le fichier est en clair");
		return null;
	}
	
	public boolean checkEncryptedFile(byte[] f, String path){
		if(path.endsWith("pdf")){
			System.out.println("Find pdf file");
			for(int i = 0; i < Core.PDF_HEADER.length; i++)
				if(Core.PDF_HEADER[i] != f[i])
					return true;
		}else{
			System.err.println("File type not handle");
		}
		
		
		return false;
	}
}
