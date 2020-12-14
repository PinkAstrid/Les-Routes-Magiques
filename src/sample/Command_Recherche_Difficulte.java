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

    public List<FicheTech> execute(List<FicheTech> listeFiches) {
        for(FicheTech fiche : listeFiches) {
            if(fiche.difficulte < difficulteMin || fiche.difficulte > difficulteMax) {
                listeFiches.remove(fiche);
            }
        }
        decorateur.execute(listeFiches);
        return listeFiches;
    }

}
