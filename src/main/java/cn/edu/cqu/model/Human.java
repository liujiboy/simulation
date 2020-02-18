package cn.edu.cqu.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Human {
    public int x;
    public int y;
    public boolean infected=false;

    public Human(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Human(int x, int y, boolean infected) {
        this.x = x;
        this.y = y;
        this.infected = infected;
    }

    public void move(City city)
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

}
