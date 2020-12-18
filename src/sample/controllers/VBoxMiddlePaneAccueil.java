package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Parcours;
import sample.controllers.PresentParcoursAccueil;
import sample.model.GestionnaireParcours;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class VBoxMiddlePaneAccueil implements Initializable, Observer
{
    public VBox vBox;
    public Stage primaryStage;
    public GestionnaireParcours gestion;

    /**
     * Constructeur
     * @param gestion
     *      gestionnaire de parcours permettant d'accéder à la liste des parcours
     * @param primaryStage
     *      fenêtre de la page
     */
    public VBoxMiddlePaneAccueil(GestionnaireParcours gestion, Stage primaryStage){
        this.gestion = gestion;
        this.primaryStage = primaryStage;
        gestion.addObserver(this);
    }

    /**
     * fonction permettant d'initialiser la Vbox du centre de la page d'accueil
     * @param parcours
     *      liste de parcours avec laquelle on initialise
     * @throws IOException
     *      si le fichier n'est pas valide
     */
    public void initList(List<Parcours> parcours) throws IOException {
        for (Parcours pa: parcours) {
            String fxmlFile = "/ressources/layout/presentParcoursAccueil.fxml"; //vers ta classe
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent rootNode = fxmlLoader.load();
            PresentParcoursAccueil controller = fxmlLoader.getController(); //type de ton controller
            controller.myFunct(primaryStage, pa, gestion); //la fonction permettant d'ajouter les éléments dans ton controller
            vBox.getChildren().add(rootNode);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * override update de la vue
     * @param o
     *      nom de l'observable
     * @param arg
     *      l'objet à observer
     */
    @Override
    public void update(Observable o, Object arg) {
        GestionnaireParcours gestion = (GestionnaireParcours) o;
        vBox.getChildren().clear();
    }
}
