package com.example.projektsystemobslugi;

public class Kartonowe extends Model{
    private int arkusze;
    Kartonowe(String nazwa, int x, int y, int skala, int cena, int material, int arkusze){
        super( nazwa, x, y, skala, cena, material);
        this.arkusze = arkusze;
    }

    Kartonowe (String nazwa, Wymiary wymiarModelu, int skala, int cena, int material, int arkusze){
        super(nazwa, wymiarModelu, skala, cena, material);
        this.arkusze = arkusze;
    }

    public String Typ(){return "kartonowe";}
}
