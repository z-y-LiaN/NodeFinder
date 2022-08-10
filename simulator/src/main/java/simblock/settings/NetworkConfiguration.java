package simblock.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Network configuration allows to configure network latency and bandwidth.
 * 配置网络时延 和带宽
 */
public class NetworkConfiguration {
  /**
   * Regions where nodes can exist.节点存在的区域
   */
  public static final List<String> REGION_LIST = new ArrayList<>(
      Arrays.asList("中国", "美国", "俄罗斯",
              "哈萨克斯坦", "其他", "加拿大", "马来西亚",
              "伊朗", "德国", "爱尔兰"
      ));
  /**
   * 每个区域的 hash rate,为了数据类型的统一，rate全部乘100，
   * 所以，STDEV_OF_MINING_POWER和AVERAGE_MINING_POWER全部除100
   */
  public static final int[] HASHRATE_LIST={
    3425, 2181, 890, 880, 691, 598, 379, 343,328, 286,
  };

  /**
   * LATENCY[i][j] is average latency from REGION_LIST[i] to REGION_LIST[j]  平均时延
   * Unit: millisecond, for year 2015
   */
  private static final long[][] LATENCY_2015 = {
      {36, 119, 255, 310, 154, 208},
      {119, 12, 221, 242, 266, 350},
      {255, 221, 137, 347, 256, 269},
      {310, 242, 347, 99, 172, 278},
      {154, 266, 256, 172, 9, 163},
      {208, 350, 269, 278, 163, 22}
  };
  /**
   * LATENCY[i][j] is average latency from REGION_LIST[i] to REGION_LIST[j]
   * Unit: millisecond, for year 2019
   */
  private static final long[][] LATENCY_2019 = {
      {32, 124, 184, 198, 151, 189},
      {124, 11, 227, 237, 252, 294},
      {184, 227, 88, 325, 301, 322},
      {198, 237, 325, 85, 58, 198},
      {151, 252, 301, 58, 12, 126},
      {189, 294, 322, 198, 126, 16}
  };
  /**
   * LATENCY[i][j] is average latency from REGION_LIST[i] to REGION_LIST[j]
   * Unit: millisecond, for year 2022 (user defined) init_data.json里面的数据有10个区域，
   */
  private static final long[][] LATENCY_2022 = {
          {100,100,100,100,100,100,100,100,100,100},
          {100,100,100,100,100,100,100,100,100,100},
          {100,100,100,100,100,100,100,100,100,100},
          {100,100,100,100,100,100,100,100,100,100},
          {100,100,100,100,100,100,100,100,100,100},
          {100,100,100,100,100,100,100,100,100,100},
          {100,100,100,100,100,100,100,100,100,100},
          {100,100,100,100,100,100,100,100,100,100},
          {100,100,100,100,100,100,100,100,100,100},
          {100,100,100,100,100,100,100,100,100,100},
  };
  /**
   * List of latency assigned to each region. (unit: millisecond)
   */
  public static final long[][] LATENCY = LATENCY_2022;

  /**
   * List of download bandwidth assigned to each region, and last element is Inter-regional
   * bandwidth. (unit: bit per second) for year 2015
   */
  private static final long[] DOWNLOAD_BANDWIDTH_2015 = {
      25000000, 24000000, 6500000, 10000000,
      17500000, 14000000, 6 * 1000000
  };
  /**
   * List of download bandwidth assigned to each region, and last element is Inter-regional
   * bandwidth. (unit: bit per second) for year 2019
   */
  private static final long[] DOWNLOAD_BANDWIDTH_2019 = {
      52000000, 40000000, 18000000, 22800000,
      22800000, 29900000, 6 * 1000000
  };
  /**
   * List of download bandwidth assigned to each region, and last element is Inter-regional
   * bandwidth. (unit: bit per second) for year 2022 (zyl defined)：取的上面2019的平均
   */
  private static final long[] DOWNLOAD_BANDWIDTH_2022 = {
          31000000, 31000000, 31000000, 31000000, 31000000,
          31000000, 31000000, 31000000, 31000000, 31000000,
          6 * 1000000
  };
  /**
   * List of download bandwidth assigned to each region, and last element is Inter-regional
   * bandwidth. (unit: bit per second)
   */
  public static final long[] DOWNLOAD_BANDWIDTH = DOWNLOAD_BANDWIDTH_2022;

