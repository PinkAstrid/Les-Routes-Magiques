import GPX.Reader;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Trace;
import sample.VisitorDistanceElevation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class TestDenivle extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
		primaryStage.setTitle("Telecom Nancy Hiking");

		Trace trace = Objects.requireNonNull(Reader.CreateReader("traceTest.gpx")).getTrace();

		VisitorDistanceElevation v = new VisitorDistanceElevation();
		v.visit(trace);

		/* ----------- Graph ----------- */
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Distance");
		yAxis.setLabel("Denivelé");
		LineChart<Number, Number> graph = new LineChart<Number, Number>(xAxis, yAxis);
		Series<Number, Number> cd = new Series();
		for (int i = 0; i < v.getDistance().size(); i++){
			cd.getData().add(new Data<Number, Number>(v.getDistance().get(i), v.getElevation().get(i)));
		}
		graph.getData().add(cd);
		graph.setLegendVisible(false);
		/* ----------- End Graph ----------- */

		((GridPane)root).add(graph, 0, 0);

		primaryStage.setScene(new Scene(root, 600, 600));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
