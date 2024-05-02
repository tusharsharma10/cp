import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.*;

public class Main5 {


  public static void main(String[] args) {
    /*double value = 4.144444444444442;
    int scale = 2; // Desired number of decimal places

    BigDecimal bigDecimal = BigDecimal.valueOf(value);
    BigDecimal roundedValue = bigDecimal.setScale(scale, RoundingMode.CEILING);

    System.out.println("Original value: " + value);
    System.out.println("Rounded value: " + roundedValue);*/
    // System.out.println(countSubstrings("aaa"));

    System.out.println(reverseWords("a good   example"));
    ;
  }

  public static String reverseWords(String s) {
    s = s.trim();
    String[] arr = s.split(" ");

    StringBuilder stringBuilder = new StringBuilder();

    for (int i = arr.length - 1; i >= 0; i--) {
      if ("".equals(arr[i])) {
        continue;
      }
      stringBuilder.append(arr[i]);
      stringBuilder.append(" ");
    }
    String ans = stringBuilder.toString();
    ans = ans.trim();
    return ans;
  }

  public static int beautySum(String s) {
    int sum = 0;
    int n = s.length();

    for (int i = 0; i < n; i++) {
      int[] count = new int[26];
      for (int j = i; j < n; j++) {
        count[s.charAt(j) - 'a']++;
        int maxCount = 0, minCount = Integer.MAX_VALUE;
        for (int k = 0; k < 26; k++) {
          if (count[k] > 0) {
            maxCount = Math.max(maxCount, count[k]);
            minCount = Math.min(minCount, count[k]);
          }
        }
        sum += maxCount - minCount;
      }
    }

    return sum;
  }

  public static int countSubstrings(String s) {
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      for (int j = i + 1; j <= s.length(); j++) {
        String check = s.substring(i, j);
        if (isPalin(check)) {
          count++;
        }
      }
    }
    return count;
  }

  public static boolean isPalin(String s) {
    int i = 0;
    int j = s.length() - 1;
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }


}
