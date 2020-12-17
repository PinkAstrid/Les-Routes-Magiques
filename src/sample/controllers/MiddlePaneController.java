package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import sample.Parcours;
import sample.model.GestionnaireParcours;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MiddlePaneController implements Initializable {
    public BorderPane borPane;
    public GestionnaireParcours gestion;

    public MiddlePaneController(GestionnaireParcours gestion){
        this.gestion = gestion;
    }

    public void setBorPane(List<Parcours> parcoursList) throws IOException {
        String fxmlFile = "/ressources/layout/vBoxMiddlePaneAccueil.fxml"; //vers ta classe
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        fxmlLoader.setControllerFactory(iC->new VBoxMiddlePaneAccueil(gestion));
        Parent rootNode = fxmlLoader.load();

        VBoxMiddlePaneAccueil controller = fxmlLoader.getController(); //type de ton controller
        controller.initList(parcoursList); //la fonction permettant d'ajouter les éléments dans ton controller
        borPane.setCenter(rootNode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}