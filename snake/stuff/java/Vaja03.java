
public class Vaja03 {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println(Prvi(2));
		System.out.println(Drugi(1f));
		System.out.println(Tretji(0.1f));

	}
	
	public static float Prvi(float x)
	{
		float rezultat = 1;
		float clen = 1;
		float n = 1;
		while(true)
		{
			// z izpeljavo formule ugotovimo da je vsak naslednji clen enak
			// prejsnjemu*x/n
			clen = clen*x/n;
			if(clen <= (1e-5))
				break;
			rezultat += clen;
			n++;
		}

		return rezultat;
	}
	
	// izracuna e^x rekurzivno.
	public static float PrviRek(float prejsnji, float x, float n)
	{
		if(Math.abs(prejsnji*x/n) <= (1e-5)) 
			return prejsnji;
		return prejsnji+PrviRek(prejsnji*x/n, x, n+1);
	}

	
	public static float Drugi(float x)
	{
		float rezultat = 1;
		float prejsnji = 1;
		float predznak = -1f;
		float a = -5f;
		float n = 1;
		
		while(true)
		{
			a+=4;
			float clen = prejsnji*(a)*x/(4f*n);
			prejsnji = clen;

			rezultat += predznak*clen;
			if(Math.abs(clen) <= (1e-5))
				return rezultat;
			predznak *= -1f;
			n++;
		}
		
	}
	
	// izracuna arccos x
	public static float Tretji(float x)
	{
		// prvi clen je enak x
		float clen = x;
		// prva stopnja X-a ki jo bomo racunali je 3
		float n = 3;
		// rezultat je enak zacetnemu clenu
		float rezultat = clen;
		// zadnja spodnja soda vrednost (2,4,6,8,...).. za zacetek enaka 1
		// vendar se takoj znotraj zanke nastavi na 2.
		float b = 1;
		
		while(true)
		{
			b *= 2;
			// vsak naslednji clen je enak (prejsnjemu*(n-2)^2*x^n)/noviB*n (kvadrat zato, da se unici
			// zadnji faktor (lihi) iz konca prejsnjega clena  (prejsnji n)
			clen = (clen*(n-2)*(n-2)*(float)Math.pow(x, n)) / (b*n);
			// nastavimo novo stopnjo (samo lihe) in rezultatu pristejemo clen.
			n += 2;
			rezultat += clen;
			
			// ce je manjsi od sprejemljive napake... koncamo
			if(clen <= (1e-5))
				break;
		}	
		// rezultat je PI/2 - rezultat (ker je rezultat vsota clenov znotraj oklepaja enacbe)
		return (float)Math.PI/2.0f - (rezultat);
	}

}
