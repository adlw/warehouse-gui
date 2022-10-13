package com.example.projektsystemobslugi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class StanMagazynuController implements Initializable {
    private Parent root;
    Magazyn magazyn_stan;
    GrupaKlientow indywidualni;
    GrupaKlientow instytucjonalni;

    @FXML
    private ListView stanMagazynu2;

    @FXML
    private ToggleGroup radioButtons;

    @FXML
    private TextField nazwaText;

    @FXML
    private TextField xText;

    @FXML
    private TextField yText;

    @FXML
    private TextField cenaText;

    @FXML
    private TextField skalaText;

    @FXML
    protected void writeListView(){
        stanMagazynu2.getItems().clear();
        for (int i = 0; i < magazyn_stan.getWielkoscMag(); i++) {
            if (magazyn_stan.getModele()[i] != null)
                stanMagazynu2.getItems().add(magazyn_stan.getModele()[i]);
        }
    }

    @FXML
    protected void delete(){
        Model model_del = (Model) stanMagazynu2.getSelectionModel().getSelectedItem();
        magazyn_stan.usun(model_del);
        //indywidualni.usunModel(model_del);
        deleteForGrupaKlientow(indywidualni, model_del);
        //instytucjonalni.usunModel(model_del);
        deleteForGrupaKlientow(instytucjonalni, model_del);

//        for (int i = 0; i < indywidualni.getModele().length; i++) {
//            if (indywidualni.getModele()[i].getId() == model_del.getId())
//                deleteForGrupaKlientow(indywidualni, model_del);
//        }
//        for (int i = indywidualni.getModele().length; i < instytucjonalni.getModele().length; i++){
//            if (instytucjonalni.getModele()[i].getId() == model_del.getId())
//                //instytucjonalni.usunModel(model_del);
//                deleteForGrupaKlientow(instytucjonalni, model_del);
//
//        }
//        deleteForGrupaKlientow(indywidualni, model_del);
//        deleteForGrupaKlientow(instytucjonalni, model_del);
        writeListView();
    }

    @FXML
    protected void add(){
        String nazwa = nazwaText.getText();
        int x = Integer.parseInt(xText.getText());
        int y = Integer.parseInt(yText.getText());
        int cena = Integer.parseInt(cenaText.getText());
        int skala = Integer.parseInt(skalaText.getText());

        int id_material = 1;
        String material = ((RadioButton)radioButtons.getSelectedToggle()).getText();

        switch (material) {
            case "Plastik":
                id_material = 1;
                break;
            case "Drewno":
                id_material = 2;
                break;
            case "Karton":
                id_material = 3;
                break;
        }
        magazyn_stan.dodaj(new Model(nazwa, x, y, skala, cena,id_material));
        writeListView();
    }

    protected void writeForGrupaKlientow(GrupaKlientow grupaKlientow){
        stanMagazynu2.getItems().clear();
        for (int i = 0; i < grupaKlientow.getModele().length; i++){
            if (grupaKlientow.getModele()[i] != null)
                stanMagazynu2.getItems().add(grupaKlientow.getModele()[i]);
        }
    }

    protected void deleteForGrupaKlientow(GrupaKlientow grupaKlientow, Model model_delete) {
        for (int i = 0; i < grupaKlientow.getModele().length; i++) {
            if (grupaKlientow.getModele()[i] == model_delete)
                grupaKlientow.usunModel(model_delete);
        }
    }

    @FXML
    protected void listForGroupIndy(){
        writeForGrupaKlientow(indywidualni);
    }

    @FXML
    protected void listForGroupInsty(){
        writeForGrupaKlientow(instytucjonalni);
    }

    @FXML
    protected void closeWindow(){
        exit();
    }

    @FXML
    protected void getVariables(Magazyn magazyn){
        magazyn_stan = magazyn;
        writeListView();
    }

    @FXML
    protected void switchViewMain(ActionEvent event) throws IOException {
        StateInit.state = true;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = loader.load();

        MainController mainController = loader.getController();
        mainController.getVariables(magazyn_stan);
        mainController.indywidualni = indywidualni;
        mainController.instytucjonalni = instytucjonalni;

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


//        StateInit.state = true;
//
//        Parent rootP = FXMLLoader.load(getClass().getResource("main-view.fxml"));
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(rootP));
//        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
