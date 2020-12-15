package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import sample.controllers.AccueilHautController;

public class TestInterfa extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/interfa/PageAccueilBandeauHaut.fxml"));

        AccueilHautController control = new AccueilHautController();
        TreeView tree = control.getTree();

        TreeItem rootitem = new TreeItem("Recherches avancées");

        TreeItem denivele = new TreeItem("Dénivelé");
        TreeItem duree = new TreeItem("Durée");

        tree.setRoot(rootitem);
        rootitem.getChildren().addAll(denivele,duree);
        

        primaryStage.setTitle("Telecom Nancy Hiking");
        primaryStage.setScene(new Scene(root, 664, 104));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}


