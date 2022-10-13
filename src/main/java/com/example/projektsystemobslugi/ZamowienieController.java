package com.example.projektsystemobslugi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class ZamowienieController {
    private Parent root;
    Magazyn magazyn_order;
    Magazyn current_order = new Magazyn(30);
    GrupaKlientow indywidualni;
    GrupaKlientow instytucjonalni;

    @FXML
    private ListView stanMagazynu;

    @FXML
    private ListView orderList;

    @FXML
    protected void writeListView(Magazyn magazyn, ListView listView){
        listView.getItems().clear();
        for (int i = 0; i < magazyn.getWielkoscMag(); i++) {
            if (magazyn.getModele()[i] != null)
                listView.getItems().add(magazyn.getModele()[i]);
        }
    }

    @FXML
    protected void addToOrder(){
        Model model_order = (Model) stanMagazynu.getSelectionModel().getSelectedItem();
        current_order.dodaj(model_order);
        writeListView(current_order, orderList);
        stanMagazynu.getItems().remove(model_order);
    }

    @FXML
    protected void deleteFromOrder(){
        Model model_order = (Model) orderList.getSelectionModel().getSelectedItem();
        current_order.usunLocalId(model_order);
        writeListView(current_order, orderList);
        for(int i = 0; i < magazyn_order.getModele().length; i++){
            try {
                if (magazyn_order.getModele()[i].getId() == model_order.getId())
                    stanMagazynu.getItems().add(magazyn_order.getModele()[i]);
            }catch (Exception e){
            }
        }
        //stanMagazynu.getItems().add(model_order);
        //orderList.getItems().remove(model_order);
    }

    @FXML
    protected void makeOrder() throws IOException {
        orderList.getItems().clear();

        for(int i = 0; i < current_order.getModele().length; i++){
            for(int j = 0; j < magazyn_order.getModele().length; j++){
                try {
                    if (current_order.getModele()[i].getId() == magazyn_order.getModele()[j].getId())
                        magazyn_order.usun(current_order.getModele()[i]);
                }
                catch (Exception e){ }
                }
        }
        writeListView(magazyn_order, stanMagazynu);
        current_order = new Magazyn(30);

        if (current_order.getModele()[0] != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("potwierdzenie.fxml"));
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Potwierdzenie");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    protected void getVariables(Magazyn magazyn){
        magazyn_order = magazyn;
        writeListView(magazyn_order, stanMagazynu);
    }

    @FXML
    protected void switchViewMain(ActionEvent event) throws IOException {
        StateInit.state = true;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = loader.load();

        MainController mainController = loader.getController();
        mainController.getVariables(magazyn_order);
        mainController.indywidualni = indywidualni;
        mainController.instytucjonalni = instytucjonalni;

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
