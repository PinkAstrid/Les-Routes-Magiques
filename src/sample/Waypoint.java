package sample;

import sample.Coordonees;
import sample.ElementVisitor;
import sample.Visitor;

public class Waypoint implements ElementVisitor {

	private Coordonees cords;
	private String name;

	/**
	 * Constructeur
	 */
	public Waypoint(){
		cords = new Coordonees();
		name = "";
	}

	/**
	 * Constructeur avec paramètres
	 * @param cords
	 * coordonnées du point d'arrêt
	 *
	 * @param name
	 * nom du point d'arrêt
	 */
	public Waypoint(Coordonees cords, String name){
		this.cords = cords;
		this.name = name;
	}

	/**
	 * fonction getter permettant de récupérer les coordonnées d'un point d'arrêt
	 * @return
	 * coordonnées
	 */
	public Coordonees getCords() {
		return cords;
	}

	/**
	 * fonction permettant de mettre à jour les coordonnées
	 * @param cords
	 * nouvelles coordonnées à donner au point d'arrêt
	 */
	public void setCords(Coordonees cords) {
		this.cords = cords;
	}

	/**
	 * fonction getter permettant de récupérer le nom d'un point d'arrêt
	 *
	 * @return
	 * nom du point
	 */
	public String getName() {
		return name;
	}

	/**
	 * fonction permettant de mettre à jour le nom
	 * @param name
	 * nouveau nom à donner au point d'arrêt
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * fonction permettant de passer les coordonnées en chaîne de caractères
	 * @return
	 * coordonnées en chaîne de caractères
	 */
	public String toString(){
		String str = "";
		str += name + ", ";
		str += "Coords : [" + cords.lattitude + ", " + cords.longitude + ", " + cords.elevation + "]";
		return  str;
	}

	/**
	 * override accepte du Waypoint
	 * @param v
	 * visiteur à accepter
	 */
	@Override
	public void accept(Visitor v) {
		v.visit(cords);
	}
}
