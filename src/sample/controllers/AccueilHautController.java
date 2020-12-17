package sample.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import sample.*;
import javafx.scene.input.KeyEvent;
import sample.model.GestionnaireParcours;

import java.util.ArrayList;
import java.util.List;

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

    GestionnaireParcours gestion;
    /**
     * Constructeur
     *
     * @param gestion
     * gestionnaire des parcours à filtrer lors d'une recherche
     */
    public AccueilHautController(GestionnaireParcours gestion) {
        this.gestion = gestion;
    }

    /**
     * fonction permettant d'afficher les parcours correspondant à une recherche
     */
    public void rechercher(){
        float distMin;
        float distMax;
        float denMin;
        float denMax;
        float diffMin;
        float diffMax;
        float durMin;
        float durMax;

        List<Parcours> resultat;

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
            Decorator_Recherche_Distance CRDist = new Decorator_Recherche_Distance(CDR, distMin, distMax);
            Decorator_Recherche_Denivele CRDen = new Decorator_Recherche_Denivele(CRDist, denMin, denMax);
            Decorator_Recherche_Difficulte CRDiff = new Decorator_Recherche_Difficulte(CRDen, diffMin, diffMax);
            Decorator_Recherche_Duree CRDur = new Decorator_Recherche_Duree(CRDiff, durMin, durMax);

            if(recherche.length() != 0) {
                Decorator_Recherche_Description CRDescr = new Decorator_Recherche_Description(CRDur, recherche);
                resultat = CRDescr.execute(gestion.getListeParcours());
            }
            else {
                resultat = CRDur.execute(gestion.getListeParcours());
            }

            gestion.setParcoursRecherche(resultat);

        }
        catch(Exception e) {
            System.out.println("There is a problem with your input");
        }
    }

    /**
     * fonction récupérant la distance minimale rentrée par l'utilisateur
     *
     * @return
     * distance minimale rentrée par l'utilisateur
     */
    public String getdistMin() {
        return distMinT.getText();
    }

    /**
     * fonction récupérant la distance maximale rentrée par l'utilisateur
     *
     * @return
     * distance maximale rentrée par l'utilisateur
     */
    public String getdistMax() {
        return distMaxT.getText();
    }

    /**
     * fonction récupérant le denivelé minimal rentré par l'utilisateur
     *
     * @return
     * denivelé minimal rentré par l'utilisateur
     */
    public String getdenMin() {
        return denMinT.getText();
    }

    /**
     * fonction récupérant la denivelé maximal rentré par l'utilisateur
     *
     * @return
     * denivelé maximal rentré par l'utilisateur
     */
    public String getdenMax() {
        return denMaxT.getText();
    }

    /**
     * fonction récupérant la difficulté minimale rentrée par l'utilisateur
     *
     * @return
     * difficulté minimale rentrée par l'utilisateur
     */
    public String getdiffMin() {
        return diffMinT.getText();
    }

    /**
     * fonction récupérant la difficutlé maximale rentrée par l'utilisateur
     *
     * @return
     * difficulté maximale rentrée par l'utilisateur
     */
    public String getdiffMax() {
        return diffMaxT.getText();
    }

    /**
     * fonction récupérant la durée minimale rentrée par l'utilisateur
     *
     * @return
     * durée minimale rentrée par l'utilisateur
     */
    public String getdurMin() {
        return durMinT.getText();
    }

    /**
     * fonction récupérant la durée maximale rentrée par l'utilisateur
     *
     * @return
     * durée maximale rentrée par l'utilisateur
     */
    public String getdurMax() {
        return durMaxT.getText();
    }

    /**
     * fonction récupérant les mots rentrés par l'utilisateur
     *
     * @return
     * mots rentrés par l'utilisateur
     */
    public String getRecherche() {
        return rechercheT.getText();
    }

    /**
     * fonction lançant la recherche lors du clic sur la loupe
     *
     *  @param mouseEvent
     *  événement de la souris
     */
    public void rechercherMouse(MouseEvent mouseEvent) {
        rechercher();
    }

    /**
     * fonction lançant la recherche lors de l'appui sur la touche entrée
     *
     * @param keyEvent
     * événement sur le clavier
     */
    public void rechercherKey(KeyEvent keyEvent) {
        accueil.setOnKeyPressed(ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                rechercher();
            }
        });
    }

}
