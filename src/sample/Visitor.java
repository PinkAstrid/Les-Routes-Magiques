package sample;

public interface Visitor {
    public void visit(Parcours parc);

    public void visit(FicheTech fiche);

    public void visit(Coordonees cord);

    public void visit(Trace trace);
}
