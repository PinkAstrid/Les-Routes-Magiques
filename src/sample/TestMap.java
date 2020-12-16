package sample;

import GPX.Reader;
import com.sothawo.mapjfx.Projection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.interfa.DemoMapController;

public class TestMap extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String fxmlFile = "demoMap.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader();
		Parent rootNode = null;
		rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));

		final DemoMapController controller = fxmlLoader.getController();
		final Projection projection = getParameters().getUnnamed().contains("wgs84")
				? Projection.WGS_84 : Projection.WEB_MERCATOR;
		controller.initMapAndControls(projection);

		Scene scene = new Scene(rootNode);

		System.out.println("Création d'un lecteur de fichier xml pour la trace gpx");
		Reader gpxReader = Reader.CreateReader("./traceTest.gpx");
		System.out.println("Creation du visiteur pour la trace");
		VisitorVisualisation v = new VisitorVisualisation();
		System.out.println("Creation de l'objet Trace");
		Trace trace = gpxReader.getTrace();
		System.out.println("Visite");
		controller.addCoordinateLine(trace);

		primaryStage.setTitle("sothawo mapjfx demo application");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
