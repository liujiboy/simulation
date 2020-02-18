package cn.edu.cqu.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Human {
    public int x;
    public int y;
    public boolean infected=false;
    private City city;
    public Human(int x, int y,City city) {
        this.x = x;
        this.y = y;
        this.city=city;
    }

    public Human(int x, int y, boolean infected) {
        this.x = x;
        this.y = y;
        this.infected = infected;
    }

    public void move()
    {
        Random r=new Random();
        switch (r.nextInt(4))
        {
            case 0:
                if(city.canMove(x+1,y)) {
                    x++;
                }
                break;
            case 1:
                if(city.canMove(x-1,y)) {
                    x--;
                }
                break;
            case 2:
                if(city.canMove(x,y+1)) {
                    y++;
                }
                break;
            default:
                if(city.canMove(x,y-1)) {
                    y--;
                }

        }
    }
    public void draw(Graphics g)
    {
        if(infected) {
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.GREEN);
        }
        g.fillRect(x*city.cell,y*city.cell,city.cell,city.cell);

    }

}
