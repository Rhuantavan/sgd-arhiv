public class MExpK extends Funkcija 
{
    
    double m,k;

    public MExpK(double m, double k) 
    {
        this.m = m;
        this.k = k;
    }
    
    public double vrednost(double x)
    {
        double rezultat = 1;
        double clen = 1;
        double n = 1;
        while(true)
        {
                // z izpeljavo formule ugotovimo da je vsak naslednji clen enak
                // prejsnjemu*x/n
                clen = clen*x/n;
                if(clen <= (1e-5))
                        break;
                rezultat += clen;
                n++;
        }

        return m*rezultat-k;
    }
}
