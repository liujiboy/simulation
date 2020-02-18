package view;

import cn.edu.cqu.model.Simulation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame implements  Runnable{
    private Simulation simulation;
    private SimulationCanvas simulationCanvas;
    private JPanel eastPanel;
    private JLabel message;
    private Thread simulationThread;
    private JPanel southPanel;
    private SimulationChartPanel simulationChartPanel;
    private SimulationParameterPanel simulationParameterPanel;
    public SimulationFrame()
    {
        simulationCanvas=new SimulationCanvas();
        simulationCanvas.setPreferredSize(new Dimension(1200,800));
        simulationChartPanel=new SimulationChartPanel(0,300);
        initEastPanel();
        initSouthPanel();
        setLayout(new BorderLayout());
        add(simulationCanvas, BorderLayout.CENTER);
        add(eastPanel,BorderLayout.EAST);
        add(southPanel,BorderLayout.SOUTH);
        add(simulationChartPanel,BorderLayout.NORTH);
        //setSize(1200,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }



    private void initSouthPanel() {
        southPanel=new JPanel();
        message=new JLabel();
        southPanel.add(message,BorderLayout.CENTER);
    }

    private void initEastPanel() {
        eastPanel=new JPanel();
        eastPanel.setLayout(new GridLayout(2,1));
        eastPanel.setPreferredSize(new Dimension(200,0));
        simulationParameterPanel=new SimulationParameterPanel();
        eastPanel.add(simulationParameterPanel);
        JButton startBt=new JButton("开始模拟");
        eastPanel.add(startBt);
        startBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSimulationThread();
                int humanSize=simulationParameterPanel.getHumanSize();
                double iRatio=simulationParameterPanel.getIRatio();
                double p=simulationParameterPanel.getP();
                simulation=new Simulation(humanSize,iRatio,p);
                simulationCanvas.setSimulation(simulation);
                simulationChartPanel.clear();
                startSimulationThread();
            }
        });
    }

    private void startSimulationThread() {
        running=true;
        simulationThread=new Thread(this);
        simulationThread.start();
    }

    private void stopSimulationThread() {
        running=false;
        try {
            if(simulationThread!=null)
                simulationThread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        SimulationFrame frame=new SimulationFrame();
        frame.pack();
        frame.setVisible(true);
    }
    private boolean running=false;
    @Override
    public void run() {
        while(running)
        {
            try {
                Thread.sleep(simulationParameterPanel.getSpeed());
                simulation.run();
                simulationCanvas.repaint();
                message.setText(simulation.getResult());
                simulationChartPanel.addUnInfected(simulation.getIteration(),simulation.getUninfected());
                simulationChartPanel.addInfected(simulation.getIteration(),simulation.getInfected());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
