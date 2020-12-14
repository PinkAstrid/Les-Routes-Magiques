package sample;

import java.util.List;

public class Command_Recherche_Description extends Decorator_Recherche {
    Object receiver;
    List<String> mots;

    public void Command_Recherche_Duree(Object receiver, List<String> mots) {
    }

    public void execute() {}

}
