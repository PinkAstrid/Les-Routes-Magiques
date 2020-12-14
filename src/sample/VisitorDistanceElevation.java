package sample;

import java.util.ArrayList;
import java.util.List;

public class VisitorDistanceElevation implements Visitor{

	List<Float> distance;
	Coordonees lastCords;
	List<Float> elevation;

	public VisitorDistanceElevation(){
		distance = new ArrayList<>();
		distance.add(0f);
		elevation = new ArrayList<>();
		elevation.add(0f);
	}
	public List<Float> getDistance(){ return distance; }
	public List<Float> getElevation(){ return elevation; }

	public float haversine(Coordonees c1, Coordonees c2){
		return 0f;
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
			d = haversine(lastCords, cord);
			lastCords = cord;
		}
		else {
			d = cord.lattitude + cord.longitude;
			lastCords = cord;
		}
		distance.add(d);
		elevation.add(cord.elevation);
		cord.accept(this);
	}

	@Override
	public void visit(Trace trace) {
		trace.accept(this);
	}
}
