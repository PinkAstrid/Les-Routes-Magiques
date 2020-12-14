package sample;

import java.util.List;

public class Command_Recherche_Denivele extends Decorator_Recherche {
    Decorator_Recherche decorateur;
    float deniveleMin;
    float deniveleMax;

    public void Command_Recherche_Duree(Decorator_Recherche decorateur, float deniveleMin, float deniveleMax) {
        this.decorateur = decorateur;
        this.deniveleMin = deniveleMin;
        this.deniveleMax = deniveleMax;
    }

    public List<Parcours> execute(List<Parcours> listeParcours) {
        for (Parcours parcours : listeParcours) {
            if (parcours.fiche.denivele < deniveleMin || parcours.fiche.denivele > deniveleMax){
                listeParcours.remove(parcours);
            }
        }
        listeParcours = decorateur.execute(listeParcours);
        return listeParcours;
    }
}
