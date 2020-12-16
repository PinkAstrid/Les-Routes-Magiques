package sample;

import java.util.ArrayList;
import java.util.List;

public class Command_Recherche_Description extends Decorator_Recherche {
    Decorator_Recherche decorateur;
    String recherche;

    public Command_Recherche_Description(Decorator_Recherche decorateur, String recherche) {
        this.decorateur = decorateur;
        this.recherche = recherche;
    }

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
