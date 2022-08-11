package entity;

public class Parameter {


    String dbname;//本次探测的数据库名
    String dbaccount;//本次探测的数据库账号
    String dbpassword;//本次探测的数据库密码
    String nodefindername;//本次探测名字
    String begintime;//探测开始时间
    String cycle;//快照周期（/分钟）
    String model;//模式
    String cycleindex;//循环次数（/次）
    String interval;//探测间隔（/分钟）
    public Parameter(){

    }
    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbaccount() {
        return dbaccount;
    }

    public void setDbaccount(String dbaccount) {
        this.dbaccount = dbaccount;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    public String getNodefindername() {
        return nodefindername;
    }

    public void setNodefindername(String nodefindername) {
        this.nodefindername = nodefindername;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCycleindex() {
        return cycleindex;
    }

    public void setCycleindex(String cycleindex) {
        this.cycleindex = cycleindex;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Parameter(String dbname, String dbaccount, String dbpassword, String nodefindername, String begintime, String cycle, String model, String cycleindex, String interval) {
        this.dbname = dbname;
        this.dbaccount = dbaccount;
        this.dbpassword = dbpassword;
        this.nodefindername = nodefindername;
        this.begintime = begintime;
        this.cycle = cycle;
        this.model = model;
        this.cycleindex = cycleindex;
        this.interval = interval;
    }
}
