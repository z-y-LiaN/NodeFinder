package simblock.block;

import simblock.node.Node;

/**
 * 区块 定义
 */
public class Block {
  /**
   * 当前块的高度.比特币区块高度，顾名思义 就是指生成了多少个区块;就是代表这是生成的第几个区块
   */
  private final int height;

  /**
   * The parent {@link Block}. 当前块的前一个块？
   */
  private final Block parent;

  /**
   * The {@link Node} that minted the block.
   */
  private final Node minter;

  /**
   * 铸造时间戳，自simulation开始的绝对时间。
   */
  private final long time;

  /**
   * Block unique id.这个区块的唯一标识
   */
  private final int id;

  /**
   * Latest known block id.
   */
  private static int latestId = 0;

  /**
   * Instantiates a new Block.
   *
   * @param parent the parent
   * @param minter the minter
   * @param time   the time
   */
  public Block(Block parent, Node minter, long time) {
    this.height = parent == null ? 0 : parent.getHeight() + 1;
    this.parent = parent;
    this.minter = minter;
    this.time = time;
    this.id = latestId;
    latestId++;
  }

  /**
   * Get height int.
   *
   * @return the int
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Get parent block.
   *
   * @return the block
   */
  public Block getParent() {
    return this.parent;
  }

  /**
   * Get minter node.
   *
   * @return the node
   */
  @SuppressWarnings("unused")
  public Node getMinter() {
    return this.minter;
  }

  /**
   * Get time.
   *
   * @return the time
   */
  //TODO what format
  public long getTime() {
    return this.time;
  }

  /**
   * Gets the block id.
   *
   * @return the id
   */
  //TODO what format
  public int getId() {
    return this.id;
  }

  /**
   * Generates the genesis block. The parent is set to null and the time is set to 0
   * 创世区块，父节点null，时间0
   * @param minter the minter
   * @return the block
   */
  @SuppressWarnings("unused")
  public static Block genesisBlock(Node minter) {
    return new Block(null, minter, 0);
  }

  /**
   * Recursively searches for the block at the provided height.
   * 递归地搜索 给定高度的区块
   * @param height the height
   * @return the block with the provided height
   */
  public Block getBlockWithHeight(int height) {
    if (this.height == height) {
      return this;
    } else {
      return this.parent.getBlockWithHeight(height);
    }
  }

  /**
   * Checks if the provided block is on the same chain as self.
   * 检查给定的区块是否和当前区块在同一条链上
   * @param block the block to be checked
   * @return true if block are on the same chain false otherwise
   */
  public boolean isOnSameChainAs(Block block) {
    if (block == null) {
      return false;
    } else if (this.height <= block.height) {
      return this.equals(block.getBlockWithHeight(this.height));
    } else {
      return this.getBlockWithHeight(block.height).equals(block);
    }
  }
}
