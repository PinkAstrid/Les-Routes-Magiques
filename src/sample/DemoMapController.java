
package sample;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private final MapLabel TNLabel;
	private Marker markerClick;

	private CoordinateLine redLine;
	private List<CoordinateLine> followedLines;

	public DemoMapController() {
		markerClick = Marker.createProvided(Marker.Provided.ORANGE).setVisible(true).setPosition(TelecomNancy);

		TNLabel = new MapLabel("Telecom Nancy").setPosition(TelecomNancy).setVisible(true);
		followedLines = new ArrayList<CoordinateLine>();
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

			if (markerClick.getVisible()) {
				final Coordinate oldPosition = markerClick.getPosition();
				if (oldPosition != null) {
					markerClick.setPosition(newPosition);
				} else {
					markerClick.setPosition(newPosition);
					// adding can only be done after coordinate is set
					mapView.addMarker(markerClick);
				}
			}
		});

		redLine = new CoordinateLine(new Coordinate[]{new Coordinate(48.6692041D,6.156187D), new Coordinate(48.6702041D,6.166187D)}).setColor(Color.RED).setVisible(true);

		this.mapView.initialize(Configuration.builder().projection(projection).showZoomControls(false).build());

	}
	private void afterMapIsInitialized() {
		logger.trace("map intialized");
		this.mapView.setZoom(ZOOM_DEFAULT);
		this.mapView.setCenter(TelecomNancy);

		mapView.addLabel(TNLabel);
		mapView.addCoordinateLine(redLine);
	}
}
