package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Parcours implements ElementVisitor {
	String name;
	String descCourte;
	String descLongue;
	List<Image> photos;
	FicheTech fiche;
	Trace trace;

	public Parcours() {
		photos = new ArrayList<Image>();
		fiche = new FicheTech();
		trace = new Trace();
	}

	public Trace getTrace(){
		return trace;
	}

	public FicheTech getFiche(){
		return fiche;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(fiche);
		v.visit(trace);
	}
}
