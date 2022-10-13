package com.example.projektsystemobslugi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class MainController implements Initializable {
    private Parent root;
    Magazyn magazyn_main = new Magazyn(30);
    GrupaKlientow indywidualni;
    GrupaKlientow instytucjonalni;

    @FXML
    private ListView stanMagazynu;

    @FXML
    protected void genModele(){
        Model modele[] = new Model[3];
        modele[0] = new DoSklejania("Suzuki ECSTAR", 20, 60, 12, 250, Model.getMaterialId("plastik"), 70);
        modele[1] = new Kolekcjonerskie("Ford Mustang", 50, 100, 24, 70, Model.getMaterialId("drewno"));
        Wymiary wymiaryModelu = new Wymiary(100, 80);
        modele[2] = new Kartonowe("Czolg T-34", wymiaryModelu, 50, 10, Model.getMaterialId("drewno"), 2);

        magazyn_main.dodaj(modele[0]);
        magazyn_main.dodaj(modele[1]);
        magazyn_main.dodaj(modele[2]);
        magazyn_main.dodaj(new Kolekcjonerskie("Ford", 65, 23, 12, 90, Model.getMaterialId("plastik")));
        magazyn_main.dodaj(new Kolekcjonerskie("Nissan", 43, 23, 30, 56, Model.getMaterialId("drewno")));
        magazyn_main.dodaj(new DoSklejania("Boeing", 34, 45, 8, 300, Model.getMaterialId("plastik"), 80));
        magazyn_main.dodaj(new Kartonowe("Czolg", wymiaryModelu, 50, 10, Model.getMaterialId("drewno"), 3));
        magazyn_main.dodaj(new Kartonowe("Airbus", wymiaryModelu, 24, 25, Model.getMaterialId("drewno"), 3));
        magazyn_main.dodaj(new Kolekcjonerskie("Nissan", 50, 80, 24, 75, Model.getMaterialId("drewno")));
        magazyn_main.dodaj(new DoSklejania("Honda", 30, 60, 15, 220, Model.getMaterialId("plastik"), 70));

        magazyn_main.dodaj(new Kolekcjonerskie("Cessna Aircraft", 85, 48, 20, 350, Model.getMaterialId("plastik")));
        magazyn_main.dodaj(new Kolekcjonerskie("Nissan", 43, 23, 30, 56, Model.getMaterialId("drewno")));
        magazyn_main.dodaj(new DoSklejania("Boeing", 34, 45, 8, 300, Model.getMaterialId("plastik"), 80));
        magazyn_main.dodaj(new Kartonowe("Aston Martin", wymiaryModelu, 50, 10, Model.getMaterialId("drewno"), 3));
        magazyn_main.dodaj(new Kartonowe("Airbus", wymiaryModelu, 24, 25, Model.getMaterialId("drewno"), 3));
        magazyn_main.dodaj(new Kolekcjonerskie("Nissan", 50, 80, 24, 75, Model.getMaterialId("drewno")));
        magazyn_main.dodaj(new DoSklejania("Honda", 30, 60, 15, 220, Model.getMaterialId("plastik"), 70));
        magazyn_main.dodaj(new DoSklejania("Boeing", 34, 45, 8, 300, Model.getMaterialId("plastik"), 80));
        magazyn_main.dodaj(new Kartonowe("Czolg", wymiaryModelu, 50, 10, Model.getMaterialId("drewno"), 3));
        magazyn_main.dodaj(new Kartonowe("Airbus", wymiaryModelu, 24, 25, Model.getMaterialId("drewno"), 3));

        for (int i = 0; i < magazyn_main.getWielkoscMag(); i++){
            if (magazyn_main.getModele()[i] != null)
                stanMagazynu.getItems().add(magazyn_main.getModele()[i]);
        }

        Model[] modele_group = magazyn_main.getModele();

        Klient klienci1[] = new Klient[5];
        for (int i = 0; i < 5; i++)
            klienci1[i] = new Klient();
        Model modele_ind[] = new Model[8];
        for (int i = 0; i < 8; i++)
            modele_ind[i] = modele_group[i];

        indywidualni = new GrupaKlientow("Klienci indywidualni", klienci1, modele_ind);

        Klient klienci2[] = new Klient[2];
        for (int i = 0; i < 2; i++)
            klienci2[i] = new Klient();
        Model modele_insty[] = new Model[12];
        for (int i = 8; i < 20; i++)
            modele_insty[i-8] = modele_group[i];

        instytucjonalni = new GrupaKlientow("Klienci instytucjonalni", klienci2, modele_insty);
    }

    @FXML
    protected void writeListView(Magazyn magazyn, ListView listView){
        listView.getItems().clear();
        for (int i = 0; i < magazyn.getWielkoscMag(); i++) {
            if (magazyn.getModele()[i] != null)
                listView.getItems().add(magazyn.getModele()[i]);
        }
    }

    @FXML
    protected void closeWindow(){
        exit();
    }

    @FXML
    protected void switchViewMagazyn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("stan_magazynu.fxml"));
        root = loader.load();

        StanMagazynuController stanMagazynuController = loader.getController();
        stanMagazynuController.getVariables(magazyn_main);
        stanMagazynuController.indywidualni = indywidualni;
        stanMagazynuController.instytucjonalni = instytucjonalni;

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Magazyn");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void switchViewOrder(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("zamowienie.fxml"));
        root = loader.load();

        ZamowienieController zamowienieController = loader.getController();
        zamowienieController.getVariables(magazyn_main);
        zamowienieController.indywidualni = indywidualni;
        zamowienieController.instytucjonalni = instytucjonalni;

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Zamowienie");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    protected void getVariables(Magazyn magazyn){
        magazyn_main = magazyn;
        writeListView(magazyn_main, stanMagazynu);
    }

    @FXML
    protected void openHelp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pomoc.fxml"));
        root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Pomoc");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(StateInit.state == false)
            genModele();
        else if (StateInit.state == true)
            System.out.println("Bez genModele");
    }
}