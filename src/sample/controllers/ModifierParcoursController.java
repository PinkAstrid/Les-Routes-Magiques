package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Coordonees;
import sample.Parcours;
import sample.Trace;
import sample.model.GestionnaireParcours;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierParcoursController implements Initializable {
    @FXML
    private TextField parcoursName;
    @FXML
    private TextField parcoursShortDescr;
    @FXML
    private TextField parcoursLongDescr;
    @FXML
    private TextField parcoursDuree;
    @FXML
    private TextField parcoursDistance;
    @FXML
    private TextField parcoursDifficulte;
    @FXML
    private TextField parcoursDenivele;
    @FXML
    private ImageView imRando0;

    private Stage dialogStage;
    public GestionnaireParcours gestion;
    public Parcours parcours;
    private int index;

    public ModifierParcoursController(GestionnaireParcours gestion, Parcours p, int index) {
        this.gestion = gestion;
        this.parcours = p;
        this.index = index;
    }

    public void initialiserChamps() {
        parcoursName.setText(parcours.getName());
        parcoursShortDescr.setText(parcours.getDescCourte());
        parcoursLongDescr.setText(parcours.getDescLongue());
        parcoursDuree.setText(Float.toString(parcours.getFiche().getDuree()));
        parcoursDistance.setText(Float.toString(parcours.getFiche().getDistance()));
        parcoursDifficulte.setText(Integer.toString(parcours.getFiche().getDifficulte()));
        parcoursDenivele.setText(Float.toString(parcours.getFiche().getDenivele()));
        // il faut rajouter les photos et coordonnées GPS deja définies
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public String getName(){return parcoursName.getText(); }
    public String getShortDescr(){ return parcoursShortDescr.getText();}
    public String getLongDescr(){ return parcoursLongDescr.getText();}
    public String getDuree(){ return parcoursDuree.getText();}
    public String getDistance(){ return parcoursDistance.getText();}
    public String getDiff(){ return parcoursDifficulte.getText();}
    public String getDenivele(){ return parcoursDenivele.getText();}

    public void modifierParcours(javafx.event.ActionEvent actionEvent) {
        try {
            float duree = Float.parseFloat(getDuree());
            float distance = Float.parseFloat(getDistance());
            int difficulte = Integer.parseInt(getDiff());
            float denivele = Float.parseFloat(getDenivele());
            ArrayList<Image> photos = new ArrayList<Image>();
            Trace chemin = new Trace();

            parcours.setName(getName());
            parcours.setDescCourte(getShortDescr());
            parcours.setDescLongue(getLongDescr());
            parcours.getFiche().setDuree(duree);
            parcours.getFiche().setDistance(distance);
            parcours.getFiche().setDifficulte(difficulte);
            parcours.getFiche().setDenivele(denivele);
            //parcours.setPhotos(photos);
            //parcours.setTrace(chemin);

            //Parcours p = gestion.createParcours(chemin, duree, distance, denivele, difficulte, getName(), getShortDescr(), getLongDescr(), photos);
            gestion.setParcours(index, parcours);

            this.dialogStage.close();
        }
        catch(Exception e) {
            System.out.println("There is a problem with your input"+e.getCause());
        }
    }

    @FXML
    private void annuler() {
        this.dialogStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
