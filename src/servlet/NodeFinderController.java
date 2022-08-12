package servlet;

import entity.NodeFinder;

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
        List<String> config = new ArrayList<String>();
        NodeFinder nf = new NodeFinder(config);
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
