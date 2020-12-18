package sample;

import com.sothawo.mapjfx.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Trace implements ElementVisitor {
	List<Coordonees> chemin;

	/**
	 * Constructeur de trace
	 */
	public Trace(){
		chemin = new ArrayList<Coordonees>();
	}

	/**
	 * Constructeur avec paramètre
	 * @param chemin
	 * liste de coordonnées définissant le chemin
	 */
	public Trace(List<Coordonees> chemin){
		this.chemin = chemin;
	}

	/**
	* fonction getter permettant de récupérer le chemin
	* @return
	* chemin sous forme d'une liste de coordonnées
	*/
	public List<Coordonees> getChemin(){
		return chemin;
	}

	/**
	 * fonction permettant de récupérer la liste des coordonnées
	 * @return
	 * liste de longitudes et lattitudes du chemin
	 */
	public List<Coordinate> getListCoordinates(){
		List<Coordinate> list = new ArrayList<>();
		for (Coordonees c : this.getChemin()){
			list.add(new Coordinate((double)c.getLattitude(), (double)c.getLongitude()));
		}
		return list;

	}

	/**
	 * override accepte de la trace, acceptant le visiteur pour chaque coordonnée du chemin
	 * @param v
	 * visiteur à accepter
	 */
	@Override
	public void accept(Visitor v) {
		for (Coordonees c : chemin){
			v.visit(c);
		}
	}
}
