package sample;

import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VisitorVisualisation implements Visitor {
	String name;
	String descCourte;
	String descLongue;
	List<Image> photos;
	float distance;
	float duree;
	float denivele;
	int diff;

	Trace trace;


	public VisitorVisualisation(){
		this.photos = new ArrayList<Image>();
	}

	@Override
	public void visit(Parcours parc) {
		this.name = parc.name;
		this.descCourte = parc.descCourte;
		this.descLongue= parc.descLongue;
		this.photos = parc.photos;
		parc.accept(this);
		this.denivele = parc.fiche.denivele;
		this.diff = parc.fiche.difficulte;
		this.distance = parc.fiche.distance;
		this.duree = parc.fiche.duree;
		parc.fiche.accept(this);
	}

	public String getName() {return name;}

	public String getDescCourte() {	return descCourte;}

	public String getDescLongue() {return descLongue;}

	public List<Image> getPhotos() {return photos;}

	public float getDenivele() { return denivele; }

	public int getDiff() { return diff;	}

	public float getDistance() { return distance;}

	public float getDuree() { return duree; }

	@Override
	public void visit(FicheTech fiche) {
		System.out.println("durée:"+fiche.duree);
		System.out.println("distance:"+fiche.distance);
		System.out.println("denivelé:"+fiche.denivele);
		System.out.println("difficulté:"+(float)fiche.difficulte/2+"/5");
		fiche.accept(this);
	}

	@Override
	public void visit(Coordonees cord) {
		System.out.println("\tlat:"+ cord.lattitude+" long:"+cord.longitude+" elev:"+cord.elevation);
		cord.accept(this);
	}

	@Override
	public void visit(Trace trace) {
		System.out.println("chemin :");
		trace.accept(this);
	}

}
