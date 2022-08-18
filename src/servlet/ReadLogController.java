package servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.NodeFinder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ReadLogController", value = "/ReadLogController")
public class ReadLogController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        System.out.println("asdasd");
        //将数据存储到session中
        try {
            JSONObject jsonObject=new JSONObject();
            Object nodeFinder=session.getAttribute("NodeFinder");
            NodeFinder nf=null;
            List<String>lines=new ArrayList<String>();
            if(nodeFinder!=null)
                nf = (NodeFinder) nodeFinder;
            if(nf.status())
            {
             // lines=nf.read_log();
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream("D:\\IdeaProjects\\NodeFinder\\data.txt");
                    InputStreamReader isr = null;
                    isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    BufferedReader br = new BufferedReader(isr);
                    String line = null;
                    while (true) {
                        if (!((line = br.readLine()) != null)) break;
                        lines.add(line);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            jsonObject.put("log",lines);
            response.getWriter().print(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("sdasdas");
        JSONObject object = new JSONObject();
        object.put("name", "tom");
        object.put("age", 15);
        System.out.println(object);
        response.getWriter().print(object);
    }
}
