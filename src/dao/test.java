package dao;
import dao.EthereumDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void Exec_program(String command[]) {
        ProcessBuilder pbGet = new ProcessBuilder(command);
        pbGet.redirectErrorStream(true);
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
            InputStreamReader inReader ;
            String line;
            while (process.isAlive())
            {
                inReader = new InputStreamReader(process.getInputStream(), "GBK");
                BufferedReader br = new BufferedReader(inReader);
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
/*
        long now_time=System.currentTimeMillis();
        System.out.println(now_time);


        String config[]={"py","ethereum_P2Pnetwork_Probe/main.py"};
        Exec_program(config);


 */
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("data.txt");
            InputStreamReader isr = null;
            isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while (true) {
                if (!((line = br.readLine()) != null)) break;
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }}
//py ethereum_P2Pnetwork_Probe/main.py