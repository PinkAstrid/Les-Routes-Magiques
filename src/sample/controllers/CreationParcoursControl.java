package sample.controllers;

import GPX.Reader;
import com.sothawo.mapjfx.Projection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.*;

import javafx.scene.control.TextField;
import sample.model.GestionnaireParcours;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private ImageView photos;
    @FXML
    private AnchorPane mapPane;


    private Stage dialogStage;
    public GestionnaireParcours gestion;
    public Parcours parcours;
    private MapCreationParcours mapCreationParcours;
    private List<Image> images = new ArrayList<Image>();
    private List<String> pathImage = new ArrayList<String>();
    private Path source;
    private Path destination;
    private String name;
    private String pathGPX = "";

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void validerParcours(javafx.event.ActionEvent actionEvent) {
        try {
            float duree = Float.parseFloat(getDuree());
            float distance = Float.parseFloat(getDistance());
            int difficulte = Integer.parseInt(getDiff());
            float denivele = Float.parseFloat(getDenivele());
            List<Coordonees> chemin = mapCreationParcours.getTrace().getChemin();
            List<Waypoint> waypoints = mapCreationParcours.getWaypoints();

            gestion.createParcours(chemin, duree, distance, denivele, difficulte, getName(), getShortDescr(), getLongDescr(), images, waypoints);

            this.dialogStage.close();
        }
        catch(Exception e) {
            e.printStackTrace();
            // TODO : afficher dans la fenetre de creation de parcours
            System.out.println("There is a problem with your input"+e.getCause());
        }
    }


    public void choixFichier(MouseEvent mouseEvent) throws IOException, URISyntaxException {
        /*FileChooser dialogue = new FileChooser();

        List<File> fichiers = dialogue.showOpenMultipleDialog(dialogStage);

        if (fichiers != null) {
            for(File f : fichiers) {
                name = f.getName();
                source = Paths.get(f.getAbsolutePath());
                Path tmp = Paths.get("images");
                destination = Paths.get(tmp.toString(), name);
                try {
                    Files.copy(source, destination);
                } catch (IOException e){}
                pathImage.add(name);
                images.add(new Image("/images/" + name));
            }
            photos.setImage(images.get(0));
        }*/
    }

    public void chargerGPX(MouseEvent mouseEvent) {
        FileChooser dialogue = new FileChooser();

        File fichier = dialogue.showOpenDialog(dialogStage);
        if (fichier != null) {
            pathGPX = fichier.getPath();
            Reader r = Reader.CreateReader(pathGPX);
            Parcours p = r.getParcours();
            mapCreationParcours.addParcours(p);
            if (r.getParcoursName() != "")
                parcoursName.setText(r.getParcoursName());
            if (r.getDescription() != "")
                parcoursShortDescr.setText(r.getDescription());
            if (r.getDetails() != "")
                parcoursLongDescr.setText(r.getDetails());
            if (r.getDistance() != 0f)
                parcoursDistance.setText(r.getDistance() + "");
            if (r.getDuree() != 0f)
                parcoursDuree.setText(r.getDuree() + "");
            if (r.getDenivele() != 0f)
                parcoursDenivele.setText(r.getDenivele() + "");
            if (r.getDificulte() != 0f)
                parcoursDifficulte.setText(r.getDificulte() + "");
        }
    }
}