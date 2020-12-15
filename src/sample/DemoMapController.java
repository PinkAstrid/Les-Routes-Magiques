
package sample;

import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Projection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoMapController {
	private static final Logger logger = LoggerFactory.getLogger(DemoMapController.class);
	private static final Coordinate TelecomNancy = new Coordinate(48.6692041D,6.156187D);
	private static final int ZOOM_DEFAULT = 14;
	@FXML
	private Button buttonZoom;
	@FXML
	private MapView mapView;

	public DemoMapController() {

	}

	public void initMapAndControls(Projection projection) {

		//this.buttonZoom.setOnAction((event) -> {
		//	this.mapView.setZoom(14.0D);
		//});

		this.mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				this.afterMapIsInitialized();
			}

		});

		this.mapView.initialize(Configuration.builder().projection(projection).showZoomControls(false).build());

	}
	private void afterMapIsInitialized() {
		logger.trace("map intialized");
		this.mapView.setZoom(14.0D);
		this.mapView.setCenter(TelecomNancy);
	}
}
