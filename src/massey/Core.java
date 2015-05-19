package massey;

import polynomial.Polynomial;
import polynomial.PolynomialCalculator;
import engine.LFSR;

public abstract class Core {

	public static LFSR findLFSR(String input){
		LFSR lfsr = null;
		int m = -1;
		int L = 0;
		int d = 0;
		int []tab = {0};
		Polynomial g = new Polynomial(tab);
		Polynomial f = new Polynomial(tab);
		Polynomial t = new Polynomial(tab);
		for(int n = 0; n < input.length(); n++){
			d = Integer.parseInt(""+input.charAt(n));
			
			//Somme de 1 à L de Ci * input(n-i)
			for(int i = 1; i <= L; i++)
				d += Integer.parseInt(""+input.charAt(n-i)) * f.getCoefficients()[i] ;
			d = d%2;
			
			if(d == 1){
				t = f;
				int []tab2 = {n-m};
				Polynomial X = new Polynomial(tab2);
				f = (PolynomialCalculator.substract(f, PolynomialCalculator.multiply(g, X)));
				if (2 * L <= n){
					L = n +1 - L;
					m = n;
					g = t;
				}
			}
			System.out.println(n+"\t"+input.charAt(n)+"\t"+d+"\t"+L+"\t"+f.toString()+"\t"+m+"\t"+g.toString());
		}
		String etat = input.substring(0, L);
		
		lfsr = new LFSR(L, f.getCoefficients(), Integer.parseInt(etat, 2));
		
		return lfsr;
	}
}
