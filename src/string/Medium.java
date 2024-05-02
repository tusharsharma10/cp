package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

  /**
   * https://leetcode.com/problems/word-search/
   * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
   *
   *  The word can be constructed from letters of sequentially adjacent cells,
   *  where adjacent cells are horizontally or
   *  vertically neighboring. The same letter cell may not be used more than once.
   */

  public static boolean exist(char[][] board, String word) {

    char start = word.charAt(0);

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == start) {
          if (explore(board, i, j, word, 0)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private static boolean explore(char[][] board, int i, int j, String word, int count) {

    if (count == word.length()) {
      return true;
    }

    if (i >= board.length || j >= board[0].length || i < 0 || j < 0 || board[i][j] != word.charAt(
        count)) {
      return false;
    }

    char temp = board[i][j];
    board[i][j] = '*';

    boolean flag = explore(board, i + 1, j, word, count + 1) ||
        explore(board, i - 1, j, word, count + 1) ||
        explore(board, i, j + 1, word, count + 1) ||
        explore(board, i, j - 1, word, count + 1);

    //backtrack
    board[i][j] = temp;

    return flag;
  }


  /**
   * https://leetcode.com/problems/isomorphic-strings/
   * not efficient
   */

  public static boolean isIsomorphic(String s, String t) {
    Map<Character, Character> dict = new HashMap();

    for (int i = 0; i < s.length(); i++) {

      if (dict.containsKey(s.charAt(i))) {
        if (dict.get(s.charAt(i)) != t.charAt(i)) {
          return false;
        }
      } else {
        // if t char already exists in some s char mapping then return false
        if (dict.containsValue(t.charAt(i))) {
          return false;
        } else {
          dict.put(s.charAt(i), t.charAt(i));
        }
      }

    }
    return true;
  }

  /**
   *   https://leetcode.com/problems/add-digits/
   */

  public static int addDigits(int num) {

    String s;
    boolean flag = true;
    int sum = num;
    while (flag) {
      s = String.valueOf(sum);
      if (s.length() == 1) {
        return Integer.parseInt(String.valueOf(s.charAt(0)));
      }
      sum = 0;
      for (int i = 0; i < s.length(); i++) {

        int x = Integer.parseInt(String.valueOf(s.charAt(i)));
        sum += x;
      }

    }

    return -1;
  }

  /**
   *  https://leetcode.com/problems/word-pattern/
   */

  public static boolean wordPattern(String pattern, String s) {
    String[] arr = s.split(" ");
    Map<Character, String> map = new HashMap<>();

    if(pattern.length() != arr.length){
      return false;
    }

    for (int i = 0; i < pattern.length(); i++) {

      char c = pattern.charAt(i);

      if (map.containsKey(c)) {
        String temp = map.get(c);
        if (!temp.equals(arr[i])) {
          return false;
        }
      } else {

        if (map.containsValue(arr[i])) {
          return false;
        } else {
          map.put(c, arr[i]);
        }
      }

    }
    return true;
  }

  /**
   * https://leetcode.com/problems/longest-palindrome/
   */

  public static int longestPalindrome(String s) {

    Map<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      map.put(s.charAt(i), 1 + map.getOrDefault(s.charAt(i), 0));
    }

    int ans = 0;
    boolean isfirstodd = false;

    for (Character key : map.keySet()) {
      if (map.get(key) % 2 == 0) {
        ans += map.get(key);
      }
      // value is odd
      else {
        if (isfirstodd == false) {
          ans += map.get(key);
          isfirstodd = true;
        } else {
          ans += map.get(key) - 1;
        }
      }
    }
    return ans;

  }

  public String reverseVowels(String s) {
    int i = 0;
    int j = s.length() - 1;

    StringBuilder str = new StringBuilder(s);

    while (j > i) {

      char begin = s.charAt(i);
      char last = s.charAt(j);

      if (!isVowel(begin)) {
        i++;
      }

      if (!isVowel(last)) {
        j--;
      }

      if (isVowel(begin) && isVowel(last)) {
        str.setCharAt(i, last);
        str.setCharAt(j, begin);
      }

    }
    return str.toString();
  }

  private boolean isVowel(char c) {
    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
        || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
      return true;
    }
    return false;
  }


  /**
   * https://leetcode.com/problems/repeated-substring-pattern/
   */

  public static boolean repeatedSubstringPattern(String s) {
    String str = "";
    int len = s.length();

    for (int i = 0; i <= len / 2; i++) {
      str = s.substring(0, i);
      int l = str.length();

      if (l != 0) {
        int num = len % l;

        if (num == 0) {
          String temp = s.replace(str, "");
          if (temp.length() == 0) {
            return true;
          }
        }

      }

    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println(repeatedSubstringPattern("abacab abacab"));
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
}
