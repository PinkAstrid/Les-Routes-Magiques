
package sample.interfa;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.Parcours;
import sample.Trace;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DemoMapController {
	private static final Logger logger = LoggerFactory.getLogger(DemoMapController.class);
	private static final Coordinate TelecomNancy = new Coordinate(48.6692041D,6.156187D);
	private static final int ZOOM_DEFAULT = 14;
	@FXML
	private Button buttonZoom;
	@FXML
	private MapView mapView;

	private Marker markerClick;

	private List<CoordinateLine> followedLines;
	private List<Marker> followedMarker;

	public DemoMapController() {
		markerClick = Marker.createProvided(Marker.Provided.ORANGE);
		followedLines = new ArrayList<CoordinateLine>();
		followedMarker = new ArrayList<Marker>();
	}

	public void initMapAndControls(Projection projection) {
		this.mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				this.afterMapIsInitialized();
			}

		});

		mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
			event.consume();
			final Coordinate newPosition = event.getCoordinate().normalize();
			markerClick.setPosition(newPosition).setVisible(true);

			final Coordinate oldPosition = markerClick.getPosition();
			if (oldPosition != null) {
				mapView.addMarker(markerClick);
			}
		});

		mapView.addEventHandler(MapViewEvent.MAP_RIGHTCLICKED, event -> {
			event.consume();
			if (markerClick.getVisible()) {
				markerClick.setVisible(false);
			}
		});


		this.mapView.initialize(Configuration.builder().projection(projection).showZoomControls(true).build());

	}
	private void afterMapIsInitialized() {
		logger.trace("map intialized");
		this.mapView.setZoom(ZOOM_DEFAULT);
		this.mapView.setCenter(TelecomNancy);

		for (CoordinateLine cl : followedLines){
			mapView.addCoordinateLine(cl);
		}
	}

	/**
	 * ajout d'une CoordinateLine sur la carte
	 * @param p
	 * 		parcours contenant le chemin à afficher
	 * @return CoordinateLine
	 * 		CoordinateLine créé dans la liste followedLines
	 */
	public CoordinateLine addCoordinateLine(Parcours p){
		return addCoordinateLine(p.getTrace());
	}

	/**
	 * ajout d'une CoordinateLine sur la carte
	 * @param t
	 * 		trace contenant le chemin à afficher
	 * @return CoordinateLine
	 * 		CoordinateLine créé dans la liste followedLines
	 */
	public CoordinateLine addCoordinateLine(Trace t){
		return addCoordinateLine(new CoordinateLine(t.getListCoordinates()));
	}

	/**
	 * ajout d'une CoordinateLine sur la carte
	 * @param cl
	 * 		CoordinateLine à ajouter
	 * @return CoordinateLine
	 * 		CoordinateLine créée
	 */
	public CoordinateLine addCoordinateLine(CoordinateLine cl) {
		if (followedLines.contains(cl)){
			cl.setVisible(true);
			return cl;
		}
		else{
			followedLines.add(cl);
			cl.setVisible(true);
			mapView.addCoordinateLine(cl);
			return cl;
		}
	}

	/**
	 * Ajoute un marker sur la carte
	 * @param url
	 * 		url de l'image à ajouter sur la carte
	 * @return Marker
	 * 		Marker ajouté
	 */
	public Marker addMarker(URL url){
		Marker m = new Marker(url);
		return addMarker(m);
	}

	/**
	 * Ajoute un marker sur la carte
	 * @param m
	 * 		Marker à ajouter sur la carte
	 * @return Marker
	 * 		Marker ajouté
	 */
	public Marker addMarker(Marker m){
		if (followedMarker.contains(m)){
			m.setVisible(true);
			return m;
		}
		else{
			followedMarker.add(m);
			m.setVisible(true);
			mapView.addMarker(m);
			return m;
		}
	}

	/**
	 * renvoie la liste des lignes sur la carte
	 * @return List<CoordinateLine>
	 */
	public List<CoordinateLine> getFollowedLines(){
		return followedLines;
	}

	/**
	 * renvoie la liste des marker sur la carte
	 * @return List<Marker>
	 */
	public List<Marker> getMarker(){
		return followedMarker;
	}

}
