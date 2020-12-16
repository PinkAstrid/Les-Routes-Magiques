package sample;

import java.util.ArrayList;
import java.util.List;

public class Command_Recherche_Denivele extends Decorator_Recherche {
    Decorator_Recherche decorateur;
    float deniveleMin;
    float deniveleMax;

    public Command_Recherche_Denivele(Decorator_Recherche decorateur, float deniveleMin, float deniveleMax) {
        this.decorateur = decorateur;
        this.deniveleMin = deniveleMin;
        this.deniveleMax = deniveleMax;
    }

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
