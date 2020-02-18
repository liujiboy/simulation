package view;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {
    private SimulationCanvas canvas=new SimulationCanvas();
    public SimulationFrame()
    {
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        setSize(1200,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Thread t=new Thread(canvas);
        t.start();
    }
    public static void main(String[] args)
    {
        SimulationFrame frame=new SimulationFrame();
        frame.setVisible(true);
    }
}
