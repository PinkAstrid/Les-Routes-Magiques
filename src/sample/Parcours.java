package sample;

import javafx.scene.image.Image;

import java.util.List;

public class Parcours implements ElementVisitor {
    String name;
    String descCourte;
    String descLongue;
    List<Image> photos;
    FicheTech fiche;
    Trace trace;

    public void visit(Visitor v) {}

    @Override
    public void accept(Visitor v) {

    }
}
