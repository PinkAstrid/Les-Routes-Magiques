package GPX;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sample.Coordonees;
import sample.Trace;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	private File file;
	private DocumentBuilder db;
	private Document doc;

	public Reader(String filePath) throws ParserConfigurationException, IOException, SAXException {
		file = new File("src/traceTest.gpx");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		db = dbf.newDocumentBuilder();
		doc = db.parse(file);
		doc.getDocumentElement().normalize();
	}

	public static Reader CreateReader(String filePath) {
		try {
			Reader r = new Reader(filePath);
			return r;
		}catch (IOException ioException){
			System.err.println("IO error");
		}catch (SAXException saxException){
			System.err.println("parsing error");
		}catch (ParserConfigurationException parserConfigurationException){
			System.err.println("Parser configuration error");
		}
		return null;
	}

	public Node getRoot(){
		return doc.getDocumentElement();
	}

	public NodeList getElementsByTag(String tag){
		return doc.getElementsByTagName(tag);
	}

	public void print(){
		Node root = this.getRoot();
		System.out.println("Root : " + root.getNodeName());
		NodeList list = this.getElementsByTag("wpt");
		for (int i = 0; i < list.getLength() ; i++) {
			Node n = list.item(i);
			System.out.println("Latitude : " + getLatitude(n));
			System.out.println("Longitude : " + getLongitude(n));
			System.out.println("Elevation : " + getElevation(n) + "\n");
		}
	}

	static double getLatitude(Node n){
		Element e = (Element) n;
		if ( e.hasAttribute("lat")){
			return Double.parseDouble(e.getAttribute("lat"));
		}
		return 0d;
	}
	static double getLongitude(Node n){
		Element e = (Element) n;
		if ( e.hasAttribute("lon")){
			return Double.parseDouble(e.getAttribute("lon"));
		}
		return 0d;
	}
	static double getElevation(Node n){
		Element e = (Element) n;
		if ( e.getElementsByTagName("ele").getLength() > 0){
			return Double.parseDouble(e.getElementsByTagName("ele").item(0).getTextContent());
		}
		return 0d;
	}

	static Coordonees getCords(Node n){
		return new Coordonees((float)getLatitude(n), (float)getLongitude(n), (float)getElevation(n));
	}

	public Trace getTrace(){
		List<Coordonees> listCords = new ArrayList<Coordonees>();
		NodeList list = this.getElementsByTag("wpt");
		for (int i = 0; i < list.getLength() ; i++) {
			listCords.add(getCords(list.item(i)));
		}
		return new Trace(listCords);
	}

}
