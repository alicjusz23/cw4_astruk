#include "raster.h"
#include "pixel.h"

#include <stdio.h>
#include <stdlib.h>

//
//Wypisuje strukturê rastra na standardowe wyjscie
void wydrukujStrukture(Raster r){
	int a, b;
	printf("Rozmiar: %i x %i\n", r.szerX, r.dlugY);
	for(a=0; a<r.szerX; a++){
		for(b=0; b<r.dlugY;b++){
			printf("%i\t", r.tab[a][b].g);
		}
		printf("\n");
	}
}


//
//funkcja main
int main(int argc, char** argv) {
	//Przyk³adowy rozmiar rastra
	int szer = 5;
	int wys = 5;
	//jakies przykladowe piksele
	int pixele[]={1,35,56,35,200,
					200,199,76,40,99,
					35,35,220,160,160,
					200,131,199,200,54,
					200,35,199,160,200};
	
	//rezerwacja pamieci
	Pixel **tab = (Pixel**) malloc(szer*wys*sizeof(Pixel)*szer*wys*sizeof(Pixel));
	
	//Przyk³adowa macierz pikseli
	for(int a=0; a<szer; a++){
		tab[a]= (Pixel*) malloc(sizeof(Pixel*) * szer*3);
		for(int b=0; b<wys;b++){
			tab[a][b].wspX = a;
			tab[a][b].wspX = b;
			tab[a][b].g = pixele[a*(b+1)+b];
		}
		
	}
	//Przyk³adowy raster
	Raster raster1 ={szer, wys, tab};
	
	wydrukujStrukture(raster1);
	fLiniowa(raster1);
	//fWyrownaniaHistogramu(raster1);
	printf("Nowy obraz: ");
	wydrukujStrukture(raster1);
	
	return 0;
}
