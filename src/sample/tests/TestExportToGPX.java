package sample.tests;

import GPX.Reader;
import GPX.Writer;
import javafx.scene.image.Image;
import sample.*;

import java.util.ArrayList;
import java.util.Objects;

public class TestExportToGPX{

	public static void main(String[] args) {
		VisitorToGPX v1 = new VisitorToGPX();
		Reader r = Reader.CreateReader("GPX/outputTestGPX.gpx");

		Parcours parc = r.getParcours();
		v1.visit(parc);
		System.out.println(v1);
		Writer writer = Writer.CreateWriter("GPX/outputTestGPX.gpx");
		assert writer != null;
		writer.writeGPX(parc);

	}
}
