package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.*;

import javafx.scene.control.TextField;
import sample.model.GestionnaireParcours;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreationParcoursControl implements Initializable {
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

    public CreationParcoursControl(GestionnaireParcours gestion) {
        this.gestion = gestion;
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


    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }

    public void setParcours(Parcours p){
        this.parcours = p;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void validerParcours(javafx.event.ActionEvent actionEvent) {
        try {
            float duree = Float.parseFloat(getDuree());
            System.out.println(duree);
            float distance = Float.parseFloat(getDistance());
            System.out.println(distance);
            int difficulte = Integer.parseInt(getDiff());
            System.out.println(difficulte);
            float denivele = Float.parseFloat(getDenivele());
            System.out.println(denivele);
            ArrayList<Image> photos = new ArrayList<Image>();
            System.out.println(photos);
            List<Coordonees> chemin = new ArrayList<Coordonees>();
            System.out.println(chemin);
            System.out.println(getName());
            System.out.println(getLongDescr());
            System.out.println(getShortDescr());

            gestion.createParcours(chemin, duree, distance, denivele, difficulte, getName(), getShortDescr(), getLongDescr(), photos);


            this.dialogStage.close();
        }
        catch(Exception e) {
            System.out.println("There is a problem with your input"+e.getCause());
        }
    }

    
}