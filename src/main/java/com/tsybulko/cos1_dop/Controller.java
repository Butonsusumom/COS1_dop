package com.tsybulko.cos1_dop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button draw;
    @FXML
    private ComboBox N;
    @FXML
    private ComboBox Phi;
    @FXML
    LineChart<Number,Number> signalGraph;
    @FXML
    LineChart<Number,Number> analyzGraph;
    @FXML
    LineChart<Number,Number> staticGraph;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        N.getItems().addAll(
                "64",
                "128",
                "256",
                "512",
                "1024",
                "2048"
        );
        Phi.getItems().addAll(
                "0",
                "pi/16"
        );
    }

    @FXML
    public void onDraw(){
        analyzGraph.getData().clear();
        signalGraph.getData().clear();
        staticGraph.getData().clear();

        Integer n = Integer.parseInt( (String) N.getValue());
        double fi;
        if ((Phi.getValue()).equals("0")) {
            fi = 0;
        }
        else fi = Math.PI/16;
        int k = (int)( n / 4);
        Analizator secondLabWorker = new Analizator(n, k, 2, fi);
        secondLabWorker.GenerateSignals();

        XYChart.Series<Number, Number> series1 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> series3 = new XYChart.Series<Number, Number>();

        XYChart.Series<Number, Number> series4 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> series5 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> series6 = new XYChart.Series<Number, Number>();

        series1.setName("Delta SQR Static");
        series2.setName("Delta SQR Dinamic");
        series3.setName("Delta Amplitude");

        series4.setName("SQR Static");
        series5.setName("SQR Dinamic");
        series6.setName("Amplitude");

        for (SignalGenerator item: secondLabWorker.getSignals()) {
            series1.getData().add(new XYChart.Data(item.getM(), item.getDeltaAvgSqrValue()));
            series2.getData().add(new XYChart.Data(item.getM(), item.getDeltaAvgSqrDiviation()));
            series3.getData().add(new XYChart.Data(item.getM(), item.getDeltaAmplitude()));

            series4.getData().add(new XYChart.Data(item.getM(), item.getAvgSqrValueFormula1()));
            series5.getData().add(new XYChart.Data(item.getM(), item.getAvgSqrValueFormula2()));
            series6.getData().add(new XYChart.Data(item.getM(), item.getAmplitude()));
        }

        XYChart.Series<Number, Number> seriesSignal = new XYChart.Series<Number, Number>();
        seriesSignal.setName("Signal");
        int j = 0;
        for (Double value:
                secondLabWorker.getSignals().get(secondLabWorker.getSignals().size()-1).getValues()) {
            seriesSignal.getData().add(new XYChart.Data(j, value));
            j++;
        }

        signalGraph.getData().add(seriesSignal);
        staticGraph.getData().addAll(series4, series5, series6);
        analyzGraph.getData().addAll(series1, series2, series3);
    }
}