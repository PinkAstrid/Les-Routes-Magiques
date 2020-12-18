package GPX;

import sample.Parcours;
import sample.VisitorToGPX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Writer {
	File file;
	boolean isNew = true;

	public Writer(String filePath) throws IOException {
		String[] strTab = filePath.split("/");
		if (strTab.length > 1){
			String folderPath = "";
			for (int i = 0; i < strTab.length -1; i++){
				folderPath += strTab[i] + "/";
			}
			Path folder = Paths.get(folderPath);
			Files.createDirectories(folder);
		}
		file = new File(filePath);
		if (!file.createNewFile()){
			isNew = false;
		}
	}

	public static Writer CreateWriter(String filePath){
		try{
			Writer w = new Writer(filePath);
			return w;
		} catch (IOException ioException) {
			System.err.println(filePath);
			ioException.printStackTrace();
		}
		return null;
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
