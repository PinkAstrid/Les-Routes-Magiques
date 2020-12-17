package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Parcours;
import sample.model.GestionnaireParcours;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MiddlePaneController implements Initializable, Observer {
    public BorderPane borPane;
    public GestionnaireParcours gestion;
    public Stage primaryStage;
    private VBoxMiddlePaneAccueil controller;

    @FXML
    Label label;

    public MiddlePaneController(GestionnaireParcours gestion, Stage primaryStage){
        this.gestion = gestion;
        this.primaryStage = primaryStage;
        gestion.addObserver(this);
    }

    public void setBorPane(List<Parcours> parcoursList) throws IOException {
        String fxmlFile = "/ressources/layout/vBoxMiddlePaneAccueil.fxml"; //vers ta classe
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        fxmlLoader.setControllerFactory(iC->new VBoxMiddlePaneAccueil(gestion, primaryStage));
        Parent rootNode = fxmlLoader.load();

        VBoxMiddlePaneAccueil controller = fxmlLoader.getController(); //type de ton controller
        this.controller = controller;
        controller.initList(parcoursList); //la fonction permettant d'ajouter les éléments dans ton controller
        borPane.setCenter(rootNode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void update(Observable o, Object arg) {
        GestionnaireParcours gestionnaireParcours = (GestionnaireParcours) o;
        try {
            if(gestionnaireParcours.getMarqueurRecherche()==1) {
                label.setText("Résultats de recherches");
                controller.initList(gestionnaireParcours.getListeParcoursRecherches());
            }

            if(gestionnaireParcours.getMarqueurChangementGlobal()==1) {
                label.setText("Propositions de randonnées");
                controller.initList(gestionnaireParcours.getListeParcours());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}