  /**
   * List of upload bandwidth assigned to each region. (unit: bit per second), and last element
   * is Inter-regional bandwidth for year 2015
   */
  private static final long[] UPLOAD_BANDWIDTH_2015 = {
      4700000, 8100000, 1800000, 5300000,
      3400000, 5200000, 6 * 1000000
  };
  /**
   * List of upload bandwidth assigned to each region. (unit: bit per second), and last element
   * is Inter-regional bandwidth for year 2019
   */
  private static final long[] UPLOAD_BANDWIDTH_2019 = {
      19200000, 20700000, 5800000, 15700000,
      10200000, 11300000, 6 * 1000000
  };
  /**
   * List of upload bandwidth assigned to each region. (unit: bit per second), and last element
   * is Inter-regional bandwidth for year 2022 (zyl defined)：取的上面2019的平均
   */
  private static final long[] UPLOAD_BANDWIDTH_2022 = {
          13800000, 13800000, 13800000,13800000,13800000,
          13800000,13800000,13800000,13800000,13800000,
          6 * 1000000
  };

  /**
   * List of upload bandwidth assigned to each region. (unit: bit per second), and last element
   * is Inter-regional bandwidth.
   */
  public static final long[] UPLOAD_BANDWIDTH = UPLOAD_BANDWIDTH_2022;

  /**
   * Region distribution Bitcoin 2015.
   */
  private static final double[] REGION_DISTRIBUTION_BITCOIN_2015 = {
      0.3869, 0.5159, 0.0113,
      0.0574, 0.0119, 0.0166
  };

  /**
   * Region distribution Bitcoin 2019.
   */
  private static final double[] REGION_DISTRIBUTION_BITCOIN_2019 = {
      0.3316, 0.4998, 0.0090,
      0.1177, 0.0224, 0.0195
  };

  /**
   * Region distribution Litecoin.
   */
  //TODO year
  private static final double[] REGION_DISTRIBUTION_LITECOIN = {
      0.3661, 0.4791, 0.0149, 0.1022, 0.0238, 0.0139
  };

  /**
   * Region distribution Dogecoin.
   */
  //TODO year
  private static final double[] REGION_DISTRIBUTION_DOGECOIN = {
      0.3924, 0.4879, 0.0212, 0.0697, 0.0106, 0.0182
  };

  /**
   * The distribution of node's region. Each value means the rate of the number of nodes in the
   * corresponding region to the number of all nodes.
   */
  public static final double[] REGION_DISTRIBUTION = REGION_DISTRIBUTION_BITCOIN_2019;

  /**
   * The cumulative distribution of number of outbound links for Bitcoin 2015.
   */
  private static final double[] DEGREE_DISTRIBUTION_BITCOIN_2015 = {
      0.025, 0.050, 0.075, 0.10, 0.20, 0.30, 0.40, 0.50, 0.60, 0.70, 0.80, 0.85, 0.90, 0.95, 0.97,
      0.97, 0.98, 0.99, 0.995, 1.0
  };

  /**
   * The cumulative distribution of number of outbound links for Litecoin.
   */
  //TODO year
  private static final double[] DEGREE_DISTRIBUTION_LITECOIN = {
      0.01, 0.02, 0.04, 0.07, 0.09, 0.14, 0.20, 0.28, 0.39, 0.5, 0.6, 0.69, 0.76, 0.81, 0.85, 0.87,
      0.89, 0.92, 0.93, 1.0
  };

  /**
   * The cumulative distribution of number of outbound links for Dogecoin.
   */
  private static final double[] DEGREE_DISTRIBUTION_DOGECOIN = {
      0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 1.0, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00,
      1.00, 1.00, 1.00, 1.0
  };

  /**
   * The cumulative distribution of number of outbound links. Cf. Andrew Miller et al.,
   * "Discovering bitcoin's public topology and influential nodes", 2015.
   */
  public static final double[] DEGREE_DISTRIBUTION = DEGREE_DISTRIBUTION_BITCOIN_2015;
}