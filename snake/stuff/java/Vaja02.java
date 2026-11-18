
public class Vaja02 {

	/**
	 * Christian Goldbach
	 */
	public static void main(String[] args) 
	{
		// za vsa soda stevila od 4 do 100
		for(int x=4; x <= 100; x += 2)
		{
			System.out.print(x);
			// za prvo stevilo vzamemo samo stevila do polovice
			// trenutnega (naprej se kombinacije samo obrnejo.
			for(int prvi=1; prvi <= x/2; prvi++)
			{
				// drugi clen
				int drugi = x-prvi;
				
				// ce sta prvi in drugi clen prastevili ju izpisi.
				if(jePrastevilo(drugi) && jePrastevilo(prvi))
				{
					System.out.print(" = ");
					System.out.print(prvi + " + " + drugi);
				}
			}
			System.out.println();
		}

	}
	
	
	// funkcija, ki vrne true, ce je prastevilo ali false, ce ni
	static boolean jePrastevilo(int n) {

		// predpostavimo da je prastevilo
		boolean jePra = true;

		// preverimo ce je stevilo deljivo s katerimkoli predhodnim stevilom
		// preverjanje je potrebno le do sqrt(n), saj so naprej kvadrati ze
		// preverjenih stevil.
		// prav tako preverimo le liha stevila
		
		if(n%2 == 0) jePra = false;
		
		for (int i = 3; i <= Math.sqrt(n); i += 2) 
		{
			// ce je ostanek pri deljenju 0, potem to ni prastevilo
			if ( (n%i) == 0)
			{
				jePra = false;
				break;
			}
		}

		return jePra;

	}

}
