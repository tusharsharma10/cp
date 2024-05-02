import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DpRevise {

  public static int findWays(int nums[], int tar) {
    int idx = 0;
    int currSum = 0;
    int n = nums.length;
    long[][] dp = new long[n + 1][tar + 1];
    for (int i = 0; i <= n; i++) {
      Arrays.fill(dp[i], -1);
    }
    int MOD = (int) Math.pow(10, 9) + 7;
    long ans = findWaysRec(nums, tar, idx, currSum, dp) % MOD;
    return (int) ans;
  }

  public static long findWaysRec(int nums[], int tar, int idx, int currSum, long[][] dp) {
    int MOD = (int) Math.pow(10, 9) + 7;
    if (currSum == tar) {
      return 1;
    }

    if (idx == nums.length || currSum > tar) {
      return 0;
    }

    if (dp[idx][currSum] != -1) {
      return dp[idx][currSum];
    }

    dp[idx][currSum] =
        (findWaysRec(nums, tar, idx + 1, currSum, dp) + findWaysRec(nums, tar, idx + 1,
            nums[idx] + currSum, dp));

    return dp[idx][currSum];
  }


  static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
    int[][] dp = new int[maxWeight + 1][n + 1];

    for (int i = 0; i <= maxWeight; i++) {
      Arrays.fill(dp[i], -1);
    }

    return knapsackRec(weight, value, n, maxWeight, 0, dp);
  }

  static int knapsackRec(int[] weight, int[] value, int n, int maxWeight, int i, int[][] dp) {

    if (i == n || maxWeight <= 0) {
      return 0;
    }

    if (dp[maxWeight][i] != -1) {
      return dp[maxWeight][i];
    }

    int c1 = Integer.MIN_VALUE;
    int c2;
    if (maxWeight >= weight[i]) {
      c1 = knapsackRec(weight, value, n, maxWeight - weight[i], i + 1, dp) + value[i];
    }
    c2 = knapsackRec(weight, value, n, maxWeight, i + 1, dp);

    dp[maxWeight][i] = Math.max(c1, c2);

    return Math.max(c1, c2);
  }


  /**
   * Converting it into top down dp
   */

  static int knapsackTopDown(int[] weight, int[] value, int n, int maxWeight) {

    int[][] dp = new int[n + 1][maxWeight + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= maxWeight; j++) {
        if (j >= weight[i - 1]) {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return dp[n][maxWeight];

  }


  public static boolean subsetSumToK(int n, int k, int arr[]) {
    Boolean[][] dp = new Boolean[n][k];
    return subsetSumToKRec(n, k, arr, 0, 0, dp);

  }


  public static boolean subsetSumToKRec(int n, int k, int arr[], int i, int sum, Boolean[][] dp) {
    if (sum == k) {
      return true;
    }
    if (i == n || sum > k) {
      return false;
    }

    if (dp[i][sum] != null) {
      return dp[i][sum];
    }

    dp[i][sum] = subsetSumToKRec(n, k, arr, i + 1, sum, dp) || subsetSumToKRec(n, k, arr, i + 1,
        sum + arr[i], dp);

    return dp[i][sum];

  }

  /**
   *
   */

  public static boolean subsetSumToKTopDown(int n, int k, int arr[]) {

    Boolean[][] dp = new Boolean[n + 1][k + 1];
    dp[0][0] = true;

    for (int i = 1; i <= k; i++) {
      dp[0][i] = false;
    }

    for (int i = 1; i <= n; i++) {
      dp[i][0] = true;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= k; j++) {
        if (arr[i - 1] > j) {
          // Exclude the current element
          dp[i][j] = dp[i - 1][j];
        } else {
          // Exclude or include the current element
          dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
        }
      }
    }
    return dp[n][k];
  }


  public static int findTargetSumWays(int[] nums, int target) {
    Map<String, Integer> memo = new HashMap<>();
    return findTargetSumWaysRec(nums, target, 0, 0, memo);
  }

  public static int findTargetSumWaysRec(int[] nums, int target, int currSum, int idx,
      Map<String, Integer> memo) {

    // Base cases
    if (idx == nums.length) {
      if (currSum == target) {
        return 1;
      } else {
        return 0;
      }
    }

    String key = idx + "-" + currSum;

    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    int c1 = findTargetSumWaysRec(nums, target, nums[idx] + currSum, idx + 1, memo);
    int c2 = findTargetSumWaysRec(nums, target, -nums[idx] + currSum, idx + 1, memo);

    memo.put(key, c1 + c2);
    return memo.get(key);

  }


  public static int cutRod(int price[], int n) {
    Map<String, Integer> map = new HashMap<>();
    int[] rodLength = new int[n];
    for (int i = 0; i < rodLength.length; i++) {
      rodLength[i] = i + 1;
    }
    return cutRodRec(price, n, 0, map, rodLength);
  }

  public static int cutRodRec(int price[], int len, int idx, Map<String, Integer> map,
      int[] rodLength) {

    if (len == 0) {
      return 0;
    }

    if (len < 0) {
      return Integer.MIN_VALUE;
    }

    if (idx == price.length) {
      if (len == 0) {
        return 0;
      } else {
        return Integer.MIN_VALUE;
      }
    }

    String key = idx + "-" + len;

    if (map.containsKey(key)) {
      return map.get(key);
    }

    int c1 = cutRodRec(price, len - rodLength[idx], idx + 1, map, rodLength) + price[idx];
    int c3 = cutRodRec(price, len - rodLength[idx], idx, map, rodLength) + price[idx];
    int c2 = cutRodRec(price, len, idx + 1, map, rodLength);

    map.put(key, Math.max(c1, Math.max(c2, c3)));
    return map.get(key);
  }

  public static long countWaysToMakeChange(int denominations[], int value) {
    Map<String, Long> map = new HashMap<>();
    long ways = countWaysToMakeChangeRec(denominations, value, 0, map);
    return ways;
  }

  public static long countWaysToMakeChangeRec(int denominations[], int value, int idx,
      Map<String, Long> map) {

    if (value == 0) {
      return 1;
    }

    if (idx == denominations.length) {
      return 0;
    }

    String key = idx + "-" + value;
    if (map.containsKey(key)) {
      return map.get(key);
    }

    long c1 = 0;

    if (value >= denominations[idx]) {
      c1 = countWaysToMakeChangeRec(denominations, value - denominations[idx], idx, map);
    }
    long c3 = countWaysToMakeChangeRec(denominations, value, idx + 1, map);

    long max = c1 + c3;

    map.put(key, max);
    return max;
  }


  public static int coinChangeMin(int[] coins, int amount) {
    Map<String, Integer> map = new HashMap<>();
    int ways = coinChangeMinRec(coins, amount, 0, map);
    if (ways >= Integer.MAX_VALUE / 2) {
      return -1;
    }
    return ways;
  }

  public static int coinChangeMinRec(int[] coins, int amount, int idx, Map<String, Integer> map) {

    if (amount == 0) {
      return 0;
    }

    if (idx == coins.length) {
      return Integer.MAX_VALUE / 2;
    }

    String key = idx + "-" + amount;

    if (map.containsKey(key)) {
      return map.get(key);
    }

    int c1 = Integer.MAX_VALUE / 2;

    if (amount >= coins[idx]) {
      int val = amount - coins[idx];
      c1 = 1 + coinChangeMinRec(coins, val, idx, map);
    }

    int c2 = coinChangeMinRec(coins, amount, idx + 1, map);

    int min = Math.min(c1, c2);

    map.put(key, min);
    return min;

  }


  public static int getLengthOfLCS(String str1, String str2) {
    Map<String, Integer> map = new HashMap<>();
    return getLengthOfLCSRec(str1, str2, str1.length() - 1, str2.length() - 1, map);
  }

  public static int getLengthOfLCSRec(String str1, String str2, int n, int m,
      Map<String, Integer> map) {
    if (n < 0 || m < 0) {
      return 0;
    }

    String key = n + "-" + m;

    if (map.containsKey(key)) {
      return map.get(key);
    }

    int c1 = Integer.MIN_VALUE;
    int c2 = Integer.MIN_VALUE;
    int c3 = Integer.MIN_VALUE;
    if (str1.charAt(n) == str2.charAt(m)) {
      c1 = 1 + getLengthOfLCSRec(str1, str2, n - 1, m - 1, map);
    } else {
      c2 = getLengthOfLCSRec(str1, str2, n, m - 1, map);
      c3 = getLengthOfLCSRec(str1, str2, n - 1, m, map);
    }

    int max = Math.max(c1, Math.max(c2, c3));
    map.put(key, max);
    return max;
  }


  public static int canYouMake(String s1, String s2) {
    int lcsLen = lcs(s1, s2);
    return s1.length() + s2.length() - (2 * lcsLen);
  }

  public static int lcs(String str1, String str2) {

    int n = str1.length();
    int m = str2.length();

    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= m; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[n][m];
  }


  public static int longestPalindromeSubsequence(String s) {
    StringBuilder str = new StringBuilder(s);
    return lcs(s, str.reverse().toString());
  }

  public static String shortestSupersequence(String a, String b) {
    return null;
  }

  public static int longestRepeatingSubsequence(String st, int n) {

    int[][] dp = new int[n + 1][n + 1];
    String t = st.substring(0, st.length());

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (st.charAt(i - 1) == t.charAt(j - 1) && i != j) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[n][n];
  }


  public static int lcSubstring(String str1, String str2) {
    int n = str1.length();
    int m = str2.length();
    Map<String, Integer> map = new HashMap<>();
    int len = lcSubstringRec(str1, str2, n - 1, m - 1, 0, map);
    return len;
  }

  private static int lcSubstringRec(String str1, String str2, int n, int m, int res,
      Map<String, Integer> map) {

    if (n < 0 || m < 0) {
      return res;
    }

    /*String key = n + "-" + m;

    if (map.containsKey(key)) {
      return map.get(key);
    }*/

    if (str1.charAt(n) == str2.charAt(m)) {
      res = lcSubstringRec(str1, str2, n - 1, m - 1, res + 1, map);
    }
    int c2 = lcSubstringRec(str1, str2, n, m - 1, 0, map);
    int c3 = lcSubstringRec(str1, str2, n - 1, m, 0, map);

    int max = Math.max(res, Math.max(c2, c3));

    // map.put(key, max);

    return max;
  }


  private static int lcSubstringBottomUp(String str1, String str2) {

    int n = str1.length();
    int m = str2.length();

    int[][] dp = new int[n + 1][m + 1];

    int max = 0;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = 0;
        }
        max = Math.max(max, dp[i][j]);
      }
    }
    return max;
  }


  /**
   * https://leetcode.com/problems/edit-distance/description/
   */

  public static int minDistance(String word1, String word2) {
    Map<String, Integer> map = new HashMap<>();
    return minDistanceRec(word1, word2, word1.length(), word2.length(), map);
  }

  public static int minDistanceRec(String word1, String word2, int n, int m,
      Map<String, Integer> map) {

    if (m == 0) {
      return n;
    }
    if (n == 0) {
      return m;
    }

    String key = n + "-" + m;

    if (map.containsKey(key)) {
      return map.get(key);
    }

    // If the last characters of the strings are the same, no operation is needed
    if (word1.charAt(n - 1) == word2.charAt(m - 1)) {
      return minDistanceRec(word1, word2, n - 1, m - 1, map);
    }

    int insert = minDistanceRec(word1, word2, n, m - 1, map) + 1;
    int delete = minDistanceRec(word1, word2, n - 1, m, map) + 1;
    int replace = minDistanceRec(word1, word2, n - 1, m - 1, map) + 1;

    int ans = Math.min(Math.min(insert, delete), replace);
    map.put(key, ans);
    return ans;

  }

  /**
   * Perfect
   *
   * https://leetcode.com/problems/edit-distance/submissions/1242987108/
   */

  public static int minDistanceBotUp(String word1, String word2) {
    int n = word1.length();
    int m = word2.length();

    int[][] dp = new int[n + 1][m + 1];

    for (int i = 0; i <= n; i++) {
      dp[i][0] = i;
    }

    for (int i = 0; i <= m; i++) {
      dp[0][i] = i;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
        }
      }
    }

    return dp[n][m];
  }


  /**
   * https://www.interviewbit.com/problems/distinct-subsequences/
   */

  public int numDistinct(String A, String B) {
    return countDistinctSubsequences(A, B, A.length(), B.length());
  }

  private int countDistinctSubsequences(String A, String B, int m, int n) {
    // If B is empty, there's only one way to form a subsequence in A, which is to delete all characters
    if (n == 0) {
      return 1;
    }
    // If A is empty but B is not, there's no way to form a subsequence in A that is identical to B
    if (m == 0) {
      return 0;
    }

    // If the last characters of A and B match, we have two choices:
    // 1. Include the last character of A and continue matching the remaining substrings
    // 2. Exclude the last character of A and match the remaining substring in B
    if (A.charAt(m - 1) == B.charAt(n - 1)) {
      return countDistinctSubsequences(A, B, m - 1, n - 1) + countDistinctSubsequences(A, B, m - 1,
          n);
    } else {
      // If the last characters of A and B don't match, we can only exclude the last character of A
      return countDistinctSubsequences(A, B, m - 1, n);
    }
  }

  public static int numDistinctTab(String A, String B) {
    int m = A.length();
    int n = B.length();

    int[][] dp = new int[m + 1][n + 1];

    // m == 0 then 0
    for (int j = 0; j <= n; j++) {
      dp[0][j] = 0;
    }

    // n == 0 then 1
    for (int i = 0; i <= m; i++) {
      dp[i][0] = 1;
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (A.charAt(i - 1) == B.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
        } else {
          // If the last characters of A and B don't match, we can only exclude the last character of A
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[m][n];
  }


  public int maxProfit(final List<Integer> A) {
    int buy = 1;
    return maxProfitRec(A, 1, 0);
  }

  public int maxProfitRec(List<Integer> A, int buy, int idx) {
    if (idx == A.size()) {
      return 0;
    }

    int profit = 0;
    if (buy == 1) {
      int letsBuy = -A.get(idx) + maxProfitRec(A, 0, idx + 1);
      int skip = maxProfitRec(A, 1, idx + 1);
      profit = Math.max(letsBuy, skip);
    } else {
      int sell = A.get(idx) + maxProfitRec(A, 1, idx + 1);
      int skip = maxProfitRec(A, 0, idx + 1);
      profit = Math.max(sell, skip);
    }

    return profit;
  }

  public int maxProfitTab(List<Integer> A) {
    int n = A.size();
    int[][] dp = new int[n + 1][2];

    for (int i = n - 1; i >= 0; i--) {
      for (int buy = 1; buy >= 0; buy--) {
        if (buy == 1) {
          dp[i][buy] = Math.max(dp[i + 1][1 - buy] - A.get(i), dp[i + 1][buy]);
        } else {
          dp[i][buy] = Math.max(dp[i + 1][1 - buy] + A.get(i), dp[i + 1][buy]);
        }
      }
    }
    return dp[0][1];
  }


  public static int cutRod2(int price[], int n) {
    int[] rod = new int[n];

    for (int i = 0; i < rod.length; i++) {
      rod[i] = i + 1;
    }

    return cutRodRec(price, rod, 0, n);
  }

  public static int cutRodRec(int price[], int[] rod, int idx, int remaining) {

    if (remaining == 0) {
      return 0;
    }

    if (remaining < 0) {
      return Integer.MIN_VALUE;
    }

    if (idx == price.length) {
      if (remaining == 0) {
        return 0;
      } else {
        return Integer.MIN_VALUE;
      }
    }

    int c1 = price[idx] + cutRodRec(price, rod, idx, remaining - rod[idx]);
    int c2 = price[idx] + cutRodRec(price, rod, idx + 1, remaining - rod[idx]);
    int c3 = cutRodRec(price, rod, idx + 1, remaining);

    int max = Math.max(c1, Math.max(c2, c3));
    return max;

  }


  public static void main(String[] args) {

    System.out.println(minDistance("intention", "execution"));

    //System.out.println(knapsack(new int[]{1, 2, 4, 5}, new int[]{5, 4, 8, 6}, 4, 5));

    // System.out.println(subsetSumToK(5, 4, new int[]{2, 5, 1, 6, 7}));

    /*System.out.println(findWays(
        new int[]{7, 7, 7, 9, 1, 4, 4, 7, 8, 2, 10, 3, 9, 8, 1, 9, 0, 1, 2, 8, 7, 3, 5, 3, 9, 8, 9,
            7, 8, 3, 2, 4, 2, 6, 10, 1, 6, 4, 10, 5, 3, 7, 1, 6, 5, 6, 1, 8, 6, 7, 5, 1, 2, 3, 5, 2,
            9, 10, 3, 1, 2, 10, 5, 7, 10, 2, 7, 9, 3, 4, 2, 8, 10, 5,
        }, 53));
*/

    //System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));

    //System.out.println(cutRod(new int[]{42, 68, 35, 1, 70}, 5));
    //System.out.println(countWaysToMakeChange(new int[]{1, 2, 3}, 4));

    //System.out.println(lcSubstringBottomUp("abcjklp", "acjkp"));
  }


}
