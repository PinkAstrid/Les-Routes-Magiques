package sample;

import java.util.ArrayList;
import java.util.List;

public class Decorator_Recherche_Difficulte extends Decorator_Recherche {
    Decorator_Recherche decorateur;
    float difficulteMin;
    float difficulteMax;

    /**
     * Constructeur
     *
     * @param decorateur
     * prochain décorateur à appeler pour filtrer de nouveaux éléments
     *
     * @param difficulteMin
     * difficulté minimum à filtrer
     *
     * @param difficulteMax
     * difficulté maximum à filtrer
     */
    public Decorator_Recherche_Difficulte(Decorator_Recherche decorateur, float difficulteMin, float difficulteMax) {
        this.decorateur = decorateur;
        this.difficulteMin = difficulteMin;
        this.difficulteMax = difficulteMax;
    }

    /**
     * fonction qui va filtrer les parcours ayant une difficulté comprise entre "difficulteMin" et "difficulteMax"
     *
     * @param listeParcours
     * liste des parcours à filtrer
     *
     * @return
     * liste des parcours ayant un dénivelé compris entre "difficulteMin" et "difficulteMax"
     */
    public ArrayList<Parcours> execute(ArrayList<Parcours> listeParcours) {
        ArrayList<Parcours> parcoursCompatibles = new ArrayList<Parcours>();
        for(Parcours parcours : listeParcours) {
            if(parcours.fiche.difficulte >= difficulteMin && parcours.fiche.difficulte <= difficulteMax) {
                parcoursCompatibles.add(parcours);
            }
        }
        parcoursCompatibles = decorateur.execute(parcoursCompatibles);
        return parcoursCompatibles;
    }

}
