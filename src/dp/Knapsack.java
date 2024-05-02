package dp;

import java.util.Arrays;


public class Knapsack {


  public static void main(String[] args) {
    System.out.println(findTargetSumWays2(new int[]{9, 7, 0, 3, 9, 8, 6, 5, 7, 6}, 31));
  }

  /**
   * Knapsack recursive returns profit
   */

  public static int knapSack(int W, int wt[], int val[], int n) {

    if (n == 0 || W <= 0) {
      return 0;
    }

    int res1 = 0;
    if (W - wt[n] >= 0) {
      res1 = val[n] + knapSack(W - wt[n], wt, val, n - 1);
    }

    int res2 = knapSack(W, wt, val, n - 1);

    return Math.max(res1, res2);
  }

  /**
   * Knapsack DP
   */

  public static int knapSackDP(int W, int wt[], int val[], int n) {

    int dp[][] = new int[n + 1][W + 1];

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= W; j++) {
        if (j - wt[i - 1] >= 0) {
          dp[i][j] = Math.max(dp[i - 1][j], val[i - 1] + dp[i - 1][j - wt[i - 1]]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return dp[n][W];
  }

  /**
   * Count of subset with a given sum
   */

  public static int countSubset(int[] nums, int sum) {

    int numZeroes = 0;

    for (int i = 0; i < nums.length; i++) {

      sum += nums[i];

      if (nums[i] == 0) {
        numZeroes++;
      }
    }

    int n = nums.length;
    int[][] dp = new int[n + 1][sum + 1];

    for (int i = 0; i <= n; i++) {
      dp[i][0] = 1;
    }

    for (int i = 1; i <= sum; i++) {
      dp[0][i] = 0;
    }

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= sum; j++) {
        if (j - nums[i - 1] >= 0) {
          dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }

    }

    return dp[n][sum] * (int) Math.pow(2, numZeroes);
  }

  /**
   * Subset Sum
   */

  public boolean subsetSum(int[] nums, int sum) {

    int n = nums.length;
    boolean[][] dp = new boolean[n + 1][sum + 1];

    for (int i = 0; i <= n; i++) {
      dp[i][0] = true;
    }

    for (int i = 1; i <= sum; i++) {
      dp[0][i] = false;
    }

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= sum; j++) {
        if (j - nums[i - 1] >= 0) {
          dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return dp[n][sum];
  }

  /**
   * https://leetcode.com/problems/target-sum/submissions/
   */
  public static int findTargetSumWays2(int[] nums, int target) {

    int sum = 0;
    int n = nums.length;

    int numZeroes = 0;

    for (int i = 0; i < nums.length; i++) {

      sum += nums[i];

      if (nums[i] == 0) {
        numZeroes++;
      }
    }

    double x = (target + sum) / 2;

    if (x < 0) {
      return 0;
    }

    if (target > sum || (target + sum) % 2 != 0) {
      return 0;
    }

    int s1 = (int) Math.ceil(x);

    // find no. of ways in which s1 exists using count of subsets
    int dp[][] = new int[n + 1][s1 + 1];

    // base case when sum to be formed is 0 there lies one way by not picking any
    for (int i = 0; i <= n; i++) {
      dp[i][0] = 1;
    }

    // base case when empty set is taken and sum to be formed is 1,2,3.....
    for (int j = 1; j <= s1; j++) {
      dp[0][j] = 0;
    }

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= s1; j++) {

        // no choice but to exclude current element
        if (nums[i - 1] > j || nums[i - 1] == 0) {
          dp[i][j] = dp[i - 1][j];
        }
        // sum of all ways of excluding and including of element
        else {
          dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
        }

      }
    }
    return dp[n][s1] * (int) Math.pow(2, numZeroes);
  }

  /**
   * Equal Sum partition
   */

  public boolean canPartition(int[] nums) {

    int sum = 0;
    for (int x : nums) {
      sum += x;
    }

    if (sum % 2 != 0) {
      return false;
    }

    return subsetSum(nums, sum / 2);

  }


  /**
   * https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
   */

  public static int minimumDifference(int[] nums) {
    int sum = 0;
    int n = nums.length;
    for (int x : nums) {
      sum += Math.abs(x);
    }

    boolean[][] dp = subsetSumArr(nums, sum);

    int min = Integer.MAX_VALUE;

    for (int i = 0; i <= sum / 2; i++) {

      if (dp[n][i]) {
        min = Math.min(min, Math.abs(sum - 2 * i));
      }

    }

    return min;
  }


  public static boolean[][] subsetSumArr(int[] nums, int sum) {
    int n = nums.length;
    boolean[][] dp = new boolean[n + 1][sum + 1];

    for (int i = 0; i <= n; i++) {
      dp[i][0] = true;
    }

    for (int i = 1; i <= sum; i++) {
      dp[0][i] = false;
    }

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= sum; j++) {
        if (j - Math.abs(nums[i - 1]) >= 0) {
          dp[i][j] = dp[i - 1][j] || dp[i - 1][j - Math.abs(nums[i - 1])];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return dp;
  }

  /**
   * Count the number of subsets of given
   */

  public static int countSubsetSumsWithDiff(int[] nums, int diff) {

    int sum = 0;
    for (int x : nums) {
      sum += x;
    }

    int val = (sum + diff) / 2;

    // find no. of subsets with sum = val

    int x = countSubset(nums, val);
    return x;
  }


  /**
   * https://leetcode.com/problems/coin-change-2/submissions/
   */
  public int coinChangeWays(int[] coins, int amount) {

    int ways = 0;

    int dp[] = new int[amount + 1];

    dp[0] = 1;

    for (int i = 0; i < coins.length; i++) {
      for (int j = 0; j < dp.length; j++) {
        if (coins[i] > j) {
          continue;
        }

        dp[j] += dp[j - coins[i]];
      }
    }

    return dp[dp.length - 1];
  }

  /**
   * https://leetcode.com/problems/coin-change/
   */

  public static int coinChangeMinCount(int[] coins, int amount) {

    int dp[] = new int[amount + 1];

    Arrays.fill(dp, Integer.MAX_VALUE / 2);

    dp[0] = 0;

    for (int i = 0; i < coins.length; i++) {

      for (int j = 0; j < dp.length; j++) {
        if (j - coins[i] >= 0) {
          dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
        }
      }
    }

    for (int i = 0; i < dp.length; i++) {
      if (dp[i] == Integer.MAX_VALUE / 2) {
        dp[i] = -1;
      }
    }

    //Util.printArr(dp);
    return dp[dp.length - 1];
  }

  /**
   * https://leetcode.com/problems/target-sum/
   */

  public int findTargetSumWays(int[] nums, int target) {
    return 1;
  }

}
