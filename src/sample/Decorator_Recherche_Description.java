package sample;

import java.util.ArrayList;
import java.util.List;

public class Decorator_Recherche_Description extends Decorator_Recherche {
    Decorator_Recherche decorateur;
    String recherche;

    /**
     * Constructeur
     *
     * @param decorateur
     * prochain décorateur à appeler pour filtrer de nouveaux éléments
     *
     * @param recherche
     * mots à rechercher dans les descriptions ou le nom d'un parcours
     */
    public Decorator_Recherche_Description(Decorator_Recherche decorateur, String recherche) {
        this.decorateur = decorateur;
        this.recherche = recherche;
    }

    /**
     * fonction qui va filtrer les parcours ayant "recherche" dans ses descriptions ou son nom
     *
     * @param listeParcours
     * liste des parcours à filtrer
     *
     * @return
     * liste des parcours ayant un dénivelé compris entre deniveleMin et deniveleMax
     */
    public ArrayList<Parcours> execute(ArrayList<Parcours> listeParcours) {
        ArrayList<Parcours> parcoursCompatibles = new ArrayList<Parcours>();

        String[] listeRecherche = recherche.split(" ");

        for(Parcours parcours : listeParcours) {
            for(String mot : listeRecherche) {
                if(parcours.descLongue.contains(mot) || parcours.descCourte.contains(mot) || parcours.name.contains(mot)){
                    parcoursCompatibles.add(parcours);
                    break;
                }
            }
        }
        parcoursCompatibles = decorateur.execute(parcoursCompatibles);
        return parcoursCompatibles;
    }

}
