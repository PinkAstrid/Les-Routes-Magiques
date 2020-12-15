package sample.control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Parcours;
import sample.Visitor;
import sample.VisitorVisualisation;

import java.awt.*;

public class FicheTechControl {

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

    public FicheTechControl(){}

    public FicheTechControl(Parcours p){
        VisitorVisualisation v = new VisitorVisualisation();
        v.visit(p);
        FicheName.setText(v.getName());
        FicheShortDescr.setText(v.getDescCourte());
        FicheLongDescr.setText(v.getDescLongue());
        duree.setText(String. valueOf(v.getDuree()));
        distance.setText(String. valueOf(v.getDistance()));
        difficulte.setText(String. valueOf(v.getDiff()));
        denivele.setText(String. valueOf(v.getDenivele()));
    }

}
