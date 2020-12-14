package sample;

import java.util.ArrayList;
import java.util.List;

public class VisitorDistanceElevation implements Visitor{

	List<Float> distance;
	List<Float> elevation;

	public VisitorDistanceElevation(){
		distance = new ArrayList<>();
		elevation = new ArrayList<>();
	}

	public float haversine(){
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
		float d = distance.get(distance.size()-1);
		d = d+(cord.lattitude + cord.longitude);
		distance.add(d);
		float e = elevation.get(distance.size()-1);
		e = e + cord.elevation;
		elevation.add(e);
		cord.accept(this);
	}

	@Override
	public void visit(Trace trace) {

	}
}
