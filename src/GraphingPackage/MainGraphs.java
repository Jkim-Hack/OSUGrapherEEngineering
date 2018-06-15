/*
 This class uses kerner1000's "Javafx-Chart-Zooming" API https://github.com/kerner1000/javafx-chart-zooming	
 */


package GraphingPackage;

import java.awt.Desktop;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//import com.github.javafx.charts.zooming.ZoomManager;

import com.github.javafx.charts.zooming.ZoomManager;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainGraphs {

    private Graph graphOne;


    public MainGraphs(Graph one) {
        this.graphOne = one;

    }

    public Graph getGraphOne() {
        return graphOne;
    }

    public void setGraphOne(Graph graphOne) {
        this.graphOne = graphOne;
    }


    @SuppressWarnings("restriction")
    public void displayGraphs() {
        Stage stage = new Stage();

        StackPane stackPane = new StackPane();

        BorderPane Pane = new BorderPane();

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        graphOne.series();
        graphOne.seriesdis();

        // creating the chart
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        lineChart = graphOne.display();

        stackPane.getChildren().add(lineChart);

        double start = System.currentTimeMillis();

        Pane.setCenter(stackPane);

        double end = System.currentTimeMillis();

        System.out.println(end - start);

        BorderPane pane = new BorderPane();

        VBox label = new VBox();
        VBox color = new VBox();

        for (int i = 0; i < graphOne.getSeriesName().size(); i++) {
            label.getChildren().add(new Label(graphOne.getSeriesName().get(i)));
            Rectangle rect = new Rectangle(10,10);
            rect.setStyle(graphOne.getColorFills().get(i));
            color.getChildren().add(rect);
        }


        label.setSpacing(10);
        label.setPadding(new Insets(20));

        color.setSpacing(20);
        color.setPadding(new Insets(22,20,20,20));

        HBox labels = new HBox();
        labels.getChildren().addAll(label, color);
        labels.setSpacing(10);

        pane.setCenter(labels);

        Stage graphLabels = new Stage();

        Scene newScene = new Scene(pane, 50, 50);

        graphLabels.setScene(newScene);

        MenuBar topMen = new MenuBar();

        Menu help = new Menu("Help");
        MenuItem Htu = new MenuItem("How to Zoom");

        Htu.setOnAction((ActionEvent event) -> {

            File howtouse = new File("HowToZoom.txt");
            try {
                Desktop.getDesktop().open(howtouse);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Menu file = new Menu("File");

        MenuItem screenshot = new MenuItem("Save Image...");

        screenshot.setOnAction((ActionEvent event) -> {
            WritableImage image = Pane.getCenter().snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg files (*.jpg)", "*.jpg"));
            fileChooser.setTitle("Save Image");
            File filech = fileChooser.showSaveDialog(stage);
            if (filech != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", filech);
                } catch (IOException e) {

                }
            }
        });


        file.getItems().add(screenshot);
        Pane.setTop(topMen);
        topMen.getMenus().addAll(file);



        Scene scene = new Scene(Pane, 750, 450);
        lineChart.setLegendVisible(false);

        new ZoomManager(Pane, lineChart, graphOne.getSeriesList());


/*
        lineChart.getData().get(0).nodeProperty().get().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                final Label label = new Label("Cycle #" + 1);
                label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
                label.setStyle("-fx-font-size: 10;");
                label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
                label.toFront();
                //label.setTranslateX(event.getX()/2);
                stackPane.getChildren().add(label);

            }
        });

        lineChart.getData().get(0).nodeProperty().get().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stackPane.getChildren().remove(1);
            }
        });

*/
        graphOne.getSeriesList().clear();
        MainMenu.cycle.clear();

        stage.setScene(scene);
        graphLabels.show();
        stage.show();


    }

}

class HoverOverPane extends StackPane {

    HoverOverPane(double cycleNumber){
        setPrefSize(15, 15);

        final Label label = new Label("Cycle #" + cycleNumber);
        label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
        label.setStyle("-fx-font-size: 20;");
        label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getChildren().addAll(label);
                toFront();
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getChildren().clear();
            }
        });

    }


}
