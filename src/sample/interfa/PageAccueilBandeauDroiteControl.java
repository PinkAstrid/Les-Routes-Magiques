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
import sample.CreatorParcours;
import sample.Parcours;

import java.io.IOException;

public class PageAccueilBandeauDroiteControl {
    
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
        creationParcoursController.setParcours(parcours);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }

    public void supprimerParcours(ActionEvent actionEvent) {
    }
}
