package cisla;

public class Prvocislo
{
    private int cislo;
    private int pocet;

    public Prvocislo(int a, int b)
    {
        cislo=a;
        pocet=b;
    }

    public int getCislo ()
    {
        return cislo;
    }

    public int getPocet()
    {
        return pocet;
    }

    public String toString()
    {
        return cislo+" "+pocet;
    }
}