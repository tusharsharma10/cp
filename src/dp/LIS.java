package dp;

import java.util.Arrays;

public class LIS {


  public static String addBinary(String A, String B) {
    StringBuilder result = new StringBuilder();

    int i = A.length() - 1;
    int j = B.length() - 1;
    int carry = 0;

    while (i >= 0 || j >= 0) {
      int sum = carry;
      if (i >= 0) {
        sum += A.charAt(i) - '0';
        i--;
      }
      if (j >= 0) {
        sum += B.charAt(j) - '0';
        j--;
      }
      // Insert the least significant bit of the sum at the beginning
      result.insert(0, sum % 2);
      // Update the carry for the next iteration
      carry = sum / 2;
    }

    if (carry != 0) {
      // Insert the carry at the beginning if it's not zero
      result.insert(0, carry);
    }

    return result.toString();
  }

  public static void main(String[] args) {
    System.out.println(addBinary("100", "11"));

  }

  public static int lis(int[] arr) {
    int n = arr.length;
    int[][] dp = new int[n][n];

    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], -1);
    }

    return lisRec(arr, 0, n, -1, dp);
  }

  public static int lisRec(int[] arr, int idx, int n, int prevNumIdx, int[][] dp) {
    if (idx == arr.length) {
      return 0;
    }

    if (dp[idx][prevNumIdx + 1] != -1) {
      return dp[idx][prevNumIdx + 1];
    }

    int c1 = 0;
    int c2 = 0;

    if (prevNumIdx == -1 || arr[idx] > arr[prevNumIdx]) {
      c1 = 1 + lisRec(arr, idx + 1, n, idx, dp);
    }

    c2 = lisRec(arr, idx + 1, n, prevNumIdx, dp);

    dp[idx][prevNumIdx + 1] = Math.max(c1, c2);
    return dp[idx][prevNumIdx + 1];

  }


}
