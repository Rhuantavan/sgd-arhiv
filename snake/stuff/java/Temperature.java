import java.util.AbstractCollection;

public class Temperature {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double[][][] t = new double[8][10][12];
		napolniTemperature(t);
		izpisiKrajevneTemperature(t, 3, 5);
		izpisiTemperaturePoLetih(t);
		triNajnizjeTemperature(t);

	}
	
	public static void napolniTemperature(double[][][] t)
	{
		for(int kraj=0; kraj < 8; kraj++)
		{
			for(int leto=0; leto < 10; leto++)
			{
				for(int mesec = 0; mesec < 12; mesec++)
				{
					if(mesec >= 2 && mesec <= 4)		// mar, apr, maj
					{
						t[kraj][leto][mesec] = randomRange(0f, 25f);
					}
					else if(mesec >= 5 && mesec <= 7)	// jun, jul, avg!
					{
						t[kraj][leto][mesec] = randomRange(20f, 35f);
					}
					else if(mesec >= 8 && mesec <= 10)	// sep, okt, nov!
					{
						t[kraj][leto][mesec] = randomRange(0f, 25f);
					}
					else								// dec, jan, feb
					{
						t[kraj][leto][mesec] = randomRange(-20f, 5f);
					}
						
				}

				// sortiraj jun, jul, avg
				sortiraj(t[kraj][leto], 5, 7, true);		
				// sortiraj sep, okt, nov
				sortiraj(t[kraj][leto], 8, 10, false);
			}
		}
		
	}
	
	public static void izpisiKrajevneTemperature(double[][][] t, int k, int l)
	{
		double sumTemperatures = 0;
		System.out.println("Krajevne meseène temperature za leto ["+l+"] v kraju ["+k+"]:");
		for(int mesec=0; mesec < 12; mesec++)
		{
			sumTemperatures += t[k][l][mesec];
			System.out.print((float)t[k][l][mesec]+"\t");
		}
		System.out.println();
		System.out.println("Povpreèna letna temperatura je bila: "+((float)sumTemperatures/12f)+"\n");
		
	}
	
	public static void izpisiTemperaturePoLetih(double[][][] t)
	{
		double sumTemperatures = 0;
		double povprecnaTemperatura = 0;
		System.out.println("Letne povpreène temperature po krajih: ");
		for(int leto=0; leto < 10; leto++)
		{
			System.out.println("  + Leto ["+leto+"]:");
			for(int kraj=0; kraj < 8; kraj++)
			{
				povprecnaTemperatura = krajevnoLetnoPovprecje(t, kraj, leto);
				sumTemperatures += povprecnaTemperatura;
				System.out.println("  | - Kraj ["+kraj+"]: "+(float)povprecnaTemperatura);
			}
		}
		System.out.println("Povpreèna letna temperatura je bila: "+((float)sumTemperatures/8f)+"\n");
			
	}
	
	public static void triNajnizjeTemperature(double[][][] t)
	{
		// temp, kraj, leto, mesec
		int[][] vrednosti = new int[240][4];
		int index = 0;
		double minDoZdaj = 40;
		for(int kraj=0; kraj < 8; kraj++)
		{
			for(int leto=0; leto < 10; leto++)
			{
				for(int mesec = 0; mesec < 12; mesec++)
				{
					if(t[kraj][leto][mesec] <= minDoZdaj)
					{
						vrednosti[index][0] = kraj;
						vrednosti[index][1] = leto;
						vrednosti[index][2] = mesec;
						index++;
						minDoZdaj = t[kraj][leto][mesec];
						
					}
					
				}
			}
		}
		
		int j=4;
		System.out.println("+ Najnižje temperature so bile zabeležene:");
		for(int i=index-1; i > index-4 && i >= 0; i--)
		{
			System.out.println("  | - v kraju ["+vrednosti[i][0]+"]"+", leta ["+vrednosti[i][1]+"]"+", v mesecu ["+vrednosti[i][2]+"] (temperatura: "+t[vrednosti[i][0]][vrednosti[i][1]][vrednosti[i][2]]+" .");
		}
	}
	
	private static double krajevnoLetnoPovprecje(double[][][] t, int k, int l)
	{
		double sumTemperatures = 0;
		for(int mesec=0; mesec < 12; mesec++)
			sumTemperatures += t[k][l][mesec];
		return sumTemperatures / 12d;
	}
	
	private static void sortiraj(double[] t, int mesecZacetek, int mesecKonec, boolean raste)
	{
		if(raste)
		{
			for (int i = mesecZacetek; i <= mesecKonec; i++) 
			{
				int min = i;
				// poisci najmanjso od tu do konca.
				for (int j = i; j <= mesecKonec; j++)
				{
					if (t[j] < t[min])
						min = j;
				}
				// zamenjaj s prostim mestom.
				double tmp;
				tmp = t[i];
				t[i] = t[min];
				t[min] = tmp;
			}
		}
		else
		{
			for (int i = mesecZacetek; i <= mesecKonec; i++) 
			{
				int max = i;
				// poisci najmanjso od tu do konca.
				for (int j = i; j <= mesecKonec; j++)
				{
					if (t[j] > t[max])
						max = j;
				}
				// zamenjaj s prostim mestom.
				double tmp;
				tmp = t[i];
				t[i] = t[max];
				t[max] = tmp;
			}
		}
		
	}
	
	private static java.util.Random rand = new java.util.Random();
	private static double randomRange(float min, float max)
	{
		float range = Math.abs(max-min);
		return (double)rand.nextFloat()*range+min;
	}

}
