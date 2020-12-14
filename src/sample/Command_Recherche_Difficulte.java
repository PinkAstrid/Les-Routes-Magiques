package sample;

import java.util.List;

public class Command_Recherche_Difficulte extends Decorator_Recherche {
    Decorator_Recherche decorateur;
    float difficulteMin;
    float difficulteMax;

    public void Command_Recherche_Duree(Decorator_Recherche decorateur, float difficulteMin, float difficulteMax) {
        this.decorateur = decorateur;
        this.difficulteMin = difficulteMin;
        this.difficulteMax = difficulteMax;
    }

    public List<Parcours> execute(List<Parcours> listeParcours) {
        for(Parcours parcours : listeParcours) {
            if(parcours.fiche.difficulte < difficulteMin || parcours.fiche.difficulte > difficulteMax) {
                listeParcours.remove(parcours);
            }
        }
        decorateur.execute(listeParcours);
        return listeParcours;
    }

}
