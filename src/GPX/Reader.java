package GPX;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.CoordinateLine;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sample.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reader {
	private File file;
	private DocumentBuilder db;
	private Document doc;

	/**
	 * lecteur de fichier GPX
	 * @param filePath
	 * 		chemin du fichier à lire
	 * @throws ParserConfigurationException
	 * 		Si le document Builder ne peut être créé
	 * @throws IOException
	 * 		si le fichier est invalide
	 * @throws SAXException
	 * 		si une erreur de parser est soulevé
	 */
	public Reader(String filePath) throws ParserConfigurationException, IOException, SAXException {
		file = new File(filePath);
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
			return new Reader(filePath);
		}catch (IOException | ParserConfigurationException | SAXException e){
			System.err.println(e.getClass());
			e.printStackTrace();
		}
		return null;
	}

	public File getFile() {
		return file;
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
		NodeList list;
		list = this.getElementsByTag("wpt");
		for (int i = 0; i < list.getLength() ; i++) {
			Node n = list.item(i);
			System.out.println("name : " + getName(n));
			System.out.println("Latitude : " + getLatitude(n));
			System.out.println("Longitude : " + getLongitude(n));
			System.out.println("Elevation : " + getElevation(n) + "\n");
		}
		list = this.getElementsByTag("trkpt");
		for (int i = 0; i < list.getLength() ; i++) {
			Node n = list.item(i);
			System.out.println("Latitude : " + getLatitude(n));
			System.out.println("Longitude : " + getLongitude(n));
			System.out.println("Elevation : " + getElevation(n) + "\n");
		}
	}
	/**
	 * renvoie le nom de la node n
	 * @param n
	 * 		node à regarder
	 * @return double
	 * 		nom de la node n, renvoie une chaine vide si la node n'as pas de nom
	 */
	static String getName(Node n){
		Element e = (Element) n;
		if ( e.getElementsByTagName("name").getLength() > 0){
			return e.getElementsByTagName("name").item(0).getTextContent();
		}
		return "";
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
	 * @return List<Waypoint>
	 * 		listes des waypoints correspondant aux coordonées des nodes du gpx
	 */
	public List<Waypoint> getWaypoints(){
		List<Waypoint> listWpt = new ArrayList<Waypoint>();
		NodeList list;
		list = this.getElementsByTag("wpt");
		for (int i = 0; i < list.getLength() ; i++) {
			listWpt.add(new Waypoint(getCords(list.item(i)), getName(list.item(i))));
		}
		return listWpt;
	}

	/**
	 *
	 * @return Trace
	 * 		trace correspondant aux coordonées des nodes du gpx
	 */
	public Trace getTrace(){
		List<Coordonees> listCords = new ArrayList<Coordonees>();
		NodeList list;
		list = this.getElementsByTag("trkpt");
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

	public String getParcoursName(){
		String str = getName(this.getElementsByTag("metadata").item(0));
		return str;
	}

	public String getDetails(){
		NodeList list = this.getElementsByTag("metadata");
		Node n;
		if (list.getLength() > 0)
			n = list.item(0);
		else
			return "";
		Element e = (Element) n;
		if (e.getElementsByTagName("details").getLength() > 0)
			return e.getElementsByTagName("details").item(0).getTextContent();
		return "";
	}

	public String getDescription(){
		NodeList list = this.getElementsByTag("metadata");
		Node n;
		if (list.getLength() > 0)
			n = list.item(0);
		else
			return "";
		Element e = (Element) n;
		if (e.getElementsByTagName("description").getLength() > 0)
			return e.getElementsByTagName("description").item(0).getTextContent();
		return "";
	}

	public float getDuree(){
		NodeList list = this.getElementsByTag("metadata");
		Node n;
		if (list.getLength() > 0)
			n = list.item(0);
		else
			return 0f;
		Element e = (Element) n;
		if (e.getElementsByTagName("duree").getLength() > 0)
			return Float.parseFloat(e.getElementsByTagName("duree").item(0).getTextContent());
		return 0f;
	}

	public float getDistance(){
		NodeList list = this.getElementsByTag("metadata");
		Node n;
		if (list.getLength() > 0)
			n = list.item(0);
		else
			return 0f;
		Element e = (Element) n;
		if (e.getElementsByTagName("distance").getLength() > 0)
			return Float.parseFloat(e.getElementsByTagName("distance").item(0).getTextContent());
		return 0f;
	}

	public float getDenivele(){
		NodeList list = this.getElementsByTag("metadata");
		Node n;
		if (list.getLength() > 0)
			n = list.item(0);
		else
			return 0f;
		Element e = (Element) n;
		if (e.getElementsByTagName("denivele").getLength() > 0)
			return Float.parseFloat(e.getElementsByTagName("denivele").item(0).getTextContent());
		return 0f;
	}
	public int getDificulte(){
		NodeList list = this.getElementsByTag("metadata");
		Node n;
		if (list.getLength() > 0)
			n = list.item(0);
		else
			return 0;
		Element e = (Element) n;
		if (e.getElementsByTagName("difficulte").getLength() > 0)
			return Integer.parseInt(e.getElementsByTagName("difficulte").item(0).getTextContent());
		return 0;
	}

	public FicheTech getFiche(){
		return new FicheTech(getDuree(), getDistance(), getDenivele(), getDificulte());
	}

	public Parcours getParcours(){
		Parcours p = new Parcours();
		p.setName(this.getParcoursName());
		p.setDescCourte(this.getDescription());
		p.setDescLongue(this.getDetails());
		p.setFiche(this.getFiche());
		p.setTrace(this.getTrace());
		p.setWaypoints(this.getWaypoints());
		return p;
	}

}
