package sample;

import java.util.List;

public class FicheTech implements ElementVisitor {
    float duree;
    float distance;
    float denivele;
    int difficulte;

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getDenivele() {
		return denivele;
	}

	public void setDenivele(float denivele) {
		this.denivele = denivele;
	}

	public int getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

	public float getDuree(){
		return duree;
	}

	public void setDuree(float duree){
		this.duree = duree;
	}

	@Override
	public void accept(Visitor v) {

	}
}