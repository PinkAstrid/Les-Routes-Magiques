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

    public List<FicheTech> execute(List<FicheTech> listeFiches) {
        for(FicheTech fiche : listeFiches) {
            if(fiche.duree < dureeMin || fiche.duree > dureeMax) {
                listeFiches.remove(fiche);
            }
        }
        decorateur.execute(listeFiches);
        return listeFiches;
    }

}
