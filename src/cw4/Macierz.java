package cw4;

import java.io.*;


/**
 * Klasa Macierz.Tworzy macierz i wykonuje na nich operacje.
 * @author Alicja Puacz
 */
public class Macierz {
    int wierszy; //ile wierszy
    int kolumn; //ile kolumn
    //przechowuje indeksy
    Wektor[] macierz;
    
    
    public Macierz(){
    }
    
    public Macierz(int w, int k){
        this.wierszy = w;
        this.kolumn = k;
        macierz = new Wektor[kolumn];
        int i;
        for (i=0; i<wierszy; i++){
           macierz[i] = new Wektor();
        }
    }
    
    
    /**
    * Zwraca element z macierzy o wskazanej pozycji.
    * @param wiersz numer wiersza szukanego elementu macierzy
    * @param kolumna numer kolumny szukanego elementu macierzy
    */
    double pobierz(int wiersz, int kolumna) throws ElementPozaZakresemException{
        if(wiersz>this.wierszy || kolumna>this.kolumn )
            throw new ElementPozaZakresemException("Element ma złe nr indeksów");
        
        if (this.macierz[wiersz].wek[kolumna]!= null)
            return this.macierz[wiersz].wezZWek(kolumna);
        else
            return 0;
    }
    
    
    /**
    * Zapisuje liczbę do macierzy na wskazaną pozycję.
    * @param wiersz numer wiersza szukanego elementu macierzy
    * @param kolumna numer kolumny szukanego elementu macierzy
    * @param wartosc liczba, którą zapisujemy
    */
    void wstaw(int wiersz, int kolumna, double wartosc){
        this.macierz[wiersz].wstawDoWek(kolumna, wartosc);
    }
    
    
    /**
    * Odczytuje plik, a w nim: rozmiar macierzy i jej niezerowe elementy 
    * i wstawia je do macierzy
    * @param filename ścieżka do pliku, który zawiera wczytywaną strukturę 
    * macierzy
    */
    void czytajPlik(File filename) throws ElementPozaZakresemException{
       try{
            //otworz plik
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line= reader.readLine();
            String[] lineL = line.split("\\s+");
            //Ustaw rozmiar macierzy
            this.wierszy = Integer.parseInt(lineL[0]);
            this.kolumn = Integer.parseInt(lineL[1]);
            this.macierz = new Wektor[this.wierszy];
            //Inicjalizacja Wektorów wierszy naszej macierzy
            for (int l=0; l<this.wierszy; l++){
                this.macierz[l] = new Wektor();
            }
            while ((line= reader.readLine()) != null){
                //Jeśli pusta linia - oznacza koniec pliku, wyjdź z pętli.
                if (line == null)
                    break;
                //Rozbijamy wyrazy w linii i przypisujemy do tablicy
                String[] lineS = line.split("\\s+");
                int i = Integer.parseInt(lineS[0]);
                int j = Integer.parseInt(lineS[1]);
                //Sprawdzamy poprawność pliku z 
                if(i>this.wierszy || j>this.kolumn)
                    throw new ElementPozaZakresemException(
                            "Element ma złe nr indeksów");
                Double h = Double.parseDouble(lineS[2]);
                wstaw(i, j, h) ;
                //Nie dodawaj do wektora elementów o indeksach mniejszych od 0
                // i większych od 100000.
                if (i<0 || i>100000 || j<0 || j>100000){
                    System.err.format("Nie wczytano v[" + i + "]=" + h + 
                            ", błędny indeks\n");
                    continue;
                }
            }
            reader.close();
        //Wyłapuje błędy przy wczytywaniu pliku z danymi.
        }catch (IOException e){
            System.err.format("Blad przy wczytywaniu pliku\n");
            e.printStackTrace();
        }
    }
    
    /**
    * Dodaje do siebie dwie macierze.
    * @param a pierwsza macierz
    * @param b druga macierz
    */
    void dodajMacierze(Macierz a, Macierz b)throws WadliwyRozmiarException{
        //Gdy wymiary macierzy są niezgodne - zgłaszamy wyjątek
        if(a.kolumn != b.kolumn || a.wierszy != b.wierszy)
            throw new WadliwyRozmiarException("Wymiary macierzy są różne");
        //Ustawiamy rozmiar macierzy wynikowej: wybieramy maksymalny rozmiar 
        //kolumny i wiersza
        this.wierszy = a.wierszy>b.wierszy ? a.wierszy : b.wierszy;
        this.kolumn = a.kolumn>b.kolumn ? a.kolumn : b.kolumn;
        this.macierz = new Wektor[this.wierszy];
        for (int l=0; l<this.wierszy; l++){
            this.macierz[l] = new Wektor();
        }
        for(int i=0; i<this.wierszy; i++){
            for(int j=0; j<this.kolumn; j++){
                //Klasa Wektor przechowuje tylko elementy niezerowe - jeśli taki 
                //napotkamy, przypiszmy mu "0"
                if(a.macierz[i].wek[j] == null)
                    a.macierz[i].wek[j]=0.0;
                if(b.macierz[i].wek[j] == null)
                    b.macierz[i].wek[j]=0.0;
                this.macierz[i].wstawDoWek(j, a.macierz[i].wek[j] + b.macierz[i].wek[j]);
            }
        }
    }
    
    /**
    * Mnoży przez siebie dwie macierze.
    * @param a pierwsza macierz
    * @param b druga macierz
    */
    void mnozMacierze(Macierz a, Macierz b) throws WadliwyRozmiarException{
        //Gdy wymiary macierzy są niezgodne - zgłaszamy wyjątek
        if(a.kolumn != b.wierszy)
            throw new WadliwyRozmiarException("Liczba kolumn pierwszej macierzy "
                    + "nie jest równa liczbie wierszy drugiej macierzy");
        //Ustawiamy rozmiar macierzy wynikowej
        this.wierszy = a.wierszy;
        this.kolumn = b.kolumn;
        this.macierz = new Wektor[this.wierszy];
        for (int l=0; l<this.wierszy; l++){
            this.macierz[l] = new Wektor();
        }
        
        int p = a.kolumn;
        for(int i=0; i<this.wierszy; i++){
            for(int j=0; j<this.kolumn; j++){
                //Wektor pomocniczy
                double temp = 0.0;
                for(int n=0; n<p; n++){
                    //Klasa Wektor przechowuje tylko elementy niezerowe - jeśli 
                    //taki napotkamy, przypiszmy mu "0", nawet jeśli liczba 
                    //wierszy w pierwszej macierzy nie jest równa liczbie kolumn
                    //w drugiej
                    if(a.macierz[i].wek[n] == null)
                        a.macierz[i].wek[n]=0.0;
                    if(b.macierz[n].wek[j] == null)
                        b.macierz[n].wek[j]=0.0;
                    //Sumujemy odpowiednie iloczyny elementów
                    temp += a.macierz[i].wek[n] * b.macierz[n].wek[j];
                }
                //Klasa Wektor przechowuje tylko elementy niezerowe
                if(temp==0)
                    continue;
                this.macierz[i].wstawDoWek(j, temp);
            }
        }
    }
    
    
    /**
    * Wyjątek dla przypadku, gdy indeks elementu przekracza rozmiar macierzy
    */
    class ElementPozaZakresemException extends Exception{
        public ElementPozaZakresemException(String message) {
            super(message);
        }
    }
    
    
    /**
    * Wyjątek dla przypadku, gdy przy mnożeniu macierzy ich rozmiary są różne
    */
    class WadliwyRozmiarException extends Exception{
        public WadliwyRozmiarException(String message) {
            super(message);
        }
    }
}
