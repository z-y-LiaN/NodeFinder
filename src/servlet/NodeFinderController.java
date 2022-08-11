package servlet;
import entity.Parameter;
import service.NodeFinderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NodeFinderController  extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    }
}