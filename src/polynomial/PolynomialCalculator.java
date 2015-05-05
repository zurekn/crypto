package polynomial;

import Exception.DifferentBasisException;

public abstract class PolynomialCalculator {
	public static Polynomial add(Polynomial p, Polynomial q)
			throws DifferentBasisException {
		checkBasis(p, q);
		return null;
	}

	public static Polynomial multiply(Polynomial p, Polynomial q) throws DifferentBasisException {
		checkBasis(p, q);
		return null;
	}

	public static Polynomial substract(Polynomial p, Polynomial q) throws DifferentBasisException {
		checkBasis(p, q);
		return null;
	}

	public static Polynomial pow(Polynomial p, int n) {

		return null;
	}

	public static boolean isPrimary(Polynomial p) {
		return false;
	}

	private static boolean checkBasis(Polynomial p, Polynomial q)
			throws DifferentBasisException {
		if (p.getBase() != q.getBase())
			throw new DifferentBasisException(
					"Error can't calculate with two differents basis polynomials");
		return true;
	}
}
