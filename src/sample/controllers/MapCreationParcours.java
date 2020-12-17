package sample.controllers;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapCreationParcours {
	private static final Coordinate TelecomNancy = new Coordinate(48.6692041D,6.156187D);
	private static final int ZOOM_DEFAULT = 14;

	@FXML
	public ToggleGroup group;

	@FXML
	private RadioButton line;
	@FXML
	private RadioButton mark;
	@FXML
	private Button resetLine;
	@FXML
	private Button resetMark;
	@FXML
	private Button resetAll;
	@FXML
	private MapView mapView;

	private CoordinateLine polygonLine;

	private List<Marker> followedMarker;
	private List<MapLabel> followedLabel;


	Extent extent;

	public MapCreationParcours() {
		followedMarker = new ArrayList<Marker>();
		followedLabel = new ArrayList<MapLabel>();
	}

	public void initMapAndControls(Projection projection) {
		this.mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				this.afterMapIsInitialized();
			}
		});

		resetLine.setOnAction(e -> deleteLine());
		resetMark.setOnAction(e -> deleteMarker());
		resetAll.setOnAction(e -> deleteAll());

		mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
			event.consume();
			final Coordinate newPosition = event.getCoordinate().normalize();
			if (group.getSelectedToggle() == line)
				handlePolygonClick(event);
			if (group.getSelectedToggle() == mark)
				handleMarkerClick(event);
		});

		this.mapView.initialize(Configuration.builder().projection(projection).showZoomControls(true).build());

	}

	/**
	 * est appelé après l'initialisation de la map
	 */
	private void afterMapIsInitialized() {
		this.mapView.setZoom(14.0D);
		this.mapView.setCenter(TelecomNancy);
		if (extent != null)
			mapView.setExtent(extent);

	}

	/**
	 * shows a new polygon with the coordinate from the added.
	 *
	 * @param event
	 *     event with coordinates
	 */
	private void handlePolygonClick(MapViewEvent event) {
		final List<Coordinate> coordinates = new ArrayList<>();
		if (polygonLine != null) {
			polygonLine.getCoordinateStream().forEach(coordinates::add);
			mapView.removeCoordinateLine(polygonLine);
			polygonLine = null;
		}
		coordinates.add(event.getCoordinate());
		polygonLine = new CoordinateLine(coordinates)
				.setColor(Color.DODGERBLUE)
				.setFillColor(Color.web("lawngreen", 0.4))
				.setClosed(false);
		mapView.addCoordinateLine(polygonLine);
		polygonLine.setVisible(true);
	}


	private void handleMarkerClick(MapViewEvent event) {
		Marker marker = new Marker(getClass().getResource("/ressources/images/ressource/blackmarker.png"), -25, -50).setPosition(event.getCoordinate()).setVisible(true);
		MapLabel mapLabel = new MapLabel("text holder").setVisible(true);
		marker.attachLabel(mapLabel);
		followedMarker.add(marker);
		followedLabel.add(mapLabel);
		mapView.addMarker(marker);
		mapView.addLabel(mapLabel);
	}

	private void deleteLine(){
		mapView.removeCoordinateLine(polygonLine);
		this.polygonLine = null;
	}

	private void deleteMarker(){
		for(Marker m : followedMarker){
			m.getMapLabel().ifPresent(mapLabel -> mapView.removeLabel(mapLabel));
			m.detachLabel();
			mapView.removeMarker(m);
		}
		this.followedMarker = new ArrayList<>();
		this.followedLabel = new ArrayList<>();
	}

	private void deleteAll(){
		deleteLine();
		deleteMarker();
	}

}
