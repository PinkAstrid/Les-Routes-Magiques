package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class TestParcours {
    public static void main(String[] args) {
        Visitor v = new VisitorVisualisation();

        System.out.println("Création de coordonnées grâce au créateur");
        Coordonees c0 = new Coordonees(0f, 0f, 0f);
        Coordonees c1 = new Coordonees(0.1f, 0f, 0.1f);
        Coordonees c2 = new Coordonees(0.2f, 0f, 0.2f);
        Coordonees c3 = new Coordonees(0.2f, 0.1f, 0.1f);
        List<Coordonees> list = new ArrayList<Coordonees>();
        list.add(c0);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        ArrayList<Image> photos = new ArrayList<Image>();
        CreatorParcours c = new CreatorParcours();

        System.out.println("Création de parcours grâce au créateur");
        Parcours p = c.createProduct(list, 2.5f, 5.0f, 200, 1, "titre", "description", "details", photos);
        Parcours p2 = c.createProduct(list, 2.5f, 5.0f, 600, 1, "titre", "description", "details", photos);
        /*p.setName("easy");
        p.setDescCourte("an easy course");
        p.setDescLongue("this is a very basic track, can be walked by anyone");
        p.getFiche().setDuree(10);
        p.getFiche().setDenivele(10);
        p.getFiche().setDifficulte(1);
        p.getFiche().setDistance(0.3f);

        p.getTrace().getChemin().add(c0);
        p.getTrace().getChemin().add(c1);
        p.getTrace().getChemin().add(c2);
        p.getTrace().getChemin().add(c3);
        */
        //v.visit(p);

        Composant_Decorator_Recherche CDR = new Composant_Decorator_Recherche();
        Command_Recherche_Denivele CRD = new Command_Recherche_Denivele(CDR, 300f, 600f);
        ArrayList<Parcours> lp = new ArrayList<Parcours>();
        lp.add(p);
        lp.add(p2);
        ArrayList<Parcours> lfinale = new ArrayList<Parcours>();
        System.out.println("Filtrage grâce au décorateur");
        lfinale = CRD.execute(lp);

        System.out.println("Visite grâce au visiteur");
        for(Parcours parc : lfinale) {
            v.visit(parc);
        }
    }
}
