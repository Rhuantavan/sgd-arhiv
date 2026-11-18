
public class Analiza 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String niz1 = "AbcDEFghijklm";
		String niz2 = "otorinolaringolog";
		String niz3 = "Lorem ipsum dolor sit amet consectetuer\t\tadipiscing elit.";
		
		System.out.println("st. malih crk v nizu \""+niz1+"\n je: "+steviloMalihCrk(niz1));
		System.out.println();
		
		System.out.println("st. znakov 'o' v nizu \""+niz2+"\n je: "+steviloZnakov(niz2, 'o'));
		System.out.println();
		
		System.out.println("st. besed v nizu \""+niz3+"\n je: "+steviloBesed(niz3));
		System.out.println();
		
		System.out.println("beseda na mestu 5 v nizu \""+niz3+"\n je: "+besedaNaMestu(niz3, 7));
		System.out.println();
		
		System.out.println("najvecje stevilo enakih znakov v nizu \""+niz2+"\n je: "+steviloEnakihZnakov(niz2));
		System.out.println();
		
		System.out.println("beseda z najvec enakimi znaki v nizu \""+niz3+"\n je: "+besedaZNajvecEnakimiZnaki(niz3));
		System.out.println();
		
		
	}
	
	public static int steviloMalihCrk(String niz)
	{
		// AbcdeFGh
		// ABCDEFGH
		// presteje razlike
		int stevec = 0;
		String niz2 = niz.toUpperCase();
		for(int i=0; i<niz.length(); i++)
		{
			if(niz.charAt(i) != niz2.charAt(i))
				stevec++;
		}
		return stevec;
	}
	
	public static int steviloZnakov(String niz, char c)
	{
		int stevec = 0;
		for(int i=0; i<niz.length(); i++)
		{
			if(niz.charAt(i) == c)
				stevec++;
		}
		return stevec;
	}
	
	private static boolean jePrejsnjiWhitespace = false;
	public static int steviloBesed(String niz)
	{
		int stevec = 1;
		
		for(int i=0; i<niz.length(); i++)
		{
			if(		niz.charAt(i) == ' ' ||
					niz.charAt(i) == '\t' ||
					niz.charAt(i) == '\r' ||
					niz.charAt(i) == '\n' ||
					niz.charAt(i) == '\f')
			{
				if(jePrejsnjiWhitespace == true)
				{
					stevec--;
				}
				stevec++;
				jePrejsnjiWhitespace = true;
			}
			else
				jePrejsnjiWhitespace = false;
				
		}
		return stevec;
	}

	public static String besedaNaMestu(String niz, int m)
	{
		if(m > steviloBesed(niz))
			return "";
		else
		{
			int stevec = 1;
			String beseda = "";
			for(int i=0; i<niz.length(); i++)
			{
				if(		niz.charAt(i) == ' ' ||
						niz.charAt(i) == '\t' ||
						niz.charAt(i) == '\r' ||
						niz.charAt(i) == '\n' ||
						niz.charAt(i) == '\f')
				{	// ce je bil prejsnji znak whitespace in je ta tudi, ne stejemo tega kot nova beseda.
					if(jePrejsnjiWhitespace == true)
					{
						stevec--;
					}
					stevec++;
					jePrejsnjiWhitespace = true;
				}
				else
					jePrejsnjiWhitespace = false;

				if(stevec == m)
				{
					while(	(niz.charAt(i) == ' ' ||
							niz.charAt(i) == '\t' ||
							niz.charAt(i) == '\r' ||
							niz.charAt(i) == '\n' ||
							niz.charAt(i) == '\f' ) &&
							i < niz.length() )
						i++;
					while(	niz.charAt(i) != ' ' &&
							niz.charAt(i) != '\t' &&
							niz.charAt(i) != '\r' &&
							niz.charAt(i) != '\n' &&
							niz.charAt(i) != '\f' &&
							i < niz.length())
					{
						beseda += niz.charAt(i);
						i++;
					}
					return beseda;
				}
			}	
		}
		return "";
	}
		
	public static int steviloEnakihZnakov(String niz)
	{
		int stZnakov = 0;
		int maksZnakov = 0;
		// [97, 122]
		for(char ch=97; ch <= 122; ch++)
		{
			stZnakov = steviloZnakov(niz, ch);
				
			if(stZnakov > maksZnakov)
				maksZnakov = stZnakov;
		}
		return maksZnakov;
	}
	
	public static String besedaZNajvecEnakimiZnaki(String niz)
	{
		int stBesed = steviloBesed(niz);
		int stZnakov = 0, maksZnakov = 0;
		
		String beseda = "";
		String besedaZNajvec = "";
		
		for(int i=0; i < stBesed; i++)
		{
			beseda = besedaNaMestu(niz, i);
			stZnakov = steviloEnakihZnakov(beseda);
			if(stZnakov > maksZnakov)
			{
				maksZnakov = stZnakov;
				besedaZNajvec = beseda;
			}
			
		}
		return besedaZNajvec;
	}
}
