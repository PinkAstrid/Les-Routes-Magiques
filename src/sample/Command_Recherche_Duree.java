package sample;

public class Command_Recherche_Duree extends Decorator_Recherche{
    Object receiver;
    float dureeMin;
    float dureeMax;

    public void Command_Recherche_Duree(Object receiver, float dureeMin, float dureeMax) {
    }

    public void execute() {}

}
