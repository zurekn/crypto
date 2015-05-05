package polynomial;

import io.FileRW;

public class Polynomial {
	private int[] polynomial = { 0 };
	private int basis;

	private void checkInitialValues(){
		for(int i = 0 ; i <= getOrder();i++){
			polynomial[i]=(polynomial[i]>=basis)?basis-1:polynomial[i];
		}
	}
	
	public Polynomial(int[] p) {
		if (p != null)
			this.polynomial = p;
		else
			this.polynomial = new int[1];
		this.basis = 2;
		checkInitialValues();
		
	}

	public Polynomial(int[] p, int basis) {
		this(p);
		this.basis = basis;
		checkInitialValues();
	}

	public int getOrder() {
		int i = polynomial.length - 1;
		while (i > 0) {
			if (polynomial[i] != 0)
				return i;
			else
				i--;
		}
		return 0;
	}

	public int getBasis() {
		return this.basis;
	}

	public void addElement(int var, int order) throws Exception {
		if (getOrder() < order)
			if (polynomial[order] != 0)
				throw new Exception("Can't add Element at the degre [" + order
						+ "], it already exist");
		polynomial[order] = var;
	}

	public int getValue(int ind) {
		return polynomial[ind];
	}

	@Override
	public String toString() {
		String s = "";
		int order = getOrder();
		boolean first = true;
		if (order == 0) {
			s = "Polynomial null";
		} else {
			if (polynomial[0] != 0) {
				s += Integer.toString(polynomial[0], basis);
				first = false;
			}
			if (polynomial[1] != 0) {
				if (first) {
					s += Integer.toString(polynomial[1], basis) + "X ";
					first = false;
				} else {
					s += "+ " + Integer.toString(polynomial[1], basis) + "X ";
				}
			}
			for (int i = 2; i <= order; i++) {
				if (polynomial[i] != 0) {
					if (!first)
						s += "+ ";
					else
						first = false;

					s += Integer.toString(polynomial[i], basis)
							+ FileRW.superscript("X" + i + " ");
				}
			}
		}
		return s;
	}
}
