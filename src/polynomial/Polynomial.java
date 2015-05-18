package polynomial;

import exception.WrongArgumentException;
import io.FileRW;

public class Polynomial {
	private final String WAE_MESSAGE = "Les coefficients doivent être 0 ou 1";

	private int[] polynomial = { 0 };

	private void checkInitialValues() throws WrongArgumentException {
		for (int i = 0; i <= getDegree(); i++) {
			if (polynomial[i] != 1 && polynomial[i] != 0)
				throw new WrongArgumentException(WAE_MESSAGE);
		}
	}

	public Polynomial(int[] p, boolean coefficients) {
		try {
			if (p != null)
				this.polynomial = p;
			checkInitialValues();
		} catch (WrongArgumentException wae) {
			polynomial = new int[0];
			wae.printStackTrace();
		}
	}
	
	public Polynomial(int[] p){
		try{
			polynomial = new int[p[p.length-1]+1];
			for(int i = 0 ; i < p.length ; i++){
				polynomial[p[i]]=1;
			}
		}catch(NullPointerException npe){
			System.err.println("Tableau null.");
			polynomial=new int[0];
		}catch(IndexOutOfBoundsException e){
			polynomial=new int[0];
			System.err.println("Les indices dans le tableau doivent être positif et croissant.");
		}
	}

	public int getDegree() {
		int i = polynomial.length - 1;
		while (i > 0) {
			if (polynomial[i] != 0)
				return i;
			else
				i--;
		}
		return 0;
	}

	public void addElement(int var, int degree) throws Exception {
		if (getDegree() > degree)
			if (polynomial[degree] != 0)
				throw new Exception("Can't add Element at the degre [" + degree
						+ "], it already exist");
		if (this.getDegree() < degree) {
			int[] tab = new int[degree + 1];
			for (int i = 0; i <= getDegree(); i++)
				tab[i] = polynomial[i];
			tab[degree] = var;
			polynomial = tab;
		} else {
			polynomial[degree] = var;
		}
	}

	public int getValue(int ind) {
		try{
			return polynomial[ind];
		}catch(IndexOutOfBoundsException e){
			System.err.println("Valeur inexistente");
			return -1;
		}
	}

	public void setValue(int ind, int val) {
		try {
			if (val !=0 && val != 1)
				throw new WrongArgumentException(WAE_MESSAGE);
			polynomial[ind] = val;
		} catch (WrongArgumentException wae) {
			wae.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String s = "";
		int degree = getDegree();
		boolean first = true;
		if (degree == 0) {
			s = "Polynomial null";
		} else {
			if (polynomial[0] != 0) {
				s += polynomial[0] + " ";
				first = false;
			}
			if (polynomial.length > 1)
				if (polynomial[1] != 0) {
					if (first) {
						s += polynomial[1] + "X ";
						first = false;
					} else {
						s += ((polynomial[1] > 0) ? "+ " : "") + polynomial[1]
								+ "X ";
					}
				}
			for (int i = 2; i <= degree; i++) {
				if (polynomial[i] != 0) {
					if (!first && polynomial[i] > 0)
						s += "+ ";
					else
						first = false;

					s += polynomial[i] + FileRW.superscript("X" + i + " ");
				}
			}
		}
		s = s.replaceAll("-", "- ");
		return s;
	}

	public String toStringReverse() {
		String s = "";
		int degree = getDegree();
		boolean first = true;
		if (degree == 0) {
			s = "Polynomial null";
		} else {
			for (int i = degree; i >= 2; i--) {
				if (polynomial[i] != 0) {
					if (!first && polynomial[i] > 0)
						s += "+ ";
					else
						first = false;

					s += polynomial[i] + FileRW.superscript("X" + i + " ");
				}
			}
			if (polynomial.length > 1)
				if (polynomial[1] != 0) {
					if (first) {
						s += polynomial[1] + "X ";
						first = false;
					} else {
						s += ((polynomial[1] > 0) ? "+ " : "") + polynomial[1]
								+ "X ";
					}
				}
			if (polynomial[0] != 0) {
				if (!first && polynomial[0] > 0)
					s += "+ ";
				s += polynomial[0];
			}

		}
		s = s.replaceAll("-", "- ");
		return s;
	}
}
