package entity;

public class Node {
    int id;
    String nodeid;
    String publickey;
    String pingtime;
    String port;
    String ip;
    String pongtime;

    public Node() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }

    public String getPingtime() {
        return pingtime;
    }

    public void setPingtime(String pingtime) {
        this.pingtime = pingtime;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPongtime() {
        return pongtime;
    }

    public void setPongtime(String pongtime) {
        this.pongtime = pongtime;
    }

    public Node(String pingtime) {
        this.pingtime = pingtime;
    }

    public Node(int id, String nodeid, String publickey, String pingtime, String port, String ip, String pongtime) {
        this.id = id;
        this.nodeid = nodeid;
        this.publickey = publickey;
        this.pingtime = pingtime;
        this.port = port;
        this.ip = ip;
        this.pongtime = pongtime;
    }
}
