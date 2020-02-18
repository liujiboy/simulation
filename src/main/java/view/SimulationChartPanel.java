package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class SimulationChartPanel  extends JPanel {
    private XYSeries uninfectedSeries;
    private XYSeries infectedSeries;
    private JFreeChart chart;
    private ChartPanel chartPanel;
    public SimulationChartPanel(int width,int height) {
        uninfectedSeries = new XYSeries("健康人");
        infectedSeries = new XYSeries("感染者");
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(uninfectedSeries);
        dataset.addSeries(infectedSeries);
        chart = ChartFactory.createXYLineChart(
                "病毒传播模拟",  // title
                "迭代次数",             // x-axis label
                "人数",   // y-axis label
                dataset);
        chart.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        setLayout(new BorderLayout());
        add(chartPanel,BorderLayout.CENTER);
        setPreferredSize(new Dimension(width,height));
    }
    public void addInfected(double x,double y)
    {
        infectedSeries.add(x,y);
    }
    public void addUnInfected(double x,double y)
    {
        uninfectedSeries.add(x,y);
    }

    public void clear() {
        infectedSeries.clear();
        uninfectedSeries.clear();
    }
}
