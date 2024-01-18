package org.example.functions;

import org.example.classes.Motion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphicalFunctions {
    public static final XYDataset createDataset(List<Motion> listMotion, int element, String elementName){
        XYSeries series = new XYSeries(elementName + " in function of the timestamp");

        if(!listMotion.isEmpty()){
            switch(element){
                case 0:
                    for(Motion i : listMotion){
                        series.add(i.getTimestamp(),i.getAccx());
                    }
                    break;

                case 1:
                    for(Motion i : listMotion){
                        series.add(i.getTimestamp(),i.getAccy());
                    }
                    break;

                case 2:
                    for(Motion i : listMotion){
                        series.add(i.getTimestamp(),i.getAccz());
                    }
                    break;

                case 3:
                    for(Motion i : listMotion){
                        series.add(i.getTimestamp(),i.getGyrox());
                    }
                    break;

                case 4:
                    for(Motion i : listMotion){
                        series.add(i.getTimestamp(),i.getGyroy());
                    }
                    break;

                case 5:
                    for(Motion i : listMotion){
                        series.add(i.getTimestamp(),i.getGyroz());
                    }
                    break;
            }
        }

        XYSeriesCollection seriesCollection = new XYSeriesCollection(series);
        return seriesCollection;
    }

    public static ChartPanel getChartPanel(String title, String xLabel, String yLabel, XYDataset dataset) {
        JFreeChart jfc = ChartFactory.createScatterPlot(title, xLabel, yLabel,  dataset, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel cp = new ChartPanel(jfc);

        return cp;
    }

    public static void main(String[] args) {

        List<Motion> lm = Functions.parseMotions(Functions.getBodyRequest(818970));
        //List<Motion> lm = new ArrayList<>();
        XYDataset ds = GraphicalFunctions.createDataset(lm,0,"accx");
        ChartPanel cp = GraphicalFunctions.getChartPanel("test","timestamp","accx",ds);

        JFrame frame = new JFrame("Your App Title");
        frame.setLayout(new BorderLayout());
        frame.add(cp, BorderLayout.CENTER);
        frame.pack();  // Adjusts the frame size based on its content
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
