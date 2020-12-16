package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Parcours;
import sample.controllers.AccueilHautController;
import sample.controllers.MiddlePaneController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PageAccueil implements Initializable {
    public BorderPane bordPa;
    List<Parcours> parcours;
    private Stage primaryStage;
    public PageAccueil(){}

    public void initPage(List<Parcours> parc, Stage primaryStage) throws IOException {
        this.parcours = parc;

        String fxmlFileMiddle = "/ressources/layout/middlePane.fxml"; //vers ta classe
        FXMLLoader fxmlLoaderMiddle = new FXMLLoader(getClass().getResource(fxmlFileMiddle));
        Parent middleNode = fxmlLoaderMiddle.load();
        MiddlePaneController controllerMiddle = fxmlLoaderMiddle.getController(); //type de ton controller
        controllerMiddle.setBorPane(parcours); //la fonction permettant d'ajouter les éléments dans ton controller
        bordPa.setCenter(middleNode);

        String fxmlFileTop = "/ressources/layout/PageAccueilBandeauHaut.fxml"; //vers ta classe
        FXMLLoader fxmlLoaderTop = new FXMLLoader(getClass().getResource(fxmlFileTop));
        fxmlLoaderTop.setControllerFactory(iC->new AccueilHautController((ArrayList<Parcours>) parcours));
        Parent rootNodeTop = fxmlLoaderTop.load();
        bordPa.setTop(rootNodeTop);

        String fxmlFileRight = "/ressources/layout/PageAccueilBandeauDroite.fxml"; //vers ta classe
        FXMLLoader fxmlLoaderRight = new FXMLLoader(getClass().getResource(fxmlFileRight));
        Parent rightNode = fxmlLoaderRight.load();
        //PageAccueilBandeauDroiteControl controllerRight = fxmlLoaderRight.getController(); //type de ton controller
        //controllerRight.myfunct(primaryStage); //la fonction permettant d'ajouter les éléments dans ton controller
        bordPa.setRight(rightNode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
