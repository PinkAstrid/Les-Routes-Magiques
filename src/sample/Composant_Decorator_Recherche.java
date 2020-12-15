package sample;

import java.util.List;

public class Composant_Decorator_Recherche extends Decorator_Recherche{
    @Override
    public List<Parcours> execute(List<Parcours> listeParcours) {
        return listeParcours;
    }
}
