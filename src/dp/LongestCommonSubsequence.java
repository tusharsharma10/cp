package dp;

public class LongestCommonSubsequence {

  /**
   * Longest common subsequence
   */

  public int lcsRec(int n, int m, String x, String y) {

    if (n <= 0 || m <= 0) {
      return 0;
    }

    if (x.charAt(n - 1) == y.charAt(m - 1)) {
      return 1 + lcsRec(n - 1, m - 1, x, y);
    }

    int w1 = lcsRec(n - 1, m, x, y);
    int w2 = lcsRec(n, m - 1, x, y);

    return Math.max(w1, w2);
  }

  /**
   * lcs dp
   */

  public static int lcs(int n, int m, String x, String y) {

    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= m; j++) {
        if (x.charAt(i - 1) == y.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[n][m];
  }


  /**
   * longest common substring
   */

  public static int longestCommonSubstr(String S1, String S2, int n, int m) {
    int max = Integer.MIN_VALUE;

    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= m; j++) {
        if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        }

        max = Math.max(max, dp[i][j]);
      }
    }

    return max;
  }

  /**
   * Printing longest common subsequence
   */

  public static void printLcsubseq(int n, int m, String x, String y) {

    int[][] dp = getLcsDpArray(n, m, x, y);

    int i = n;
    int j = m;

    StringBuilder str = new StringBuilder();

    while (i > 0 && j > 0) {

      if (dp[i][j] > dp[i - 1][j]) {
        i--;
        str.append(x.charAt(i));
      } else if (dp[i][j] > dp[i][j - 1]) {
        j--;
        str.append(y.charAt(j));
      } else {
        i--;
      }
    }

    while (i > 0) {
      if (dp[i][j] > dp[i - 1][j]) {
        str.append(x.charAt(i-1));
      }
      i--;
    }

    while (j > 0) {
      if (dp[i][j] > dp[i][j - 1]) {
        str.append(x.charAt(j-1));
      }
      j--;
    }

    System.out.println(str.reverse().toString());

  }

  public static int[][] getLcsDpArray(int n, int m, String x, String y) {

    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= m; j++) {

        if (x.charAt(i - 1) == y.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
        }

      }
    }

    return dp;
  }


  /**
   * https://leetcode.com/problems/shortest-common-supersequence/
   */

  public String shortestCommonSupersequence(String str1, String str2) {

   return "";
  }

  /**
   *https://practice.geeksforgeeks.org/problems/shortest-common-supersequence0322/1
   */
  public static int shortestCommonSupersequenceLength(String X, String Y, int m, int n) {

    int length = lcs(n, m, X, Y);

    return X.length() + Y.length() - length;

  }

  /**
   * https://leetcode.com/problems/delete-operation-for-two-strings/
   * Minimum number of deletions to make str1 to str2
   */

  public static int minDistance(String word1, String word2) {
      int n = word1.length();
      int m = word2.length();

      int len = lcs(n,m,word1,word2);

      return word1.length() - len + word2.length() - len;
  }


  /**
   * https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions-and-insertions0209/1
   */

  public static int minOperationsRec(String str1, String str2, int n, int m) {

    int len = lcs(n, m, str1, str2);
    // char to be deleted + char to be inserted
    return (n - len) + (m - len);
  }


  /**
   * Palindromic Subsequence
   *
   * https://leetcode.com/problems/longest-palindromic-subsequence/
   */

  public static int longestPalindromicSubsequence(String x) {

    StringBuilder str = new StringBuilder(x);
    String y = str.reverse().toString();
    int n = x.length();
    return lcs(n, n, x, y);
  }


  /**
   * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
   */

  public static int minInsertionsToMakePalindrome(String s) {
    int len = longestPalindromicSubsequence(s);
    return s.length() - len;
  }


  /**
   * https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions4610/0/
   */

  public static int minDeletionsToMakePalindrome(String str, int n) {
    int len = longestPalindromicSubsequence(str);
    return n - len;
  }


  /**
   * Longest repeating subsequence
   * https://practice.geeksforgeeks.org/problems/longest-repeating-subsequence2004/1
   */

  public static int longestRepeatingSubsequence(String x) {

    int n = x.length();

    int[][] dp = new int[n + 1][n + 1];

    for (int i = 1; i <= n; i++) {

      for (int j = 1; j <= n; j++) {
        if (i != j && x.charAt(i - 1) == x.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[n][n];
  }


  /**
   * Sequence Pattern Matching
   */

  public boolean isMatch(String s, String p) {
    int len = lcs(s.length(), p.length(), s, p);

    if (s.length() == len || p.length() == len) {
      return true;
    }
    return false;
  }




  public static void main(String[] args) {
    printLcsubseq(6, 6, "bcdghr", "bedfhr");
  }


}
