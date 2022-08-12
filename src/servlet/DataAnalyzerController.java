package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import entity.Node;
import jdk.nashorn.internal.ir.Block;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import service.EthereumService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.*;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Subdivision;
@WebServlet(name = "DataAnalyzerController",urlPatterns = "/DataAnalyzerController")
public class DataAnalyzerController extends HttpServlet {

    public static JSONObject readJsonFile(String file_name){
        try{
            File file = new File(file_name);
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            String jsonStr = sb.toString();
            System.out.println(JSON.parseObject(jsonStr));
            return JSON.parseObject(jsonStr);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        doPost(request,response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<String>data_list=new ArrayList<String>();
        List<String>type_list=new ArrayList<String>();
        List<String>degree_list=new ArrayList<String>();
        List<String>bin_list=new ArrayList<String>();
        DecimalFormat df = new DecimalFormat("#.000");
        String source_path="D:\\IdeaProjects\\NodeFinder\\simulator\\src\\dist\\output\\";
        ProcessBuilder pbGet_RAW = new ProcessBuilder();
        pbGet_RAW.redirectErrorStream(true);
        pbGet_RAW.directory(new File("D:\\IdeaProjects\\NodeFinder"));
        pbGet_RAW.command("java","-Dfile.encoding=UTF-8","-jar","simblock-pro.jar","false","RAW","1");
        Map<String, Object> map=new HashMap<>();
        try {
            Process process_RAW = pbGet_RAW.start();
            try {
                int exitcode=process_RAW.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JSONObject raw_data=readJsonFile( source_path+"OUT_DATA_RAW_1.json");
             map=raw_data;
            for(String key:map.keySet())
            {
                String str=df.format(Double.valueOf(map.get(key).toString())).toString();
                map.put(key,str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("dead");

        String []node_type={"BET","DEG","MY1","MY2","MY3","RAND"};
        String []value={"1","2","3","5"};
        HashMap<String, List<String>> consensus=new HashMap<String, List<String>>();
        //枚举需处理的文件类型输出其平均共识达成时间  该部分代码待修改
        /**
         * 所需的修改：       (OUT_DATA_(...).json 这种json文件传参的方式已经被抛弃)
         * 根据simulator/src/dist/conf/data/(可变部分).json的文件名
         * 传入simblock-pro.jar文件 运行
         * 保存其输出的平均共识达成时间
         */
        for (String type:node_type)
        {
            List<String>times=new ArrayList<String>();
            for (String rate:value)
            {


                ProcessBuilder pbGet = new ProcessBuilder();
                pbGet.redirectErrorStream(true);
                pbGet.directory(new File("D:\\IdeaProjects\\NodeFinder"));
                pbGet.command("java","-Dfile.encoding=UTF-8","-jar","simblock-pro.jar","false",type,rate);
                try {
                    Process process = pbGet.start();
                    try {
                        int exitcode=process.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    JSONObject jobj=readJsonFile(source_path+"OUT_DATA_"+type+"_"+rate+".json");
                    times.add(df.format(Double.valueOf(jobj.get("平均共识达成时间").toString())));
                    System.out.println(jobj.get("平均共识达成时间").toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("dead");
            }
            consensus.put(type,times);
        }

        Process proc;
        try {
            //String[] args = new String[] {  };
            ProcessBuilder pbGet_anl = new ProcessBuilder();
            pbGet_anl.directory(new File("D:\\IdeaProjects\\NodeFinder\\ethereum_P2Pnetwork_Probe"));
            pbGet_anl.command("py","data_analyzer.py");
            System.out.println("py "+"data_analyzer.py");
            proc=pbGet_anl.start();
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            int count=0;
            while(count<=14) {
                line=in.readLine();
                System.out.println(line);
                count++;
                    String[]buff=line.split(":");
                    System.out.println(buff[0]);
                    System.out.println(buff[1]);
                    type_list.add(buff[0]);
                    data_list.add(buff[1]);
            }
            line=in.readLine();
            String[]buff=line.split(" ");
            for(String str:buff)
            {
                System.out.println(str);
                degree_list.add(str);
            }
            line=in.readLine();
            buff=line.split(" ");
            for(String str:buff)
            {
                System.out.println(str);
                bin_list.add(str);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpSession session= request.getSession();

        //session.setAttribute("nodequery",nodequery);
        session.setAttribute("consensus",consensus);
        System.out.println(degree_list);
        session.setAttribute("degree_list",degree_list);
        session.setAttribute("bin_list",bin_list);
        session.setAttribute("data_list",data_list);
        session.setAttribute("type_list",type_list);
        session.setAttribute("raw_data",map);
        request.getRequestDispatcher("analysis.jsp").forward(request, response);
        PrintWriter out = response.getWriter();
    }
}