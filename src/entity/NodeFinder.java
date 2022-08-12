package entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NodeFinder {
    List<String>command=new ArrayList<String>();
    Process process;
    Parameter config;

    /**
     *首先创建一个NodeFinder
     * 该NoderFinder是创建了一个进程运行ethereum_P2Pnetwork_Probe/main.py的代码
     *
     * @param parameter  该网络探测器的一些参数设置
     */
    public NodeFinder(Parameter parameter){
        config=parameter;
        command.add("py");
        command.add("D:\\IdeaProjects\\NodeFinder\\ethereum_P2Pnetwork_Probe\\main.py");
        /*
               command.add(parameter.getBegintime());
        command.add(parameter.getCycle());
        command.add(parameter.getCycleindex());
        command.add(parameter.getInterval());
        command.add(parameter.getModel());
         */
        ProcessBuilder pbGet = new ProcessBuilder(command);
        pbGet.redirectErrorStream(true);
        pbGet.directory(new File("D:\\IdeaProjects\\NodeFinder"));
        try {
            process = pbGet.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(process.isAlive());
    }

    /**
     * 查询该NodeFinder进程是否存活
     * @return
     */
    public boolean status(){
        return process.isAlive();
    }

    /**
     * 销毁该进程
     */
    public void destory(){
        process.destroy();
    }

    /**
     * 该方法实现了项目进程与网络探测进程的交互
     * 网络探测的日志输出会存在该项目进程的输入缓冲区
     * 调用该方法访问输入缓冲区获取网络探测进程的日志输出
     * @return
     */
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
