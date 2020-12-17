package sample.tests;

import javafx.scene.image.Image;
import sample.*;
import sample.model.GestionnaireParcours;

import java.util.ArrayList;
import java.util.List;

public class TestParcours {
    public static void main(String[] args) {
        Visitor v = new VisitorVisualisation();

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

        GestionnaireParcours gestion = new GestionnaireParcours();
        gestion.createParcours(list, 2.5f, 5.0f, 200, 1, "titre", "description", "details", photos, new ArrayList<>());
        gestion.createParcours(list, 2.5f, 5.0f, 600, 1, "titre", "description", "details", photos, new ArrayList<>());

        Composant_Decorator_Recherche CDR = new Composant_Decorator_Recherche();
        Decorator_Recherche_Denivele CRD = new Decorator_Recherche_Denivele(CDR, 300f, 600f);

        List<Parcours> lfinale = new ArrayList<Parcours>();
        lfinale = CRD.execute(gestion.getListeParcours());

        for(Parcours parc : lfinale) {
            v.visit(parc);
        }
    }
}
