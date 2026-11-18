public class TestFun
{
    
    public static void main(String[] args)
    {
        /*
        Polinom kvadrat = new Polinom(new double[] {-1,-2});
        System.out.println(kvadrat.enaNicla(-5,5));
        */
        Funkcija funkcija;
        for(int i=1;i<=10;i++)
        {
            System.out.println("----------------------------- "+i+". -----------------------------");
            int kateri = randomRange(1, 100);
            int stClenov = randomRange(1, 100);
            
            double min = (double)randomRange(-100,100);
            double max = (double)randomRange(-100,100);
            if(min > max)
            {
                double temp = max;
                max = min;
                min = temp;
            }
            
            if(kateri > 50) // polinom
            {
                double[] koeficienti = new double[stClenov];
                for(int clen=0; clen<stClenov; clen++)
                {
                    koeficienti[clen] = rand.nextDouble()*200-100; // (-100, 100)
                }
                funkcija = new Polinom(koeficienti);
            }
            else // mexk
            {
                funkcija = new MExpK(rand.nextDouble()*200-100, rand.nextDouble()*2000-100);
            }
            double x = funkcija.enaNicla(min, max);
            if(x != 0.0)
                System.out.println("Nicla na intervalu ("+min+", "+max+") je v x = "+x);
            System.out.println("\n");
        }
    }
    
    private static java.util.Random rand = new java.util.Random();
    private static int randomRange(int min, int max)
    {
        return min+(int)Math.round(Math.random()*(max-min));
    }

}
