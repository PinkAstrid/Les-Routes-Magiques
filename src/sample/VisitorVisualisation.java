package sample;

public class VisitorVisualisation implements Visitor {
	@Override
	public void visit(Parcours parc) {
		System.out.println("Name:"+parc.name);
		System.out.println("description(short):"+parc.descCourte);
		System.out.println("description:"+parc.descLongue);
		System.out.println("photos:"+parc.photos.size());
		parc.accept(this);
	}

	@Override
	public void visit(FicheTech fiche) {
		System.out.println("durée:"+fiche.duree);
		System.out.println("distance:"+fiche.duree);
		System.out.println("denivelé:"+fiche.duree);
		System.out.println("difficulté:"+fiche.duree+"/5");
		fiche.accept(this);
	}

	@Override
	public void visit(Coordonees cord) {
		System.out.println("\tlat:"+ cord.lattitude+" long:"+cord.longitude+" elev:"+cord.elevation);
		cord.accept(this);
	}

	@Override
	public void visit(Trace trace) {
		System.out.println("chemin :");
		trace.accept(this);
	}
}
