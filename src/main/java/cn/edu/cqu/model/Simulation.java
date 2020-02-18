package cn.edu.cqu.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Simulation {
    private City city;

    public City getCity() {
        return city;
    }

    private double p;
    public Simulation(int humanSize,double p)
    {
        city=new City(humanSize);
        this.p=p;
    }
    public void run()
    {
        Human[] humans=city.getHumans();
        for(int i=0;i<humans.length;i++)
        {
            humans[i].move(city);
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

}
