package sample.tests;

import javafx.scene.image.Image;
import sample.Coordonees;
import sample.Parcours;
import sample.VisitorDistanceElevation;
import sample.model.GestionnaireParcours;

import java.util.ArrayList;
import java.util.List;

public class TestDistanceElevation {
	public static void main(String[] args) {
		VisitorDistanceElevation v = new VisitorDistanceElevation();

		Coordonees c0 = new Coordonees(0.2f, 0f, 0f);
		Coordonees c1 = new Coordonees(0.1f, 0f, 0.1f);
		Coordonees c2 = new Coordonees(0.2f, 0f, 0.2f);
		Coordonees c3 = new Coordonees(0.2f, 0.1f, 0.1f);
		List<Coordonees> list = new ArrayList<Coordonees>();
		list.add(c0);
		list.add(c1);
		list.add(c2);
		list.add(c3);
		ArrayList<Image> photos = new ArrayList<Image>();
		GestionnaireParcours gestion = new GestionnaireParcours();

		Parcours p = gestion.createParcours(list, 2.5f, 5.0f, 200, 1, "titre", "description", "details", photos, new ArrayList<>());
		v.visit(p);
		System.out.println(v.getElevation());
		System.out.println(v.getDistance());
	}
}
