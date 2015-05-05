package polynomial;

import Exception.DifferentBasisException;

public abstract class PolynomialCalculator {
	public static Polynomial add(Polynomial p, Polynomial q)
			throws DifferentBasisException {
		checkBasis(p, q);
		return null;
	}

	/**
	 * 
	 * @param p, Polynomial
	 * @param q, Polynomial
	 * @return p x q
	 * @throws DifferentBasisException
	 */
	public static Polynomial multiply(Polynomial p, Polynomial q) throws DifferentBasisException {
		checkBasis(p, q);
		int[] result = new int[p.getDegree() + q.getDegree()];
		for(ini i = 0; i < result.length; i++)
			result[i] = 0;
		
		for(int i = 0; i < p.getDegree(); i++){
			for(int j = 0; j < q.getDegree(); j++){
				result[i+j] += p.getValue(i) * q.getValue(j);
						mod(result[i+j], q.getBasis());
			}
		}
		
		return new Polynomial(result);
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
		if (p.getBasis() != q.getBasis())
			throw new DifferentBasisException(
					"Error can't calculate with two differents basis polynomials");
		return true;
	}

	private static int mod(int i, int basis){
		if(basis != 10)
			return i%basis;
		return i;
	}
}

