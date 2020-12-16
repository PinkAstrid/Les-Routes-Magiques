package sample.interfa;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.*;

import java.awt.*;
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
    public Parcours parcours;

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

    public void setParcours(Parcours p){this.parcours = p;}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Parcours validerParcours(javafx.event.ActionEvent actionEvent) {
        try {
            float duree = Float.parseFloat(getDuree());
            float distance = Float.parseFloat(getDistance());
            int difficulte = Integer.parseInt(getDiff());
            float denivele = Float.parseFloat(getDenivele());
            ArrayList<Image> photos = new ArrayList<Image>();
            List<Coordonees> chemin = new ArrayList<Coordonees>();

            CreatorParcours c = new CreatorParcours();

            Parcours p = c.createProduct(chemin, duree, distance, denivele, difficulte, getName(), getShortDescr(), getLongDescr(), photos);

            this.dialogStage.close();
            return p;
        }
        catch(Exception e) {
            System.out.println("There is a problem with your input");
        }
        return null;
    }
}