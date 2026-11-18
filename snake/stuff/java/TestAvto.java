public class TestAvto
{
	public static void main(String[] args) 
	{
		Avto a = new Avto();
		Avto b = new Avto(17456);
		Avto c = new Avto(633, 240.4, 50, 9.8);
		
		a.nastaviStevilko(2409);
		a.nastaviNajvecjoHitrost(250.4);
		a.nastaviVelRezervoarja(51.5);
		a.nastaviPovPorabo(6.9);
		
		b.nastaviNajvecjoHitrost(243);
		b.nastaviVelRezervoarja(50);
		b.nastaviPovPorabo(6.7);

		a.napolniGorivo();
		b.napolniGorivo();
		c.napolniGorivo();

		a.izpisi();
		b.izpisi();
		c.izpisi();

		a.vzgiAvto();
		a.spremeniHitrost(60);
		System.out.println("potovalnaHitrost: " + a.potovalnaHitrost(18,10));
		System.out.println("casVoznje: " + a.casVoznje(20));
		System.out.println("doseg: " + a.doseg(a.velikostRezervoarja - 0.05));
		System.out.println("najvecjiCas: " + a.najvecjiCas());
		System.out.println("vozi: " + a.vozi(1000));
		a.napolniGorivo();
		a.spremeniHitrost(120);
		System.out.println("vozi: " + a.vozi(1000));
		a.ustavi();
		a.ugasniAvto();

		System.out.println("/////////////////////////////////////////");

		b.vzgiAvto();
		b.spremeniHitrost(60);
		System.out.println("potovalnaHitrost: " +  b.potovalnaHitrost(18,10));
		System.out.println("casVoznje: " + b.casVoznje(20));
		System.out.println("doseg: " + b.doseg(20));
		System.out.println("najvecjiCas: " + b.najvecjiCas());
		System.out.println("vozi: " + b.vozi(1000));
		b.napolniGorivo();
		b.spremeniHitrost(120);
		System.out.println("vozi: " + b.vozi(1000));
		b.ustavi();
		b.ugasniAvto();

		System.out.println("/////////////////////////////////////////");

		c.vzgiAvto();
		c.spremeniHitrost(60);
		System.out.println("potovalnaHitrost: " + c.potovalnaHitrost(18,10));
		System.out.println("casVoznje: " + c.casVoznje(20));
		System.out.println("doseg: " + c.doseg(20));
		System.out.println("najvecjiCas: " + c.najvecjiCas());
		System.out.println("vozi: " + c.vozi(1000));
		c.napolniGorivo();
		c.spremeniHitrost(120);
		System.out.println("vozi: " + c.vozi(1000));
		System.out.println("najvecjiCas: " + c.najvecjiCas());
		c.ustavi();
		c.ugasniAvto();

		a.izpisi();
		b.izpisi();
		c.izpisi();

		System.out.println(a.stevilka+" : "+b.stevilka+"\t:: boljsi je: " + a.boljsi(b));
		System.out.println(a.stevilka+" : "+c.stevilka+"\t:: boljsi je: " + a.boljsi(c));
		System.out.println(b.stevilka+" : "+c.stevilka+"\t:: boljsi je: " + b.boljsi(c));
	}
}
