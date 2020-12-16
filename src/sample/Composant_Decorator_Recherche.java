package sample;

import java.util.ArrayList;

public class Composant_Decorator_Recherche extends Decorator_Recherche{
    @Override
    /**
     * fonction finale qui va permettre de filtrer les parcours selon une recherche
     *
     * @param listeParcours
     * liste des parcours à filtrer
     *
     * @return
     * liste des parcours filtrés par les "Decorator_Recherche"
     */
    public ArrayList<Parcours> execute(ArrayList<Parcours> listeParcours) {
        return listeParcours;
    }
}
