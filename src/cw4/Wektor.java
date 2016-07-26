package cw4;


public class Wektor {
    Double[] wek = new Double[100000];
    Tree drzewo = new Tree();
    
    
    public Wektor(){
        this.wek = new Double[100000];
        this.drzewo = new Tree();
    }
    
    
    
    void wstawDoWek(int i, double v){
        wek[i] = v;
        drzewo.insert(i, v);
    }
    
    
    double wezZWek(int i){
        return drzewo.get(i);
    }
}
