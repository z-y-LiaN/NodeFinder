package dao;
import dao.EthereumDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class test {
    public static void Exec_program() {
        ProcessBuilder pbGet = new ProcessBuilder("java","-jar", "simblock-pro.jar","fefeffef");
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
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        /*
        String Timestamp="1650872900";
        List<String>Nodes=EthereumDAO.queryDistinctConnectedNodeByTimestamp(Timestamp);
        System.out.println(Nodes);
        */
        Exec_program();
    }


}
