package dao;

import entity.Node;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class EthereumDAO {


    //查询一个员工的信息
    public static List<Node> queryNodeByPingtme(String pingtime) {
        List<Node> nodes= null;
        try {
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
            String sql="select * from ethereum where pingtime=?";
            nodes= qr.query(sql, new BeanListHandler<Node>(Node.class),pingtime);
            return nodes;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;


    }
}
