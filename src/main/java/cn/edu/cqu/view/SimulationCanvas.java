package cn.edu.cqu.view;

import cn.edu.cqu.model.Human;
import cn.edu.cqu.model.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SimulationCanvas extends JPanel {
    public Simulation simulation;

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
        cityMap=simulation.getCity().getMap();
    }
    public BufferedImage cityMap;

    private void drawHumans(Graphics g,Human[] humans)
    {

        for(int i=0;i<humans.length;i++)
        {
            humans[i].draw(g);
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(simulation!=null) {
            BufferedImage image = new BufferedImage(cityMap.getWidth(), cityMap.getHeight(), cityMap.getType());
            image.getGraphics().drawImage(cityMap, 0, 0, null);
            drawHumans(image.getGraphics(), simulation.getHumans());
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }

    }


}
