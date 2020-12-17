package sample.tests;

import com.sothawo.mapjfx.Projection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.DemoMapController;
import sample.controllers.MapCreationParcours;

import java.io.IOException;

public class TestCreationParcoursOnMap extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		String fxmlFile = "/ressources/layout/mapCreationParcours.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader();
		Parent rootNode = null;
		rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));

		final MapCreationParcours controller = fxmlLoader.getController();
		final Projection projection = getParameters().getUnnamed().contains("wgs84")
				? Projection.WGS_84 : Projection.WEB_MERCATOR;
		controller.initMapAndControls(projection);

		Scene scene = new Scene(rootNode);
		primaryStage.setTitle("Test creation parcours sur map");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
