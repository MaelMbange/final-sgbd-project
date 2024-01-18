package org.example.ui;

import org.example.classes.Motion;
import org.example.functions.Functions;
import org.example.functions.GraphicalFunctions;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.*;
import java.util.Timer;

public class chart extends JFrame{
    private JPanel jpanel1;
    private JPanel panelChart;
    private JTextField textFieldTimeStamp;
    private JButton validerButton;
    private JLabel labelTimestamp;
    private JComboBox AxeY;
    private JButton startButton;
    private JButton screenshotButton;
    private JButton restartButton;
    private JComboBox comboBoxTORTDROIT;


    private List<Motion> listMotion = new ArrayList<>();
    private List<Motion> listTriee = new ArrayList<>();

    private double firstTimestamp = 0;
    private double leastTimestamp = 0;

    private Timer scrollTimer;
    private double currentIndex = 0;

    public chart() throws HeadlessException {
        super("Chart");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(jpanel1);

        {
            XYDataset ds = GraphicalFunctions.createDataset(listMotion, 0, AxeY.getSelectedItem().toString());
            ChartPanel cp = GraphicalFunctions.getChartPanel("Analyse des données de navigations", "timestamp", "accx", ds);

            panelChart.setLayout(new BorderLayout());
            panelChart.add(cp, BorderLayout.CENTER);
        }

        validerButton.addActionListener(a->{
            String content = textFieldTimeStamp.getText();
            if(!content.isBlank()){
                float timestamp = Float.parseFloat(content);
                if(timestamp != 0){
                    labelTimestamp.setText(textFieldTimeStamp.getText());
                    listMotion = Functions.parseMotions(Functions.getBodyRequest(Float.parseFloat(labelTimestamp.getText())));
                    System.out.println("\033[92m" + listMotion + "\033[0m");

                    listMotion.sort(Comparator.comparingDouble(Motion::getTimestamp));
                    firstTimestamp = listMotion.get(0).getTimestamp();
                    currentIndex = firstTimestamp;
                    leastTimestamp = Float.parseFloat(labelTimestamp.getText());

                    System.out.println("\033[93m" + firstTimestamp + "\033[0m");
                    System.out.println("\033[93m" + (firstTimestamp + 10) + "\033[0m");

                    listTriee = Functions.filtrerListeMotion(listMotion,firstTimestamp,firstTimestamp + 10);

                    System.out.println("\033[94m" + listTriee + "\033[0m");
                    reloadChart();
                }
            }
            else
                JOptionPane.showMessageDialog(null,"vous devez entrer un timestamp","Erreur",JOptionPane.ERROR_MESSAGE);
        });

        startButton.addActionListener(e->{
            setStartButton();
        });

        AxeY.addActionListener(e->{
            reloadChart();
        });

        restartButton.addActionListener(e->{
            listTriee = Functions.filtrerListeMotion(listMotion,firstTimestamp,firstTimestamp + 10);
            setCurrentIndex(firstTimestamp);
            reloadChart();
        });

        screenshotButton.addActionListener(e->{
            try {
                Robot robot = new Robot();
                Rectangle area = panelChart.getBounds();
                BufferedImage screenshot = robot.createScreenCapture(area);
                byte[] imageInBytes;

                try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
                    ImageIO.write(screenshot, "png", baos);
                    imageInBytes = baos.toByteArray();
                }
                //System.out.println("image -> " + comboBoxTORTDROIT.getSelectedItem().toString());

                Functions.sendImage(imageInBytes,(int)currentIndex,comboBoxTORTDROIT.getSelectedItem().toString(),Date.valueOf(LocalDate.now()));
                JOptionPane.showMessageDialog(null,"Image enregistrée !" ,"image",JOptionPane.INFORMATION_MESSAGE);

            } catch (AWTException | IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private synchronized void reloadChart(){
        XYDataset ds = GraphicalFunctions.createDataset(listTriee,AxeY.getSelectedIndex(),AxeY.getSelectedItem().toString());
        ChartPanel cp = GraphicalFunctions.getChartPanel("Analyse des données de navigations","timestamp",AxeY.getSelectedItem().toString(),ds);
        System.out.println("\033[93m indexY= " + AxeY.getSelectedIndex() + "\033[0m");

        panelChart.setLayout(new BorderLayout());
        panelChart.removeAll();
        panelChart.add(cp,BorderLayout.CENTER);

        panelChart.validate();
        panelChart.repaint();
    }

    private void setStartButton(){
        if(startButton.getText().equalsIgnoreCase("Start")){
            startScrolling();
            startButton.setText("Stop");
            validerButton.setEnabled(false);
        }
        else if (startButton.getText().equalsIgnoreCase("Stop")){
            stopScrolling();
            startButton.setText("Start");
            validerButton.setEnabled(true);
        }
    }

    private void startScrolling() {
        if (scrollTimer != null) {
            scrollTimer.cancel();
            scrollTimer = null;
        }

        scrollTimer = new Timer();
        scrollTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Mettre à jour le graphique avec les 10 secondes suivantes
                SwingUtilities.invokeLater(() -> {
                    if(currentIndex < leastTimestamp){
                        setCurrentIndex(currentIndex + 0.5);

                        listTriee = Functions.filtrerListeMotion(listMotion,currentIndex,currentIndex + 10);

                        reloadChart();
                    }
                });
            }
        }, 0, 500); // Rafraîchir toutes les 500 millisecondes
    }

    private synchronized void setCurrentIndex(double val){
        currentIndex = val;
    }

    private void stopScrolling() {
        if (scrollTimer != null) {
            scrollTimer.cancel();
            scrollTimer = null;
        }
    }

    //818982
    public static void main(String[] args) {
        chart a1 = new chart();
        a1.pack();
        a1.setVisible(true);
    }
}
