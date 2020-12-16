package sample;

import java.util.ArrayList;
import java.util.List;

public class Decorator_Recherche_Denivele extends Decorator_Recherche {
    Decorator_Recherche decorateur;
    float deniveleMin;
    float deniveleMax;

    /**
     * Constructeur
     *
     * @param decorateur
     * prochain décorateur à appeler pour filtrer de nouveaux éléments
     *
     * @param deniveleMin
     * dénivelé minimum à filtrer
     *
     * @param deniveleMax
     * dénivelé maximum à filtrer
     */
    public Decorator_Recherche_Denivele(Decorator_Recherche decorateur, float deniveleMin, float deniveleMax) {
        this.decorateur = decorateur;
        this.deniveleMin = deniveleMin;
        this.deniveleMax = deniveleMax;
    }


    /**
     * fonction qui va filtrer les parcours ayant un dénivelé compris entre "deniveleMin" et "deniveleMax"
     *
     * @param listeParcours
     * liste des parcours à filtrer
     *
     * @return
     * liste des parcours ayant un dénivelé compris entre "deniveleMin" et "deniveleMax"
     */
    public ArrayList<Parcours> execute(ArrayList<Parcours> listeParcours) {
        ArrayList<Parcours> parcoursCompatibles = new ArrayList<Parcours>();

        for (Parcours parcours : listeParcours) {
            if (parcours.fiche.denivele >= deniveleMin && parcours.fiche.denivele <= deniveleMax){
                parcoursCompatibles.add(parcours);
            }
        }
        parcoursCompatibles = decorateur.execute(parcoursCompatibles);
        return parcoursCompatibles;
    }
}
