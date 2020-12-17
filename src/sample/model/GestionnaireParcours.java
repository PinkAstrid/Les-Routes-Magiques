package sample.model;

import javafx.scene.image.Image;
import sample.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GestionnaireParcours extends Observable {
    private List<Parcours> listeParcours;
    private List<Parcours> listeParcoursRecherches;

    public GestionnaireParcours() {
        listeParcours = new ArrayList<Parcours>();
        listeParcoursRecherches = new ArrayList<Parcours>();
    }

    public FicheTech createFicheTechnique(float duree, float distance, float denivele, int difficulte) {
        FicheTech ficheTechnique = new FicheTech(duree, distance, denivele, difficulte);
        return ficheTechnique;
    }

    public Parcours createParcours(List<Coordonees> chemin, float duree, float distance, float denivele, int difficulte, String title, String description, String details, ArrayList<Image> photos) {
        Trace t = new Trace(chemin);

        FicheTech ficheTechnique = createFicheTechnique(duree, distance, denivele, difficulte);

        Parcours p = new Parcours(title, description, details, photos, ficheTechnique, t);
        listeParcours.add(p);
        return p;
    }

    public void ajouterParcours(Parcours p){
        listeParcours.add(p);
    }

    public void supprimerParcours(Parcours p){
        listeParcours.remove(p);
    }

    public List<Parcours> getListeParcours() {
        return listeParcours;
    }

    public void setParcoursRecherche(List<Parcours> listeParcoursRecherches) {
        this.listeParcoursRecherches = listeParcoursRecherches;
    }




    public void afficherParcours(Parcours p){}
}
