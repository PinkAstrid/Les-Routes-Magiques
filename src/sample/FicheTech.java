package sample;

import java.util.List;

public class FicheTech implements ElementVisitor {
    float duree;
    float distance;
    float denivele;
    int difficulte;
    String description;

    public void visit(Visitor v){}

    @Override
    public void accept(Visitor v) {
    }
}