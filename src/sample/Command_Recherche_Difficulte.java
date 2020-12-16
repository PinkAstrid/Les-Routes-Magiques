package sample;

import java.util.ArrayList;
import java.util.List;

public class Command_Recherche_Difficulte extends Decorator_Recherche {
    Decorator_Recherche decorateur;
    float difficulteMin;
    float difficulteMax;

    public Command_Recherche_Difficulte(Decorator_Recherche decorateur, float difficulteMin, float difficulteMax) {
        this.decorateur = decorateur;
        this.difficulteMin = difficulteMin;
        this.difficulteMax = difficulteMax;
    }

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
