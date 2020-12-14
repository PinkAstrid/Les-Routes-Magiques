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

    public List<FicheTech> execute(List<FicheTech> listeFiches) {
        for(FicheTech fiche : listeFiches) {
            if(fiche.distance < distanceMin && fiche.distance > distanceMax) {
                listeFiches.remove(fiche);
            }
        }

        decorateur.execute(listeFiches);
        return listeFiches;
    }

}
