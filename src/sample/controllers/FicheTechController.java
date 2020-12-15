package sample.controllers;

import javafx.fxml.FXML;
import sample.Parcours;

import java.awt.*;

public class FicheTechController {
    private String name;
    @FXML
    private Label FicheName;
    @FXML
    private Label FicheShortDescr;
    @FXML
    private Label FicheLongDescr;
    public FicheTechController(){}

    public FicheTechController(Parcours p){
        FicheName.setText(p.getName());
        FicheShortDescr.setText(p.getDescCourte());
        FicheLongDescr.setText(p.getDescLongue());
    }

}
