package sample.interfa;

import GPX.Reader;
import com.sothawo.mapjfx.Projection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.Parcours;
import sample.Trace;
import sample.Visitor;
import sample.VisitorVisualisation;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FicheTechControl implements Initializable {

    public GridPane grid;
    public AnchorPane anchorMap;
    @FXML
    private Label FicheName;
    @FXML
    private Label FicheShortDescr;
    @FXML
    private Label FicheLongDescr;
    @FXML
    private Label duree;
    @FXML
    private Label distance;
    @FXML
    private Label difficulte;
    @FXML
    private Label denivele;
    @FXML
    private ImageView imRando0;

    public FicheTechControl(){}

    public void myFichControl(Parcours p) throws IOException {
        VisitorVisualisation v = new VisitorVisualisation();
        v.visit(p);
        FicheName.setText(v.getName());
        FicheShortDescr.setText(v.getDescCourte());
        FicheLongDescr.setText(v.getDescLongue());
        duree.setText(v.getDuree()+"h");
        distance.setText(v.getDistance()+"km");
        difficulte.setText(v.getDiff()+"/5");
        denivele.setText(v.getDenivele()+"m");

        if (!v.getPhotos().isEmpty()){
            imRando0.setImage(v.getPhotos().get(0));
        }

        //Def carte
        String fxmlFile = "demoMap.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = null;
        rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));

        final DemoMapController controller = fxmlLoader.getController();
        final Projection projection = Projection.WEB_MERCATOR;
        controller.initMapAndControls(projection);

        controller.addCoordinateLine(p).setWidth(20);

        anchorMap.getChildren().add(rootNode);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
