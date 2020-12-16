package GPX;

import sample.Parcours;
import sample.VisitorToGPX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	File file;
	boolean isNew = true;

	public Writer(String filePath){

		try{
			file = new File(filePath);
			if (!file.createNewFile()){
				isNew = false;
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void writeGPX(Parcours parc){
		try{
			FileWriter fw = new FileWriter(file);
			VisitorToGPX v = new VisitorToGPX();
			v.visit(parc);
			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
			fw.write("<gpx version=\"1.1\" creator=\"Telecorne Viking\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.topografix.com/GPX/1/1\" xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\">\n");
			fw.write(v.str);
			fw.write("</gpx>");
			fw.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

}
