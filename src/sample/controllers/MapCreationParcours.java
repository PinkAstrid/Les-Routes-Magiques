package sample.controllers;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapLabelEvent;
import com.sothawo.mapjfx.event.MapViewEvent;
import com.sothawo.mapjfx.event.MarkerEvent;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import sample.Coordonees;
import sample.Parcours;
import sample.Trace;
import sample.Waypoint;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MapCreationParcours {
	private static final Coordinate TelecomNancy = new Coordinate(48.6692041D,6.156187D);
	private static final int ZOOM_DEFAULT = 14;

	@FXML
	public ToggleGroup group;
	@FXML
	public TextField labelText;
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

	private MapLabel selectedLabel;
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

		mapView.addEventHandler(MapLabelEvent.MAPLABEL_CLICKED, event -> {
			selectedLabel = event.getMapLabel();
			labelText.setDisable(false);
			labelText.requestFocus();
		});

		labelText.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER){
				if (selectedLabel != null){
					if (selectedLabel.getMarker().isPresent()) {
						Marker marker = selectedLabel.getMarker().get();
						marker.detachLabel();
						followedLabel.remove(selectedLabel);
						mapView.removeLabel(selectedLabel);

						MapLabel newLabel = new MapLabel(labelText.getText()).setVisible(true);
						marker.attachLabel(newLabel);
						followedLabel.add(newLabel);
						mapView.addLabel(newLabel);

						labelText.setText("");
						labelText.setDisable(true);
					}
				}
			}
		});
		mapView.addEventHandler(MapLabelEvent.MAPLABEL_RIGHTCLICKED, event -> {
			Marker marker;
			if (event.getMapLabel().getMarker().isPresent()) {
				marker = event.getMapLabel().getMarker().get();
				marker.setVisible(false);
				mapView.removeMarker(marker);
				followedMarker.remove(marker);
			}
			MapLabel label = event.getMapLabel().setVisible(false);
			mapView.removeLabel(label);
			followedLabel.remove(label);
		});
		mapView.addEventHandler(MarkerEvent.MARKER_RIGHTCLICKED, event -> {
			if (event.getMarker().getMapLabel().isPresent()) {
				MapLabel label = event.getMarker().getMapLabel().get().setVisible(false);
				mapView.removeLabel(label);
				followedLabel.remove(label);
			}
			Marker marker = event.getMarker().setVisible(false);
			mapView.removeMarker(marker);
			followedMarker.remove(marker);
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
				.setClosed(false);
		mapView.addCoordinateLine(polygonLine);
		polygonLine.setVisible(true);
	}


	private void handleMarkerClick(MapViewEvent event) {
		Marker marker = new Marker(getClass().getResource("/ressources/images/ressource/blackmarker.png"), -25, -50).setPosition(event.getCoordinate()).setVisible(true);
		MapLabel mapLabel = new MapLabel("Clique moi pour me changer").setVisible(true);
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
			m.detachLabel();
			mapView.removeMarker(m);
		}
		for (MapLabel l : followedLabel){
			mapView.removeLabel(l);
		}
		this.followedMarker = new ArrayList<>();
		this.followedLabel = new ArrayList<>();
	}

	private void deleteAll(){
		deleteLine();
		deleteMarker();
	}

	public CoordinateLine getPolygonLine() {
		return polygonLine;
	}

	public List<Marker> getFollowedMarker() {
		return followedMarker;
	}

	public Trace getTrace(){
		List<Coordonees> list = new ArrayList<Coordonees>();
		List<Coordinate> l2 = polygonLine.getCoordinateStream().collect(Collectors.toList());
		for( Coordinate c : l2){
			list.add(new Coordonees(c.getLatitude().floatValue(), c.getLongitude().floatValue(), 0));
		}
		return new Trace(list);
	}

	public List<Waypoint> getWaypoints(){
		List<Waypoint> list = new ArrayList<>();
		String name;
		for(Marker m : followedMarker){
			if (m.getMapLabel().isPresent()){
				name = m.getMapLabel().get().getText();
			}
			else{
				name = "";
			}
			list.add(new Waypoint(new Coordonees(m.getPosition().getLatitude().floatValue(), m.getPosition().getLongitude().floatValue(), 0f), name));
		}
		return list;
	}

	public void addParcours(Parcours p){
		Marker marker;
		MapLabel label;
		for (Waypoint w : p.getWaypoints()){
			marker = new Marker(getClass().getResource("/ressources/images/ressource/blackmarker.png"), -25, -50).setPosition(new Coordinate((double)w.getCords().getLattitude(), (double)w.getCords().getLongitude())).setVisible(true);
			label = new MapLabel(w.getName()).setVisible(true);
			marker.attachLabel(label);
			followedMarker.add(marker);
			followedLabel.add(label);
			mapView.addMarker(marker);
			mapView.addLabel(label);
		}

		final List<Coordinate> coordinates = new ArrayList<>(p.getTrace().getListCoordinates());
		if (polygonLine != null) {
			mapView.removeCoordinateLine(polygonLine);
			polygonLine = null;
		}
		polygonLine = new CoordinateLine(coordinates)
				.setColor(Color.DODGERBLUE)
				.setClosed(false);
		mapView.addCoordinateLine(polygonLine);
		polygonLine.setVisible(true);
	}

}
