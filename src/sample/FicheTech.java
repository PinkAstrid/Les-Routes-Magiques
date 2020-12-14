package sample;

public class FicheTech implements ElementVisitor {
    float duree;
    float distance;
    float denivele;
    int difficulte;

    public void visit(Visitor v){}

    @Override
    public void accept(Visitor v) {
    }
}