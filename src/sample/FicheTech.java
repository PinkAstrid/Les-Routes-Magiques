package sample;

import java.util.List;

public class FicheTech implements ElementVisitor {
    float duree;
    float distance;
    float denivele;
    int difficulte;

	/**
	 * Constructeur
	 */
	public FicheTech(){}

	/**
	 * Constructeur avec paramètres
	 * @param duree
	 * durée du parcours
	 *
	 * @param distance
	 * distance du parcours
	 *
	 * @param denivele
	 * dénivelé du parcours
	 *
	 * @param difficulte
	 * difficulté du parcours (entier inférieur ou égal à 10)
	 */
	public FicheTech(float duree, float distance, float denivele, int difficulte){
		this.duree = duree;
		this.distance = distance;
		this.denivele = denivele;
		this.difficulte = difficulte;

	}

	/**
	 * fonction permettant de récupérer la distance
	 * @return
	 * distance sous forme de float
	 */
	public float getDistance() {
		return distance;
	}

	/**
	 * fonction permettant de mettre à jour la distance
	 */
	public void setDistance(float distance) {
		this.distance = distance;
	}

	/**
	 * fonction permettant de récupérer le dénivelé
	 * @return
	 * dénivelé sous forme de float
	 */
	public float getDenivele() {
		return denivele;
	}

	/**
	 * fonction permettant de mettre à jour la distance
	 */
	public void setDenivele(float denivele) {
		this.denivele = denivele;
	}

	/**
	 * fonction permettant de récupérer la difficulté
	 * @return
	 * dénivelé sous forme de int
	 */
	public int getDifficulte() {
		return difficulte;
	}

	/**
	 * fonction permettant de mettre à jour la distance
	 */
	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

	/**
	 * fonction permettant de récupérer la durée
	 * @return
	 * durée sous forme de float
	 */
	public float getDuree(){
		return duree;
	}

	/**
	 * fonction permettant de mettre à jour la distance
	 */
	public void setDuree(float duree){
		this.duree = duree;
	}

	/**
	 * override accepte de la fiche technique
	 * @param v
	 * visiteur à accepter
	 */
	@Override
	public void accept(Visitor v) {

	}
}