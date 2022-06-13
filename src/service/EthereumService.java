package service;

import dao.EthereumDAO;
import entity.Node;

import java.util.List;

public class EthereumService {

    public static List<Node> queryNodeByPingtme(String pingtime){
      return EthereumDAO.queryNodeByPingtme(pingtime);


    }
}
