package kotaidaniel.toto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Alkalmazas {

    static List<String> adatsor;
    static List<Integer> nyeremenyek;
    
    static void fajlBeolvasas(String fajl){
        
        nyeremenyek = new LinkedList<>();
        adatsor = new LinkedList<>();
        try{
        FileReader fr = new FileReader(fajl);
        BufferedReader br = new BufferedReader(fr);
        String sor = br.readLine();
        while(sor != null){
            adatsor.add(sor);
            String[] adatok = sor.split(";");
            for (int i = 5; i < 14; i += 2) {
                nyeremenyek.add(osszegAtalakitas(adatok[i]));
            }
            sor = br.readLine();
        }
            br.close();
            fr.close();
        }catch(FileNotFoundException e){
            System.err.println("A Fájl nem létezik");
        }  
        catch(Exception e){
            PrintStream printf = System.err.printf("Hiba %s", e);
        }        
    }
    
    static int osszegAtalakitas(String osszeg){
            String ujosszeg;
            String vegleges;
            ujosszeg = osszeg.replaceAll(" ", "");
            vegleges = ujosszeg.replaceAll("Ft", "");
            int nyeremeny = Integer.parseInt(vegleges);        
        return nyeremeny;
    }
    
    
    static void legnagyobbNyeremeny(){
    int temp;
    int max = nyeremenyek.get(0);
        for (int i = 0; i < nyeremenyek.size(); i++) {
            temp = nyeremenyek.get(i);
            if (temp > max) {
                max = temp;
            }
        }
        System.out.printf("A legnagyobb nyerenény amit rögzítettek: %d Ft", max);
    }
    
    
    
    
    static void statisztika(){
            double count1 = 0;
            double count2 = 0;
            double countX = 0;
            double countOssz = 0;
            
        for (int i = 0; i < adatsor.size(); i++) {
            String sor = adatsor.get(i);
            String[] adatok = sor.split(";");
            for (int j = 14; j < 28; j++) {
                
                if(adatok[j].contains("1")) {
                    count1 += 1;
                    countOssz += 1;
                }
                if(adatok[j].contains("2")) {
                    count2 += 1;
                    countOssz += 1;
                }
                if(adatok[j].contains("X")){
                    countX += 1;
                    countOssz += 1;
                }
                
            }
        }
        double szazalek1 = (count1 / countOssz) * 100;
        double szazalek2 = (count2 / countOssz) * 100;
        double szazalekX = (countX / countOssz) * 100;
        /*
        System.out.printf("#1 csapat nyert: %f %, #2 csapat nyert: %f %, #3 csapat nyert: %f %", szazalek1, szazalek2, szazalekX);
        */    
        System.out.println("\n#1 csatap győzelme: " + Math.round(szazalek1)+ "%");
        System.out.println("#2 csatap győzelme: " + Math.round(szazalek2) + "%");
        System.out.println("Döntetlen:" + Math.round(szazalekX)+ "%");
    }
    
    /*
    static void adatBekeres(){
        Scanner sc = new Scanner(System.in);
        int index = 1;
        String[] tippek = new String[14];
        do{
            System.out.printf("Kérem adja meg az %d meccs tippjét: ", index);
            tippek[index - 1] = sc.next() + ";";
            index++;
            if (!"1;".equals(tippek[index - 1]) || !"2;".equals(tippek[index - 1]) || !"X;".equals(tippek[index - 1])) {
                System.out.print("Nem megfelelő karaktert adott meg, kérem próbálja újra: ");
                tippek[index - 1] = sc.next() + ";";
            }
        }while(index < 14);
        
        do{
        System.out.print("Kérem adja meg a +1 meccs eredményét: ");
        tippek[13] = sc.next();
        }
        while(!"1".equals(tippek[13]) || !"2".equals(tippek[13]) || !"X".equals(tippek[13]));
           
    

    }
    */
    
    
    
    
    
    
    
    public static void main(String[] args) {
        fajlBeolvasas("toto.csv");
         legnagyobbNyeremeny();
         statisztika();
         //adatBekeres();
        
    }
                
}
