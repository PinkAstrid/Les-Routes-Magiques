package sample;

import javafx.scene.image.Image;
import java.util.List;

public class CreatorParcours extends Creator{

    public CreatorParcours(){}

    public Parcours createProduct(List<Coordonees> chemin, float duree, float distance, float denivele, int difficulte, String title, String description, String details, List<Image> photos, Trace trace, FicheTech fiche) {
        Trace t = new Trace(chemin);

        CreatorFicheTech ficheParcours = new CreatorFicheTech();
        FicheTech ficheTechnique = ficheParcours.createProduct(duree, distance, denivele, difficulte);

        Parcours p = new Parcours(title, description, details, photos, ficheTechnique, t);
        return p;

        // doit pouvoir appeler Creator FichTech
    }

}
