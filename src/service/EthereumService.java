package service;

import dao.EthereumDAO;
import entity.Connection;
import entity.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EthereumService {

    public static List<Node> queryNodeByTimestamp(String Timestamp){
      return EthereumDAO.queryNodeByTimestamp(Timestamp);


    }
    public static Long queryConnecionNumByTimestamp(String Timestamp){
        return EthereumDAO.queryConnectionNumByTimestamp(Timestamp);


    }
    public static Long queryActiveNodeNumByTimestamp(String Timestamp){
        return EthereumDAO.queryActiveNodeNumByTimestamp(Timestamp);


    }
    public static List<Connection> queryConnectionByTimestamp(String Timestamp){
        return EthereumDAO.queryConnectionByTimestamp(Timestamp);
    }
    public static List<String> queryDistinctConnectedNodeByTimestamp(String Timestamp){
        return EthereumDAO.queryDistinctConnectedNodeByTimestamp(Timestamp);
    }
    public static List<String> queryConnectedNodeByTimestamp(String Timestamp){
        return EthereumDAO.queryConnectedNodeByTimestamp(Timestamp);
    }
    public static Long queryRouteNodeNumNodeByTimestamp(String Timestamp){
        List<String>distinct=EthereumDAO.queryDistinctConnectedNodeByTimestamp(Timestamp);
        List<String>all=EthereumDAO.queryConnectedNodeByTimestamp(Timestamp);
        Set<String> tmpSet = new HashSet<>(distinct);
        Set<String> RouteNodes = new HashSet<>();
        for (String node : all)
        {
            if(tmpSet.contains(node))
                tmpSet.remove(node);
            else
            RouteNodes.add(node);
        }
        return Long.valueOf(RouteNodes.size());
    }
    public static List<String> queryRouteNodeNodeByTimestamp(String Timestamp){
        List<String>distinct=EthereumDAO.queryDistinctConnectedNodeByTimestamp(Timestamp);
        List<String>all=EthereumDAO.queryConnectedNodeByTimestamp(Timestamp);
        Set<String> tmpSet = new HashSet<>(distinct);
        Set<String> RouteNodes = new HashSet<>();
        for (String node : all)
        {
            if(tmpSet.contains(node))
                tmpSet.remove(node);
            else
                RouteNodes.add(node);
        }
        return new ArrayList<>(RouteNodes);
    }
}
