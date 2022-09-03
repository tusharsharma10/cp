package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Medium {

  /**
   * Given n pairs of parentheses, write a function to generate all combinations of well-formed
   * parentheses.
   */

  public List<String> generateParenthesis(int n) {
    List<String> ans = new ArrayList();
    backtrack(ans, new StringBuilder(), 0, 0, n);
    return ans;
  }

  public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {

    if (cur.length() == max * 2) {
      ans.add(cur.toString());
      return;
    }

    if (open < max) {
      cur.append("(");
      backtrack(ans, cur, open + 1, close, max);
      cur.deleteCharAt(cur.length() - 1);
    }

    if (close < open) {
      cur.append(")");
      backtrack(ans, cur, open, close + 1, max);
      cur.deleteCharAt(cur.length() - 1);
    }


  }

  /**
   * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
   */

  public static String smallestSubsequence(String s) {
    int n = s.length();

    int[] index = new int[26];
    int[] visited = new int[26];

    char[] ch = s.toCharArray();
    Stack<Character> st = new Stack<>();

    // index defines the latest position of occuring character.
    for (int i = 0; i < n; i++) {
      index[ch[i] - 'a'] = i;
    }

    for (int i = 0; i < n; i++) {
      if (visited[ch[i] - 'a'] != 1) {

        // states that pop the element from stack if smaller ascii character element comes only if last occuring position of character is greater
        while (!st.isEmpty() && ch[i] <= st.peek() && index[st.peek() - 'a'] > i) {
          visited[st.peek() - 'a'] = 0;
          st.pop();
        }

        st.push(ch[i]);
        visited[ch[i] - 'a'] = 1;
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < st.size(); i++) {
      sb.append(st.get(i));
    }
    return sb.toString();
  }

  /**
   * https://practice.geeksforgeeks.org/problems/split-the-binary-string-into-substrings-with-equal-number-of-0s-and-1s/1/
   */
  public static int maxSubStr(String str) {

    int cnt0 = 0;
    int cnt1 = 0;

    int ans = 0;

    for (int i = 0; i < str.length(); i++) {

      if (str.charAt(i) == '0') {
        cnt0++;
      } else {

        cnt1++;
      }

      if (cnt0 == cnt1) {
        ans++;
      }

    }
    if (cnt0 != cnt1) {
      return -1;
    }

    return ans;

  }

  /**
   * https://practice.geeksforgeeks.org/problems/word-wrap1646/1
   */


  /**
   * https://practice.geeksforgeeks.org/problems/next-permutation5226/1
   * https://leetcode.com/problems/next-permutation/
   */

  /**
   * Implement the next permutation, which rearranges the
   * list of numbers into Lexicographically next greater permutation
   * of list of numbers. If such arrangement is not possible,
   * it must be rearranged to the lowest possible order i.e.
   * sorted in an ascending order. You are given an list of numbers arr[ ] of size N.
   *
   * Input: N = 6
   * arr = {1, 2, 3, 6, 5, 4}
   * Output: {1, 2, 4, 3, 5, 6}
   * Explanation: The next permutation of the
   * given array is {1, 2, 4, 3, 5, 6}.
   */

  /**
   * Correct Solution
   */

  public static void nextPermutation2(int[] nums) {
    int n = nums.length;
    int idx1 = -1;

    for (int i = n - 2; i >= 0; i--) {

      if (nums[i] < nums[i + 1]) {
        idx1 = i;
        break;
      }
    }

    if (idx1 == -1) {
      Arrays.sort(nums);
      return;
    }

    int min = Integer.MAX_VALUE;

    int idx2 = -1;

    for (int i = n - 1; i > idx1; i--) {

      if (nums[idx1] < nums[i]) {

        if (nums[i] - nums[idx1] < min) {
          idx2 = i;
          min = nums[i] - nums[idx1];
        }

      }
    }

    int temp = nums[idx1];
    nums[idx1] = nums[idx2];
    nums[idx2] = temp;

    Arrays.sort(nums, idx1 + 1, nums.length);
    System.out.println();
  }

  /**
   * https://leetcode.com/problems/count-and-say/
   */

  public static String countAndSay(int n) {
    return "nothing";
  }

  /**
   * Permutations of a string
   * https://practice.geeksforgeeks.org/problems/permutations-of-a-given-string2041/1
   */

  public static List<String> findPermutation(String s) {
    Set<String> ans = new HashSet<>();
    findPermRec(s, "", ans);

    List<String> ans2 = new ArrayList<>();

    for (String x : ans) {
      ans2.add(x);
    }

    Collections.sort(ans2);

    return ans2;
  }

  private static void findPermRec(String s, String s1, Set<String> ans) {
    // s ko pura tod chuke ho to s1 ban chuka hai use set main add karo
    if (s.length() == 0) {
      ans.add(s1);
      return;
    }

    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      String leftPart = s.substring(0, i);
      String rightPart = s.substring(i + 1);
      findPermRec(leftPart + rightPart, s1 + ch, ans);

    }
  }

  /**
   * https://leetcode.com/problems/word-search/
   */

  public static boolean exist(char[][] board, String word) {
    return false;
  }


  public static void main(String[] args) {

  }
}
