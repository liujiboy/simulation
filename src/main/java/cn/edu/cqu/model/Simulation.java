package cn.edu.cqu.model;

import java.util.Random;

public class Simulation {
    private City city;
    private Human[] humans;
    private double p;
    //迭代次数
    private long iteration =0;
    private int infected;
    private int uninfected;
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
        infected=(int)(humanSize*iRatio);
        uninfected=humanSize-infected;
    }
    public Simulation(int humanSize,double iRatio,double p)
    {
        city=new City();
        this.p=p;
        initHumans(humanSize,iRatio);
    }
    public void run()
    {
        iteration++;
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
        infected=0;
        for(Human human:humans)
        {
            if(human.infected)
                infected++;
        }
        uninfected=humans.length-infected;
    }

    public String getResult() {

        return "感染人数："+infected+" 健康人数："+uninfected+" 迭代次数："+ iteration;
    }

    public long getIteration() {
        return iteration;
    }

    public int getInfected() {
        return infected;
    }

    public int getUninfected() {
        return uninfected;
    }
}
