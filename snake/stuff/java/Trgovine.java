
import java.util.Random;

public class Trgovine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		napolniProdajo();
		izpisiVsoProdajo();
		letnaPovprecja();
		int leto = args.length > 0 ? Integer.parseInt(args[0]) : 2;
		System.out.println("Najboljša trgovina leta ["+leto+"]: "+najTrgovinaLeta(leto));
		int[] letoTrgovina = najProdajaLeta();
		System.out.println("Najboljša trgovina je št. "+letoTrgovina[0]+" v najboljšem letu "+letoTrgovina[1]);

	}
	
	
	private static int[][] prodaja = new int[7][5];
	
	public static void napolniProdajo()
	{
		Random random = new Random();
		
		for(int trgovina=0; trgovina < 7; trgovina++)
		{
			for(int leto=0; leto < 5; leto++)
			{
				prodaja[trgovina][leto] = random.nextInt(990)+10;
			}		
		}
	}
	
	public static void izpisiVsoProdajo()
	{
		int sumLeto = 0;
		System.out.println("Prodaja (v milijonih SIT)");
		System.out.println("Trgovina:\tLeto:\t0.\t1.\t2.\t3.\t4. |\tSkupaj:");
		for(int trgovina=0; trgovina < 7; trgovina++)
		{
			System.out.print(trgovina+"\t\t");
			sumLeto = 0;
			for(int leto=0; leto < 5; leto++)
			{
				System.out.print("\t"+prodaja[trgovina][leto]);
				sumLeto += prodaja[trgovina][leto];
			}		
			System.out.print(" |\t"+sumLeto);
			System.out.println();
		}
	}
	
	public static void letnaPovprecja()
	{
		int sumLeto = 0;
		System.out.println("Povpreèna letna prodaja (v milijonih SIT)");
		System.out.println("Leto:\tSkupaj:\t\tPovpreèje:");
		for(int leto=0; leto < 5; leto++)
		{
			sumLeto = 0;
			System.out.print((leto)+".\t");
			for(int trgovina=0; trgovina < 7; trgovina++)
			{
				sumLeto += prodaja[trgovina][leto];
			}

			System.out.println(sumLeto+"\t\t"+((float)sumLeto/5f));
		}	
	}
	
	public static int najTrgovinaLeta(int leto)
	{
		if(leto > 5 || leto < 0) return -1;
		
		int maxProdaja = 0;
		int maxIndex = 0;
		
		for(int trgovina=0; trgovina < 7; trgovina++)
		{
			if(prodaja[trgovina][leto] > maxProdaja)
			{
				maxProdaja = prodaja[trgovina][leto];
				maxIndex = trgovina;
			}
		}	
		return maxIndex;
		
	}
	
	public static int[] najProdajaLeta()
	{
		int maxLetoSumIndex = 0;
		int maxSumLeto = 0;
		int sumLeto = 0;
		
		for(int leto=0; leto < 5; leto++)
		{
			for(int trgovina=0; trgovina < 7; trgovina++)
			{
				sumLeto += prodaja[trgovina][leto];			
				if(sumLeto >= maxSumLeto)
				{
					maxSumLeto = sumLeto;
					maxLetoSumIndex = leto;
				}
			}
		}
		return new int[] {najTrgovinaLeta(maxLetoSumIndex), maxLetoSumIndex};
	}
	

}
