package servlet;

import entity.Node;
import service.EthereumService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "QueryByPingtimeController",urlPatterns = "/QueryByPingtimeController")
public class QueryByPingtimeController extends HttpServlet {


        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            response.setContentType("text/html");


        }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String pingtime=request.getParameter("pingtimeInput");


        List<Node> nodequery= EthereumService.queryNodeByPingtme(pingtime);
        HttpSession session= request.getSession();
        session.setAttribute("nodequery",nodequery);
        request.getRequestDispatcher("networkfind.jsp").forward(request, response);

        PrintWriter out = response.getWriter();

    }

}
