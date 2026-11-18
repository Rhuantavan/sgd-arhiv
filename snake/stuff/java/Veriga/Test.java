
public class Test 
{
	public static Node[][] tab1;  //deklaracija 2D tabele
	public static int maksimalniSestevek;
	public static void main(String[] args)
	{
		int stVrstic = 20;
		int spMeja = 0;
		int zgMeja = 99;
		
		if(args.length >= 3)  // meje zaradi enostavnosti preberemo kot argumente.
		{
			stVrstic = Integer.parseInt(args[0]);
			spMeja = Integer.parseInt(args[1]);
			zgMeja = Integer.parseInt(args[2]);
		}
		
		generirajTrikotnik(stVrstic, spMeja, zgMeja);  //klicemo metodo, ki generira trikotnik
		izracunajPoti(stVrstic);
		
		// poiscemo najvecji koncni sestevek (== zadnji vrstici sestevkov)
		for(int i=0;i<stVrstic;i++) 
		{
			if (tab1[stVrstic-1][i].sestevek > maksimalniSestevek) 
			{
				maksimalniSestevek = tab1[stVrstic-1][i].sestevek;
			}
		}
		
		// izpisemo verigo vsake vsote, ki je enaka maksimalnemu sestevku
		for(int i=0;i<stVrstic;i++) 
		{
			if (tab1[stVrstic-1][i].sestevek == maksimalniSestevek) 
			{
				zapisiPoti(stVrstic-1, tab1[stVrstic-1][i],new int[stVrstic]);
			}
		}
	}



	 public static void generirajTrikotnik(int n, int sp, int zg)  // metoda, ki napolni tabelo in izrise trikotnik števil
	 {
		 int st = 0;
		 tab1 = new Node[n][n];

		 for(int i=0;i<n;i++)
		 {
			 for(int j=0;j<=i;j++)
			 {
				 tab1[i][j] = new Node((int)((Math.random()*(zg-sp+1))+sp));
			 }
		 }
	
		 // izpise trikotnik
		 for(int i=0;i<n;i++)
		 {
			 st = st+2;  
			   
			 for(int j=0;j<=i;j++)
			 {
				 System.out.print(tab1[i][j].vrednost + "  ");
			 }
			 System.out.println();
		 }
		 System.out.println();
		 
	}
	 
	public static void izracunajPoti(int stVrstic) 
	{
		for (int i=0;i<stVrstic;i++) 
		{
			for (int j=0;j<=i;j++)
			{
				if (i != stVrstic-1) // ce ni zadnja vrstica
				{
					tab1[i+1][j].dodajStarsa(tab1[i][j]);
					tab1[i+1][j+1].dodajStarsa(tab1[i][j]);
				}
				if (i != 0)	// ce nismo na vrhu izracunaj sestevek
				{
					tab1[i][j].izracunajSestevek();
				} 
				else // drugace pa za sestevek vzemi kar vrednost clena
				{
					tab1[i][j].sestevek = tab1[i][j].vrednost;
				}
			}
		}
	}
	
	private static boolean zeZapisano = false;
	public static void zapisiPoti(int i, Node st, int[] vrednosti)
	{
		vrednosti[i] = st.vrednost;
	  	if (i == 0) 
	  	{
	  		if(zeZapisano == false)
	  		{
	  			System.out.println("\nNajdaljsa veriga: " + maksimalniSestevek);
	  			zeZapisano = true;
	  		}
	  		
	  		for (int j=0;j<vrednosti.length;j++)
	  		{
	  			System.out.print(vrednosti[j] + " ");
	  		}
	  		System.out.println();
	  	} 
	  	else 
	  	{
	  		if (st.bestParent == BestParentEnum.Left)
	  		{
	  			zapisiPoti(i-1,st.left, vrednosti);    
	  		}
	  		else if (st.bestParent == BestParentEnum.Right)
	  		{
	  			zapisiPoti(i-1,st.right, vrednosti);    
	  		}
	  		else
	  		{
	  			zapisiPoti(i-1,st.left, vrednosti);          
	  			zapisiPoti(i-1,st.left, (int[])vrednosti.clone());          
	  		}
	  	}
	}

}
