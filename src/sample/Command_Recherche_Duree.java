package sample;

import java.util.List;

public class Command_Recherche_Duree extends Decorator_Recherche{
    Decorator_Recherche decorateur;
    float dureeMin;
    float dureeMax;

    public void Command_Recherche_Duree(Decorator_Recherche decorateur, float dureeMin, float dureeMax) {
        this.decorateur = decorateur;
        this.dureeMin = dureeMin;
        this.dureeMax = dureeMax;
    }

    public List<Parcours> execute(List<Parcours> listeParcours) {
        for(Parcours parcours : listeParcours) {
            if(parcours.fiche.duree < dureeMin || parcours.fiche.duree > dureeMax) {
                listeParcours.remove(parcours);
            }
        }
        listeParcours = decorateur.execute(listeParcours);
        return listeParcours;
    }

}
