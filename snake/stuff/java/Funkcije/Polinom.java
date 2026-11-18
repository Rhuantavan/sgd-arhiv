public class Polinom extends Funkcija
{
    double[] koeficienti;
    public Polinom(double[] koeficienti) 
    {
        this.koeficienti = koeficienti;
    }
    
    public double vrednost(double x)
    {
        double clen = 0;
        if(koeficienti.length < 1)
            return 0;
        else
            clen = koeficienti[0];
        
        for(int i=1; i<koeficienti.length; i++)
        {
            clen = clen*x+koeficienti[i];
        }
        return clen;
    }
    
}
