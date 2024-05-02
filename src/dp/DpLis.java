package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DpLis {

  public static int lengthOfLIS(int[] nums) {
    Map<String, Integer> map = new HashMap<>();
    return lengthOfLISRec(nums, -1, 0, map);
  }

  public static int lengthOfLISRec(int[] nums, int prevIdx, int idx, Map<String, Integer> map) {
    if (nums.length == idx) {
      return 0;
    }

    String key = idx + "-" + prevIdx;

    if (map.containsKey(key)) {
      return map.get(key);
    }

    int c1 = Integer.MIN_VALUE / 2;
    if (prevIdx == -1 || nums[idx] > nums[prevIdx]) {
      c1 = 1 + lengthOfLISRec(nums, idx, idx + 1, map);
    }

    int c2 = lengthOfLISRec(nums, prevIdx, idx + 1, map);

    int max = Math.max(c1, c2);
    map.put(key, max);
    return max;
  }

  public static int lengthOfLISTab(int[] nums) {
    int n = nums.length;
    if (n == 0) {
      return 0;
    }

    int[] dp = new int[n + 2];

    Arrays.fill(dp, 1);

    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    // Find the maximum value in the dp array, which represents the length of LIS
    int maxLIS = 1;
    for (int len : dp) {
      maxLIS = Math.max(maxLIS, len);
    }

    return maxLIS;
  }

  public static int lengthOfLISTab2D(int[] nums) {
    int n = nums.length;

    int[] currState = new int[n + 1];
    int[] nextState = new int[n + 1];

    for (int curr = n - 1; curr >= 0; curr--) {

      for (int prev = curr - 1; prev >= -1; prev--) {

        int c1 = Integer.MIN_VALUE;
        if (prev == -1 || nums[curr] > nums[prev]) {
          c1 = 1 + nextState[curr + 1];
        }
        int c2 = nextState[prev + 1];

        currState[prev + 1] = Math.max(c1, c2);
        nextState = currState;
      }
    }
    return nextState[0];
  }


  /**
   * https://leetcode.com/problems/largest-divisible-subset/description/
   *
   * Largest Divisible Subset
   */

  public static int lis1D(int[] nums) {
    int n = nums.length;

    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    for (int i = 1; i < n; i++) {

      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], 1 + dp[j]);
        }
      }
    }

    int max = Integer.MIN_VALUE;
    for (int x : dp) {
      max = Math.max(x, max);
    }

    return max;
  }

  /**
   * https://leetcode.com/problems/largest-divisible-subset/submissions/1243265027/
   *
   * Largest Divisible Subset
   */

  public static List<Integer> largestDivisibleSubset(int[] nums) {
    int n = nums.length;
    // Sort the array
    Arrays.sort(nums);
    int[] dp = new int[n];
    int[] hash = new int[n];

    for (int i = 0; i < n; i++) {
      hash[i] = i;
      for (int prev_index = 0; prev_index < i; prev_index++) {
        if (nums[i] % nums[prev_index] == 0 && 1 + dp[prev_index] > dp[i]) {
          dp[i] = 1 + dp[prev_index];
          hash[i] = prev_index;
        }
      }
    }

    int ans = -1;
    int lastIndex = -1;

    for (int i = 0; i < n; i++) {
      if (dp[i] > ans) {
        ans = dp[i];
        lastIndex = i;
      }
    }

    List<Integer> temp = new ArrayList<>();
    temp.add(nums[lastIndex]);

    while (hash[lastIndex] != lastIndex) {
      lastIndex = hash[lastIndex];
      temp.add(nums[lastIndex]);
    }
    return temp;
  }


  /**
   * https://leetcode.com/problems/longest-string-chain/
   *
   * Longest String Chain
   */

  public static int longestStrChain(String[] words) {
    return 0;
  }

  /**
   * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
   *
   * Number of Longest Increasing Subsequence
   */

  public static int findNumberOfLIS(int[] nums) {
    return 0;
  }

  public static void main(String[] args) {
    System.out.println(largestDivisibleSubset(new int[]{3, 17}));
  }

}
