package sample;

import com.sothawo.mapjfx.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Trace implements ElementVisitor {
	List<Coordonees> chemin;

	public Trace(){
		chemin = new ArrayList<Coordonees>();
	}

	public Trace(List<Coordonees> chemin){
		this.chemin = chemin;
	}

	public List<Coordonees> getChemin(){
		return chemin;
	}
	public List<Coordinate> getListCoordinates(){
		List<Coordinate> list = new ArrayList<>();
		for (Coordonees c : this.getChemin()){
			list.add(new Coordinate((double)c.getLattitude(), (double)c.getLongitude()));
		}
		return list;

	}
	@Override
	public void accept(Visitor v) {
		for (Coordonees c : chemin){
			v.visit(c);
		}
	}
}
