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
    //网格大小
    public int cell;
    //地图图像
    private BufferedImage mapImage;

    public BufferedImage getMap() {
        return mapImage;
    }

    public City(){
        initMap();
        try {
            mapImage=ImageIO.read(this.getClass().getResourceAsStream("/map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void initMap() {
        Scanner s=new Scanner(this.getClass().getResourceAsStream("/map.txt"));
        w=Integer.parseInt(s.nextLine());
        h=Integer.parseInt(s.nextLine());
        cell=Integer.parseInt(s.nextLine());
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
