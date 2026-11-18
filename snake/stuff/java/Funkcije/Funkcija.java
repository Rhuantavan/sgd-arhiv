public abstract class Funkcija
{

    public abstract double vrednost(double x);

    public double enaNicla(double x1, double x2)
    {
        double velIntervala = 100.0;
        
        while(velIntervala > 10e-4 )
        {
            velIntervala = java.lang.Math.abs(x2-x1);
            if(!jePredznakRazlicen(x1, x2))
            {
                System.out.println("Vrednosti v obeh x-ih ("+x1+", "+x2+ ") sta enaki. ");
                return 0;
            }
            else
            {
                double x = (x1+x2)/2.0;
                if(jePredznakRazlicen(x1, x))
                    x2 = x;
                else
                    x1 = x;
                
                
            }
        }
        return (x1+x2)/2.0;
    }
    
    private boolean jePredznakRazlicen(double x1, double x2)
    {
        double y1 = vrednost(x1);
        double y2 = vrednost(x2);
        if(y1 >= 0 && y2 >= 0)
            return false;
        else if(y1 <= 0 && y2 <= 0)
            return false;
        
        return true;
    }
    
}
