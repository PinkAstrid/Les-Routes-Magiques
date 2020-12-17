package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.FicheTech;
import sample.Parcours;
import sample.VisitorVisualisation;

import javafx.event.ActionEvent;
import sample.model.GestionnaireParcours;

import java.awt.event.MouseEvent;
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
    public  Label descLongue;
    public  ImageView imRando;
    private Parcours parc;

    private GestionnaireParcours gestion;

    public void myFunct(Parcours parc, GestionnaireParcours gestion){
        this.parc = parc;
        this.gestion = gestion;
        VisitorVisualisation vis = new VisitorVisualisation();
        vis.visit(parc);
        this.name.setText(vis.getName());
        descCourte.setText(vis.getDescCourte());
        distance.setText(vis.getDistance()+"km");
        duree.setText(vis.getDuree()+"h");
        level.setText(vis.getDiff()+"/5");
        diff.setText(vis.getDenivele()+"m");
        descLongue.setText(vis.getDescLongue());

        if (!vis.getPhotos().isEmpty()){
            imRando.setImage(vis.getPhotos().get(0));
        }
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

    public void modifier() {}

}
