package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Easy {

  public boolean isAnagram(String s, String t) {

    int[] c = new int[256];

    if (s.length() != t.length()) {
      return false;
    }
    for (int i = 0; i < s.length(); i++) {
      c[s.charAt(i)] += 1;
      c[t.charAt(i)] -= 1;
    }

    for (int i = 0; i < 256; i++) {
      if (c[i] != 0) {
        return false;
      }
    }
    return true;
  }

  public static List<String> removeAnagrams(String[] words) {

    List<String> list = new ArrayList<>();

    Map<String, String> map = new LinkedHashMap<>();

    for (int i = 0; i < words.length; i++) {
      char[] c = words[i].toCharArray();
      Arrays.sort(c);
      if (!map.containsKey(String.copyValueOf(c))) {
        map.put(String.copyValueOf(c), words[i]);
      }
    }

    for (String s : map.values()) {
      list.add(s);
    }

    return list;
  }

  /**
   * https://leetcode.com/problems/valid-palindrome/
   */

  public static boolean isPalindrome(String s) {

    s = s.toLowerCase();

    StringBuilder str = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {

      int ascii = s.charAt(i);

      if ((ascii >= 97 && ascii <= 122) || (ascii >= 48 && ascii <= 57)) {
        str.append((char) ascii);
      }
    }

    String x = str.toString();

    int i = 0;
    int j = x.length() - 1;

    while (j > i) {

      if (x.charAt(i) != x.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }

    return true;
  }

  public String removeDuplicateLetters(String s) {
    Set<Character> set = new HashSet<>();

    for (int i = 0; i < s.length(); i++) {
      set.add(s.charAt(i));
    }

    StringBuilder str = new StringBuilder();

    for (char c : set) {

      str.append(c);
    }

    s = str.toString();
    char arr[] = s.toCharArray();
    Arrays.sort(arr);

    return String.copyValueOf(arr);
  }


  public static int titleToNumber(String columnTitle) {
    int val = 0;

    int j = 0;

    for (int i = columnTitle.length() - 1; i >= 0; i--) {
      char c = columnTitle.charAt(i);
      int x = c - 'A' + 1;
      val += Math.pow(26, j) * x;
      j++;
    }

    return val;
  }

  public static String addBinary(String a, String b) {
    return "";
  }

  /**
   *
   */


  /**
   * https://leetcode.com/problems/number-of-segments-in-a-string/submissions/
   */

  public static int countSegments(String s) {

    if (s == null || s.length() == 0) {
      return 0;
    }

    String[] arr = s.split(" ");
    int count = 0;
    for (int i = 0; i < arr.length; i++) {

      if (!arr[i].equals("")) {
        count++;
      }

    }

    return count;
  }

  public static int arrangeCoins(int n) {

    int count = 0;
    int i = 1;
    while (n - i > 0) {

      n -= i;
      count++;
      i++;
    }

    return count;
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

  /**
   *
   */
  public static String reverseStr(String s, int k) {

    int i = 0;
    int j = k - 1;

    StringBuilder str = new StringBuilder(s);

    while (i < j && j >= 0 && j < s.length()) {

      char c1 = s.charAt(i);
      char c2 = s.charAt(j);
      str.setCharAt(i, c2);
      str.setCharAt(j, c1);
      i++;
      j--;
    }


    return str.toString();
  }

  public static boolean validPalindrome(String s) {

    for (int l = 0, r = s.length() - 1; l < r; ++l, --r) {

      if (s.charAt(l) != s.charAt(r)) {
        return validPalindrome(s, l + 1, r) || validPalindrome(s, l, r - 1);
      }

    }
    return true;
  }

  private static boolean validPalindrome(final String s, int l, int r) {

    while (l < r) {

      if (s.charAt(l++) != s.charAt(r--)) {
        return false;
      }

    }
    return true;
  }

  public static boolean backspaceCompare(String s, String t) {

    Stack<Character> st1 = new Stack<>();

    Stack<Character> st2 = new Stack<>();

    int i = 0;

    while (i < s.length()) {

      char c = s.charAt(i);

      if (c == '#') {
        if (!st1.isEmpty()) {
          st1.pop();
        }
      } else {
        st1.push(c);
      }

      i++;
    }

    int j = 0;

    while (j < t.length()) {

      char c = t.charAt(j);

      if (c == '#') {
        if (!st2.isEmpty()) {
          st2.pop();
        }
      } else {
        st2.push(c);
      }
      j++;
    }

    StringBuilder str1 = new StringBuilder();
    StringBuilder str2 = new StringBuilder();

    while (!st1.isEmpty()) {
      str1.append(st1.pop());
    }

    while (!st2.isEmpty()) {
      str2.append(st2.pop());
    }

    return (str1.toString().equals(str2.toString()));

  }

  public static void main(String[] args) {

    System.out.println(backspaceCompare("y#fo##f", "y#f#o##f"));
  }

}
