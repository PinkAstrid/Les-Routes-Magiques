package GPX;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.CoordinateLine;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sample.Coordonees;
import sample.Trace;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	private File file;
	private DocumentBuilder db;
	private Document doc;

	/**
	 * lecteur de fichier GPX
	 * @param filePath
	 * 		chemin du fichier à lire
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public Reader(String filePath) throws ParserConfigurationException, IOException, SAXException {
		file = new File(getClass().getClassLoader().getResource(filePath).getFile());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		db = dbf.newDocumentBuilder();
		doc = db.parse(file);
		doc.getDocumentElement().normalize();
	}

	/**
	 * renvoie un lecteur de GPX, catch les erreurs
	 * @param filePath
	 * 		chemin du fichier à lire
	 * @return Reader
	 * 		l'instance créé du lecteur
	 */
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

	/**
	 * @return Node
	 * 		node racine du fichier
	 */
	public Node getRoot(){
		return doc.getDocumentElement();
	}

	/**
	 * @param tag
	 * 		tag à vérifier
	 * @return NodeList
	 * 		liste des node avec le tag
	 */
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

	/**
	 * renvoie la latitude de la node n
	 * @param n
	 * 		node à regarder
	 * @return double
	 * 		latitude de la node n, 0 si la node n'as pas de latitude
	 */
	static double getLatitude(Node n){
		Element e = (Element) n;
		if ( e.hasAttribute("lat")){
			return Double.parseDouble(e.getAttribute("lat"));
		}
		return 0d;
	}
	/**
	 * renvoie la longitude de la node n
	 * @param n
	 * 		node à regarder
	 * @return double
	 * 		longitude de la node n, 0 si la node n'as pas de longitude
	 */
	static double getLongitude(Node n){
		Element e = (Element) n;
		if ( e.hasAttribute("lon")){
			return Double.parseDouble(e.getAttribute("lon"));
		}
		return 0d;
	}
	/**
	 * renvoie l'élévation associé à la node n
	 * @param n
	 * 		node à regarder
	 * @return double
	 * 		élévation de la node n, 0 si la node n'as pas d'élévation
	 */
	static double getElevation(Node n){
		Element e = (Element) n;
		if ( e.getElementsByTagName("ele").getLength() > 0){
			return Double.parseDouble(e.getElementsByTagName("ele").item(0).getTextContent());
		}
		return 0d;
	}

	/**
	 * renvoie les coordonées rangé dans la node
	 * @param n
	 * 		node à regarder
	 * @return Coordonees
	 * 		coordonnées de la node n
	 */
	static Coordonees getCords(Node n){
		return new Coordonees((float)getLatitude(n), (float)getLongitude(n), (float)getElevation(n));
	}

	/**
	 *
	 * @return Trace
	 * 		trace correspondant aux coordonées des nodes du gpx
	 */
	public Trace getTrace(){
		List<Coordonees> listCords = new ArrayList<Coordonees>();
		NodeList list = this.getElementsByTag("wpt");
		for (int i = 0; i < list.getLength() ; i++) {
			listCords.add(getCords(list.item(i)));
		}
		return new Trace(listCords);
	}

	/**
	 * conversion en format mapjfx de la trace contenu dans le gpx
	 * @return List<Coordinate>
	 *     	liste des coordonées contenu dans le fichier
	 */
	public List<Coordinate> getListCoordinates(){
		return this.getTrace().getListCoordinates();
	}

}
