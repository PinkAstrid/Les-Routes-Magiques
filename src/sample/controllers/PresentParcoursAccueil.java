package sample.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.Parcours;
import sample.VisitorVisualisation;

import java.net.URL;
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

    public void myFunct(Parcours parc){
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
}
