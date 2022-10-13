package com.example.projektsystemobslugi;

public class Model {
    private String nazwa;
    private static int counter = 0;
    private static int counter_local = 0;
    private int id;
    private int id_local;
    private int klient;
    private int skala;
    private int cena;
    private int material;

    private Wymiary wymiarModelu;

    Model(){
        counter++;
        id = counter;

        counter_local++;
        id_local = counter;
    }

    Model(String nazwa, int x, int y, int skala, int cena, int material){
        this();
        setNazwa(nazwa);
        wymiarModelu = new Wymiary(x, y);
        setSkala(skala);
        setCena(cena);
        setMaterial(material);
    }

    Model(String nazwa, Wymiary wymiarMebla, int skala, int cena, int material){
        this();
        setNazwa(nazwa);
        setWymiary(wymiarMebla);
        setSkala(skala);
        setCena(cena);
        setMaterial(material);
    }

    public static int getMaterialId(String material) {
        int id_material = 0;
        switch (material){
            case "plastik":
                id_material = 1;
                break;
            case "drewno":
                id_material = 2;
                break;
            case "karton":
                id_material = 3;
                break;
        }
        return id_material;
    }

    public String Typ(){
        return "";
    }

    public int getId(){ return id; }

    public void setNazwa(String nazwa){ this.nazwa = nazwa; }
    public String getNazwa(){ return nazwa; }

    public void setSkala(int skala){ this.skala = skala; }
    public int getSkala(){ return skala; }

    public void setCena(int cena){ this.cena =cena; }
    public int getCena(){ return cena; }

    public void setMaterial(int material){ this.material = material; }
    public int getMaterial(){ return material; }

    public void setWymiary(Wymiary wymiarMebla){ this.wymiarModelu = wymiarMebla; }
    public int getWymiaryModeluX() { return wymiarModelu.getX(); }
    public int getWymiaryModeluY() { return wymiarModelu.getY(); }

    public void setIdLocal(int id_local){ this.id_local = id_local; }
    public int getIdLocal(){ return id_local; }

    public String toString(){
        String opis = id_local + ". " + "(" + id + ")" + nazwa + " 1:" + skala + " Cena: " + cena;
        return opis;
    }
}
