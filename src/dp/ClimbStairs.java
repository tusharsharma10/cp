package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClimbStairs {


  /**
   * https://www.naukri.com/code360/problems/count-ways-to-reach-the-n-th-stairs_798650?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
   *
   * You are supposed to return the number of distinct ways you can climb from the 0th step to the
   * Nth step.
   */

  public static long countDistinctWayToClimbStair(long nStairs) {
    Map<String, Long> map = new HashMap<>();
    return countDistinctWayToClimbStairRec(nStairs, map);
  }


  public static long countDistinctWayToClimbStairRec(long nStairs,
      Map<String, Long> map) {
    if (nStairs == 1 || nStairs == 0) {
      return 1;
    }

    String key = String.valueOf(nStairs);

    if (map.containsKey(key)) {
      return map.get(key);
    }

    long c1 = countDistinctWayToClimbStairRec(nStairs - 1, map);
    long c2 = countDistinctWayToClimbStairRec(nStairs - 2, map);

    long ans = c1 + c2;
    map.put(key, ans);
    return ans;
  }

  public static long climbStairBottomUp(long nStairs) {

    if (nStairs == 0 || nStairs == 1) {
      return 1;
    }
    if (nStairs == 2) {
      return 2;
    }

    long[] dp = new long[(int) nStairs + 1];
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 2;

    for (int i = 3; i <= nStairs; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[(int) nStairs];
  }

  /**
   * There is a frog on the '1st' step of an 'N' stairs long staircase. The frog wants to reach the
   * 'Nth' stair. 'HEIGHT[i]' is the height of the '(i+1)th' stair.If Frog jumps from 'ith' to 'jth'
   * stair, the energy lost in the jump is given by absolute value of ( HEIGHT[i-1] - HEIGHT[j-1] ).
   * If the Frog is on 'ith' staircase, he can jump either to '(i+1)th' stair or to '(i+2)th' stair.
   * Your task is to find the minimum total energy used by the frog to reach from '1st' stair to
   * 'Nth' stair.
   */

  public static int frogJump(int nStairs, int heights[]) {

    if (nStairs == 0) {
      return 0;
    }

    int[] dp = new int[nStairs];
    dp[0] = 0;
    dp[1] = Math.abs(heights[0] - heights[1]);

    for (int i = 2; i < nStairs; i++) {
      int c1 = dp[i - 2] + Math.abs(heights[i - 2] - heights[i]);
      int c2 = dp[i - 1] + Math.abs(heights[i - 1] - heights[i]);
      dp[i] = Math.min(c1, c2);
    }
    return dp[nStairs - 1];
  }

  /**
   * https://www.naukri.com/code360/problems/minimal-cost_8180930?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=SUBMISSION
   */

  public static int minimizeCost(int nStairs, int k, int[] heights) {
    if (nStairs == 0 || nStairs == 1) {
      return 0;
    }

    int[] dp = new int[nStairs];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    dp[1] = Math.abs(heights[0] - heights[1]);

    for (int i = 2; i < nStairs; i++) {

      for (int j = 1; j <= Math.min(i, k); j++) {
        int c1 = dp[i - j] + Math.abs(heights[i - j] - heights[i]);
        dp[i] = Math.min(c1, dp[i]);

      }


    }
    return dp[nStairs - 1];
  }

  public static void main(String[] args) {
    System.out.println(minimizeCost(4, 2, new int[]{10, 40, 30, 10}));
  }

}
