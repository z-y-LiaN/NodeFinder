package entity;

import java.security.PublicKey;
import java.util.Date;

public class Connection {
    int id;
    String nodeid1;
    String nodeid2;
    Date update_time;
    int intTIME;
    int RECV_NUM;

    public Connection(int id, String nodeid1, String nodeid2, Date update_time, int intTIME, int RECV_NUM) {
        this.id = id;
        this.nodeid1 = nodeid1;
        this.nodeid2 = nodeid2;
        this.update_time = update_time;
        this.intTIME = intTIME;
        this.RECV_NUM = RECV_NUM;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeid1() {
        return nodeid1;
    }

    public void setNodeid1(String nodeid1) {
        this.nodeid1 = nodeid1;
    }

    public String getNodeid2() {
        return nodeid2;
    }

    public void setNodeid2(String nodeid2) {
        this.nodeid2 = nodeid2;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getIntTIME() {
        return intTIME;
    }

    public void setIntTIME(int intTIME) {
        this.intTIME = intTIME;
    }

    public int getRECV_NUM() {
        return RECV_NUM;
    }

    public void setRECV_NUM(int RECV_NUM) {
        this.RECV_NUM = RECV_NUM;
    }

}
