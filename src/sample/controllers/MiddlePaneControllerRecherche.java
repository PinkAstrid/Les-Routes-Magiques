package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import sample.Parcours;

import java.io.IOException;
import java.util.List;

public class MiddlePaneControllerRecherche {
    public BorderPane borPane;

    public MiddlePaneControllerRecherche(){}

    public void setBorPane(List<Parcours> parcoursList) throws IOException {
        String fxmlFile = "./../../ressources/layout/vBoxMiddlePaneAccueil.fxml"; //vers ta classe
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent rootNode = fxmlLoader.load();

        VBoxMiddlePaneAccueil controller = fxmlLoader.getController(); //type de ton controller
        controller.initList(parcoursList); //la fonction permettant d'ajouter les éléments dans ton controller
        borPane.setCenter(rootNode);

    }
}
