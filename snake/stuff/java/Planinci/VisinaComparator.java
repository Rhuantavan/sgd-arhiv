public class VisinaComparator implements java.util.Comparator {
    
    private boolean padajoce = true;
    private int zacLeto = 0;
    private int konLeto = 0;
    
    public VisinaComparator(boolean padajoce, int zacLeto, int konLeto)
    {
        this.padajoce = padajoce;
        this.zacLeto = zacLeto;
        this.konLeto = konLeto;

    }
    
    public int compare(Object element1, Object element2) throws ClassCastException
    {
        Planinec p1 = (Planinec)element1;
        Planinec p2 = (Planinec)element2;
        
        int vrh1 = p1.najvisjiVrh(zacLeto, konLeto);
        int vrh2 = p2.najvisjiVrh(zacLeto, konLeto);
        
        if(padajoce == true)
        {
        if(vrh2 > vrh1 ) return 1;
        else if(vrh1 > vrh2 ) return -1;
        else return 0; 
        }
        else
        {
            if(vrh2 > vrh1 ) return -1;
            else if(vrh1 > vrh2 ) return 1;
            else return 0;          
        }
      }
}
