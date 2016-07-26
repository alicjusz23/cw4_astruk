package cw4;

import java.io.File;
/**
 * Funkcja testująca klasę Macierz.
 * @author Alicja Puacz
 */
public class Test {
    
    
    /**
    * Funkcja main, wymagane są 3 argumenty
    * @param f macierz, któej strunturę drukujemy
    */
    public static void drukujMacierz(Macierz f) throws 
            Macierz.ElementPozaZakresemException{
        for (int i=0; i<f.wierszy; i++){
            for (int j=0; j<f.kolumn; j++){
                    System.out.print("\t" + f.pobierz(i, j) + "\t");
            }
            System.out.print("\n");
        }
    }
    
    
    /**
    * Funkcja main, wymagane są 3 argumenty
    */
    public static void main(String args[]) throws 
            Macierz.ElementPozaZakresemException, Macierz.WadliwyRozmiarException{
        //Gdy liczba argumentów się nie zgadza, kończymy program
        if (args.length != 3){
            System.out.println("\nZła liczba argumentów, powinno być 3\n");
            return;
        }
        //Czytamy macierze z plików
        Macierz a = new Macierz();
        a.czytajPlik(new File(args[0]));
        Macierz b = new Macierz();
        b.czytajPlik(new File(args[2]));
        //Drukujemy macierze
        System.out.println("\nPierwsza macierz:");
        drukujMacierz(a);
        System.out.println("\nDruga macierz:");
        drukujMacierz(b);
        //Wykonujemy operację i drukujemy wynik
        switch (args[1]) {
            //Dodawanie
            case "+":
                {
                    //dodaj i pokaz wynik
                    System.out.println("\nWynik dodawania:");
                    Macierz c = new Macierz();
                    c.dodajMacierze(a, b);
                    drukujMacierz(c);
                    break;
                }
            //Mnożenie
            case "x":
                {
                    //pomnoz i pokaz wynik
                    System.out.println("\nWynik mnożenia:");
                    Macierz c = new Macierz();
                    c.mnozMacierze(a, b);
                    drukujMacierz(c);
                    break;
                }
            //Gdy nie rozpoznano znaku
            default:
                System.out.println("Nieznana operacja");
                break;
        }
    }
}
