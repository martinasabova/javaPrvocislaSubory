package cisla;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args)
    {
	    vygeneruj();
        vyberPrvocisla();
	    spocitaj();
    }

    public static void vygeneruj ()
    {
        Random rand = new Random();
        BufferedWriter wr = null;
        try
        {
            wr = new BufferedWriter(new FileWriter("cisla.txt"));   //vygenerovanie a zapisanie cisel
            for (int i=0; i<1000; ++i)
            {
                wr.write((rand.nextInt(100))+" ");
            }

        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Subor sa nevytvoril.");
        }
        catch(IOException ex)
        {
            System.out.println("Nepodarilo sa zapisat do suboru.");
        }
        finally
        {
            try
            {
                if (wr != null)
                {
                    wr.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Nepodarilo sa zatvorit subor.");
            }

        }
    }

    public static void vyberPrvocisla()
    {
        int [] cisla = null;    //pole, kde sa nacitaju vsetky cisla zo suboru cisla.txt
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader("cisla.txt"));
            String riadok;
            while((riadok=br.readLine())!=null)
            {
                String [] cisla1 = riadok.split(" ");
                cisla = new int [cisla1.length];
                int j=0;
                for (String i:cisla1)
                {
                    cisla[j]=Integer.parseInt(i);
                    ++j;
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Subor sa neotvoril.");
        }
        catch(IOException ex)
        {
            System.out.println("Chyba pri citani suboru.");
        }
        finally
        {
            try
            {
                if (br != null)
                {
                    br.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Chyba pri uzatvarani suboru.");
            }
        }

        BufferedWriter wr = null;
        try
        {
            wr = new BufferedWriter(new FileWriter("prvocisla.txt"));
            int [] prvocisla = new int []{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97}; //prvocisla do 100
            for (int i:cisla)
            {
                if(Arrays.binarySearch(prvocisla,i)>=0)
                {
                    wr.write(i+" ");    //ak je to prvocislo, zapise sa do suboru prvocisla.txt
                }
            }

        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Subor sa nevytvoril.");
        }
        catch(IOException ex)
        {
            System.out.println("Nepodarilo sa zapisat do suboru.");
        }
        finally
        {
            try
            {
                if (wr != null)
                {
                    wr.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Nepodarilo sa zatvorit subor.");
            }

        }
    }

    public static void spocitaj()
    {
        BufferedReader br = null;
        Prvocislo [] pole= new Prvocislo[25];   //pole s objektami, kde je prvocislo (bude ich najviac 25) aj pocet vyskytov
        int [] cisla=null;
        try
        {
            br = new BufferedReader(new FileReader("prvocisla.txt"));
            String riadok;
            while((riadok=br.readLine())!=null)     //nacitanie vsetkych prvocisel
            {
                String [] cisla1 = riadok.split(" ");
                cisla = new int [cisla1.length];
                int j=0;
                for (String i:cisla1)
                {
                    cisla[j]=Integer.parseInt(i);
                    ++j;
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Subor sa neotvoril.");
        }
        catch(IOException ex)
        {
            System.out.println("Chyba pri citani suboru.");
        }
        finally
        {
            try
            {
                if (br != null)
                {
                    br.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Chyba pri uzatvarani suboru.");
            }
        }
        int [] prvocisla = new int []{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
        int k=0;    //index v poli pole
        int pocet=0;    //pocet vykytov prvocisla
        for (int i:prvocisla)   //spocitanie kolkokrat tam ktore je a ulozenie do pola objektov
        {
            for (int j:cisla)
            {
                if(i==j)
                {
                    ++pocet;
                }
            }
            pole[k]= new Prvocislo(i,pocet);
            pocet=0;
            ++k;
        }
        Arrays.sort(pole, Comparator.comparing(Prvocislo::getPocet));   //utriedenie podla pocetnosti od najmensej po najvacsiu

        BufferedWriter wr = null;
        try
        {
            wr = new BufferedWriter(new FileWriter("pocetnost.txt"));
            for (int i=pole.length-1; i>=0; --i)   //zapisanie do suboru, ale odzadu, aby bolo od najvacsieho po najmensie
            {
                if (pole[i].getPocet()!=0)
                {
                    wr.write(pole[i]+"\n");
                }
            }

        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Subor sa nevytvoril.");
        }
        catch(IOException ex)
        {
            System.out.println("Nepodarilo sa zapisat do suboru.");
        }
        finally
        {
            try
            {
                if (wr != null)
                {
                    wr.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Nepodarilo sa zatvorit subor.");
            }

        }
    }
}
