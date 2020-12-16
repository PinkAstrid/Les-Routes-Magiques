package sample;

import java.util.ArrayList;
import java.util.List;

public class Decorator_Recherche_Duree extends Decorator_Recherche{
    Decorator_Recherche decorateur;
    float dureeMin;
    float dureeMax;

    /**
     * Constructeur
     *
     * @param decorateur
     *  prochain décorateur à appeler pour filtrer de nouveaux éléments
     *
     * @param dureeMin
     * durée minimum à filtrer
     *
     * @param dureeMax
     * durée maximum à filtrer
     */
    public Decorator_Recherche_Duree(Decorator_Recherche decorateur, float dureeMin, float dureeMax) {
        this.decorateur = decorateur;
        this.dureeMin = dureeMin;
        this.dureeMax = dureeMax;
    }

    /**
     * fonction qui va filtrer les parcours ayant une durée comprise entre "dureeMin" et "dureeMax"
     *
     * @param listeParcours
     * liste des parcours à filtrer
     *
     * @return
     * liste des parcours ayant un dénivelé compris entre "dureeMin" et "dureeMax"
     */
    public ArrayList<Parcours> execute(ArrayList<Parcours> listeParcours) {
        ArrayList<Parcours> parcoursCompatibles = new ArrayList<Parcours>();
        for(Parcours parcours : listeParcours) {
            if(parcours.fiche.duree >= dureeMin && parcours.fiche.duree <= dureeMax) {
                parcoursCompatibles.add(parcours);
            }
        }
        parcoursCompatibles = decorateur.execute(parcoursCompatibles);
        return parcoursCompatibles;
    }

}
