package dp;

import java.util.HashMap;
import java.util.Map;

public class DpOnStocks {

  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
   */
  public int maxProfit(int[] prices) {
    int curPrice = prices[0];
    int maxProfit = 0;
    for (int i = 1; i < prices.length; i++) {
      maxProfit = Math.max(maxProfit, prices[i] - curPrice);
      curPrice = Math.min(curPrice, prices[i]);
    }

    return maxProfit;
  }

  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/ Can be bought and
   * sold multiple number of times
   */

  public static int maxProfit2(int[] prices) {
    int currPrice = prices[0];
    int sumProfit = 0;

    for (int i = 1; i < prices.length; i++) {
      if (prices[i] < currPrice) {
        currPrice = prices[i];
      } else {
        sumProfit += prices[i] - currPrice;
        currPrice = prices[i];
      }
    }

    return sumProfit;
  }


  private static int maxProfit2Tab(int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n + 1][2];

    for (int i = n - 1; i >= 0; i--) {
      for (int buy = 0; buy <= 1; buy++) {
        if (buy == 1) {
          dp[i][buy] = Math.max(dp[i + 1][1 - buy] - prices[i], dp[i + 1][buy]);
        } else {
          dp[i][buy] = Math.max(dp[i + 1][1 - buy] + prices[i], dp[i + 1][buy]);
        }
      }
    }
    return dp[0][1];
  }


  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
   *
   * 2 transaction limit
   */

  public static int maxProfit3R(int[] prices) {
    Map<String, Integer> map = new HashMap<>();
    return maxProfit3Rec(prices, 0, true, 2, map);
  }

  public static int maxProfit3Rec(int[] prices, int idx, boolean buy, int limit,
      Map<String, Integer> map) {
    if (idx == prices.length) {
      return 0;
    }

    if (limit == 0) {
      return 0;
    }

    String key = idx + "-" + (buy == true ? 1 : 0) + "-" + limit;

    if (map.containsKey(key)) {
      return map.get(key);
    }

    int profit = 0;
    if (buy) {
      int doBuying = -prices[idx] + maxProfit3Rec(prices, idx + 1, !buy, limit, map);
      int skip = maxProfit3Rec(prices, idx + 1, buy, limit, map);
      profit = Math.max(doBuying, skip);
    } else {
      int doSelling = prices[idx] + maxProfit3Rec(prices, idx + 1, !buy, limit - 1, map);
      int skip = maxProfit3Rec(prices, idx + 1, buy, limit, map);
      profit = Math.max(doSelling, skip);
    }

    map.put(key, profit);
    return profit;
  }

  private static int maxProfitKTab(int[] prices, int k) {
    int n = prices.length;
    int[][][] dp = new int[n + 1][2][k + 1];

    for (int i = n - 1; i >= 0; i--) {
      for (int buy = 0; buy <= 1; buy++) {
        for (int limit = 1; limit <= k; limit++) {
          if (buy == 1) {
            dp[i][buy][limit] = Math.max(dp[i + 1][1 - buy][limit] - prices[i],
                dp[i + 1][buy][limit]);
          } else {
            dp[i][buy][limit] = Math.max(dp[i + 1][1 - buy][limit - 1] + prices[i],
                dp[i + 1][buy][limit]);
          }
        }
      }
    }
    return dp[0][1][k];
  }

  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
   *
   * Best Time to Buy and Sell Stock with Cooldown
   */

  public static int maxProfitCooldown(int[] prices) {
    Map<String, Integer> map = new HashMap<>();
    return maxProfitCooldownRec(prices, 0, 1, map);
  }

  public static int maxProfitCooldownRec(int[] prices, int idx, int buy, Map<String, Integer> map) {
    if (idx >= prices.length) {
      return 0;
    }

    String key = idx + "-" + buy;

    if (map.containsKey(key)) {
      return map.get(key);
    }

    int profit;
    if (buy == 1) {
      int letsBuy = -prices[idx] + maxProfitCooldownRec(prices, idx + 1, 1 - buy, map);
      int skip = maxProfitCooldownRec(prices, idx + 1, buy, map);
      profit = Math.max(letsBuy, skip);
    } else {
      int sell = prices[idx] + maxProfitCooldownRec(prices, idx + 2, 1 - buy, map);
      int skip = maxProfitCooldownRec(prices, idx + 1, buy, map);
      profit = Math.max(sell, skip);
    }

    map.put(key, profit);

    return profit;

  }

  public static int maxProfitCooldownTab(int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n + 2][2];

    for (int i = n - 1; i >= 0; i--) {
      for (int buy = 0; buy <= 1; buy++) {
        if (buy == 1) {
          dp[i][buy] = Math.max(dp[i + 1][1 - buy] - prices[i], dp[i + 1][buy]);
        } else {
          dp[i][buy] = Math.max(dp[i + 2][1 - buy] + prices[i], dp[i + 1][buy]);
        }
      }
    }
    return dp[0][1];
  }


  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
   *
   * Best Time to Buy and Sell Stock with Transaction Fee
   */

  public static int maxProfitTransaction(int[] prices, int fee) {
    int n = prices.length;
    int[][] dp = new int[n + 1][2];

    for (int i = n - 1; i >= 0; i--) {
      for (int buy = 0; buy <= 1; buy++) {
        if (buy == 1) {
          dp[i][buy] = Math.max(dp[i + 1][1 - buy] - prices[i], dp[i + 1][buy]);
        } else {
          dp[i][buy] = Math.max(dp[i + 1][1 - buy] + prices[i] - fee, dp[i + 1][buy]);
        }
      }
    }
    return dp[0][1];
  }

  public static void main(String[] args) {
    System.out.println(maxProfitTransaction(new int[]{1, 3, 2, 8, 4, 9}, 2));
  }

}
