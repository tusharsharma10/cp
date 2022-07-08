package dp;

public class UnboundedKnapsack {


  /**
   * Unbounded Knapsack - multiple occurences of same item are allowed
   */

  public static int unboundedKnapsack(int[] val, int[] wt, int W) {

    int n = val.length;
    int[][] dp = new int[n + 1][W + 1];

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= W; j++) {

        if (j - wt[i - 1] >= 0) {
          dp[i][j] = Math.max(dp[i - 1][j], val[i - 1] + dp[i][j - wt[i - 1]]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return dp[n][W];
  }


  /**
   * Rod Cutting Problem
   */

  public static int cutRod(int val[], int n) {

    int wt[] = new int[n];

    for (int i = 0; i < n; i++) {
      wt[i] = i + 1;
    }

    int[][] dp = new int[n + 1][n + 1];

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= n; j++) {

        if (j - wt[i - 1] >= 0) {
          dp[i][j] = Math.max(dp[i - 1][j], val[i - 1] + dp[i][j - wt[i - 1]]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return dp[n][n];
  }

  /**
   * Rod cutting optimized
   */
  public static int cutRod2(int val[], int n) {

    int[] dp = new int[n + 1];

    for (int i = 1; i <= n; i++) {

      for (int j = i; j > 0; j--) {

        dp[i] = Math.max(dp[i], val[j - 1] + dp[i - j]);
      }
    }

    return dp[n];
  }


  /**
   * Coin change max number of ways https://leetcode.com/problems/coin-change/
   */

  public static int change(int amount, int[] coins) {

    int n = coins.length;
    int[][] dp = new int[n + 1][amount + 1];

    for (int i = 0; i <= n; i++) {
      dp[i][0] = 1;
    }

    for (int j = 1; j <= amount; j++) {
      dp[0][j] = 0;
    }

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= amount; j++) {

        if (j - coins[i - 1] >= 0) {
          dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return dp[n][amount];
  }


  /**
   * https://leetcode.com/problems/coin-change/
   * coin change min number of coins
   */
  public static int coinChangeMinNumberOfCoins(int[] coins, int amount) {
    int n = coins.length;
    int[][] dp = new int[n + 1][amount + 1];

    for (int i = 0; i <= n; i++) {
      dp[i][0] = 0;
    }

    for (int j = 1; j <= amount; j++) {
      dp[0][j] = Integer.MAX_VALUE / 2;
    }

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= amount; j++) {
        if (j - coins[i - 1] >= 0) {
          dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    int x = dp[n][amount];

    if (x >= Integer.MAX_VALUE / 2) {
      return -1;
    }

    return x;
  }


  public static void main(String[] args) {

  }

}
