package sample;

import sample.Coordonees;
import sample.ElementVisitor;
import sample.Visitor;

public class Waypoint implements ElementVisitor {

	private Coordonees cords;
	private String name;

	public Waypoint(){
		cords = new Coordonees();
		name = "";
	}

	public Waypoint(Coordonees cords, String name){
		this.cords = cords;
		this.name = name;
	}

	public Coordonees getCords() {
		return cords;
	}

	public void setCords(Coordonees cords) {
		this.cords = cords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(cords);
	}
}
