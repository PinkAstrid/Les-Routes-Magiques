package sample;

public class Command_Recherche_Difficulte extends Decorator_Recherche {
    Object receiver;
    float difficulteMin;
    float difficulteMax;

    public void Command_Recherche_Duree(Object receiver, float difficulteMin, float difficulteMax) {
    }

    public void execute() {}

}
