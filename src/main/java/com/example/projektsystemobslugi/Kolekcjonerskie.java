package com.example.projektsystemobslugi;

public class Kolekcjonerskie extends Model{
    Kolekcjonerskie(String nazwa, int x, int y, int skala, int cena, int material){
        super(nazwa, x, y, skala, cena, material);
    }

    Kolekcjonerskie(String nazwa, Wymiary wymiarModelu, int skala, int cena, int material){
        super(nazwa, wymiarModelu, skala, cena, material);
    }

    public String Typ(){return "kolekcjonerski";}
}
