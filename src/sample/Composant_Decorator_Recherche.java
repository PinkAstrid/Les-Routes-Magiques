package sample;

import java.util.ArrayList;

public class Composant_Decorator_Recherche extends Decorator_Recherche{
    @Override
    public ArrayList<Parcours> execute(ArrayList<Parcours> listeParcours) {
        return listeParcours;
    }
}
