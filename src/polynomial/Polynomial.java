package polynomial;

import io.FileRW;

public class Polynomial {
	private int[] polynomial = { 0 };
	private int basis;

	public Polynomial(int[] p) {
		this.polynomial = p;
		this.basis = 2;
	}

	public Polynomial(int[] p, int basis) {
		this(p);
		this.basis = basis;
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
				s += polynomial[0];
				first = false;
			}
			if (polynomial[1] != 0) {
				if (first) {
					s += polynomial[1] + "X ";
					first = false;
				} else {
					s += "+ " + polynomial[1] + "X ";
				}
			}
			for (int i = 2; i <= order; i++) {
				if (polynomial[i] != 0) {
					if (!first)
						s += "+ ";
					else
						first = false;

					s += Integer.toString(polynomial[i], basis)
							+ FileRW.superscript("X"
									+ Integer.toString(i, basis) + " ");
				}
			}
		}
		return s;
	}
}
