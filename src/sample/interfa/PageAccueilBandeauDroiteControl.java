package sample.interfa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.Parcours;

import java.io.IOException;

public class PageAccueilBandeauDroiteControl {

    /*
    public void creerPopup(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CreationParcours.fxml"));
        Pane page = loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        CreationParcoursControl creationParcoursController = loader.getController();
        creationParcoursController.setDialogStage(dialogStage);
        creationParcoursController.handleCreate();

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }*/
}
