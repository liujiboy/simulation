package view;

import cn.edu.cqu.model.City;
import cn.edu.cqu.model.Human;
import cn.edu.cqu.model.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SimulationCanvas extends JPanel implements Runnable{
    public Simulation simulation;
    public BufferedImage cityMap;
    public SimulationCanvas()
    {
        simulation=new Simulation(500,0.5);
        cityMap=simulation.getCity().getImage();
    }
    private int cell=20;
    private void drawHumans(Graphics g,Human[] humans)
    {

        for(int i=0;i<humans.length;i++)
        {
            drawHuman(g,humans[i]);
        }
    }
    private void drawHuman(Graphics g, Human human)
    {

        if(human.infected) {
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.GREEN);
        }
        g.fillRect(human.x*cell,human.y*cell,cell,cell);

    }
    public BufferedImage drawCityMap()
    {
        City city=simulation.getCity();
        BufferedImage image=new BufferedImage(cell*city.w,cell*city.h,BufferedImage.TYPE_INT_RGB);
        Graphics g=image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,image.getWidth(),image.getHeight());
        for(int row=0;row<city.h;row++) {
            for (int col = 0; col < city.w; col++) {
                if(city.map[row][col]=='1')
                {
                    g.setColor(Color.BLACK);
                    g.fillRect(col*cell,row*cell,cell,cell);
                }
            }

        }
        return image;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage image=new BufferedImage(cityMap.getWidth(),cityMap.getHeight(),cityMap.getType());
        image.getGraphics().drawImage(cityMap,0,0,null);
        drawHumans(image.getGraphics(),simulation.getCity().getHumans());
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);

    }

    @Override
    public void run() {
        while (true)
        {
            try {
                Thread.sleep(1000);
                simulation.run();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
