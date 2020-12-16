package sample;

import java.util.ArrayList;
import java.util.List;

public abstract class Decorator_Recherche {

    /**
     * fonction qui va permettre de filtrer les parcours selon une recherche
     *
     * @param listeParcours
     * liste des parcours à filtrer
     *
     * @return
     * liste des parcours filtrés
     */
    public abstract ArrayList<Parcours> execute(ArrayList<Parcours> listeParcours);
}
