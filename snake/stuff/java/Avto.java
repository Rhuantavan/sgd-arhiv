public class Avto 
{
	//konstructorji
	public Avto()
	{
		stevilka = 0;
		najvecjaHitrost = 0;
		velikostRezervoarja = 0;
		povprecnaPoraba = 0;
		avtoVzgan = false;
		stevecKm = 0;
		trenutnaHitrost = 0;
		kolicinaGoriva = 0;
	}

	public Avto(int s)
	{
		stevilka = s;
		najvecjaHitrost = 0;
		velikostRezervoarja = 0;
		povprecnaPoraba = 0;
		avtoVzgan = false;
		stevecKm = 0;
		trenutnaHitrost = 0;
		kolicinaGoriva = 0;
	}
	
	public Avto(int s, double h, double v, double p)
	{
		stevilka = s;
		najvecjaHitrost = h;
		velikostRezervoarja = v;
		povprecnaPoraba = p;
		avtoVzgan = false;
		stevecKm = 0;
		trenutnaHitrost = 0;
		kolicinaGoriva = 0;
	}

	//spremenljivke
	public int stevilka;
	public double najvecjaHitrost;
	public double velikostRezervoarja;
	public double povprecnaPoraba;
	public boolean avtoVzgan;
	public double stevecKm;
	public double trenutnaHitrost;
	public double kolicinaGoriva;

	//metode
	public void nastaviStevilko(int s)
	{
		stevilka = s;
	}

	public void nastaviNajvecjoHitrost(double h)
	{
		najvecjaHitrost = h;
	}

	public void nastaviVelRezervoarja(double l)
	{
		velikostRezervoarja = l;
	}

	public void nastaviPovPorabo(double l)
	{
		povprecnaPoraba = l;
	}

	public void napolniGorivo()
	{
		kolicinaGoriva = velikostRezervoarja;
	}

	public void vzgiAvto()
	{
		if (kolicinaGoriva >= 0.05 && !avtoVzgan)
		{
			kolicinaGoriva -= 0.05;
			avtoVzgan = true;
		}
	}

	public void ugasniAvto()
	{
		if (trenutnaHitrost == 0 && avtoVzgan)
			avtoVzgan = false;
	}

	public double vozi(int t)
	{
		if (avtoVzgan && trenutnaHitrost > 0)
		{
			double prevozeno = trenutnaHitrost * ((double)t / 60);
			double gorivo = prevozeno * povprecnaPoraba / 100;

			if (gorivo > kolicinaGoriva)
			{
				prevozeno = kolicinaGoriva / (povprecnaPoraba / 100);
				kolicinaGoriva = 0;
			}
			else
			{
				kolicinaGoriva -= gorivo;
			}

			stevecKm += prevozeno;

			return prevozeno;
		}

		return 0;
	}

	public void spremeniHitrost(double h)
	{
		if (h >= 0 && h <= najvecjaHitrost && avtoVzgan)
			trenutnaHitrost = h;
	}

	public void ustavi()
	{
		trenutnaHitrost = 0;
	}

	public double najvecjiCas()
	{
		if (avtoVzgan && trenutnaHitrost > 0)
			return kolicinaGoriva / (trenutnaHitrost * (povprecnaPoraba / 100)) * 60;

		return 0;
	}

	public double doseg(double l)
	{
		return l / (povprecnaPoraba / 100);
	}

	public double casVoznje(double d)
	{
		double cas = d / trenutnaHitrost * 60;
		double potrebnoGorivo = d * povprecnaPoraba / 100;

		int tankanja = 0;
		if (kolicinaGoriva < potrebnoGorivo)
		{
			tankanja = 1 + (int)((potrebnoGorivo - kolicinaGoriva) / velikostRezervoarja);
			cas += tankanja * 15;
		}

		return cas;
	}

	public double potovalnaHitrost(double d, double t)
	{
		double potrebnoGorivo = d * povprecnaPoraba / 100;
		
		int tankanja = 0;
		if (velikostRezervoarja < potrebnoGorivo)
		{
			tankanja = (int)(potrebnoGorivo / velikostRezervoarja);
			t += tankanja * 15;
		}

		return d / (t / 60);
	}

	public int boljsi(Avto a)
	{
		double cas1, cas2;

		//izraèun za avto 1
		double cas = 1000 / najvecjaHitrost * 60;
		double potrebnoGorivo = 1000 * povprecnaPoraba / 100;

		int tankanja = 0;
		if (velikostRezervoarja < potrebnoGorivo)
		{
			tankanja = 1 + (int)(potrebnoGorivo / velikostRezervoarja);
			cas += tankanja * 15;
		}
		cas1 = cas;

		//izra?un za avto 2
		cas = 1000 / a.najvecjaHitrost * 60;
		potrebnoGorivo = 1000 * a.povprecnaPoraba / 100;

		tankanja = 0;
		if (a.velikostRezervoarja < potrebnoGorivo)
		{
			tankanja = 1 + (int)(potrebnoGorivo / a.velikostRezervoarja);
			cas += tankanja * 15;
		}
		cas2 = cas;

		if (cas1 < cas2)
			return stevilka;
		else
			return a.stevilka;
	}

	public void izpisi()
	{
		System.out.println();
		System.out.println("---------------" + stevilka + "---------------");
		System.out.println("Avto s stevilko " + stevilka);
		System.out.println("ima najvecjo hitrost " + najvecjaHitrost + "km/h, ");
		System.out.println("velikost rezervoarja " + velikostRezervoarja + "l, ");
		System.out.println("povprecno porabo goriva " + povprecnaPoraba + "l / 100km");
		System.out.println("in na stevcu " + stevecKm + "km.");
		if (avtoVzgan)
		{
			System.out.println("Trenutno je avto prizgan ");
			System.out.println("in vozi s hitrostjo " + trenutnaHitrost + "km/h, ");
		}
		else			
		{
			System.out.println("Trenutno avto ni prizgan ");
			System.out.println("zato je njegova hitrost " + trenutnaHitrost + ", ");
		}
		System.out.println("in ima v rezervoarju se " + kolicinaGoriva + "l goriva.");
		System.out.println("--------------/" + stevilka + "---------------");
	}
}