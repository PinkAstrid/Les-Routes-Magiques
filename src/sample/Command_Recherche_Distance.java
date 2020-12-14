package sample;

public class Command_Recherche_Distance extends Decorator_Recherche{
    Object receiver;
    float distanceMin;
    float distanceMax;

    public void Command_Recherche_Distance(Object receiver, float distanceMin, float distanceMax) {
    }

    public void execute() {}

}
