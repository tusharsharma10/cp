package dp;

import java.util.*;

/**
 * https://leetcode.com/problems/house-robber/description/
 */

public class HouseRobber {


  public static int rob1(int[] nums) {
    Map<String, Integer> map = new HashMap<>();
    return rob1Rec(nums, nums.length - 1, map);
  }

  private static int rob1Rec(int[] nums, int posn, Map<String, Integer> map) {
    if (posn < 0) {
      return 0;
    }

    String key = String.valueOf(posn);
    if (map.containsKey(key)) {
      return map.get(key);
    }

    int c1 = nums[posn] + rob1Rec(nums, posn - 2, map);
    int c2 = rob1Rec(nums, posn - 1, map);

    int max = Math.max(c1, c2);
    map.put(key, max);
    return max;
  }

  /**
   * https://leetcode.com/problems/house-robber/submissions/1242914935/
   */

  private static int rob1BottomUp(int[] nums, int posn, Map<String, Integer> map) {
    int n = nums.length;
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MIN_VALUE);
    dp[0] = 0;
    dp[1] = nums[0];

    for (int i = 2; i <= n; i++) {
      int c1 = dp[i - 1];
      int c2 = dp[i - 2] + nums[i - 1];
      dp[i] = Math.max(c1, c2);
    }

    return dp[n];
  }

  /****************************************************************************************************************************/

  /**
   * https://leetcode.com/problems/house-robber-ii/
   */

  public static int rob2(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n + 1];
    int[] dp2 = new int[n + 1];
    Arrays.fill(dp2, Integer.MIN_VALUE);
    Arrays.fill(dp, Integer.MIN_VALUE);
    dp[0] = 0;
    dp2[0] = 0;

    dp[1] = nums[0];
    for (int i = 2; i <= n; i++) {
      if (i == n) {
        dp[i] = dp[i - 1];
        continue;
      }
      int c1 = dp[i - 1];
      int c2 = dp[i - 2] + nums[i - 1];
      dp[i] = Math.max(c1, c2);
    }

    dp2[1] = 0;
    for (int i = 2; i <= n; i++) {
      int c1 = dp2[i - 1];
      int c2 = dp2[i - 2] + nums[i - 1];
      dp2[i] = Math.max(c1, c2);
    }

    return Math.max(dp[n], dp2[n]);
  }

  public static void main(String[] args) {
    System.out.println(rob2(new int[]{2, 3, 2}));
  }

}
