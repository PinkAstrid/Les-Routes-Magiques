package sample;

import java.util.ArrayList;
import java.util.List;

public class VisitorDistanceElevation implements Visitor{

	private static double R = 6372.8f;
	List<Float> distance; //distance cumulative
	Coordonees lastCords; //dernière coordonnées visitées (pour le calcul des distances)
	List<Float> elevation; //Elevation par rapport au point de départ
	float elev_origine = 0f; //Elévation du point de départ

	public VisitorDistanceElevation(){
		distance = new ArrayList<>();
		elevation = new ArrayList<>();
		elev_origine = 0f;
	}
	public List<Float> getDistance(){ return distance; }
	public List<Float> getElevation(){ return elevation; }

	public double haversine(Coordonees c1, Coordonees c2){
		double DLat = Math.toRadians(c2.lattitude - c1.lattitude);
		double DLong = Math.toRadians(c2.longitude - c1.longitude);
		double a = Math.pow(Math.sin(DLat / 2),2) + Math.pow(Math.sin(DLong / 2),2) * Math.cos(Math.toRadians(c1.lattitude)) * Math.cos(Math.toRadians(c2.lattitude));
		double c = 2*Math.asin(Math.sqrt(a));
		return R * c;
	}

	@Override
	public void visit(Parcours parc) {
		parc.accept(this);
	}

	@Override
	public void visit(FicheTech fiche) {

	}

	@Override
	public void visit(Coordonees cord) {
		float d = 0f;
		if (lastCords != null) {
			d = distance.get(distance.size()-1);
			d += (float)haversine(lastCords, cord);
			lastCords = cord;
		}
		else {
			lastCords = cord;
		}
		distance.add(d);
		if (elevation.isEmpty()){
			elev_origine = cord.elevation;
		}
		elevation.add(cord.elevation - elev_origine);
		cord.accept(this);
	}

	@Override
	public void visit(Trace trace) {
		trace.accept(this);
	}
}
