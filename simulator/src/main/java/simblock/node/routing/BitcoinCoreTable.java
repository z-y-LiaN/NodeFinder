
package simblock.node.routing;

import static simblock.simulator.Main.OUT_JSON_FILE;
import static simblock.simulator.Simulator.getSimulatedNodes;
import static simblock.simulator.Timer.getCurrentTime;

import java.util.ArrayList;
import java.util.Collections;
import simblock.node.Node;

/**
 * The implementation of the {@link AbstractRoutingTable} representing the Bitcoin core routing
 * table.
 */
@SuppressWarnings("unused")
public class BitcoinCoreTable extends AbstractRoutingTable {


  private final ArrayList<Node> outbound = new ArrayList<>();

  private final ArrayList<Node> inbound = new ArrayList<>();

  /**
   * Instantiates a new Bitcoin core table.
   *
   * @param selfNode the self node
   */
  public BitcoinCoreTable(Node selfNode) {
    super(selfNode);
  }


  /**
   * Gets all known outbound and inbound nodes.
   *
   * @return a list of known neighbors
   */
  public ArrayList<Node> getNeighbors() {
    ArrayList<Node> neighbors = new ArrayList<>();
    neighbors.addAll(outbound);
    neighbors.addAll(inbound);
    return neighbors;
  }

  /**
   * Initializes a new BitcoinCore routing table. From a pool of
   * all available nodes, choose candidates at random and
   * fill the table using the allowed outbound connections
   * amount.
   */
  //TODO this should be done using the bootstrap node
  public void initTable() {
    ArrayList<Integer> candidates = new ArrayList<>();
    for (int i = 0; i < getSimulatedNodes().size(); i++) {
      candidates.add(i);
    }
    Collections.shuffle(candidates);
    for (int candidate : candidates) {
      if (this.outbound.size() < this.getNumConnection()) {
        this.addNeighbor(getSimulatedNodes().get(candidate));
      } else {
        break;
      }
    }
  }
  /**
   * 重写初始化节点路由表的代码
   */
  public void initTable(ArrayList<Integer> neighbors){
    for(int node_Num:neighbors){
      //记得这里要-1！！！这个bug我tmd找了半个小时，气死了QAQ
      this.addNeighbor(getSimulatedNodes().get(node_Num-1));

    }
  }


  /**
   * Adds the provided node to the list of outbound connections of self node.The provided node
   * will not be added if it is the self node, it exists as an outbound connection of the self node,
   * it exists as an inbound connection of the self node or the self node does not allow for
   * additional outbound connections. Otherwise, the self node will add the provided node to the
   * list of outbound connections and the provided node will add the self node to the list of
   * inbound connections.
   *
   * @param node the node to be connected to the self node.
   * @return the success state
   */
  public boolean addNeighbor(Node node) {
    if (node == getSelfNode() || this.outbound.contains(node) || this.inbound.contains(
        node) ) {//|| this.outbound.size() >= this.getNumConnection()
      return false;
    } else if (this.outbound.add(node) && node.getRoutingTable().addInbound(getSelfNode())) {
      printAddLink(node);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Remove the provided node from the list of outbound connections of the self node and the
   * self node from the list inbound connections from the provided node.
   *
   * @param node the node to be disconnected from the self node.
   * @return the success state of the operation
   */
  public boolean removeNeighbor(Node node) {
    if (this.outbound.remove(node) && node.getRoutingTable().removeInbound(getSelfNode())) {
      printRemoveLink(node);
      return true;
    }
    return false;
  }

  /**
   * Adds the provided node as an inbound connection.
   *
   * @param from the node to be added as an inbound connection
   * @return the success state of the operation
   */
  public boolean addInbound(Node from) {
    if (this.inbound.add(from)) {
      printAddLink(from);
      return true;
    }
    return false;
  }

  /**
   * Removes the provided node as an inbound connection.
   *
   * @param from the node to be removed as an inbound connection
   * @return the success state of the operation
   */
  public boolean removeInbound(Node from) {
    if (this.inbound.remove(from)) {
      printRemoveLink(from);
      return true;
    }
    return false;
  }

  //TODO add example
  private void printAddLink(Node endNode) {
    OUT_JSON_FILE.print("{");
    OUT_JSON_FILE.print("\"kind\":\"add-link\",");
    OUT_JSON_FILE.print("\"content\":{");
    OUT_JSON_FILE.print("\"timestamp\":" + getCurrentTime() + ",");
    OUT_JSON_FILE.print("\"begin-node-id\":" + getSelfNode().getNodeID() + ",");
    OUT_JSON_FILE.print("\"end-node-id\":" + endNode.getNodeID());
    OUT_JSON_FILE.print("}");
    OUT_JSON_FILE.print("},");
    OUT_JSON_FILE.flush();
  }

  //TODO add example
  private void printRemoveLink(Node endNode) {
    OUT_JSON_FILE.print("{");
    OUT_JSON_FILE.print("\"kind\":\"remove-link\",");
    OUT_JSON_FILE.print("\"content\":{");
    OUT_JSON_FILE.print("\"timestamp\":" + getCurrentTime() + ",");
    OUT_JSON_FILE.print("\"begin-node-id\":" + getSelfNode().getNodeID() + ",");
    OUT_JSON_FILE.print("\"end-node-id\":" + endNode.getNodeID());
    OUT_JSON_FILE.print("}");
    OUT_JSON_FILE.print("},");
    OUT_JSON_FILE.flush();
  }

}
