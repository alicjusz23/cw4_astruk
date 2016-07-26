#ifndef RASTER_H
#define RASTER_H

#include "pixel.h"

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct{
	//rozmair rastra
	int szerX;
	int dlugY;
	//macierz pikseli
	Pixel **tab;
}Raster;

void fLiniowa(Raster r);
void fWyrownaniaHistogramu(Raster r);

#endif
