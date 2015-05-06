package polynomial;

import exception.DifferentBasisException;

public abstract class PolynomialCalculator {

	/**
	 * 
	 * @param p
	 *            , Polynomial
	 * @param q
	 *            , Polynomial
	 * @return p + q
	 * @throws DifferentBasisException
	 */
	public static Polynomial add(Polynomial p, Polynomial q)
			throws DifferentBasisException {
		checkBasis(p, q);
		int basis = p.getBasis();
		int oP = p.getOrder(), oQ = q.getOrder();
		int[] result = new int[Math.max(oP, oQ) + 1];
		int i;
		for (i = 0; i <= oP && i <= oQ; i++)
			result[i] = mod(p.getValue(i) + q.getValue(i), basis);
		for (int j = i; j <= oP; j++)
			result[j] = p.getValue(j);
		for (int j = i; j <= oQ; j++)
			result[j] = p.getValue(j);
		return new Polynomial(result, basis);
	}

	/**
	 * 
	 * @param p
	 *            , Polynomial
	 * @param q
	 *            , Polynomial
	 * @return p x q
	 * @throws DifferentBasisException
	 */
	public static Polynomial multiply(Polynomial p, Polynomial q)
			throws DifferentBasisException {
		checkBasis(p, q);
		int[] result = new int[p.getOrder() + q.getOrder()+1];
		for (int i = 0; i < result.length; i++)
			result[i] = 0;

		for (int i = 0; i <= p.getOrder(); i++) {
			for (int j = 0; j <= q.getOrder(); j++) {
				result[i + j] += mod(p.getValue(i) * q.getValue(j), p.getBasis());
				result[i+j]=mod(result[i + j], q.getBasis());
			}
		}

		return new Polynomial(result,q.getBasis());
	}

	/**
	 * 
	 * @param p
	 *            , Polynomial
	 * @param q
	 *            , Polynomial
	 * @return p - q
	 * @throws DifferentBasisException
	 */
	public static Polynomial substract(Polynomial p, Polynomial q)
			throws DifferentBasisException {
		checkBasis(p, q);
		int basis = p.getBasis();
		int oP = p.getOrder(), oQ = q.getOrder();
		int[] result = new int[Math.max(oP, oQ) + 1];
		int i;
		for (i = 0; i <= oP && i <= oQ; i++)
			result[i] = mod(p.getValue(i) - q.getValue(i), basis);
		for (int j = i; j <= oP; j++)
			result[j] = p.getValue(j);
		for (int j = i; j <= oQ; j++)
			result[j] = mod(- p.getValue(j), basis);
		return new Polynomial(result, basis);
	}

	public static Polynomial pow(Polynomial p, int n) {

		return null;
	}

	public static boolean isPrimary(Polynomial p) {
		return false;
	}

	public static Polynomial divide(Polynomial p, Polynomial q)
			throws DifferentBasisException {
		checkBasis(p, q);
		return null;
	}

	public static Polynomial factorize(Polynomial p) {
		return null;
	}

	private static boolean checkBasis(Polynomial p, Polynomial q)
			throws DifferentBasisException {
		if (p.getBasis() != q.getBasis())
			throw new DifferentBasisException(
					"Error can't calculate with two differents basis polynomials");
		return true;
	}

	private static int mod(int i, int basis) {
		if (basis != 10)
			return i % basis;

		return i;
	}
}
