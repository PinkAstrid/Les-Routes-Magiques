package sample;

import java.util.ArrayList;
import java.util.List;

public class Decorator_Recherche_Distance extends Decorator_Recherche{
    Decorator_Recherche decorateur;
    float distanceMin;
    float distanceMax;

    /**
     * Constructeur
     *
     * @param decorateur
     * prochain décorateur à appeler pour filtrer de nouveaux éléments
     *
     * @param distanceMin
     * distance minimum à filtrer
     *
     * @param distanceMax
     * distance maximum à filtrer
     */
    public Decorator_Recherche_Distance(Decorator_Recherche decorateur, float distanceMin, float distanceMax) {
        this.decorateur = decorateur;
        this.distanceMin = distanceMin;
        this.distanceMax = distanceMax;
    }

    /**
     * fonction qui va filtrer les parcours ayant une distance comprise entre "distanceeMin" et "distanceMax"
     *
     * @param listeParcours
     * liste des parcours à filtrer
     *
     * @return
     * liste des parcours ayant un dénivelé compris entre "distanceeMin" et "distanceMax"
     */
    public ArrayList<Parcours> execute(ArrayList<Parcours> listeParcours) {
        ArrayList<Parcours> parcoursCompatibles = new ArrayList<Parcours>();
        for(Parcours parcours : listeParcours) {
            if(parcours.fiche.distance >= distanceMin && parcours.fiche.distance <= distanceMax) {
                parcoursCompatibles.add(parcours);
            }
        }

        parcoursCompatibles = decorateur.execute(parcoursCompatibles);
        return parcoursCompatibles;
    }

}
