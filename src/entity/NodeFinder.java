package entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NodeFinder {
    List<String>command;
    Process process;
    public NodeFinder(List<String>commands){
        command=commands;
        ProcessBuilder pbGet = new ProcessBuilder("py","D:\\IdeaProjects\\NodeFinder\\ethereum_P2Pnetwork_Probe\\main.py");
        pbGet.redirectErrorStream(true);
        pbGet.directory(new File("D:\\IdeaProjects\\NodeFinder"));
        try {
            process = pbGet.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(process.isAlive());
    }
    public boolean status(){
        return process.isAlive();
    }
    public void destory(){
        process.destroy();
    }
    public List<String> read_log(){
        InputStreamReader inReader = null;
        List<String>lines=new ArrayList<String>();
        long now_time=System.currentTimeMillis();
        System.out.println(now_time);
        long target_time=now_time+60*1000;
        try {
            inReader = new InputStreamReader(process.getInputStream(), "GBK");
            BufferedReader br = new BufferedReader(inReader);
            String line;
            while (process.isAlive())
            {
                while (br.ready()){
                    line= br.readLine();
                    lines.add(line);
                    if(System.currentTimeMillis()>=target_time)
                        return lines;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
