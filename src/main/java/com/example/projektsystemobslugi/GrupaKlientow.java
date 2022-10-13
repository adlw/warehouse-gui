package com.example.projektsystemobslugi;

public class GrupaKlientow {
    private String nazwa;
    private static int counter = 0;
    private int id;
    private Klient klienci[];
    private Model modele_grupy[];
    private Magazyn magazyn_grupy;

    GrupaKlientow(){
        counter++;
        id = counter;
    }

    GrupaKlientow(String nazwa, Klient klienci[], Model modele[]){
        this();
        this.nazwa = nazwa;
        this.klienci = klienci;
        this.modele_grupy = modele;

        magazyn_grupy = new Magazyn(modele_grupy, 30, true);
        System.out.println("");
    }

    public void dodajModel(){

    }

    public void usunModel(Model model_del){
        int id_modelu = model_del.getId();
        for(int i = 0; i < modele_grupy.length; i++){
            try {
                if (modele_grupy[i].getId() == id_modelu)
                    modele_grupy[i] = null;
            }catch (Exception e){
            }
        }
    }

    public Model[] getModele(){ return modele_grupy; }

}
