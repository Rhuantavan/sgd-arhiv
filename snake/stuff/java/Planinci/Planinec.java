class Planinec
{

    public int stevilka; 
    private String ime, priimek;
    private int letoVpisa; 

    private int[][] podvigi;// = new int[30][20];
  
    private int stVsehVrhov = 0;
    
    public Planinec(int st, String ime, String priimek, int letoVpisa, int[][] podvigi, int stVsehVrhov)
    {
        this.stevilka = st;
        this.ime = ime;
        this.priimek = priimek;
        this.letoVpisa = letoVpisa;
        this.podvigi = podvigi;
        this.stVsehVrhov = stVsehVrhov;
    }
    
    public void izpisiPoLetih()
    {
        StringBuffer ret = new StringBuffer();
        
        ret.append(stevilka);
        ret.append(" ");
        ret.append(ime);
        ret.append(" ");
        ret.append(priimek);
        ret.append(", leto vpisa: ");
        ret.append(letoVpisa);
        ret.append(", skupaj vrhov: ");
        ret.append(stVsehVrhov);
        ret.append('\n');
        
        for(int leto=letoVpisa+1; leto <= 2005; leto++)
        {
            ret.append(leto);
            ret.append(" - Stevilo vrhov: ");
            int stPodvigov = podvigi[2005-leto].length;
            ret.append(stPodvigov);
            ret.append("; ");
            
            for(int podvig=0; podvig < stPodvigov; podvig++)
            {
                ret.append(podvigi[2005-leto][podvig]);
                if(podvig != stPodvigov-1) // ce ni zadnji damo na konec se vejico.
                    ret.append(" m, ");
                else
                    ret.append(" m");
            }
            ret.append('\n');
        }
        
        ret.append('\n');
        
        System.out.println(ret.toString());
    }
    
    public float povprecnaVisina()
    {
        if(stVsehVrhov == 0) return 0;
        
        float sum=0;
        for(int leto=letoVpisa+1; leto <= 2005; leto++)
        {
            for(int podvig=0; podvig < podvigi[2005-leto].length; podvig++)
            {
                sum += (float)podvigi[2005-leto][podvig];
            }
        }
        return sum/(float)stVsehVrhov;
    }
    
    public int najvisjiVrh(int zacLeto, int konLeto)
    {
        int maxVrh = 0;
        if(zacLeto < letoVpisa) zacLeto = letoVpisa;
        else if(zacLeto > 2005) zacLeto = 2005;
        if(konLeto < letoVpisa) konLeto = letoVpisa;
        else if(konLeto > 2005) konLeto = 2005;
        
        for(int leto=zacLeto+1; leto <= konLeto; leto++)
        {
            for(int podvig=0; podvig < podvigi[2005-leto].length; podvig++)
            {
                if(podvigi[2005-leto][podvig] > maxVrh)
                    maxVrh = podvigi[2005-leto][podvig];
            }
        }
        return maxVrh;
    }
    
    public int najnizjiVrh(int zacLeto, int konLeto)
    {
        int minVrh = 3000;
        if(zacLeto < letoVpisa) zacLeto = letoVpisa;
        else if(zacLeto > 2005) zacLeto = 2005;
        if(konLeto < letoVpisa) konLeto = letoVpisa;
        else if(konLeto > 2005) konLeto = 2005;
        
        for(int leto=zacLeto+1; leto <= konLeto; leto++)
        {
            for(int podvig=0; podvig < podvigi[2005-leto].length; podvig++)
            {
                if(podvigi[2005-leto][podvig] < minVrh)
                    minVrh = podvigi[2005-leto][podvig];
            }
        }
        return minVrh;
    }
    
    public float povprecnoStPodvigov(int zacLeto, int konLeto)
    {
        if(zacLeto < letoVpisa) zacLeto = letoVpisa;
        else if(zacLeto > 2005) zacLeto = 2005;
        if(konLeto < letoVpisa) konLeto = letoVpisa;
        else if(konLeto > 2005) konLeto = 2005;
        
        
        //return (int)Math.round(sum/(float)(konLeto-zacLeto-1));
        return (float)stPodvigov(zacLeto, konLeto)/(float)(konLeto-zacLeto-1);
    }
    
    public int stPodvigov(int zacLeto, int konLeto)
    {
        int sum = 0;
        if(zacLeto < letoVpisa) zacLeto = letoVpisa;
        else if(zacLeto > 2005) zacLeto = 2005;
        if(konLeto < letoVpisa) konLeto = letoVpisa;
        else if(konLeto > 2005) konLeto = 2005;
        
        for(int leto=zacLeto+1; leto <= konLeto; leto++)
        {
            sum += (float)podvigi[2005-leto].length;
        }
        
        return sum;
    }
    
    public void dodajPodvig(int leto, int visina)
    {
        // dodaj le ce je vecje ali enako letu vpisa 
        if(leto >= letoVpisa)
        {
            leto = 2005-leto;
            int[] newArray = new int[podvigi[leto].length+1];
            System.arraycopy(podvigi[leto], 0, newArray, 0, podvigi[leto].length);
            
            // dodaj na zadnje mesto.
            newArray[podvigi[leto].length] = visina;
            podvigi[leto] = newArray;
        }
    }
    
    public void odstraniPodvig(int leto, int stPodviga)
    {
        // dodaj le ce je vecje ali enako letu vpisa 
        if(leto >= letoVpisa && stPodviga < podvigi[2005-leto].length && podvigi[2005-leto].length > 1)
        {
            leto = 2005-leto;
            int[] newArray = new int[podvigi[leto].length-1];
            System.arraycopy(podvigi[leto], 0, newArray, 0, stPodviga);
            System.arraycopy(podvigi[leto], stPodviga+1, newArray,  stPodviga, podvigi[leto].length-stPodviga-1);
            
            podvigi[leto] = newArray;
        }
    }
    
}