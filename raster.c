#include "raster.h"


//
//funkcja liniowa
void fLiniowa(Raster r){
	//2x+20
	for (int i=0; i<r.szerX;i++){
		for (int j=0;j<r.dlugY;j++){
			r.tab[i][j].g = r.tab[i][j].g* 2 +20;
			if(r.tab[i][j].g>255){
				r.tab[i][j].g = 255;
			}
		}
	}
}


//
//funkcja wyrownania histogramu
void fWyrownaniaHistogramu(Raster r){
	int *lbZliczen = (int*) calloc(256,sizeof(int*));
	int *dystrybuanta = (int*) calloc(256,sizeof(int*));
	int minD, maxD;
	
	//Zliczanie wyst¹pieñ pikseli
	for (int i=0; i<r.szerX;i++){
		for (int j=0;j<r.dlugY;j++){
			lbZliczen[r.tab[i][j].g]=lbZliczen[r.tab[i][j].g]+1;
		}
	}
	
	//Dystrybuanta dla ka¿dej wartosci wystepujacego piksela
	for (int i=0; i<r.szerX;i++){
		for (int j=0; j<r.dlugY;j++){
			if(j>0){
				dystrybuanta[r.tab[i][j].g] = dystrybuanta[r.tab[i][j].g] + lbZliczen[r.tab[i][j].g];
			}else{
				//dla pierwszego piksela
				dystrybuanta[r.tab[i][j].g] = lbZliczen[r.tab[i][j].g];
			}
		}
	}
	//minimalna i maksymalna dystrybuanta
	maxD = dystrybuanta[r.tab[0][0].g];
	minD = dystrybuanta[r.tab[0][0].g];
	for (int i=0; i<r.szerX;i++){
		for (int j=0; j<r.dlugY;j++){
			if(minD>dystrybuanta[r.tab[i][j].g]){
				minD = dystrybuanta[r.tab[i][j].g];
			}
			if(maxD<dystrybuanta[r.tab[i][j].g]){
				maxD = dystrybuanta[r.tab[i][j].g];
			}
		}
	}
	
	//nowa wartosc pixsela
	for (int i=0; i<r.szerX;i++){
		for (int j=0;j<r.dlugY;j++){
			r.tab[i][j].g = round(255*(dystrybuanta[r.tab[i][j].g] - minD)/(maxD-minD));
		}
	}
	
}
