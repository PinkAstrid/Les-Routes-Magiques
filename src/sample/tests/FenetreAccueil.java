package sample.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Coordonees;
import sample.Parcours;
import sample.controllers.PageAccueil;
import sample.model.GestionnaireParcours;

import java.util.ArrayList;
import java.util.List;

public class FenetreAccueil extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Coordonees c0 = new Coordonees(0f, 0f, 0f);
        Coordonees c1 = new Coordonees(0.1f, 0f, 0.1f);
        Coordonees c2 = new Coordonees(0.2f, 0f, 0.2f);
        Coordonees c3 = new Coordonees(0.2f, 0.1f, 0.1f);
        List<Coordonees> list = new ArrayList<Coordonees>();
        list.add(c0);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        ArrayList<Image> photos = new ArrayList<Image>();

        GestionnaireParcours gestion = new GestionnaireParcours();

        Parcours p = gestion.createParcours(list, 2.5f, 5.0f, 200, 1, "titre", "description", "details", photos, new ArrayList<>());
        Parcours p2 = gestion.createParcours(list, 2.5f, 5.0f, 600, 1, "titre", "description", "details", photos, new ArrayList<>());
        gestion.ajouterParcours(p);
        gestion.ajouterParcours(p2);


        String fxmlFile = "/ressources/layout/pageAccueil.fxml";
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent rootNode = fxmlLoader1.load();

        PageAccueil controller = fxmlLoader1.getController();
        controller.initPage(gestion, primaryStage);
        Scene scene = new Scene(rootNode);

        primaryStage.setTitle("let us try");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
