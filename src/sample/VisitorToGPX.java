package sample;

public class VisitorToGPX implements Visitor{

	public String str = "";

	@Override
	public void visit(Parcours parc) {
		str += "\t<metadata>\n";
		str += "\t\t<name>"+ parc.name +"</name>\n";
		str += "\t</metadata>\n";
		parc.accept(this);
	}

	@Override
	public void visit(FicheTech fiche) {

	}

	@Override
	public void visit(Coordonees cord) {
		str += "\t<wpt lat=\""+cord.lattitude+"\" lon=\""+cord.longitude+"\">\n";
		str += "\t\t<ele>"+cord.elevation+"</ele>\n";
		str += "\t</wpt>\n";
	}

	@Override
	public void visit(Trace trace) {
		trace.accept(this);

	}
}
