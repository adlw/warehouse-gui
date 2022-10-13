package com.example.projektsystemobslugi;

import javafx.fxml.FXML;
import static javafx.application.Platform.exit;

public class PomocController {

    @FXML
    protected void closeWindow(){
        exit();
    }
}