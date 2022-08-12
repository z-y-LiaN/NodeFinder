package dao;
import dao.EthereumDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void Exec_program(String command[]) {
        ProcessBuilder pbGet = new ProcessBuilder(command);
        pbGet.directory(new File("D:\\IdeaProjects\\NodeFinder"));
        /**
         * 上述代码创建了一个进程执行我们指定的程序
         * 从fefeffef开始就是传入的参数，依次向后填就行
         *      所需完成的工作：
         *      将Exec_program()函数移植到servlet
         *
         */
        try {
            Process process = pbGet.start();
            InputStreamReader inReader = new InputStreamReader(process.getInputStream(), "GBK");
            BufferedReader br = new BufferedReader(inReader);
            String line;
            while (process.isAlive())
            {
                while (br.ready()) {
                    line= br.readLine();
                    System.out.println(line);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("dead");
    }
    public static void main(String[] args) {
    /*
        String Timestamp="1650872900";
        List<String>Nodes=EthereumDAO.queryDistinctConnectedNodeByTimestamp(Timestamp);
        System.out.println(Nodes);


     */

        long now_time=System.currentTimeMillis();
        System.out.println(now_time);


        String config[]={"java","-jar","simblock-pro.jar","false","MY3","1"};
        Exec_program(config);

    }


}
//py ethereum_P2Pnetwork_Probe/main.py