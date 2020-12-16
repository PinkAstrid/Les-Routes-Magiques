package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.controllers.CreationParcoursControl;

import java.io.IOException;

public class PageAccueilBandeauDroiteControl {
    private Stage primaryStage;
    public void creerPopup(ActionEvent actionEvent) throws IOException {

        FXMLLoader loaderCreation = new FXMLLoader();
        loaderCreation.setLocation(getClass().getResource("CreationParcours.fxml"));
        Pane page = loaderCreation.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        CreationParcoursControl creationParcoursController = loaderCreation.getController();
        creationParcoursController.setDialogStage(dialogStage);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }

    public void supprimerParcours(ActionEvent actionEvent) {
    }

    public void myfunct(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
}