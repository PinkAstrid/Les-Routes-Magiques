package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Parcours implements ElementVisitor {
	String name;
	String descCourte;
	String descLongue;
	List<Image> photos;
	List<Waypoint> waypoints;
	FicheTech fiche;
	Trace trace;

	public Parcours() {
		photos = new ArrayList<Image>();
		fiche = new FicheTech();
		trace = new Trace();
	}

	public Parcours(String name, String description, String details, ArrayList<Image> photos, FicheTech fiche, Trace trace){
		this.name = name;
		this.descCourte = description;
		this.descLongue = details;
		this.photos = photos;
		this.trace = trace;
		this.fiche = fiche;
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

	public List<Waypoint> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
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
		for( Waypoint wpt : waypoints)
			v.visit(wpt);
		v.visit(trace);
	}
}
