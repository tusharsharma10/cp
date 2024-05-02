package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DpOnGrids {

  /**
   * https://www.naukri.com/code360/problems/ninja%E2%80%99s-training_3621003?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
   */

  public static int ninjaTraining(int n, int points[][]) {

    boolean[] canPractice = new boolean[3];
    Arrays.fill(canPractice, true);
    int[][] dp = new int[n + 1][3];
    dp[1][0] = points[0][0];
    dp[1][1] = points[0][1];
    dp[1][2] = points[0][2];

    int[] res = new int[n + 1];
    res[1] = Math.max(dp[1][0], Math.max(dp[1][1], dp[1][2]));

    for (int i = 2; i <= n; i++) {
      dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][2]) + points[i - 1][0];
      dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + points[i - 1][1];
      dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]) + points[i - 1][2];

      res[i] = Math.max(dp[i][0], Math.max(dp[i][1], dp[i][2]));

    }

    return res[n];
  }

  /**
   * https://leetcode.com/problems/unique-paths/description/
   */

  public static int uniquePaths(int m, int n) {
    return uniquePathsRec(0, 0, m, n);
  }

  private static int uniquePathsRec(int x, int y, int m, int n) {
    if (x == m - 1 && y == n - 1) {
      return 1;
    }
    if (x >= m || y >= n) {
      return 0;
    }
    int c1 = uniquePathsRec(x + 1, y, m, n);
    int c2 = uniquePathsRec(x, y + 1, m, n);
    return c1 + c2;
  }

  private static int uniquePathsTab(int m, int n) {

    int[][] dp = new int[m + 1][n + 1];

    // Initialize the rightmost column to 1
    for (int i = 0; i < m; i++) {
      dp[i][n - 1] = 1;
    }

    // Initialize the bottom row to 1
    for (int j = 0; j < n; j++) {
      dp[m - 1][j] = 1;
    }

    for (int i = m - 2; i >= 0; i--) {
      for (int j = n - 2; j >= 0; j--) {
        dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
      }
    }

    return dp[0][0];

  }


  private static int uniquePathsBotUp(int m, int n) {
    int[][] dp = new int[m][n];

    // Initialize the rightmost column to 1
    for (int i = 0; i < m; i++) {
      dp[i][n - 1] = 1;
    }

    // Initialize the bottom row to 1
    for (int j = 0; j < n; j++) {
      dp[m - 1][j] = 1;
    }

    // Fill the rest of the grid using bottom-up approach
    for (int i = m - 2; i >= 0; i--) {
      for (int j = n - 2; j >= 0; j--) {
        dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
      }
    }

    return dp[0][0];
  }


  /**
   * https://leetcode.com/problems/coin-change/
   */

  public int coinChange(int[] coins, int amount) {
    Map<String, Integer> map = new HashMap<>();
    int ans = coinChangeRec(coins, amount, 0, map);
    if (ans >= Integer.MAX_VALUE / 2) {
      return -1;
    }
    return ans;
  }

  private int coinChangeRec(int[] coins, int amount, int idx, Map<String, Integer> map) {

    if (idx >= coins.length) {
      return Integer.MAX_VALUE / 2;
    }

    if (amount <= 0) {
      if (amount == 0) {
        return 0;
      } else {
        return Integer.MAX_VALUE / 2;
      }
    }

    String key = idx + "-" + amount;

    if (map.containsKey(key)) {
      return map.get(key);
    }

    int c1 = 1 + coinChangeRec(coins, amount - coins[idx], idx, map);
    int c2 = 1 + coinChangeRec(coins, amount - coins[idx], idx + 1, map);
    int c3 = coinChangeRec(coins, amount, idx + 1, map);

    int min = Math.min(c1, Math.min(c2, c3));
    map.put(key, min);
    return min;
  }

  /**
   * Unbounded Knapsack pattern
   */

  private int coinChangeBotUp(int[] coins, int amount) {

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


  public static int upRecTab(int[][] mat) {

    int m = mat.length;
    int n = mat[0].length;
    int[][] dp = new int[m][n];

    // Base case: Initialize the bottom row and rightmost column
    if (mat[m - 1][n - 1] == 0) {
      dp[m - 1][n - 1] = 1;
    } else {
      dp[m - 1][n - 1] = 0;
    }

    for (int i = m - 2; i >= 0; i--) {
      if (mat[i][n - 1] == 0 && dp[i + 1][n - 1] == 1) {
        dp[i][n - 1] = 1;
      } else {
        dp[i][n - 1] = 0;
      }
    }

    for (int j = n - 2; j >= 0; j--) {
      if (mat[m - 1][j] == 0 && dp[m - 1][j + 1] == 1) {
        dp[m - 1][j] = 1;
      } else {
        dp[m - 1][j] = 0;
      }
    }

    // Fill the dp table using dynamic programming
    for (int i = m - 2; i >= 0; i--) {
      for (int j = n - 2; j >= 0; j--) {
        if (mat[i][j] == 0) {
          dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
        } else {
          dp[i][j] = 0;
        }
      }
    }

    return dp[0][0];
  }


  public static int jump(int[] arr) {
    Map<String, Integer> map = new HashMap<>();
    return minJumpsRec(arr, 0, map);
  }

  private static int minJumpsRec(int[] arr, int idx, Map<String, Integer> map) {
    if (idx >= arr.length) {
      return 0;
    }

    String key = String.valueOf(idx);

    if (map.containsKey(key)) {
      return map.get(key);
    }

    int maxJumps = arr[idx];
    int minCount = Integer.MAX_VALUE;
    for (int i = 1; i <= maxJumps; i++) {
      int newIndex = idx + arr[i];
      minCount = Math.min(minCount, 1 + minJumpsRec(arr, newIndex, map));
    }

    map.put(key, minCount);

    return minCount;
  }

  public static boolean canJump(int[] arr) {
    int maxReachableIndex = 0;
    for (int i = 0; i < arr.length; i++) {
      // moment this happens we know that we are unable to reach i and hence we return false
      // this means we are either going back or not moving at all.
      if (i > maxReachableIndex) {
        return false;
      }

      maxReachableIndex = Math.max(maxReachableIndex, i + arr[i]);
      if (maxReachableIndex >= arr.length - 1) {
        return true;
      }
    }
    return true;
  }

  private static int minJumpsTab(int[] arr) {
    int minJumps = 0;
    int n = arr.length;
    int r = 0;
    int l = 0;

    while (r < n - 1) {
      int farthest = 0;
      for (int i = l; i <= r; i++) {
        farthest = Math.max(farthest, i + arr[i]);
      }
      l = r + 1;
      r = farthest;
      if (l > r) {
        return -1;
      }
      minJumps += 1;
    }
    return minJumps;
  }


  public static int[] missingAndRepeating(int[] nums, int n) {
    int[] ans = new int[2];

    // Phase 1
    for (int i = 0; i < n; i++) {
      int idx = Math.abs(nums[i]) - 1;
      if (nums[idx] > 0) {
        nums[idx] = -nums[idx];
      } else if (nums[idx] < 0) {
        ans[1] = idx + 1;
      }
    }

    for (int i = 0; i < n; i++) {
      if (nums[i] > 0) {
        ans[0] = i + 1;
        break;
      }
    }

    return ans;

  }


  public static void main(String[] args) {
  }

}

class Interval {

  int start;
  int end;

  Interval(int s, int e) {
    start = s;
    end = e;
  }
}