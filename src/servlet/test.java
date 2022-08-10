package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {

    public void getIPv4Address() {
        ProcessBuilder pbGet = new ProcessBuilder("ipconfig", "/all");
        try {
            Process process = pbGet.start();
            InputStreamReader inReader = new InputStreamReader(process.getInputStream(), "GBK");
                 BufferedReader br = new BufferedReader(inReader);
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.indexOf("IPv4") != -1) {
                        System.out.println(line);
                    }
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
