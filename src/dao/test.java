package dao;
import dao.EthereumDAO;

import java.util.List;

public class test {
    public static void main(String[] args) {
        String Timestamp="1650872900";
        List<String>Nodes=EthereumDAO.queryDistinctConnectedNodeByTimestamp(Timestamp);
        System.out.println(Nodes);
    }

}
