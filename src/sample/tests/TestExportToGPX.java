package sample.tests;

import GPX.Reader;
import GPX.Writer;
import javafx.scene.image.Image;
import sample.*;

import java.util.ArrayList;
import java.util.Objects;

public class TestExportToGPX{

	public static void main(String[] args) {
		VisitorToGPX v = new VisitorToGPX();
		Trace trace = Objects.requireNonNull(Reader.CreateReader("traceTest.gpx")).getTrace();
		v.visit(trace);
		System.out.println(v.str);

		Parcours p = new Parcours("Parcours", "short description", "long description", new ArrayList<Image>(), new FicheTech(), trace);
		Writer writer = new Writer("outputTestGPX.gpx");
		writer.writeGPX(p);

	}
}
