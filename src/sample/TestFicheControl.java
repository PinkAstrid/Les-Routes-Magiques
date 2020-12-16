package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Coordonees;
import sample.CreatorParcours;
import sample.Parcours;
import sample.interfa.FicheTechControl;

import java.util.ArrayList;
import java.util.List;

public class TestFicheControl extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parcours p = new Parcours();
        System.out.println("Création de coordonnées grâce au créateur");
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
        CreatorParcours c = new CreatorParcours();

        System.out.println("Création de parcours grâce au créateur");
        Parcours p = c.createProduct(list, 2.5f, 5.0f, 200, 1, "titre", "description", "details", photos);
        Parcours p2 = c.createProduct(list, 2.5f, 5.0f, 600, 1, "titre", "description", "details", photos);

        FXMLLoader load = new FXMLLoader(getClass().getResource("./interfa/FicheTech.fxml"));
        Parent root = load.load();
        FicheTechControl controller = load.getController();
        controller.myFichControl(p);
        primaryStage.setTitle("Telecom Nancy Hiking");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.show();
    }
}