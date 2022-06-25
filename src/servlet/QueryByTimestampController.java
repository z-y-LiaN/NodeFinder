package servlet;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import entity.Node;
import service.EthereumService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Subdivision;

@WebServlet(name = "QueryByTimestampController",urlPatterns = "/QueryByTimestampController")
public class QueryByTimestampController extends HttpServlet {


        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            response.setContentType("text/html");


        }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String Timestamp=request.getParameter("TimestampInput");

        Dictionary<String, Integer> dict = new Hashtable<>();
        List<Node> nodequery= EthereumService.queryNodeByTimestamp(Timestamp);
        Long Node_Num=Long.valueOf(nodequery.size());
        Long Conn_Num=EthereumService.queryConnecionNumByTimestamp(Timestamp);
        Long Active_Node_Num=EthereumService.queryActiveNodeNumByTimestamp(Timestamp);
        Long Route_Node_Num=EthereumService.queryRouteNodeNumNodeByTimestamp(Timestamp);
        for (Node node:nodequery
             ) {
            // GeoIP2-City 数据库文件mmdb
            File database = new File("D:\\ethereum\\NodeFinder\\GeoLite2-City.mmdb");

            // 创建 DatabaseReader对象
            DatabaseReader reader = new DatabaseReader.Builder(database).build();

            // 设置地址是使用IPv4还是IPv6
            String ip=node.getIp();
            if(ip.contains(":"))
                System.setProperty("java.net.preferIPv6Addresses", "true");
            else
                System.setProperty("java.net.preferIPv4Stack", "true");
            // 设置IP地址
//		InetAddress ipAddress = InetAddress.getByName("39.130.56.106");
            InetAddress ipAddress = InetAddress.getByName(node.getIp());

            // 获取查询结果
            CityResponse responsec = null;
            try {
                responsec = reader.city(ipAddress);
            } catch (GeoIp2Exception e) {
                e.printStackTrace();
            }

            // 获取国家信息
            Country country = responsec.getCountry();
            Integer num=dict.get(country.getNames().get("en"));
            if(num==null)
            {
                dict.put(country.getNames().get("en"),new Integer(0));
                num=dict.get(country.getNames().get("en"));
            }
            num=(num.intValue()+1);
            dict.put(country.getNames().get("en"),num);
        }
        /*
        Enumeration<String> keys = dict.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println(key+"  "+dict.get(key));
        }
        */
        /*
        for (Node node:nodequery
             ) {
            Process proc;
            try {

                String[] args = new String[] { "py","D:\\ethereum\\NodeFinder\\ask4region.py",node.getIp() };
                proc = Runtime.getRuntime().exec(args);// 执行py文件
                //用输入输出流来截取结果
                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                in.close();
                proc.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
*/
            HttpSession session= request.getSession();
        //session.setAttribute("nodequery",nodequery);
        session.setAttribute("ip_dict",dict);
        session.setAttribute("Node_Num",Node_Num);
        session.setAttribute("Conn_Num",Conn_Num);
        session.setAttribute("Active_Node_Num",Active_Node_Num);
        session.setAttribute("Route_Node_Num",Route_Node_Num);
        session.setAttribute("Cur_Timestamp",Timestamp);
        request.getRequestDispatcher("networkfind.jsp").forward(request, response);

        PrintWriter out = response.getWriter();

    }

}
