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
	private Boolean favoris; //true quand le parcours est dans les favoris

	/**
	 * Constructeur
	 */
	public Parcours() {
		photos = new ArrayList<Image>();
		waypoints = new ArrayList<Waypoint>();
		fiche = new FicheTech();
		trace = new Trace();
	}

	/**
	 * Constructeur avec paramètres
	 * @param name
	 * nom du parcours
	 * @param description
	 * description courte du parcours
	 * @param details
	 * description détaillée du parcours
	 * @param photos
	 * photos du parcours
	 * @param fiche
	 * fiche technique associée au parcours
	 * @param trace
	 * trace du parcours
	 * @param waypoints
	 * points d'arrêts du parcours
	 */
	public Parcours(String name, String description, String details, ArrayList<Image> photos, FicheTech fiche, Trace trace, List<Waypoint> waypoints){
		this.name = name;
		this.descCourte = description;
		this.descLongue = details;
		this.photos = photos;
		this.trace = trace;
		this.fiche = fiche;
		this.waypoints = waypoints;
		this.favoris = false;
	}

	/**
	 * @return
	 * nom du parcours
	 */
	public String getName() {
		return name;
	}

	/**
	 * mise à jour du nom du parcours
	 * @param name
	 * nouveau nom à donner au parcours
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 * description courte du parcours
	 */
	public String getDescCourte() {
		return descCourte;
	}

	/**
	 * mise à jour de la description courte du parcours
	 * @param descCourte
	 * nouvelle description à donner au parcours
	 */
	public void setDescCourte(String descCourte) {
		this.descCourte = descCourte;
	}

	/**
	 * @return
	 * description détaillée du parcours
	 */
	public String getDescLongue() {
		return descLongue;
	}

	/**
	 * mise à jour de la description détaillée du parcours
	 * @param descLongue
	 * nouvelle description détaillée à donner au parcours
	 */
	public void setDescLongue(String descLongue) {
		this.descLongue = descLongue;
	}

	/**
	 * @return
	 * liste de photos du parcours
	 */
	public List<Image> getPhotos() {
		return photos;
	}

	/**
	 * mise à jour des photos du parcours
	 * @param photos
	 * nouvelle liste de photos à donner au parcours
	 */
	public void setPhotos(List<Image> photos) {
		this.photos = photos;
	}

	/**
	 * @return
	 * liste des points d'arrêts du parcours
	 */
	public List<Waypoint> getWaypoints() {
		return waypoints;
	}

	/**
	 * mise à jour des points d'arrêts du parcours
	 * @param waypoints
	 * nouvelle liste de points d'arrêts à donner au parcours
	 */
	public void setWaypoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	/**
	 * @return
	 * fiche technique du parcours
	 */
	public FicheTech getFiche(){
		return fiche;
	}

	/**
	 * mise à jour de la fiche technique du parcours
	 * @param fiche
	 * nouvelle fiche technique à donner au parcours
	 */
	public void setFiche(FicheTech fiche) {
		this.fiche = fiche;
	}

	/**
	 * @return
	 * trace du parcours
	 */
	public Trace getTrace(){
		return trace;
	}

	/**
	 * mise à jour de la trace du parcours
	 * @param trace
	 * nouvelle trace à donner au parcours
	 */
	public void setTrace(Trace trace) {
		this.trace = trace;
	}

	/**
	 * @return
	 * booléen de favori. Retourne true si le parcours est dans les favoris, false sinon
	 */
	public Boolean getFavoris() {return favoris;}

	/**
	 * mise à jour du champs favoris du parcours
	 * @param favoris
	 * nouvelle valeur du favoris du parcours
	 */
	public void setFavoris(Boolean favoris) {this.favoris = favoris;}

	/**
	 * override accepte pour le parcours, doit accepter la fiche technique, l'ensemble des points d'arrêts et la trace
	 * @param v
	 * visiteur à accepter
	 */
	@Override
	public void accept(Visitor v) {
		v.visit(fiche);
		for( Waypoint wpt : waypoints)
			v.visit(wpt);
		v.visit(trace);
	}
}
