package com.example.projektsystemobslugi;

public class DoSklejania extends Model{
    private int czesci;
    DoSklejania(String nazwa, int x, int y, int skala, int cena, int material, int czesci){
        super( nazwa, x, y, skala, cena, material);
        this.czesci = czesci;
    }

    DoSklejania (String nazwa, Wymiary wymiarModelu, int skala, int cena, int material, int czesci){
        super(nazwa, wymiarModelu, skala, cena, material);
        this.czesci = czesci;
    }

    public String Typ(){return "do sklejania";}
}
