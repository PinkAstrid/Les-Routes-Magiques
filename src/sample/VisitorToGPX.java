package sample;

public class VisitorToGPX implements Visitor{

	public String str = "";

	@Override
	public void visit(Parcours parc) {
		str += "\t<metadata>\n";
		str += "\t\t<name>"+ parc.name +"</name>\n";
		str += "\t\t<details>"+parc.descLongue+"</details>\n";
		str += "\t\t<description>"+parc.descCourte+"</description>\n";
		parc.accept(this);
	}

	@Override
	public void visit(FicheTech fiche) {

		str += "\t\t<duree>"+fiche.duree+"</duree>\n";
		str += "\t\t<distance>"+fiche.distance+"</distance>\n";
		str += "\t\t<denivele>"+fiche.denivele+"</denivele>\n";
		str += "\t\t<difficulte>"+fiche.difficulte+"</difficulte>\n";
		str += "\t</metadata>\n";
	}

	@Override
	public void visit(Coordonees cord) {
		str += "\t\t\t<trkpt lat=\""+cord.lattitude+"\" lon=\""+cord.longitude+"\">\n";
		str += "\t\t\t\t<ele>"+cord.elevation+"</ele>\n";
		str += "\t\t\t</trkpt>\n";
	}

	@Override
	public void visit(Waypoint wpt) {

	}

	@Override
	public void visit(Trace trace) {
		str += "\t<trk>\n" +
				"\t\t<trkseg>\n";
		trace.accept(this);
		str += "\t\t</trkseg>\n" +
				"\t</trk>\n";
	}
}
