package sample.interfa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.Parcours;
import sample.Visitor;
import sample.VisitorVisualisation;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class FicheTechControl implements Initializable {

    @FXML
    private Label FicheName;
    @FXML
    private Label FicheShortDescr;
    @FXML
    private Label FicheLongDescr;
    @FXML
    private Label duree;
    @FXML
    private Label distance;
    @FXML
    private Label difficulte;
    @FXML
    private Label denivele;
    @FXML
    private ImageView imRando0;

    public FicheTechControl(){}

    public void myFichControl(Parcours p){
        VisitorVisualisation v = new VisitorVisualisation();
        v.visit(p);
        FicheName.setText(v.getName());
        FicheShortDescr.setText(v.getDescCourte());
        FicheLongDescr.setText(v.getDescLongue());
        duree.setText(v.getDuree()+"h");
        distance.setText(v.getDistance()+"km");
        difficulte.setText(v.getDiff()+"/5");
        denivele.setText(v.getDenivele()+"m");

        if (!v.getPhotos().isEmpty()){
            imRando0.setImage(v.getPhotos().get(0));
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
