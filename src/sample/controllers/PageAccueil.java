package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Parcours;
import sample.controllers.AccueilHautController;
import sample.controllers.MiddlePaneController;
import sample.model.GestionnaireParcours;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PageAccueil implements Initializable, Observer {
    public BorderPane bordPa;
    GestionnaireParcours gestion;
    List<Parcours> parcours;
    public PageAccueil(){}

    /**
     * fonction permettant d'initialiser l'affichage de la page d'accueil
     * @param gestion
     *      gestionnaire de parcours permettant d'accéder aux parcours enregistrés
     * @param primaryStage
     *      fenêtre d'accueil
     * @throws IOException
     *      si le fichier est invalide
     */
    public void initPage(GestionnaireParcours gestion, Stage primaryStage) throws IOException {
        this.gestion = gestion;
        this.parcours = gestion.getListeParcours();

        String fxmlFileMiddle = "/ressources/layout/middlePane.fxml"; //vers ta classe
        FXMLLoader fxmlLoaderMiddle = new FXMLLoader(getClass().getResource(fxmlFileMiddle));
        fxmlLoaderMiddle.setControllerFactory(iC->new MiddlePaneController(gestion, primaryStage));
        Parent middleNode = fxmlLoaderMiddle.load();
        MiddlePaneController controllerMiddle = fxmlLoaderMiddle.getController(); //type de ton controller
        controllerMiddle.setBorPane(parcours); //la fonction permettant d'ajouter les éléments dans ton controller
        bordPa.setCenter(middleNode);

        String fxmlFileTop = "/ressources/layout/PageAccueilBandeauHaut.fxml"; //vers ta classe
        FXMLLoader fxmlLoaderTop = new FXMLLoader(getClass().getResource(fxmlFileTop));
        fxmlLoaderTop.setControllerFactory(iC->new AccueilHautController(gestion));
        Parent rootNodeTop = fxmlLoaderTop.load();
        bordPa.setTop(rootNodeTop);

        String fxmlFileRight = "/ressources/layout/PageAccueilBandeauDroite.fxml"; //vers ta classe
        FXMLLoader fxmlLoaderRight = new FXMLLoader(getClass().getResource(fxmlFileRight));
        Parent rightNode = fxmlLoaderRight.load();
        PageAccueilBandeauDroiteControl controllerRight = fxmlLoaderRight.getController(); //type de ton controller
        controllerRight.myfunct(primaryStage, gestion); //la fonction permettant d'ajouter les éléments dans ton controller
        bordPa.setRight(rightNode);

        bordPa.setStyle("-fx-border-color: white");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void update(Observable o, Object arg) {}

}
