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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescCourte() {
		return descCourte;
	}

	public void setDescCourte(String descCourte) {
		this.descCourte = descCourte;
	}

	public String getDescLongue() {
		return descLongue;
	}

	public void setDescLongue(String descLongue) {
		this.descLongue = descLongue;
	}

	public List<Image> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Image> photos) {
		this.photos = photos;
	}

	public void setFiche(FicheTech fiche) {
		this.fiche = fiche;
	}

	public void setTrace(Trace trace) {
		this.trace = trace;
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
