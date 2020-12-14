package sample;

import java.util.List;

public class Command_Recherche_Distance extends Decorator_Recherche{
    Decorator_Recherche decorateur;
    float distanceMin;
    float distanceMax;

    public void Command_Recherche_Distance(Decorator_Recherche decorateur, float distanceMin, float distanceMax) {
        this.decorateur = decorateur;
        this.distanceMin = distanceMin;
        this.distanceMax = distanceMax;
    }

    public List<Parcours> execute(List<Parcours> listeParcours) {
        for(Parcours parcours : listeParcours) {
            if(parcours.fiche.distance < distanceMin || parcours.fiche.distance > distanceMax) {
                listeParcours.remove(parcours);
            }
        }

        decorateur.execute(listeParcours);
        return listeParcours;
    }

}
