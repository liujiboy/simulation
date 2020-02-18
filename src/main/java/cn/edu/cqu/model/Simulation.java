package cn.edu.cqu.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Simulation {
    private City city;
    private Human[] humans;
    private double p;
    private long num=0;
    public City getCity() {
        return city;
    }

    public Human[] getHumans() {
        return humans;
    }

    private void initHumans(int humanSize,double iRatio) {
        humans=new Human[humanSize];
        Random r=new Random();
        for(int i=0;i<humans.length;i++)
        {
            int x,y;
            while(true)
            {
                x=r.nextInt(city.w);
                y=r.nextInt(city.h);
                if(city.map[y][x]=='0')
                    break;
            }
            humans[i] = new Human(x,y,city);
        }
        for(int i=0;i<(int)(humanSize*iRatio);i++)
            humans[i].infected=true;

    }
    public Simulation(int humanSize,double iRatio,double p)
    {
        city=new City();
        this.p=p;
        initHumans(humanSize,iRatio);
    }
    public void run()
    {
        num++;
        for(int i=0;i<humans.length;i++)
        {
            humans[i].move();
        }
        for(int i=0;i<humans.length;i++)
        {
            for(int j=0;j<humans.length;j++)
            {
                if(i!=j&&humans[i].x==humans[j].x&&humans[i].y==humans[j].y)
                {
                    if(humans[i].infected&&!humans[j].infected)
                    {
                        if(Math.random()<p)
                        {
                            humans[j].infected=true;
                        }
                    }
                }
            }
        }
    }

    public String getResult() {
        int count=0;
        for(Human human:humans)
        {
            if(human.infected)
                count++;
        }
        return "感染人数："+count+" 健康人数："+(humans.length-count)+" 迭代次数："+num;
    }
}
