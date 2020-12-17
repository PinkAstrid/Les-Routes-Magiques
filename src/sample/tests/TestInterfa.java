package sample.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Coordonees;
import sample.Parcours;
import sample.controllers.AccueilHautController;
import sample.model.GestionnaireParcours;

import java.util.ArrayList;
import java.util.List;

public class TestInterfa extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
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

        gestion.createParcours(list, 2.5f, 5.0f, 200, 1, "chemin", "randonée sur un petit chemin", "details", photos, new ArrayList<>());
        gestion.createParcours(list, 2.5f, 100, 600, 1, "titre", "description", "details", photos, new ArrayList<>());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ressources/layout/PageAccueilBandeauHaut.fxml"));
        loader.setControllerFactory(iC->new AccueilHautController(gestion));
        Parent root = loader.load();

        //Parent root = FXMLLoader.load(getClass().getResource("/sample/interfa/PageAccueilBandeauHaut.fxml"));

        primaryStage.setTitle("Telecom Nancy Hiking");
        primaryStage.setScene(new Scene(root, 774, 126));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}


