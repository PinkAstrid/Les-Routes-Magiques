package sample;

public class VisitorToGPX implements Visitor{

	public String str = "";

	/**
	 * override visite d'un parcours GPX
	 * @param parc
	 * parcours à visiter
	 */
	@Override
	public void visit(Parcours parc) {
		str += "\t<metadata>\n";
		str += "\t\t<name>"+ parc.name +"</name>\n";
		str += "\t\t<details>"+parc.descLongue+"</details>\n";
		str += "\t\t<description>"+parc.descCourte+"</description>\n";
		parc.accept(this);
	}

	/**
	 * override visite d'un parcours GPX
	 * @param fiche
	 * fiche technique à visiter
	 */
	@Override
	public void visit(FicheTech fiche) {

		str += "\t\t<duree>"+fiche.duree+"</duree>\n";
		str += "\t\t<distance>"+fiche.distance+"</distance>\n";
		str += "\t\t<denivele>"+fiche.denivele+"</denivele>\n";
		str += "\t\t<difficulte>"+fiche.difficulte+"</difficulte>\n";
		str += "\t</metadata>\n";
	}

	/**
	 * override visite des coordonnées d'un parcours GPX
	 * @param cord
	 * coordonnées à visiter
	 */
	@Override
	public void visit(Coordonees cord) {
		str += "\t\t\t<trkpt lat=\""+cord.lattitude+"\" lon=\""+cord.longitude+"\">\n";
		str += "\t\t\t\t<ele>"+cord.elevation+"</ele>\n";
		str += "\t\t\t</trkpt>\n";
	}

	/**
	 * override visite des waypoints d'un parcours GPX
	 * @param wpt
	 * waypoints à visiter
	 */
	@Override
	public void visit(Waypoint wpt) {
		str += "\t<wpt lat=\""+wpt.getCords().lattitude+"\" lon=\""+wpt.getCords().longitude+"\">\n";
		str += "\t\t<ele>"+wpt.getCords().elevation+"</ele>\n";
		str += "\t\t<name>"+wpt.getName()+"</name>\n";
		str += "\t</wpt>\n";
	}

	/**
	 * override visite des coordonnées d'un parcours GPX
	 * @param trace
	 */
	@Override
	public void visit(Trace trace) {
		str += "\t<trk>\n" +
				"\t\t<trkseg>\n";
		trace.accept(this);
		str += "\t\t</trkseg>\n" +
				"\t</trk>\n";
	}

	/**
	 * fonction récupérant les éléments du parcours passés en chaîne de caractères
	 * @return
	 * ensemble des données du parcours passé en chaîne de caractères
	 */
	@Override
	public String toString() {
		return str;
	}
}
