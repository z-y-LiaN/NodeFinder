package dao;

import entity.Connection;
import entity.Node;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class EthereumDAO {


    //查询一个员工的信息
    public static List<Node> queryNodeByTimestamp(String Timestamp) {
        List<Node> nodes= null;
        try {
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
            String sql="select * from ethereum where pingtime<=? and pingtime!=0";
            nodes= qr.query(sql, new BeanListHandler<Node>(Node.class),Timestamp);
            return nodes;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;


    }
    public static Long queryConnectionNumByTimestamp(String Timestamp) {
        Long conns;
        try {
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
            String sql="select COUNT(*)from ethereum_neighbours where intTIME<=?";
            conns= qr.query(sql, new ScalarHandler<Long>(),Timestamp);
            return conns;
        }catch(Exception e){
            e.printStackTrace();
        }

        return Long.valueOf(-1);


    }
    public static Long queryActiveNodeNumByTimestamp(String Timestamp) {
        Long Acitve_nodes_Num;
        try {
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
            String sql="select COUNT(*)from ethereum_active_nodes where pongtime<=? and pongtime!=0";
            Acitve_nodes_Num= qr.query(sql, new ScalarHandler<Long>(),Timestamp);
            return Acitve_nodes_Num;
        }catch(Exception e){
            e.printStackTrace();
        }

        return Long.valueOf(-1);


    }
    public static List<Connection> queryConnectionByTimestamp(String Timestamp) {
        List<Connection> Conns=null;
        try {
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
            String sql="select * from ethereum_neighbours where intTIME<=?";
            Conns= qr.query(sql, new BeanListHandler<Connection>(Connection.class),Timestamp);
            return Conns;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;


    }
    public static List<String> queryDistinctConnectedNodeByTimestamp(String Timestamp) {
        List<String> Nodes=null;
        try {
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
            String sql="select DISTINCT nodeid2 from ethereum_neighbours where intTIME<=?";
            Nodes= qr.query(sql, new ColumnListHandler<String>(),Timestamp);
            return Nodes;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;


    }
    public static List<String> queryConnectedNodeByTimestamp(String Timestamp) {
        List<String> Nodes=null;
        try {
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
            String sql="select nodeid2 from ethereum_neighbours where intTIME<=?";
            Nodes= qr.query(sql, new ColumnListHandler<String>(),Timestamp);
            return Nodes;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;


    }
}
