package sample.tests;

import GPX.Reader;
import com.sothawo.mapjfx.Projection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Parcours;
import sample.Trace;
import sample.VisitorVisualisation;
import sample.controllers.DemoMapController;

public class TestMap extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String fxmlFile = "/ressources/layout/demoMap.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader();
		Parent rootNode = null;
		rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));

		final DemoMapController controller = fxmlLoader.getController();
		final Projection projection = getParameters().getUnnamed().contains("wgs84")
				? Projection.WGS_84 : Projection.WEB_MERCATOR;
		controller.initMapAndControls(projection);

		Scene scene = new Scene(rootNode);

		Reader gpxReader = Reader.CreateReader("src/traceTest.gpx");

		VisitorVisualisation v = new VisitorVisualisation();

		Parcours p = gpxReader.getParcours();

		controller.addCoordinateLine(p).setWidth(10).setColor(Color.RED);
		controller.addListWaypoint(p.getWaypoints());

		primaryStage.setTitle("sothawo mapjfx demo application");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
