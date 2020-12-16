package sample.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import sample.*;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

import static java.lang.Float.isInfinite;

public class AccueilHautController {
    @FXML
    public HBox accueil;
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
    @FXML
    public TextField rechercheT;

    ArrayList<Parcours> parcours;


    public AccueilHautController(ArrayList<Parcours> parcours) {
        this.parcours = parcours;
    }

    public void rechercher(){
        float distMin;
        float distMax;
        float denMin;
        float denMax;
        float diffMin;
        float diffMax;
        float durMin;
        float durMax;

        ArrayList<Parcours> resultat;

        float Pinf = Float.POSITIVE_INFINITY;
        float Ninf = Float.NEGATIVE_INFINITY;

        String distMinS = getdistMin();
        String distMaxS = getdistMax();
        String denMinS = getdenMin();
        String denMaxS = getdenMax();
        String diffMinS = getdiffMin();
        String diffMaxS = getdiffMax();
        String durMinS = getdurMin();
        String durMaxS = getdurMax();
        String recherche = getRecherche();


        try {
            if(distMinS.length() != 0) {
                distMin = Float.parseFloat(distMinS);
            } else distMin = Ninf;

            if(distMaxS.length() != 0) {
                distMax = Float.parseFloat(distMaxS);
            } else distMax = Pinf;

            if(denMinS.length() != 0) {
                denMin = Float.parseFloat(denMinS);
            } else denMin = Ninf;

            if(denMaxS.length() != 0) {
                denMax = Float.parseFloat(denMaxS);
            } else denMax = Pinf;

            if(diffMinS.length() != 0) {
                diffMin = Float.parseFloat(diffMinS);
            } else diffMin = Ninf;

            if(diffMaxS.length() != 0) {
                diffMax = Float.parseFloat(diffMaxS);
            } else diffMax = Pinf;

            if(durMinS.length() != 0) {
                durMin = Float.parseFloat(durMinS);
            } else durMin = Ninf;

            if(durMaxS.length() != 0) {
                durMax = Float.parseFloat(durMaxS);
            } else durMax = Pinf;

            Composant_Decorator_Recherche CDR = new Composant_Decorator_Recherche();
            Command_Recherche_Distance CRDist = new Command_Recherche_Distance(CDR, distMin, distMax);
            Command_Recherche_Denivele CRDen = new Command_Recherche_Denivele(CRDist, denMin, denMax);
            Command_Recherche_Difficulte CRDiff = new Command_Recherche_Difficulte(CRDen, diffMin, diffMax);
            Command_Recherche_Duree CRDur = new Command_Recherche_Duree(CRDiff, durMin, durMax);

            if(recherche.length() != 0) {
                Command_Recherche_Description CRDescr = new Command_Recherche_Description(CRDur, recherche);
                resultat = CRDescr.execute(parcours);
            }
            else {
                resultat = CRDur.execute(parcours);
            }

            Visitor v = new VisitorVisualisation();
            for(Parcours parc : resultat) {
                v.visit(parc);
            }

        }
        catch(Exception e) {
            System.out.println("There is a problem with your input");
        }
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

    public String getRecherche() {
        return rechercheT.getText();
    }


    public void rechercherMouse(MouseEvent mouseEvent) {
        rechercher();
    }

    public void rechercherKey(KeyEvent keyEvent) {
        accueil.setOnKeyPressed(ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                rechercher();
            }
        });
    }

}
