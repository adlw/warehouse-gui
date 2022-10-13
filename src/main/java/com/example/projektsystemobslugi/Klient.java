package com.example.projektsystemobslugi;

public class Klient {
    private int id;
    private static int counter = 0;

    Klient(){
        counter++;
        id = counter;
    }

}
