package sample;

import com.sothawo.mapjfx.Projection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
		System.out.println(controller);
		final Projection projection = getParameters().getUnnamed().contains("wgs84")
				? Projection.WGS_84 : Projection.WEB_MERCATOR;
		controller.initMapAndControls(projection);

		Scene scene = new Scene(rootNode);

		primaryStage.setTitle("sothawo mapjfx demo application");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
