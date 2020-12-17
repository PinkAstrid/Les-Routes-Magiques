package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.FicheTech;
import sample.Parcours;
import sample.VisitorVisualisation;

import javafx.event.ActionEvent;
import sample.model.GestionnaireParcours;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class PresentParcoursAccueil implements Initializable {
    public  Label level;
    public  Button name;
    public  Label descCourte;
    public  Label distance;
    public  Label duree;
    public  Label diff;
    private Parcours parc;

    private GestionnaireParcours gestion;
    Stage primaryStage;

    public void myFunct(Stage primaryStage, Parcours parc, GestionnaireParcours gestion){
        this.parc = parc;
        this.gestion = gestion;
        this.primaryStage = primaryStage;
        VisitorVisualisation vis = new VisitorVisualisation();
        vis.visit(parc);
        this.name.setText(vis.getName());
        descCourte.setText(vis.getDescCourte());
        distance.setText(vis.getDistance()+"km");
        duree.setText(vis.getDuree()+"h");
        level.setText(vis.getDiff()+"/5");
        diff.setText(vis.getDenivele()+"m");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void visuParcoursDetail (ActionEvent actionEvent) throws IOException {
        //Load
        FXMLLoader loaderDetail = new FXMLLoader();
        loaderDetail.setLocation(getClass().getResource("/ressources/layout/FicheTech.fxml"));
        Pane page = loaderDetail.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle(name.getText());
        dialogStage.initModality(Modality.NONE);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        FicheTechControl ficheTechControl = loaderDetail.getController();
        ficheTechControl.myFichControl(parc);

        // Show the new stage
        dialogStage.setHeight(1000);
        dialogStage.setWidth(800);
        dialogStage.show();
    }

    public void supprimer(MouseEvent mouseEvent) {
        gestion.supprimerParcours(this.parc);
    }

    public void modifier(ActionEvent actionEvent) throws IOException {
        int index =  gestion.getListeParcours().indexOf(parc);
        FXMLLoader loaderModification = new FXMLLoader();
        loaderModification.setLocation(getClass().getResource("/ressources/layout/ModifierParcours.fxml"));
        loaderModification.setControllerFactory(iC -> new ModifierParcoursController(gestion, parc, index));
        Pane page = loaderModification.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modifier un parcours");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        ModifierParcoursController modifierParcoursController = loaderModification.getController();
        modifierParcoursController.setDialogStage(dialogStage);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

    }

}
