package dp;

import java.util.Arrays;

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
   * Given a boolean expression S of length N with following symbols.
   * Symbols
   *     'T' ---> true
   *     'F' ---> false
   * and following operators filled between symbols
   * Operators
   *     &   ---> boolean AND
   *     |   ---> boolean OR
   *     ^   ---> boolean XOR
   * Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
   */



  public static void main(String[] args) {

  }
}
