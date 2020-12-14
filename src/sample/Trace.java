package sample;

import java.util.ArrayList;
import java.util.List;

public class Trace implements ElementVisitor {
	List<Coordonees> chemin;

	public Trace(){
		chemin = new ArrayList<Coordonees>();
	}

	public List<Coordonees> getChemin(){
		return chemin;
	}

	@Override
	public void accept(Visitor v) {
		for (Coordonees c : chemin){
			v.visit(c);
		}
	}
}
