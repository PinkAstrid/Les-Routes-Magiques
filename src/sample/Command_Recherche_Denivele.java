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

    public List<FicheTech> execute(List<FicheTech> listeFiches) {
        for (FicheTech fiche : listeFiches) {
            if (fiche.denivele < deniveleMin || fiche.denivele > deniveleMax){
                listeFiches.remove(fiche);
            }
        }
        listeFiches = decorateur.execute(listeFiches);
        return listeFiches;
    }
}
