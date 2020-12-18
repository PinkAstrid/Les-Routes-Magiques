package sample;

public class Coordonees implements ElementVisitor {
	float lattitude;
	float longitude;
	float elevation;

	/**
	 * fonction getter permettant de récupérer la lattitude d'un point
	 * @return
	 * lattitude sous forme de float
	 */
	public float getLattitude() {
		return lattitude;
	}

	/**
	 * fonction getter permettant de récupérer la longitude d'un point
	 * @return
	 * longitude sous forme de float
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * fonction getter permettant de récupérer l'élévation d'un point
	 * @return
	 * élévation sous forme de float
	 */
	public float getElevation() {
		return elevation;
	}

	/**
	 * Constructeur
	 */
	public Coordonees(){

	}

	/**
	 * Constructeur avec paramètres
	 * @param lattitude
	 * lattitude de la coordonée
	 * @param longitude
	 * longitude de la coordonnée
	 * @param elevation
	 * altitude de la coordonée
	 */
	public Coordonees(float lattitude, float longitude, float elevation){
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.elevation = elevation;
	}

	/**
	 * override accepte des coordonnées d'un point
	 * @param v
	 * visiteur à accepter
	 */
	@Override
	public void accept(Visitor v) {

	}
}
