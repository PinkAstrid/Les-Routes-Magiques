package sample;

import java.util.List;

public class Command_Recherche_Description extends Decorator_Recherche {
    Decorator_Recherche decorateur;
    List<String> motsCles;

    public void Command_Recherche_Description(Decorator_Recherche decorateur, List<String> motsCles) {
        this.decorateur = decorateur;
        this.motsCles = motsCles;
    }

    public List<Parcours> execute(List<Parcours> listeParcours) {
        List<Parcours> parcoursCompatibles = null;
        for(Parcours parcours : listeParcours) {
            for(String mot : motsCles) {
                if(parcours.descLongue.contains(mot) || parcours.descCourte.contains(mot)){
                    parcoursCompatibles.add(parcours);
                    break;
                }
            }
        }
        decorateur.execute(parcoursCompatibles);
        return parcoursCompatibles;
    }

}
