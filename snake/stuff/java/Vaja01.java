
public class Vaja01 {

	/**
	 * Izpise trikotnik stevil.
	 */
	public static void main(String[] args) 
	{
		// stevilo vrstic
		int stVrstic = 0;
		
		// parsa in preveri ce je vnesen argument veljavno celo stevilo.
		try
		{
			if(args.length > 0)
				stVrstic = Integer.parseInt(args[0]);
		}
		catch(java.lang.IllegalArgumentException ex)
		{
			System.out.println("Vnesen argument ni celo stevilo.");
			return;
		}

		// stevilka ki se trenutno izpisuje na zaslon
		int stevilka;
		for(int vrstica = 1; vrstica <= stVrstic; vrstica++)
		{
			// stevilka se zacne z indeksom trenutne vrstice
			stevilka = vrstica;
			
			// izpisi presledke (ki jih je stVrstic-vrstica)
			for(int presledek = 0; presledek < stVrstic-vrstica; presledek++)
				System.out.print(" ");
			
			// izpisi stevila
			for(int i = 0; i < vrstica; i++)
				System.out.print((stevilka++)%10);
			
			System.out.println();
			
		}

	}

}
