package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Medium {

  /**
   * https://leetcode.com/problems/count-good-numbers/
   *
   * Count Good Numbers
   */
  public static int countGoodNumbers(long n) {
    long mod = 1000000007;
    long evenCount = (n + 1) / 2;
    long oddCount = n - evenCount;

    // Calculate pow(x, y) % mod
    long powModEven = bigPowerCalc(5, evenCount);

    long powModOdd = bigPowerCalc(5, oddCount);

    // Multiply the even and odd counts
    long total = (powModEven * powModOdd) % mod;
    return (int) total;
  }

  private static long bigPowerCalc(long base, long power) {
    long mod = 1000000007;
    if (power == 0) {
      return 1;
    }

    long temp = bigPowerCalc(base, power / 2);

    if (power % 2 == 0) {
      return (temp % mod * temp % mod) % mod;
    } else {
      return (base * temp % mod * temp % mod) % mod;
    }

  }


  /**
   * Reverse A stack
   */
  public static void reverseStack(Stack<Integer> stack) {
    if (stack.isEmpty() || stack.size() == 1) {
      return;
    }
    int temp = stack.pop();
    reverseStack(stack);
    insertAtBottom(stack, temp);
  }

  public static void insertAtBottom(Stack<Integer> stack, int x) {
    if (stack.isEmpty()) {
      stack.push(x);
      return;
    }
    int temp = stack.pop();
    insertAtBottom(stack, x);
    stack.push(temp);

  }


  /**
   * https://leetcode.com/problems/valid-parentheses/submissions/723809484/
   *
   * Valid Parentheses
   */
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    stack.push(s.charAt(0));
    int i = 1;

    while (i < s.length()) {

      if (!stack.isEmpty() && checkParen(stack.peek(), s.charAt(i))) {
        stack.pop();
      } else {
        stack.push(s.charAt(i));
      }
      i++;
    }

    if (!stack.isEmpty()) {
      return false;
    } else {
      return true;
    }
  }

  boolean checkParen(char a, char b) {
    if ((a == '(' && b == ')') || (a == '{' && b == '}') || (a == '[' && b == ']')) {
      return true;
    }
    return false;
  }

  /**
   * https://leetcode.com/problems/subsets/
   *
   * Subsets
   */

  public List<List<Integer>> subsets(int[] nums) {
    int n = nums.length;
    List<List<Integer>> ans = new ArrayList<>();

    for (int i = 0; i < (1 << n); i++) {
      List<Integer> temp = new ArrayList<>();

      for (int j = 0; j < n; j++) {
        // check for corresponding bit if it is set then pick that number in the list
        if ((i & (1 << j)) != 0) {
          temp.add(nums[j]);
        }
      }
      ans.add(temp);
    }
    return ans;
  }


  public static String moreSubsequence(int n, int m, String a, String b) {
    int countA = countDistinctSubsequences(a);
    int countB = countDistinctSubsequences(b);

    if (countA >= countB) {
      return a;
    } else {
      return b;
    }
  }

  public static int countDistinctSubsequences(String str) {
    int[] dp = new int[str.length() + 1];
    dp[0] = 1;

    Map<Character, Integer> lastOccurrence = new HashMap<>();

    for (int i = 1; i < dp.length; i++) {
      dp[i] = 2 * dp[i - 1];
      char ch = str.charAt(i - 1);
      if (lastOccurrence.containsKey(ch)) {
        int idx = lastOccurrence.get(ch);
        dp[i] = dp[i] - dp[idx - 1];
      }
      lastOccurrence.put(ch, i);
    }

    return dp[str.length()];
  }

  public static void main(String[] args) {
    /*Stack<Integer> stack = new Stack<>();
    stack.push(2);
    stack.push(1);
    stack.push(3);
    reverseStack(stack);*/

    System.out.println(countDistinctSubsequences("abcbac"));
  }

}
