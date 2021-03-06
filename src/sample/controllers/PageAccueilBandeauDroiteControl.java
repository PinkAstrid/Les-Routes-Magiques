package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.GestionnaireParcours;

import java.io.IOException;

public class PageAccueilBandeauDroiteControl {
    public Button fav;
    private Stage primaryStage;
    private GestionnaireParcours gestion;
    @FXML
    private Button save;

    public void creerPopup(ActionEvent actionEvent) throws IOException {

        FXMLLoader loaderCreation = new FXMLLoader();
        loaderCreation.setLocation(getClass().getResource("/ressources/layout/CreationParcours.fxml"));
        loaderCreation.setControllerFactory(iC -> new CreationParcoursControl(gestion));
        Pane page = loaderCreation.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Créer un parcours");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        CreationParcoursControl creationParcoursController = loaderCreation.getController();
        creationParcoursController.mapFonction();
        creationParcoursController.setDialogStage(dialogStage);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }

    public void supprimerParcours(ActionEvent actionEvent) {
    }

    public void myfunct(Stage primaryStage, GestionnaireParcours gestion){
        this.primaryStage = primaryStage;
        this.gestion = gestion;
    }

    public void afficherFav(MouseEvent actionEvent) {
        gestion.afficherFav();
    }

    public void save(MouseEvent mouseEvent) {
        gestion.exportParcours("GPX/");
    }
}
