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

    /**
     * Constructeur
     *
     * @param gestion
     * gestionnaire de parcours permettant d'accéder à une liste de parcours
     *
     * @param p
     * parcours p à modifier
     *
     * @param index
     * entier indiquant l'index du parcours à modifier dans la liste
     */
    public ModifierParcoursController(GestionnaireParcours gestion, Parcours p, int index) {
        this.gestion = gestion;
        this.parcours = p;
        this.index = index;
    }

    /**
     * fonction permettant de récupérer les informations du parcours et de les pré-remplir dans les champs
     */
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

    /**
     * fonction permettant d'initialiser la fenêtre pop-up
     *
     * @param dialogStage
     * fenêtre de dialogue pop-up
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * fonction getter permettant de récupérer le nom du parcours
     *
     * @return
     * nom du parcours sous forme de chaîne de caractères
     */
    public String getName(){return parcoursName.getText(); }

    /**
     * fonction getter permettant de récupérer la description courte du parcours
     *
     * @return
     * description courte du parcours sous forme de chaîne de caractères
     */
    public String getShortDescr(){ return parcoursShortDescr.getText();}

    /**
     * fonction getter permettant de récupérer la description détaillée du parcours
     *
     * @return
     * description longue du parcours sous forme de chaîne de caractères
     */
    public String getLongDescr(){ return parcoursLongDescr.getText();}

    /**
     * fonction getter permettant de récupérer la durée du parcours
     *
     * @return
     * durée du parcours sous forme de chaîne de caractères
     */
    public String getDuree(){ return parcoursDuree.getText();}

    /**
     * fonction getter permettant de récupérer la distance du parcours
     *
     * @return
     * distance du parcours sous forme de chaîne de caractères
     */
    public String getDistance(){ return parcoursDistance.getText();}

    /**
     * fonction getter permettant de récupérer la difficulté du parcours
     *
     * @return
     * difficulté du parcours sous forme de chaîne de caractères
     */
    public String getDiff(){ return parcoursDifficulte.getText();}

    /**
     * fonction getter permettant de récupérer le dénivelé du parcours
     *
     * @return
     * dénivelé du parcours sous forme de chaîne de caractères
     */
    public String getDenivele(){ return parcoursDenivele.getText();}

    /**
     * fonction permettant de valider la modification d'un parcours et fermer la fenêtre de dialogue
     *
     * @param actionEvent
     * réagit au clic sur le bouton de validation
     */
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

            gestion.setParcours(index, parcours);

            this.dialogStage.close();
        }
        catch(Exception e) {
            System.out.println("There is a problem with your input"+e.getCause());
        }
    }

    /**
     * fonction permettant de fermer la fenêtre de modification
     */
    @FXML
    private void annuler() {
        this.dialogStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
