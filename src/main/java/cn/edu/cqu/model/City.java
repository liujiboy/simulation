package cn.edu.cqu.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class City {
    //地图
    public char[][] map;
    //地图宽
    public int w;
    //地图高
    public int h;

    private Human[] humans;

    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public Human[] getHumans() {
        return humans;
    }

    public City()
    {
        this(50);
    }
    public City(int humanSize){
        initMap();
        initHumans(humanSize);
        try {
            image=ImageIO.read(this.getClass().getResourceAsStream("/map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initHumans(int humanSize) {
        humans=new Human[humanSize];
        Random r=new Random();
        for(int i=0;i<humans.length;i++)
        {
            int x,y;
            while(true)
            {
                x=r.nextInt(w);
                y=r.nextInt(h);
                if(map[y][x]=='0')
                    break;
            }
            humans[i] = new Human(x,y);
        }
        for(int i=0;i<(int)(humanSize*0.2);i++)
            humans[i].infected=true;

    }

    private void initMap() {
        Scanner s=new Scanner(this.getClass().getResourceAsStream("/map.txt"));
        w=Integer.parseInt(s.nextLine());
        h=Integer.parseInt(s.nextLine());
        map=new char[h][w];
        for(int row=0;row<h;row++) {
            String line=s.nextLine();
            for (int col = 0; col < w; col++) {
                map[row][col]=line.charAt(col);
            }
        }
        s.close();
    }

    public void printMap()
    {
        for(int row=0;row<h;row++) {
            for (int col = 0; col < w; col++) {
                System.out.print(map[row][col]);
            }
            System.out.println();
        }
    }


    public boolean canMove(int x, int y) {
        if(x>=0&&x<w&&y>=0&&y<h)
            return map[y][x]=='0';
        else
            return false;
    }
}
