
public class Kodiraj {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String niz1 = "RCUNLNIK JE ZAKON";
		StringBuffer niz2 = kodiraj(niz1);
		System.out.println(niz2.toString());
		System.out.println(dekodiraj(niz2));

	}
	
	
	public static StringBuffer kodiraj(String n)
	{
		StringBuffer out = new StringBuffer();
		n = n.toUpperCase();
		
		for(int i=0; i < n.length(); i++)
		{
			if( n.charAt(i) == 'A')
				out.append("01");
			else if(n.charAt(i) == 'B')
				out.append("02");
			else if(n.charAt(i) >= 67 && n.charAt(i) <= 90)
			{
				out.append(n.charAt(i)-64);
			}
			else
				out.append(n.charAt(i));
			
		}
		
		
		return out;
		
	}

	private static boolean dekodiramoZnak = false;
	private static int prejsnjaCifra = -1;
	public static String dekodiraj(StringBuffer n)
	{
		String out = "";
		for(int i=0; i < n.length() ; i++)
		{
			if(prejsnjaCifra > 0) // primer: 20 
			{
				if(prejsnjaCifra <= 2)
				{
					prejsnjaCifra = prejsnjaCifra*10+n.charAt(i)-48;
				}
				out += (char)(64+prejsnjaCifra);
				prejsnjaCifra = -1; // prenehali z dekodiranjem znaka
			}
			else if(prejsnjaCifra == 0 && n.charAt(i) == '1')
			{
				out += 'A';
				prejsnjaCifra = -1; // prenehali z dekodiranjem znaka
			}
			else if(prejsnjaCifra == 0 && n.charAt(i) == '2')
			{
				out += 'B';
				prejsnjaCifra = -1; // prenehali z dekodiranjem znaka
			}
			else if(prejsnjaCifra == -1)
			{
				prejsnjaCifra = n.charAt(i)-48; // prenehali z dekodiranjem znaka
			}

		}
		
		return out;
		
	}
}
