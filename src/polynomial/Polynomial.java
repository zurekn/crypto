package polynomial;

import java.util.Arrays;

import exception.ExistingElementException;
import exception.WrongArgumentException;
import io.FileRW;

public class Polynomial {
	private final String WAE_MESSAGE = "Les coefficients doivent �tre 0 ou 1";

	private int[] polynomial = {};

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

	public Polynomial(int[] p) {
		try {
			if (p.length > 0) {
				polynomial = new int[p[p.length - 1] + 1];
				for (int i = 0; i < p.length; i++) {
					polynomial[p[i]] = 1;
				}
			}
		} catch (NullPointerException npe) {
			System.err.println("Tableau null.");
			polynomial = new int[0];
		} catch (IndexOutOfBoundsException e) {
			polynomial = new int[0];
			System.err
					.println("Les indices dans le tableau doivent �tre positif et croissant.");

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

	public void addElement(int var, int degree) throws ExistingElementException {
		if (getDegree() > degree)
			if (polynomial[degree] != 0)
				throw new ExistingElementException("Can't add Element at the degre [" + degree
						+ "], it already exist");
		if (this.getDegree() < degree) {
			int[] tab = new int[degree + 1];
			for (int i = 0; i <= getDegree() && i < polynomial.length; i++){
				tab[i] = polynomial[i];}
			tab[degree] = var;
			polynomial = tab;
		} else {
			polynomial[degree] = var;
		}
	}

	public int getValue(int ind) {
		try {
			return polynomial[ind];
		}catch(IndexOutOfBoundsException e){
			//System.err.println("Valeur inexistente");
			return 0;
		}
	}
	
	public int[] getCoefficients(){
		return polynomial;
	}
	
	public int[] getIndices(){
		int n = 0;
		for(int i = 0; i < polynomial.length;i++)
			if(polynomial[i]==1)
				n++;
		int[] tab = new int[n];
		int j=0;
		for(int i = 0; i < polynomial.length;i++){
			if(polynomial[i]==1){
				tab[j]=i;
				j++;
			}
		}
		return tab;
	}

	public void setValue(int ind, int val) {
		try {
			if (val != 0 && val != 1)
				throw new WrongArgumentException(WAE_MESSAGE);
			polynomial[ind] = val;
		} catch (WrongArgumentException wae) {
			wae.printStackTrace();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polynomial other = (Polynomial) obj;
		if (Arrays.equals(polynomial, other.polynomial))
			return true;
		return false;
	}

	/**
	 * 
	 * @param q
	 * @return 1 if q superior, 0 if equal, -1 if q inferior
	 */
	public int compare(Polynomial q) {
		int dQ = q.getDegree();
		int dP = this.getDegree();
		if (dQ > dP)
			return 1;
		if (dP > dQ)
			return -1;
		if (Arrays.equals(this.polynomial, q.polynomial))
			return 0;
		int i = dP;
		while (i >= 0) {
			if (q.polynomial[i] > this.polynomial[i])
				return -1;
			if (q.polynomial[i] < this.polynomial[i])
				return 1;
			i--;
		}
		return 0;
	}

	@Override
	public String toString() {
		String s = "";
		int degree = getDegree();
		boolean first = true;
		if (degree == 0 && polynomial[0]==0) {
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
		if (degree == 0 && polynomial[0]==0) {
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
