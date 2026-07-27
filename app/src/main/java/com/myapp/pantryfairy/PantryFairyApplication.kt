package com.myapp.pantryfairy

import android.app.Application

class PantryFairyApplication : Application()


/*
Activity może zostać zniszczona i odtworzona np. przy obrocie ekranu.
Ale Application żyje tak długo jak proces aplikacji.Dlatego jest dobrym miejscem na rzeczy, które mają istnieć przez cały czas:
baza Room,
repozytoria,
konfiguracja aplikacji.
 */