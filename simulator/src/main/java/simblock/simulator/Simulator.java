package simblock.simulator;

import static simblock.settings.SimulationConfiguration.END_BLOCK_HEIGHT;
import static simblock.settings.SimulationConfiguration.NUM_OF_NODES;
import static simblock.simulator.Timer.getCurrentTime;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import simblock.block.Block;
import simblock.node.Node;


/**
 * The type Simulator is tasked with maintaining the list of simulated nodes and managing the
 * block interval. It observes and manages the arrival of new blocks at the simulation level.
 */
public class Simulator {

  /**
   * A list of nodes that will be used in a simulation.
   */
  private static final ArrayList<Node> simulatedNodes = new ArrayList<>();


  /**
   * The target block interval in milliseconds.
   */
  private static long targetInterval;

  private static double averageSum = 0;

  private static double transmitSum = 0;

  private static double blockgenSum = 0;

  public  static  double getAverageSum(){
      return averageSum;
  }
  public  static  double gettransmitSum(){
    return transmitSum;
  }
  public  static  double getBlockgenSum(){
    return blockgenSum;
  }

  public static ArrayList<Node> getSimulatedNodes() {
    return simulatedNodes;
  }

  public static long getTargetInterval() {
    return targetInterval;
  }

  /**
   * Sets the target block interval.
   *
   * @param interval - block interval in milliseconds
   */
  public static void setTargetInterval(long interval) {
    targetInterval = interval;
  }

  public static void addNode(Node node) {
    simulatedNodes.add(node);
  }

  @SuppressWarnings("unused")
  public static void removeNode(Node node) {
    simulatedNodes.remove(node);
  }

  /**
   * Add node to the list of simulated nodes and immediately try to add the new node as a
   * neighbor to all simulated
   * nodes.
   *
   * @param node the node
   */
  @SuppressWarnings("unused")
  public static void addNodeWithConnection(Node node) {
    node.joinNetwork();
    addNode(node);
    for (Node existingNode : simulatedNodes) {
      existingNode.addNeighbor(node);
    }
  }

  /**
   * A list of observed {@link Block} instances.
   */
  private static final ArrayList<Block> observedBlocks = new ArrayList<>();

  /**
   * A list of observed block propagation times. The map key represents the id of the node that
   * has seen the block, the value represents the difference between the current time and the block minting
   * time, effectively recording the absolute time it took for a node to witness the block.
   * 能看到这个区块节点的id,
   */
  private static final ArrayList<LinkedHashMap<Integer, Long>> observedPropagations =
      new ArrayList<>();

  /**
   * Handle the arrival of a new block. For every observed block, propagation information is
   * updated, and for a new block propagation information is created.
   *
   * @param block the block
   * @param node  the node
   */
  public static void arriveBlock(Block block, Node node) {
    // If block is already seen by any node
    if (observedBlocks.contains(block)) {
      // Get the propagation information for the current block
      LinkedHashMap<Integer, Long> propagation = observedPropagations.get(
          observedBlocks.indexOf(block)
      );
      // Update information for the new block
      propagation.put(node.getNodeID(), getCurrentTime() - block.getTime());
    } else {
      // If the block has not been seen by any node and there is no memory allocated
      //TODO move magic number to constant
      if (observedBlocks.size() > 10) {
        // After the observed blocks limit is reached, log and remove old blocks by FIFO principle
        printPropagation(observedBlocks.get(0), observedPropagations.get(0));
        observedBlocks.remove(0);
        observedPropagations.remove(0);
      }
      // If the block has not been seen by any node and there is additional memory
      LinkedHashMap<Integer, Long> propagation = new LinkedHashMap<>();
      propagation.put(node.getNodeID(), getCurrentTime() - block.getTime());
      // Record the block as seen
      observedBlocks.add(block);
      // Record the propagation time
      observedPropagations.add(propagation);
    }
  }

  /**
   * Print propagation information about the propagation of the provided block  in the format:
   *
   * <p><em>node_ID, propagation_time</em>
   *
   * <p><em>propagation_time</em>: The time from when the block of the block ID is generated to
   * when the
   * node of the <em>node_ID</em> is reached.
   *
   * @param block       the block
   * @param propagation the propagation of the provided block as a list of {@link Node} IDs and
   *                    propagation times
   */
  public static void printPropagation(Block block, LinkedHashMap<Integer, Long> propagation) {
    // Print block and its height
    //TODO block does not have a toString method, what is printed here
    //System.out.println(block + ":" + block.getHeight());
    blockgenSum=block.getTime();
    long ret=0;
    int count=0;
    double total_propagation_time=0.0;
    for (Map.Entry<Integer, Long> timeEntry : propagation.entrySet()) {
//      System.out.println(timeEntry.getKey() + "," + timeEntry.getValue());
      total_propagation_time+=timeEntry.getValue()/1000.0;
      if (count<=(NUM_OF_NODES*0.51)){
        count++;
        ret=timeEntry.getValue();
      }
    }
    transmitSum+=ret;
//    Map.Entry<Integer, Long> tail=null;
//    Iterator<Map.Entry<Integer, Long>> iterator=propagation.entrySet().iterator();
//    while(iterator.hasNext()){
//      tail=iterator.next();
//    }
//    System.out.println("last time "+tail.getValue());
//    System.out.println("total nodes = "+propagation.size());
//    System.out.println("该区块的平均传输时间 "+tail.getValue()/propagation.size()+" ms");
//    System.out.println("该区块的平均传输时间 "+total_propagation_time/propagation.size()+" s");
    averageSum += total_propagation_time/propagation.size();
//    System.out.println();
  }
  /**
   * Print propagation information about all blocks, internally relying on
   * {@link Simulator#printPropagation(Block, LinkedHashMap)}.
   */
  public static void printAllPropagation(PrintWriter OUT_DATA_FILE) {
    for (int i = 0; i < observedBlocks.size(); i++) {
     printPropagation(observedBlocks.get(i), observedPropagations.get(i));
    }
    /**
     *  共识达成时间 ： 该区块产生的消息被51%的节点接受的时间 ， 即网络中的节点达成共识该区块存在
     *  块传播时间 ： 该区块产生的消息传播给某个节点所用的时间
     *  出块时间 ： 该块产生所用的时间 ， 该区块产生的时间 减去 前一区块产生的时间
     */

    System.out.println("平均共识达成时间 ："+transmitSum/(END_BLOCK_HEIGHT+1));
    System.out.println("平均块传播时间 ："+averageSum/(END_BLOCK_HEIGHT+1));
    System.out.println("平均出块时间 ："+blockgenSum/(END_BLOCK_HEIGHT+1));
    OUT_DATA_FILE.println("{");
    OUT_DATA_FILE.println("\"平均共识达成时间\":"+transmitSum/(END_BLOCK_HEIGHT+1));
    OUT_DATA_FILE.println(',');
    OUT_DATA_FILE.println("\"平均块传播时间\":"+averageSum/(END_BLOCK_HEIGHT+1));
    OUT_DATA_FILE.println(',');
    OUT_DATA_FILE.println("\"平均出块时间\":"+blockgenSum/(END_BLOCK_HEIGHT+1));
    OUT_DATA_FILE.println("}");
    OUT_DATA_FILE.flush();
  }
}
