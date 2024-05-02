package dp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixChainMultiplication {

  /**
   * Input: N = 5
   * arr = {40, 20, 30, 10, 30}
   * Output: 26000
   * Explanation: There are 4 matrices of dimension
   * 40x20, 20x30, 30x10, 10x30. Say the matrices are
   * named as A, B, C, D. Out of all possible combinations,
   * the most efficient way is (A*(B*C))*D.
   * The number of operations are -
   * 20*30*10 + 40*20*10 + 40*10*30 = 26000.
   */

  /**
   * https://practice.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1
   */

  public static int matrixMultiplication(int N, int arr[]) {

    return mcmDp(1, N - 1, arr, N, new int[N][N]);
  }

  /**
   * MCM recursion approach
   */
  public static int mcmRec(int i, int j, int[] arr, int N) {

    if (i >= j) {
      return 0;
    }

    int min = Integer.MAX_VALUE;

    for (int k = i; k < j; k++) {
      int temp = mcmRec(i, k, arr, N) + mcmRec(k + 1, j, arr, N) + arr[i - 1] * arr[k] * arr[j];
      min = Math.min(min, temp);
    }

    return min;
  }

  /**
   * MCM DP
   */
  public static int mcmDp(int i, int j, int[] arr, int N, int[][] dp) {

    if (i >= j) {
      return 0;
    }

    int min = Integer.MAX_VALUE;

    for (int k = i; k < j; k++) {
      if (dp[i][k] == 0) {
        dp[i][k] = mcmDp(i, k, arr, N, dp);
      }
      if (dp[k + 1][j] == 0) {
        dp[k + 1][j] = mcmDp(k + 1, j, arr, N, dp);
      }
      int temp = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
      min = Math.min(min, temp);
    }

    return min;

  }

  /**
   * https://leetcode.com/problems/partition-array-for-maximum-sum/
   */

  public static int maxSumAfterPartitioning(int[] arr, int k) {
    int N = arr.length;
    int[][] dp = new int[N][N];
    return maxSumAfterPartitioningDp(0, N - 1, arr, N, k, dp);
  }

  private static int maxSumAfterPartitioningDp(int i, int j, int[] arr, int N, int width,
      int[][] dp) {

    if (i >= j) {
      return 0;
    }

    int max = Integer.MIN_VALUE;

    for (int k = i; k <= width && k < j; k++) {
      if (dp[i][k] == 0) {
        dp[i][k] = maxSumAfterPartitioningDp(i, k, arr, N, width, dp);
      }
      if (dp[k + 1][j] == 0) {
        dp[k + 1][j] = maxSumAfterPartitioningDp(k + 1, j, arr, N, width, dp);
      }
      int temp = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
      max = Math.min(max, temp);
    }

    return max;

  }

  private static int maxSumAfterPartitioningDp(int[] arr, int k) {

    int n = arr.length;
    int[] dp = new int[n + 1];
    // base case
    dp[0] = 0;

    for (int i = 1; i < n + 1; i++) {
      // calculating the max element accounted so far for each window
      int maxEle = arr[i - 1];
      // calculating the max sum accumulated so far
      int maxSum = arr[i - 1];

      for (int j = i; j >= i - k + 1 && j >= 1; j--) {
        // dp has len=n+1 while arr has len=n, so j-1 is used
        maxEle = Math.max(maxEle, arr[j - 1]);
        // same logic for j-1 and window size is (i-j+1)
        int width = i - j + 1;
        maxSum = Math.max(maxSum, dp[j - 1] + maxEle * width);
      }
      dp[i] = maxSum;
    }
    return dp[n];

  }


  /**
   * Palindrome Partitioning
   *
   * Input: str = "ababbbabbababa" Output: 3 Explaination: After 3 partitioning substrings are "a",
   * "babbbab", "b", "ababa".
   *
   * Given a string str, a partitioning of the string is a palindrome partitioning if every
   * sub-string of the partition is a palindrome. Determine the fewest cuts needed for palindrome
   * partitioning of the given string.
   */

  public static int palindromicPartition(String str) {

    int[][] dp = new int[str.length()][str.length()];

    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }

    //int ans = palindromicPartitionRec(str, 0, str.length() - 1);
    int ans = palindromicPartitionDp(str, 0, str.length() - 1, dp);
    return ans;
  }

  public static int palindromicPartitionRec(String str, int low, int high) {
    if (low >= high || isPalindrome(str, low, high)) {
      return 0;
    }

    int ans = Integer.MAX_VALUE;
    for (int k = low; k < high; k++) {
      int temp =
          1 + palindromicPartitionRec(str, low, k) + palindromicPartitionRec(str, k + 1, high);
      ans = Math.min(ans, temp);
    }

    return ans;
  }

  public static int palindromicPartitionDp(String str, int low, int high, int[][] dp) {
    if (low >= high || isPalindrome(str, low, high)) {
      return 0;
    }

    int ans = Integer.MAX_VALUE;
    for (int k = low; k < high; k++) {
      if (dp[low][k] == -1) {
        dp[low][k] = palindromicPartitionDp(str, low, k, dp);
      }
      if (dp[k + 1][high] == -1) {
        dp[k + 1][high] = palindromicPartitionDp(str, k + 1, high, dp);
      }
      int temp = 1 + dp[low][k] + dp[k + 1][high];
      ans = Math.min(ans, temp);
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/palindrome-partitioning/
   */


  private static boolean isPalindrome(String s, int low, int high) {

    if (s.length() == 1) {
      return true;
    }

    int i = low;
    int j = high;

    while (j > i) {
      if (s.charAt(j) != s.charAt(i)) {
        return false;
      }
      j--;
      i++;
    }

    return true;
  }

  /**
   * Given a boolean expression S of length N with following symbols. Symbols 'T' ---> true 'F' --->
   * false and following operators filled between symbols Operators &   ---> boolean AND |   --->
   * boolean OR ^   ---> boolean XOR Count the number of ways we can parenthesize the expression so
   * that the value of expression evaluates to true.
   */


  public double largestSumOfAverages(int[] nums, int k) {
    int n = nums.length;
    double[][] dp = new double[n][k + 1];
    return largestRec(nums, k, 0, dp);
  }

  private double largestRec(int[] nums, int k, int start, double[][] dp) {
    if (dp[start][k] != 0) {
      return dp[start][k];
    }
    if (k == 1) {
      double sum = 0;
      for (int i = start; i < nums.length; i++) {
        sum += nums[i];
      }
      dp[start][k] = sum / (nums.length - start);
      return dp[start][k];
    }
    double sum = 0;
    double max = 0;
    for (int i = start; i <= nums.length - k; i++) {
      sum += nums[i];
      max = Math.max(max, sum / (i - start + 1) + largestRec(nums, k - 1, i + 1, dp));
    }
    dp[start][k] = max;
    return max;
  }


  public static void main(String[] args) {
    String num1 = "2";
    String num2 = "3";
    BigInteger b1 = new BigInteger(num1);
    BigInteger b2 = new BigInteger(num2);
    System.out.println(b1.multiply(b2).toString());
  }
}
