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
		Trace trace = Objects.requireNonNull(Reader.CreateReader("src/traceTest.gpx")).getTrace();
		v.visit(trace);

		Parcours p = new Parcours("Parcours", "short description", "long description", new ArrayList<Image>(), new FicheTech(), trace);
		Writer writer = Writer.CreateWriter("GPX/outputTestGPX.gpx");
		assert writer != null;
		writer.writeGPX(p);

		VisitorVisualisation visu = new VisitorVisualisation();

		Reader reader = Reader.CreateReader("GPX/outputTestGPX.gpx");
		assert reader != null;
		visu.visit(reader.getTrace());

	}
}
