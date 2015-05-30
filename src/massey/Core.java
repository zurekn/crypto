package massey;

import java.util.HashMap;

import polynomial.Polynomial;
import polynomial.PolynomialCalculator;
import engine.LFSR;

public abstract class Core {

	public static byte[] PDF_HEADER = {37,80,68,70,45,49,46,52,10,37};
	
	public static HashMap<String, byte[]> HEADERS = new HashMap<String, byte[]>();
	
	public static final int limit = 50;
	
	public static void initCore(){
		HEADERS.put("pdf", PDF_HEADER);
	}
	
	public static LFSR findLFSR(String input){
		LFSR lfsr = null;
		int m = -1;
		int L = 0;
		int d = 0;
		int []tab = {0};
		Polynomial g = new Polynomial(tab);
		Polynomial f = new Polynomial(tab);
		Polynomial t = new Polynomial(tab);
		for(int n = 0; n < input.length() && n <= limit; n++){
			d = Integer.parseInt(""+input.charAt(n));
			
			//Somme de 1 à L de Ci * input(n-i)
			for(int i = 1; i <= L; i++){
				try{
				d += Integer.parseInt(""+input.charAt(n-i)) * f.getCoefficients()[i] ;
				}catch(ArrayIndexOutOfBoundsException e){
					
				}
			}
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

		String s="";
		for(int i = 0; i < L ;i++){
			s+=etat.charAt(L-1-i);
		}
		if(s.length() > 32)
			s = s.substring(0, 30);
		lfsr = new LFSR(L, f.getIndices(), Integer.parseInt(s, 2));
//		10001111011101010110000001101000001000010101111111
//		10001111011101010110000001101000001000010101111111
		return lfsr;
	}
}
