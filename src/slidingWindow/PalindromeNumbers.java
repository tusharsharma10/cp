package slidingWindow;

import java.util.*;

public class PalindromeNumbers {

  public static List<Integer> generatePalindromesInRange(int lower, int upper) {
    List<Integer> palindromes = new ArrayList<>();

    // Iterate through each number from 1 to the upper limit
    for (int i = 1; i <= upper; i++) {
      // Generate palindromes of even and odd lengths
      generatePalindromes(i, true, palindromes, lower, upper);
      generatePalindromes(i, false, palindromes, lower, upper);
    }

    return palindromes;
  }

  private static void generatePalindromes(int num, boolean isOddLength, List<Integer> palindromes,
      int lower, int upper) {
    int palindrome = num;
    if (isOddLength) {
      num /= 10;
    }
    while (num > 0) {
      palindrome = palindrome * 10 + num % 10;
      num /= 10;
    }
    if (palindrome >= lower && palindrome <= upper) {
      palindromes.add(palindrome);
    }
  }


  public static void main(String[] args) {
    System.out.println(solve(80, 110, 10));
  }

  static boolean ispal(int x) {
    int original = x;
    int rev = 0;
    while (x > 0) {
      rev = rev * 10 + x % 10;
      x /= 10;
    }
    return (original == rev);
  }

  public static int solve(int A, int B, int C) {
    ArrayList<Integer> pal = new ArrayList<Integer>();
    for (int i = A; i <= B; ++i) {
      if (ispal(i)) {
        pal.add(i);
      }
    }

    int n = pal.size();
    int j = 0;
    int ans = 0;
    for (int i = 0; i < n; ++i) {
      while (j < n && pal.get(j) - pal.get(i) <= C) {
        j++;
      }
      ans = Math.max(ans, j - i);
    }
    return ans;
  }

}


