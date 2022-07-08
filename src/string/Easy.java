package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

  public static void main(String[] args) {
    isPalindrome("A man, a plan, a canal: Panama");
  }

}
