package view;

import cn.edu.cqu.model.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame implements  Runnable{
    private Simulation simulation;
    private SimulationCanvas simulationCanvas;
    private JPanel eastPanel;
    private JTextField humanSizeTextField;
    private JTextField iRatioTextField;
    private JTextField pTextField;
    private JTextField speedField;
    private JLabel message;
    private Thread simulationThread;
    private JPanel southPanel;
    public SimulationFrame()
    {
        simulationCanvas=new SimulationCanvas();
        initEastPanel();
        initSouthPanel();
        setLayout(new BorderLayout());
        add(simulationCanvas, BorderLayout.CENTER);
        add(eastPanel,BorderLayout.EAST);
        add(southPanel,BorderLayout.SOUTH);
        setSize(1200,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        simulationThread=new Thread(this);
    }

    private void initSouthPanel() {
        southPanel=new JPanel();
        message=new JLabel();
        southPanel.add(message,BorderLayout.CENTER);
    }

    private void initEastPanel() {
        eastPanel=new JPanel();
        eastPanel.setLayout(new GridLayout(5,2));
        //eastPanel.setLayout(new FlowLayout());
        JLabel humanSizeLabel=new JLabel("人口：");
        humanSizeTextField=new JTextField(10);
        JLabel iRatioLabel=new JLabel("感染比例：");
        iRatioTextField=new JTextField(10);
        JLabel pLabel=new JLabel("传染率：");
        pTextField=new JTextField(10);
        JLabel speedLabel=new JLabel("速度（ms）：");
        speedField=new JTextField(10);
        eastPanel.add(humanSizeLabel);
        eastPanel.add(humanSizeTextField);
        eastPanel.add(iRatioLabel);
        eastPanel.add(iRatioTextField);
        eastPanel.add(pLabel);
        eastPanel.add(pTextField);
        eastPanel.add(speedLabel);
        eastPanel.add(speedField);
        JButton startBt=new JButton("开始模拟");
        eastPanel.add(startBt);
        startBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSimulationThread();
                int humanSize=Integer.parseInt(humanSizeTextField.getText());
                double iRatio=Double.parseDouble(iRatioTextField.getText());
                double p=Double.parseDouble(pTextField.getText());
                simulation=new Simulation(humanSize,iRatio,p);
                simulationCanvas.setSimulation(simulation);
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
            simulationThread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        SimulationFrame frame=new SimulationFrame();
        frame.setVisible(true);
    }
    private boolean running=false;
    @Override
    public void run() {
        while(running)
        {
            try {
                Thread.sleep(Integer.parseInt(speedField.getText()));
                simulation.run();
                simulationCanvas.repaint();
                message.setText(simulation.getResult());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
