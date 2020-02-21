package cn.edu.cqu.servlet;

import cn.edu.cqu.model.Human;
import cn.edu.cqu.model.Simulation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/simulation")
public class SimulationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op=req.getParameter("op");
        HttpSession session=req.getSession();
        Simulation simulation=null;
        switch (op)
        {
            case "begin":
                double iRatio=Double.parseDouble(req.getParameter("iRatio"));
                double p=Double.parseDouble(req.getParameter("p"));
                int humanSize=Integer.parseInt(req.getParameter("humanSize"));
                simulation=new Simulation(humanSize,iRatio,p);
                Simulation finalSimulation = simulation;
                Thread t=new Thread(()->{
                    for(int i=0;i<10000;i++) {
                        finalSimulation.run();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
                session.setAttribute("simulation",simulation);
                resp.sendRedirect("view.html");
                break;
            case "view":
                simulation=(Simulation)session.getAttribute("simulation");
                BufferedImage cityMap=simulation.getCity().getMap();
                BufferedImage image = new BufferedImage(cityMap.getWidth(), cityMap.getHeight(), cityMap.getType());
                Graphics g=image.getGraphics();
                g.drawImage(cityMap, 0, 0, null);
                for(Human human:simulation.getHumans()) {
                    human.draw(g);
                }
                ImageIO.write(image,"png",resp.getOutputStream());
                break;
            case "json":
                PrintWriter out=resp.getWriter();
                simulation=(Simulation)session.getAttribute("simulation");
                Human humans[]=simulation.getHumans();
                String jsonOutput= JSON.toJSONString(humans);
                out.println(jsonOutput);
                out.flush();
                break;
        }

    }
}
