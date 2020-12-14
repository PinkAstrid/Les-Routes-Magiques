package sample;

public class TestParcours {
    public static void main(String[] args) {
        Visitor v = new VisitorVisualisation();
        Parcours p = new Parcours();
        p.setName("easy");
        p.setDescCourte("an easy course");
        p.setDescLongue("this is a very basic track, can be walked by anyone");
        p.getFiche().setDuree(10);
        p.getFiche().setDenivele(10);
        p.getFiche().setDifficulte(1);
        p.getFiche().setDistance(0.3f);
        Coordonees c0 = new Coordonees(0f, 0f, 0f);
        Coordonees c1 = new Coordonees(0.1f, 0f, 0.1f);
        Coordonees c2 = new Coordonees(0.2f, 0f, 0.2f);
        Coordonees c3 = new Coordonees(0.2f, 0.1f, 0.1f);
        p.getTrace().getChemin().add(c0);
        p.getTrace().getChemin().add(c1);
        p.getTrace().getChemin().add(c2);
        p.getTrace().getChemin().add(c3);

        v.visit(p);

    }
}
