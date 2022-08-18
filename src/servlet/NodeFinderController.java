package servlet;

import entity.NodeFinder;
import entity.Parameter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSONObject;

@WebServlet(name = "NodeFinderController", value = "/NodeFinderController")
public class NodeFinderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String dbname = request.getParameter("dbname");//本次探测的数据库名
        String dbaccount = request.getParameter("dbaccount");//本次探测的数据库账号
        String dbpassword = request.getParameter("dbpassword");//本次探测的数据库密码
        String nodefindername = request.getParameter("nodefindername");//本次探测名字
        String begintime = request.getParameter("begintime");//探测开始时间
        String cycle = request.getParameter("cycle");//快照周期（/分钟）
        String model = request.getParameter("model");//模式
        String cycleindex = request.getParameter("cycleindex");//循环次数（/次）
        String interval = request.getParameter("interval");//探测间隔（/分钟）
        Parameter parameter = new Parameter(dbname, dbaccount, dbpassword, nodefindername, begintime, cycle, model, cycleindex, interval);
        NodeFinder nf = new NodeFinder(parameter);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> lines = new ArrayList<String>();
        if (!nf.status()) {
            Process proc = nf.getProcess();
            InputStreamReader inReader = null;
            inReader = new InputStreamReader(proc.getInputStream(), "GBK");
            BufferedReader br = new BufferedReader(inReader);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                lines.add(line);
            }
            proc.destroy();
            HttpSession session = request.getSession();
            session.setAttribute("Feedback", "网络探测进程创建失败");
            request.getRequestDispatcher("networkfind.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        session.setAttribute("NodeFinder", nf);
        session.setAttribute("Feedback", "网络探测进程创建成功");
        request.getRequestDispatcher("networkfind.jsp").forward(request, response);
    }
}
