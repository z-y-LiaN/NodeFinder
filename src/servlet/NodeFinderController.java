package servlet;

import entity.NodeFinder;
import entity.Parameter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "NodeFinderController", value = "/NodeFinderController")
public class NodeFinderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String dbname=request.getParameter("dbname");//本次探测的数据库名
        String dbaccount=request.getParameter("dbaccount");//本次探测的数据库账号
        String dbpassword=request.getParameter("dbpassword");//本次探测的数据库密码
        String nodefindername=request.getParameter("nodefindername");//本次探测名字
        String begintime=request.getParameter("begintime");//探测开始时间
        String cycle=request.getParameter("cycle");//快照周期（/分钟）
        String model=request.getParameter("model");//模式
        String cycleindex=request.getParameter("cycleindex");//循环次数（/次）
        String interval=request.getParameter("interval");//探测间隔（/分钟）
        Parameter parameter=new Parameter(dbname,dbaccount,dbpassword,nodefindername,begintime,cycle,model,cycleindex,interval);


        NodeFinder nf = new NodeFinder(parameter);
        int count=1;
        long ctime=System.currentTimeMillis();
            while (nf.status()&&System.currentTimeMillis()<ctime+300*1000)
            {
                List<String>lines=nf.read_log();
                for (String line:lines
                ) {
                    System.out.println(count);
                    System.out.println(line);
                    count++;
                }
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sleep");
            }

        nf.destory();
        System.out.println("dsdsds");
    }
}
