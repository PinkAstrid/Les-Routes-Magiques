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

       

        primaryStage.setTitle("Telecom Nancy Hiking");
        primaryStage.setScene(new Scene(root, 774, 126));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}


