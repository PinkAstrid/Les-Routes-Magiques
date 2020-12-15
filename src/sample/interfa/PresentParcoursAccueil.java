package sample.interfa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Parcours;
import sample.VisitorVisualisation;

import java.net.URL;
import java.util.ResourceBundle;


public class PresentParcoursAccueil implements Initializable {
    public Label level;
    public Button name;
    public Label descCourte;
    public Label distance;
    public Label duree;
    public Label diff;
    public Label descLongue;

    private Parcours parc;
    private VisitorVisualisation vis;

    public PresentParcoursAccueil(Parcours parc){
        this.parc = parc;
        this.vis = new VisitorVisualisation();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vis.visit(parc);
        name.setText(vis.getName());
        descCourte.setText(vis.getDescCourte());
        distance.setText(vis.getDistance()+"km");
        duree.setText(vis.getDuree()+"h");
        diff.setText(vis.getDiff()+"5");
        descLongue.setText(vis.getDescLongue());
    }
}
