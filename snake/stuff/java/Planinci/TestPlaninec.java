class TestPlaninec
{
    final static int ST_PL=5;            
    static Planinec[] planinci = new Planinec[ST_PL];
  
    public static void main(String[] args) 
    {
        for(int stPlaninca = 0; stPlaninca < ST_PL; stPlaninca++)
        {
            int stVrhovSkupaj = 0;
            
            System.out.println("---- Planinec st. " + stPlaninca+ " ----");
            
            System.out.println("Vpisi ime:");
            //String ime = BranjePodatkov.preberiString();
            String ime = "Janez";
            System.out.println("Vpisi priimek:");
            //String priimek = BranjePodatkov.preberiString();
            String priimek = "Novak";
            
            int letoVpisa = generirajLetoVpisa();
            
            int stLetPlaninarjenja = 2005-letoVpisa;
            
            int[][] podvigi = new int[stLetPlaninarjenja][];
            // za vsako vpisano leto zapolni vrhove.
            for(int leto = 0; leto < stLetPlaninarjenja; leto++)
            {
                int stVrhov = generirajStVrhov();
                podvigi[leto] = new int[stVrhov];
                
                for(int stVrha = 0; stVrha < stVrhov; stVrha++)
                {
                    podvigi[leto][stVrha] = generirajVisinoVrha();
                    stVrhovSkupaj++;
                }
            }
            
            planinci[stPlaninca] = new Planinec(stPlaninca, ime, priimek,  letoVpisa, podvigi, stVrhovSkupaj);
        }
        
        String line = "";
        while(true)
        {
            line = BranjePodatkov.preberiString();
            
            if(line == "1")
                izpisiPodatkePoLetih();
            else if(line == "2")
                izpisiPovprecnoVisinoPoPlanincu();
            else if(line == "3")
                izpisiNajnizjiVrhPoPlanincu(1980, 2004);
            else if(line == "4")
                izpisiNajvisjiVrhPoPlanincu(1980, 2004);
            else if(line == "5")
                izpisiPovprecnoStPodvigovPoPlanincu(1980, 2004);
            else if(line == "6")
                izpisiUrejenoPoVisini(true, 1980, 1998);
            else if(line == "7")
                izpisiUrejenoPoStVrhov(true, 1980, 1998);
            else if(line == "q")
                return;
        }
        
        /*
        dodajPodvig(planinci[1], 2004, 1000); // dodaj podvig planincu 2 leta 2004
        odstraniPodvig(planinci[2], 2002, 3); // odstrani 4. podvig planincu 3 leta 2002
        izpisiPodatkePoLetih();
        */
    }
    
    private static void izpisiPodatkePoLetih()
    {
        for(int stPlaninca = 0; stPlaninca < ST_PL; stPlaninca++)
        {
            planinci[stPlaninca].izpisiPoLetih();
        }
    }
    
    private static void izpisiPovprecnoVisinoPoPlanincu()
    {
        for(int stPlaninca = 0; stPlaninca < ST_PL; stPlaninca++)
        {
            System.out.println("Planinec "+planinci[stPlaninca].stevilka +" hodi na vrhove visoke povprecno "+planinci[stPlaninca].povprecnaVisina() +" m");

        }
    }
    
    private static void izpisiNajvisjiVrhPoPlanincu(int zacLeto, int konLeto)
    {
        for(int stPlaninca = 0; stPlaninca < ST_PL; stPlaninca++)
        {
            System.out.println("Planinec "+planinci[stPlaninca].stevilka +" je med letoma "+zacLeto +" in "+konLeto+" obiskal najvisji vrh z visino "+planinci[stPlaninca].najvisjiVrh(zacLeto, konLeto) +" m.");
        }
        System.out.println();       
    }
    
    private static void izpisiNajnizjiVrhPoPlanincu(int zacLeto, int konLeto)
    {
        for(int stPlaninca = 0; stPlaninca < ST_PL; stPlaninca++)
        {
            System.out.println("Planinec "+planinci[stPlaninca].stevilka +" je med letoma "+zacLeto +" in "+konLeto+" obiskal najnizji vrh z visino "+planinci[stPlaninca].najnizjiVrh(zacLeto, konLeto) +" m.");
        } 
        System.out.println();        
    }
    
    private static void izpisiPovprecnoStPodvigovPoPlanincu(int zacLeto, int konLeto)
    {
        for(int stPlaninca = 0; stPlaninca < ST_PL; stPlaninca++)
        {
            System.out.print("Planinec "+planinci[stPlaninca].stevilka +" je med letoma "+zacLeto +" in "+konLeto+" obiskal v povprecju ");
            System.out.println(planinci[stPlaninca].povprecnoStPodvigov(zacLeto, konLeto) +" na leto.");
            //System.out.printf("%f.1 na leto.",planinci[stPlaninca].povprecnoStPodvigov(zacLeto, konLeto));
        } 
        System.out.println();
    }
    
    private static void izpisiUrejenoPoVisini(boolean padajoce, int zacLeto, int konLeto)
    {
        Planinec tmp[] = (Planinec[])planinci.clone();
        java.util.Arrays.sort(tmp, new VisinaComparator(padajoce, zacLeto, konLeto));
        
        System.out.println("Planinci si po osvojenih vrhovih"+((padajoce==true)?" (od najvisjega proti najnizjemu)":"(od najnizjega proti najvisjemu)")+" med leti "+zacLeto+" in "+konLeto+" sledijo takole (stevilka planinca): ");
        for(int stPlaninca = 0; stPlaninca < ST_PL; stPlaninca++)
        {
            tmp[stPlaninca].izpisiPoLetih();
            /*
            if(stPlaninca != ST_PL-1)
                System.out.print(tmp[stPlaninca].stevilka +", ");
            else
                System.out.print(tmp[stPlaninca].stevilka);
            */    
        } 
        System.out.println();     
    }
    
    private static void izpisiUrejenoPoStVrhov(boolean padajoce, int zacLeto, int konLeto)
    {
        Planinec tmp[] = (Planinec[])planinci.clone();
        java.util.Arrays.sort(tmp, new StVrhovComparator(padajoce, zacLeto, konLeto));
        
        System.out.println("Planinci si po stevilu podvigov"+((padajoce==true)?" (od najvec proti najmanj)":"(od najmanj proti najvec)")+" med leti "+zacLeto+" in "+konLeto+" sledijo takole (stevilka planinca): ");
        for(int stPlaninca = 0; stPlaninca < ST_PL; stPlaninca++)
        {
            tmp[stPlaninca].izpisiPoLetih();
            /*
            if(stPlaninca != ST_PL-1)
                System.out.print(tmp[stPlaninca].stevilka +", ");
            else
                System.out.print(tmp[stPlaninca].stevilka);
             */  
        } 
        System.out.println();     
    }
    
    private static void dodajPodvig(Planinec planinec, int leto, int visina)
    {
        planinec.dodajPodvig(leto, visina);
    }
    
    private static void odstraniPodvig(Planinec planinec, int leto, int stPodviga)
    {
        planinec.odstraniPodvig(leto, stPodviga);
    }
    
    
    private static int generirajLetoVpisa()
    {
        return randomRange(1976, 2005);
    }
    
    private static int generirajStVrhov()
    {
        return randomRange(1, 20);
    }
    
    private static int generirajVisinoVrha()
    {
        return randomRange(800, 2864);
    }
    
    
    private static java.util.Random rand = new java.util.Random();
    private static int randomRange(int min, int max)
    {
        return min+(int)Math.round(Math.random()*(max-min));
    }
}
