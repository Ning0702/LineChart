package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class PartiesApplication extends Application {

    public static void main(String[] args) throws IOException {
//        Data data = new Data();
        System.out.println("Hello world!");
//        data.readData();
        launch(PartiesApplication.class);

    }

    @Override
    public void start(Stage stage) throws Exception {
        // create the x and y axes that the chart is going to use
        NumberAxis xAxis = new NumberAxis(1968, 2008, 4);//
        NumberAxis yAxis = new NumberAxis();

        // set the titles for the axes
        xAxis.setLabel("");
        yAxis.setLabel("");

        // create the line chart. The values of the chart are given as numbers
        // and it uses the axes we created earlier
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Relative support of the parties");

        // create the data set that is going to be added to the line chart
        XYChart.Series kokData = new XYChart.Series();
        kokData.setName("KOK");
        XYChart.Series sdpData = new XYChart.Series();
        sdpData.setName("SDP");
        XYChart.Series keskData = new XYChart.Series();
        keskData.setName("KESK");
        XYChart.Series vihrData = new XYChart.Series();
        vihrData.setName("VIHR");
        XYChart.Series vasData = new XYChart.Series();
        vasData.setName("VAS");
        XYChart.Series psData = new XYChart.Series();
        psData.setName("PS");
        XYChart.Series rkpData = new XYChart.Series();
        rkpData.setName("RKP");

        Data resource = new Data();
        Map<String, Map<Integer, Double>> values = resource.readData();

//        System.out.println("start");
//        for (String each : values.keySet()) {
//                System.out.println("party: " + each + ": " + values.get(each));
//            }
        // go through the parties and add them to the chart
        values.keySet().stream().forEach(party -> {
            // a different data set for every party
            XYChart.Series data = new XYChart.Series();
            data.setName(party);
//            System.out.println("1");

            // add the party's support numbers to the data set
            values.get(party).entrySet().stream().forEach(pair -> {
                data.getData().add(new XYChart.Data(pair.getKey(), pair.getValue()));
            });
//            System.out.println("2");

            // and add the data set to the chart
            lineChart.getData().add(data);
//            System.out.println("3");
        });

        // display the line chart
        Scene view = new Scene(lineChart, 640, 480);
//        System.out.println("4");
        stage.setScene(view);
//        System.out.println("5");
        stage.show();
//        System.out.println("6");
    }

}
