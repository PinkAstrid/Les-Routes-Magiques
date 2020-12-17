package sample.controllers;

import com.sothawo.mapjfx.Projection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.*;

import javafx.scene.control.TextField;
import sample.model.GestionnaireParcours;

import java.awt.event.ActionEvent;
import java.io.IOException;
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
    @FXML
    private AnchorPane mapPane;

    private Stage dialogStage;
    public GestionnaireParcours gestion;
    public Parcours parcours;
    private MapCreationParcours mapCreationParcours;

    public CreationParcoursControl(GestionnaireParcours gestion) {
        this.gestion = gestion;
    }

    public void  mapFonction(){
        String fxmlFile = "/ressources/layout/mapCreationParcours.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = null;
        try {
            rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        mapCreationParcours = fxmlLoader.getController();
        final Projection projection = Projection.WEB_MERCATOR;
        mapCreationParcours.initMapAndControls(projection);

        mapPane.getChildren().add(rootNode);
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
            List<Coordonees> chemin = mapCreationParcours.getTrace().getChemin();
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