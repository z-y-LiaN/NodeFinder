package simblock.block;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * The type Coinage tracks the age of coins.
 */
public class Coinage implements Cloneable {
  private BigInteger coins;
  private long age;

  /**
   * Instantiates a new Coinage.
   *
   * @param coins the coins
   * @param age   the age
   */
  public Coinage(BigInteger coins, long age) {
    this.coins = coins;
    this.age = age;
  }

  /**
   * Gets coins.
   *
   * @return the coins
   */
  public BigInteger getCoins() {
    return this.coins;
  }

  /**
   * Gets age.
   *
   * @return the age
   */
  public long getAge() {
    return this.age;
  }

  /**
   * Increase age.
   */
  public void increaseAge() {
    this.age++;
  }

  /**
   * Reset age.
   */
  public void resetAge() {
    this.age = 0;
  }

  /**
   * Reward gained for consensus maintenance.
   * 维护共识所获得的奖励
   * @param reward the reward
   */
  public void reward(double reward) {
    this.coins = this.coins.add(
        new BigDecimal(this.getCoinage()).multiply(new BigDecimal(reward)).toBigInteger()
    );
  }

  /**
   * Gets coin age, i.e. the coins amount multiplied by age.
   *
   * @return the coinage
   */
  //TODO what is coin age
  public BigInteger getCoinage() {
    return this.getCoins().multiply(BigInteger.valueOf(this.getAge()));
  }

  @Override
  public Coinage clone() {
    Coinage ret = null;
    try {
      ret = (Coinage) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return ret;
  }
}
