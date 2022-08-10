package simblock.node.consensus;

import static simblock.simulator.Main.random;

import java.math.BigInteger;
import simblock.block.Block;
import simblock.block.ProofOfWorkBlock;
import simblock.node.Node;
import simblock.task.MiningTask;

/**
 * The type Proof of work.
 */
@SuppressWarnings("unused")
public class ProofOfWork extends AbstractConsensusAlgo {
  /**
   * Instantiates a new Proof of work consensus algorithm.
   *
   * @param selfNode the self node
   */
  public ProofOfWork(Node selfNode) {
    super(selfNode);
  }

  /**
   * Mints a new block by simulating Proof of Work.
   */
  @Override
  public MiningTask minting() {
    Node selfNode = this.getSelfNode();
    ProofOfWorkBlock parent = (ProofOfWorkBlock) selfNode.getBlock();
    BigInteger difficulty = parent.getNextDifficulty();
    double p = 1.0 / difficulty.doubleValue();
    double u = random.nextDouble();
    return p <= Math.pow(2, -53) ? null : new MiningTask(selfNode, (long) (Math.log(u) / Math.log(
        1.0 - p) / selfNode.getMiningPower()), difficulty);
  }

  /**
   * Tests if the receivedBlock is valid with regards to the current block. The receivedBlock
   * is valid if it is an instance of a Proof of Work block and the received block needs to have
   * a bigger difficulty than its parent next difficulty and a bigger total difficulty compared to
   * the current block.
   *
   * @param receivedBlock the received block
   * @param currentBlock  the current block
   * @return true if block is valid false otherwise
   */
  @Override
  public boolean isReceivedBlockValid(Block receivedBlock, Block currentBlock) {
    if (!(receivedBlock instanceof ProofOfWorkBlock)) {
      return false;
    }
    ProofOfWorkBlock recPoWBlock = (ProofOfWorkBlock) receivedBlock;
    ProofOfWorkBlock currPoWBlock = (ProofOfWorkBlock) currentBlock;
    int receivedBlockHeight = receivedBlock.getHeight();
    ProofOfWorkBlock receivedBlockParent = receivedBlockHeight == 0 ? null :
        (ProofOfWorkBlock) receivedBlock.getBlockWithHeight(receivedBlockHeight - 1);

    //TODO - dangerous to split due to short circuit operators being used, refactor?
    return (
        receivedBlockHeight == 0 ||
            recPoWBlock.getDifficulty().compareTo(receivedBlockParent.getNextDifficulty()) >= 0
    ) && (
        currentBlock == null ||
            recPoWBlock.getTotalDifficulty().compareTo(currPoWBlock.getTotalDifficulty()) > 0
    );
  }

  @Override
  public ProofOfWorkBlock genesisBlock() {
    return ProofOfWorkBlock.genesisBlock(this.getSelfNode());
  }

}
