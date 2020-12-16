package sample.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AccueilHautController {
    @FXML
    public TextField distMinT;
    @FXML
    public TextField distMaxT;
    @FXML
    public TextField denMinT;
    @FXML
    public TextField denMaxT;
    @FXML
    public TextField diffMinT;
    @FXML
    public TextField diffMaxT;
    @FXML
    public TextField durMinT;
    @FXML
    public TextField durMaxT;

    ArrayList<Parcours> parcours;

    public void AccueilHautController(ArrayList<Parcours> parcours) {
        this.parcours = parcours;
    }


    public void rechercher(MouseEvent mouseEvent) {
        float Pinf = Float.POSITIVE_INFINITY;
        float Ninf = Float.NEGATIVE_INFINITY;

        float distMin;
        float distMax;
        float denMin;
        float denMax;
        float diffMin;
        float diffMax;
        float durMin;
        float durMax;

        String distMinS = getdistMin();
        String distMaxS = getdistMax();
        String denMinS = getdenMin();
        String denMaxS = getdenMax();
        String diffMinS = getdiffMin();
        String diffMaxS = getdiffMax();
        String durMinS = getdurMin();
        String durMaxS = getdurMin();

        try {
            if (distMinS.length() != 0) {
                distMin = Float.parseFloat(distMinS);
            } else distMin = Ninf;

            if (distMaxS.length() != 0) {
                distMax = Float.parseFloat(distMaxS);
            } else distMax = Pinf;

            if (denMinS.length() != 0) {
                denMin = Float.parseFloat(denMinS);
            } denMin = Ninf;

            if (denMaxS.length() != 0) {
                denMax = Float.parseFloat(denMaxS);
            } denMax = Pinf;

            if (diffMinS.length() != 0) {
                diffMin = Float.parseFloat(diffMinS);
            } else diffMin = Ninf;

            if (diffMaxS.length() != 0) {
                diffMax = Float.parseFloat(diffMaxS);
            } else diffMax = Pinf;

            if (durMinS.length() != 0) {
                durMin = Float.parseFloat(durMinS);
            } else durMin = Ninf;

            if (durMaxS.length() != 0) {
                durMax = Float.parseFloat(durMaxS);
            } else durMax = Pinf;
        }
        catch(Exception e) {
                System.out.println("There is a problem with your input");
        }

        /*Composant_Decorator_Recherche CDR = new Composant_Decorator_Recherche();
        Command_Recherche_Distance CRD = new Command_Recherche_Distance(CDR, distMin, distMax)
        Command_Recherche_Denivele CRD = new Command_Recherche_Denivele(CDR, denMin, denMax)
        Command_Recherche_Difficulte CRD = new Command_Recherche_Difficulte(CDR, diffMin, diffMax)
        Command_Recherche_Duree CRD = new Command_Recherche_Duree(CDR, durMin, durMax);

        ArrayList<Parcours> resultat = CRD.execute(parcours);*/

    }

    public String getdistMin() {
        return distMinT.getText();
    }

    public String getdistMax() {
        return distMaxT.getText();
    }

    public String getdenMin() {
        return denMinT.getText();
    }

    public String getdenMax() {
        return denMaxT.getText();
    }

    public String getdiffMin() {
        return diffMinT.getText();
    }

    public String getdiffMax() {
        return diffMaxT.getText();
    }

    public String getdurMin() {
        return durMinT.getText();
    }

    public String getdurMax() {
        return durMaxT.getText();
    }


}
