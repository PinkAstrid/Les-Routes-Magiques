package sample;

import javafx.scene.image.Image;

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
	/**
	 * override visite du parcours
	 * @param parc
	 * 		parcours à visiter
	 */
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

	/**
	 * @return String
	 * 		nom du parcours visité
	 */
	public String getName() {return name;}
	/**
	 * @return String
	 * 		description courte du parcours visité
	 */
	public String getDescCourte() {	return descCourte;}
	/**
	 * @return String
	 * 		description longue du parcours visité
	 */
	public String getDescLongue() {return descLongue;}
	/**
	 * @return List<Image>
	 * 		liste des photos du parcours visité
	 */
	public List<Image> getPhotos() {return photos;}
	/**
	 * @return float
	 * 		dénivelé total du parcours visité
	 */
	public float getDenivele() { return denivele; }
	/**
	 * @return int
	 * 		difficulté noté sur 5 du parcours visité
	 */
	public int getDiff() { return diff;	}
	/**
	 * @return float
	 * 		distance total du parcours visité
	 */
	public float getDistance() { return distance;}
	/**
	 * @return float
	 * 		durée total du parcours visité
	 */
	public float getDuree() { return duree; }

	@Override
	/**
	 * override visite de la fiche technique
	 * @param fiche
	 * 		fiche technique à visiter
	 */
	public void visit(FicheTech fiche) {
		fiche.accept(this);
	}

	@Override
	/**
	 * override visite de coordonée
	 * @param cord
	 * 		Coordonée à visiter
	 */
	public void visit(Coordonees cord) {
		cord.accept(this);
	}

	@Override
	public void visit(Waypoint wpt) {
		wpt.accept(this);
	}

	@Override
	/**
	 * override visite du chemin
	 * @param trace
	 * 		Trace à visiter
	 */
	public void visit(Trace trace) {
		trace.accept(this);
	}

}
