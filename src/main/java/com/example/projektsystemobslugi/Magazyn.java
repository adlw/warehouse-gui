package com.example.projektsystemobslugi;

import java.util.Scanner;

public class Magazyn{
    private static int counter_mag = 0;
    private int id_mag;
    private int wielkosc_mag;
    private Model modele[];

    static int ilosc_mebli = 0;
    int used_id = 0;

    Scanner sc = new Scanner(System.in);

    Magazyn(){
        counter_mag++;
        id_mag = counter_mag;
    }

    Magazyn(int wielkosc_mag){
        this();
        this.wielkosc_mag = wielkosc_mag;
        modele = new Model[wielkosc_mag];
    }

    Magazyn(Model modele[], int wielkosc_mag){
        this();
        this.wielkosc_mag = wielkosc_mag;
        this.modele = modele;
        ilosc_mebli = wielkosc_mag;
        used_id = ilosc_mebli;
        setLocalMagazynId();
    }

    Magazyn(Model modele[], int wielkosc_mag, boolean new_id){
        this();
        this.wielkosc_mag = wielkosc_mag;
        this.modele = modele;
        ilosc_mebli = wielkosc_mag;
        used_id = ilosc_mebli;
        //setLocalMagazynId();
    }

    public void setLocalMagazynId(){
        int indeks = 0;
        for (int i = 0; i < used_id; i++){
            if (modele[i] != null){
                modele[i].setIdLocal(indeks + 1);
                indeks++;
            }
        }
    }

    public void dodaj(Model nowy_model){
        modele[used_id] = nowy_model;
        used_id++;
        ilosc_mebli++;
        setLocalMagazynId();
    }

    public void usun(Model model){
        int id = model.getId();
        modele[id-1] = null;
        ilosc_mebli--;
        setLocalMagazynId();
    }

    public void usunLocalId(Model model){
        int id_local = model.getIdLocal();
        modele[id_local-1] = null;
        ilosc_mebli--;
        setLocalMagazynId();
    }

    public Model[] getModele(){ return modele; }

    public int getWielkoscMag(){ return wielkosc_mag; }
}
