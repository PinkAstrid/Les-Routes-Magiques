package sample;

import java.util.List;

public class Command_Recherche_Description extends Decorator_Recherche {
    Decorator_Recherche decorateur;
    List<String> motsCles;

    public void Command_Recherche_Description(Decorator_Recherche decorateur, List<String> motsCles) {
        this.decorateur = decorateur;
        this.motsCles = motsCles;
    }

    public List<FicheTech> execute(List<FicheTech> listeFiches) {
        List<FicheTech> fichesCompatibles = null;
        for(FicheTech fiche : listeFiches) {
            for(String mot : motsCles) {
                if(fiche.description.contains(mot)){
                    fichesCompatibles.add(fiche);
                    break;
                }
            }
        }
        decorateur.execute(fichesCompatibles);
        return fichesCompatibles;
    }

}
