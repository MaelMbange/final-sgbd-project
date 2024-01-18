package org.example;

import org.example.classes.test.InvalidDataProduction;
import org.example.classes.test.Productions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class appTestchart {
    public static void main( String[] args )
    {
        //testChat();
        //testXY();
        //showTimeSeries();
        showScatterPlot();
        //graphtype();
        //showHistogram();
        //showBarChart();
        //showEvolution();
    }

    private static void showHistogram()
    {
        double[] values = { 95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
                12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
                49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
                93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
                77, 44, 58, 91, 10, 67, 57, 19, 88, 84
        };


        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 10);

        JFreeChart jfc = ChartFactory.createHistogram("JFreeChart Histogram",
                "Data", "Frequency", dataset);

        ChartPanel cp = new ChartPanel(jfc);

        JFrame frame = new JFrame("Your App Title");
        frame.setLayout(new BorderLayout());
        frame.add(cp, BorderLayout.CENTER);
        frame.pack();  // Adjusts the frame size based on its content
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showEvolution()
    {
        String[] jours = {"lundi", "mardi", "mercredi", "jeudi", "vendredi"};
        double[] valProduction = { 20.5, 27.6, 30.4, 39.5, 31.9 };
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        for (int i=0; i< jours.length; i++)
            ds.addValue(valProduction[i], "Bouteilles", jours[i]);
        JFreeChart jfc = ChartFactory.createLineChart
                ("Production de lait à Trois-Ponts", "jour", "milliers de bouteilles",
                        ds, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel cp = new ChartPanel(jfc);

        JFrame frame = new JFrame("Your App Title");
        frame.setLayout(new BorderLayout());
        frame.add(cp, BorderLayout.CENTER);
        frame.pack();  // Adjusts the frame size based on its content
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showBarChart()
    {
        String jour1 = "lundi", jour2 = "vendredi";
        String atelier1 = "Huy", atelier2 = "Verviers", atelier3 = "Liège", atelier4 = "Waremme";

        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ds.addValue(25.3, jour1, atelier1);
        ds.addValue(20.7, jour2, atelier1);
        ds.addValue(30.1, jour1, atelier2);
        ds.addValue(34.2, jour2, atelier2);
        ds.addValue(85.3, jour1, atelier3);
        ds.addValue(82.1, jour2, atelier3);
        ds.addValue(19.6, jour1, atelier4);
        ds.addValue(15.9, jour2, atelier4);

        JFreeChart jfc = ChartFactory.createBarChart(
                "Productions de bouteilles de lait (en milliers)",
                "Ateliers",
                "Production",
                ds,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel cp = new ChartPanel(jfc);

        JFrame frame = new JFrame("Your App Title");
        frame.setLayout(new BorderLayout());
        frame.add(cp, BorderLayout.CENTER);
        frame.pack();  // Adjusts the frame size based on its content
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void testChat(){
        DefaultPieDataset ds = new DefaultPieDataset();

        ds.setValue("Parti du progrès contrôlé", 22.36);
        ds.setValue("Parti démocrate conservateur", 27.69);
        ds.setValue("Intérêts des gens riches", 3.78);
        ds.setValue("Prolétariat uni", 7.85);
        ds.setValue("Mouvement des Gens Heureux", 35.12);
        ds.setValue("Autres", 3.2);

        JFreeChart jfc = ChartFactory.createPieChart("Résulats des élections en Boursoulavie", ds, true, false, false);
        ChartPanel cp = new ChartPanel(jfc);

        JFrame frame = new JFrame("Your App Title");
        frame.setLayout(new BorderLayout());
        frame.add(cp, BorderLayout.CENTER);
        frame.pack();  // Adjusts the frame size based on its content
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void testXY(){
        XYSeries series = new XYSeries("Points");

        // Ajouter des données à la série
        series.add(1.0, 3.0);
        series.add(2.0, 5.0);
        series.add(3.0, 8.0);
        series.add(4.0, 12.0);
        series.add(5.0, 15.0);

        // Créer un ensemble de données XY
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        XYDataset ds = dataset;

        JFreeChart chart = ChartFactory.createScatterPlot(
                "Exemple de Scatter Plot",  // Titre du graphique
                "timestamp",                     // Titre de l'axe X
                "Axe Y",                     // Titre de l'axe Y
                dataset,                     // Ensemble de données XY
                PlotOrientation.VERTICAL,
                true,                        // Afficher la légende
                true,                        // Activer les outils de zoom
                false                        // Activer les outils de tooltips
        );

        // Créer le panneau de graphique
        ChartPanel cp = new ChartPanel(chart);
        cp.setPreferredSize(new Dimension(360, 170));

        JFrame frame = new JFrame("Your App Title");
        frame.setLayout(new BorderLayout());
        frame.add(cp, BorderLayout.CENTER);
        frame.pack();  // Adjusts the frame size based on its content
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showTimeSeries()
    {
        double [][] ProductionsLait = {
                {65.23, 54.54, 59.71, 45.12,
                        32.14, 32.19, 40.84, 46.21,
                        47.67, 42.36, 45.65, 55.81 },
                {60.31, 57.54, 62.71, 49.12,
                        30.14, 38.19, 44.84, 49.21,
                        53.67, 54.36, 49.65, 56.81 }
        };

        String[] lieuxProductions = { "Trois-Ponts", "Stavelot"};
        int annee = 2006;

        Productions[] p = new Productions[2];

        try
        {
            for (int j=0; j<lieuxProductions.length; j++)
                p[j] = new Productions(Calendar.MONTH, ProductionsLait[j], annee);
        }
        catch (InvalidDataProduction e)
        {
            System.out.println("Oh oh ... " + e.getMessage());
        }

        Calendar c = Calendar.getInstance();

        TimeSeriesCollection ds = new TimeSeriesCollection();
        TimeSeries[] s = new TimeSeries[2];
        for (int j=0; j<lieuxProductions.length; j++)
        {
            s[j] = new TimeSeries("Productions de lait en " + p[j].getPeriodeSuperieure() + " à "  + lieuxProductions[j]); //, Month.class);

            for (int i = 1; i<= c.getActualMaximum(p[j].getTypePeriode()); i++ )
            {
                s[j].add(new Month(i, p[j].getPeriodeSuperieure()), p[j].getData()[i-1]);
            }
            ds.addSeries(s[j]);
        }

        JFreeChart jfc = ChartFactory.createTimeSeriesChart(
                "Productions de lait",
                "Date", // x
                "Milliers de litres", // y
                ds,
                true, true, false
        );
        ChartPanel cp = new ChartPanel(jfc);


        JFrame frame = new JFrame("Your App Title");
        frame.setLayout(new BorderLayout());
        frame.add(cp, BorderLayout.CENTER);
        frame.pack();  // Adjusts the frame size based on its content
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showScatterPlot()
    {
        double couples[][] = { {3.8,68.0}, {3.6,72.0}, {3.4,86.0}, {3.5,78.0}, {3.9,64.0},
                {4.2,61.0}, {3.7,66.0}, {3.5,74.0}, {3.8,59.0}, {4.1,55.0},
                {3.7,64.0}, {3.6,73.0}, {3.8,62.0}, {3.4,75.0}, {3.5,78.0} };
        XYSeries serieObs = new XYSeries("Relations vision-manipulation");
        for (int i=0; i<15; i++)
            serieObs.add(couples[i][0],couples[i][1]);
        XYSeriesCollection ds = new XYSeriesCollection();
        ds.addSeries(serieObs);

        JFreeChart jfc = ChartFactory.createScatterPlot(
                "Perception visuelle et habileté manuelle",
                "réponse à stimulus visuel", "dextérité manuelle",
                ds,
                PlotOrientation.VERTICAL,
                true, true, false );

        ChartPanel cp = new ChartPanel(jfc);

        JFrame frame = new JFrame("Your App Title");
        frame.setLayout(new BorderLayout());
        frame.add(cp, BorderLayout.CENTER);
        frame.pack();  // Adjusts the frame size based on its content
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void graphtype(){
        XYDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Time Series Chart Example | WWW.BORAJI.COM", // Chart
                "Date", // X-Axis Label
                "Number", // Y-Axis Label
                dataset);

        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        //plot.setBackgroundPaint(new Color(255,228,196));

        XYLineAnnotation line = new XYLineAnnotation(
                new Day(3, 1, 2017).getFirstMillisecond(),40,
                new Day(12, 1, 2017).getFirstMillisecond(),60,
                new BasicStroke(2),Color.blue);
        plot.addAnnotation(line);

        ChartPanel cp = new ChartPanel(chart);

        JFrame frame = new JFrame("Your App Title");
        frame.setLayout(new BorderLayout());
        frame.add(cp, BorderLayout.CENTER);
        frame.pack();  // Adjusts the frame size based on its content
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static XYDataset createDataset()
    {
        TimeSeriesCollection dataset = new TimeSeriesCollection();

        TimeSeries series1 = new TimeSeries("Series1");
        series1.add(new Day(1, 1, 2017), 50);
        series1.add(new Day(2, 1, 2017), 40);
        series1.add(new Day(3, 1, 2017), 45);
        series1.add(new Day(4, 1, 2017), 30);
        series1.add(new Day(5, 1, 2017), 50);
        series1.add(new Day(6, 1, 2017), 45);
        series1.add(new Day(7, 1, 2017), 60);
        series1.add(new Day(8, 1, 2017), 45);
        series1.add(new Day(9, 1, 2017), 55);
        series1.add(new Day(10, 1, 2017), 48);
        series1.add(new Day(11, 1, 2017), 60);
        series1.add(new Day(12, 1, 2017), 45);
        series1.add(new Day(13, 1, 2017), 65);
        series1.add(new Day(14, 1, 2017), 45);
        series1.add(new Day(15, 1, 2017), 55);
        dataset.addSeries(series1);

        TimeSeries series2 = new TimeSeries("Series2");
        series2.add(new Day(1, 1, 2017), 40);
        series2.add(new Day(2, 1, 2017), 35);
        series2.add(new Day(3, 1, 2017), 26);
        series2.add(new Day(4, 1, 2017), 45);
        series2.add(new Day(5, 1, 2017), 40);
        series2.add(new Day(6, 1, 2017), 35);
        series2.add(new Day(7, 1, 2017), 45);
        series2.add(new Day(8, 1, 2017), 48);
        series2.add(new Day(9, 1, 2017), 31);
        series2.add(new Day(10, 1, 2017), 32);
        series2.add(new Day(11, 1, 2017), 21);
        series2.add(new Day(12, 1, 2017), 35);
        series2.add(new Day(13, 1, 2017), 10);
        series2.add(new Day(14, 1, 2017), 25);
        series2.add(new Day(15, 1, 2017), 15);
        dataset.addSeries(series2);

        return dataset;
    }
}
