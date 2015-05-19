package polynomial;

import exception.ExistingElementException;
import exception.WrongArgumentException;

public abstract class PolynomialCalculator {

	/**
	 * 
	 * @param p
	 *            , Polynomial
	 * @param q
	 *            , Polynomial
	 * @return p + q
	 * @throws WrongArgumentException
	 */
	public static Polynomial add(Polynomial p, Polynomial q) {
		int dP = p.getDegree(), dQ = q.getDegree();
		int max = Math.max(dP, dQ) + 1;
		int[] result = new int[max+1];
		int i;
		for (i = 0; i<max; i++) {
			int n = result[i] + p.getValue(i) + q.getValue(i);
			if (n >= 2 && i < result.length - 1)
				result[i + 1] = 1;
			result[i] = n % 2;
		}
		
		for (int j = i; j <= dP; j++)
			result[j] = p.getValue(j);
		for (int j = i; j <= dQ; j++)
			result[j] = p.getValue(j);

		return new Polynomial(result, true);
	}

	/**
	 * 
	 * @param p
	 *            , Polynomial
	 * @param q
	 *            , Polynomial
	 * @return p x q
	 * @throws WrongArgumentException
	 */
	public static Polynomial multiply(Polynomial p, Polynomial q) {
		int[] result = new int[p.getDegree() + q.getDegree() + 1];
		for (int i = 0; i < result.length; i++)
			result[i] = 0;

		for (int i = 0; i <= p.getDegree(); i++) {
			for (int j = 0; j <= q.getDegree(); j++) {
				result[i + j] += (p.getValue(i) * q.getValue(j)) % 2;
				result[i + j] = (result[i + j]) % 2;
			}
		}

		return new Polynomial(result, true);
	}

	/**
	 * 
	 * @param p
	 *            , Polynomial
	 * @param q
	 *            , Polynomial
	 * @return p - q
	 * @throws WrongArgumentException
	 */
	public static Polynomial substract(Polynomial p, Polynomial q) {
		int dP = p.getDegree(), dQ = q.getDegree();
		int[] result = new int[Math.max(dP, dQ) + 1];
		int i;
		for (i = 0; i <= dP && i <= dQ; i++)
			result[i] = Math.abs(p.getValue(i) - q.getValue(i)) % 2;
		for (int j = i; j <= dP; j++)
			result[j] = p.getValue(j);
		for (int j = i; j <= dQ; j++)
			result[j] = q.getValue(j); //TODO demander si on recopie la valeur ou si on fait passer une retenue

		return new Polynomial(result, true);
	}

	/**
	 * 
	 * @param p
	 * @param n
	 * @return p^n
	 */
	public static Polynomial pow(Polynomial p, int n) {
		int[] tab = { 1 };
		Polynomial q = new Polynomial(tab, true);
		if (n == 0)
			return q;
		if (n == 1)
			return p;
		if (n > 1) {
			q = multiply(p, p);
			for (int i = 3; i <= n; i++)
				q = multiply(q, p);
			return q;
		}
		return null;
	}

	/**
	 * 
	 * @param p
	 * @param q
	 * @return p % q
	 */
	public static Polynomial mod(Polynomial p, Polynomial q) {
		int n = p.compare(q);
		if (n == 1)
			return p;
		if (n == 0)
			return new Polynomial(new int[0]);

		int dP = p.getDegree();
		int dQ = q.getDegree();
		Polynomial remainder = new Polynomial(new int[0]), result = new Polynomial(
				new int[0]);
		boolean done = false;
		while (!done) {
			dP = p.getDegree();
			n = dP - dQ;
			if (n < 0) {
				done = true;
			} else {
				try {
					result.addElement(1, n);
				} catch (ExistingElementException e) {
					System.err
							.println("Réécriture au lieu de rajout dans le polynôme");
					result.setValue(n, 1);
				}
				int[] tab = new int[1];
				tab[0]=n;
				Polynomial tmp = multiply(q,new Polynomial(tab));
				remainder = substract(p,tmp);
				p = remainder;
			}

		}

		return remainder;
	}
	
	public static Polynomial expMod(Polynomial p, int n, Polynomial mod){
		return mod(pow(p,n),mod);
	}

	@Deprecated
	public static boolean isPrimary(Polynomial p) {
		return false;
	}

	/*
	 * public static Polynomial divide(Polynomial p, Polynomial q) throws
	 * DifferentBasisException, OrderException { checkBasis(p, q);
	 * if(q.getOrder() > p.getOrder()) throw new
	 * OrderException("Error, divisor's order can't be greater than dividend's"
	 * );
	 * 
	 * 
	 * return null; }
	 */

	@Deprecated
	public static Polynomial factorize(Polynomial p) {
		return null;
	}

	@Deprecated
	public static Polynomial GCD(Polynomial p, Polynomial q) {
		if (q.getDegree() > p.getDegree()) {
			Polynomial tmp = q;
			q = p;
			p = tmp;
		}
		Polynomial remainder, s, result = null;
		try {
			int[] coefficient;
			do {
				remainder = p;
				int degreeP = p.getDegree();
				int degreeQ = q.getDegree();
				int n;
				Polynomial res = new Polynomial(null);
				do {
					int[] tab = new int[degreeQ];
					n = degreeP - q.getDegree();// TODO infinite loop
					coefficient = new int[n + 1];
					for (int i = 0; i < n; i++)
						coefficient[i] = 0;
					coefficient[n] = p.getValue(degreeP);

					res.addElement(p.getValue(degreeP), n);

					s = new Polynomial(coefficient, true);
					s = multiply(s, remainder);
					System.out.println(remainder.toString() + "\n"
							+ s.toString());
					remainder = substract(remainder, s);
					System.out.println("\t" + n);
				} while (n >= 0);
				s = remainder;
				remainder = q;
				q = s;
				System.out.println(remainder.toString());
			} while (remainder.getDegree() > 0);
			if (remainder.getValue(0) == 0)
				result = q;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
